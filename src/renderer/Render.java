package renderer;

import geometries.Intersectable;
import primitives.*;
import scene.Scene;
import elements.*;
import geometries.Intersectable.GeoPoint;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * Render class, rendering an image for writing
 * @author Ruth Miller
 * ruthmiller2000@gmail.com
 */
public class Render {
    private ImageWriter _imageWriter;
    private Scene _scene;
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;
    private int _threads = 1;
    private final int SPARE_THREADS = 2;
    private boolean _print = false;
    private double _superSampling = 0d;
    private int _rayNumber = 1;
    /**
     * Pixel is an internal helper class whose objects are associated with a Render object that
     * they are generated in scope of. It is used for multithreading in the Renderer and for follow up
     * its progress.<br/>
     * There is a main follow up object and several secondary objects - one in each thread.
     * @author Dan
     *
     */
    private class Pixel {
        private long _maxRows = 0;
        private long _maxCols = 0;
        private long _pixels = 0;
        public volatile int row = 0;
        public volatile int col = -1;
        private long _counter = 0;
        private int _percents = 0;
        private long _nextCounter = 0;

        /**
         * The constructor for initializing the main follow up Pixel object
         * @param maxRows the amount of pixel rows
         * @param maxCols the amount of pixel columns
         */
        public Pixel(int maxRows, int maxCols) {
            _maxRows = maxRows;
            _maxCols = maxCols;
            _pixels = maxRows * maxCols;
            _nextCounter = _pixels / 100;
            if (Render.this._print) System.out.printf("\r %02d%%", _percents);
        }

        /**
         *  Default constructor for secondary Pixel objects
         */
        public Pixel() {}

        /**
         * Internal function for thread-safe manipulating of main follow up Pixel object - this function is
         * critical section for all the threads, and main Pixel object data is the shared data of this critical
         * section.<br/>
         * The function provides next pixel number each call.
         * @param target target secondary Pixel object to copy the row/column of the next pixel
         * @return the progress percentage for follow up: if it is 0 - nothing to print, if it is -1 - the task is
         * finished, any other value - the progress percentage (only when it changes)
         */
        private synchronized int nextP(Pixel target) {
            ++col;
            ++_counter;
            if (col < _maxCols) {
                target.row = this.row;
                target.col = this.col;
                if (_counter == _nextCounter) {
                    ++_percents;
                    _nextCounter = _pixels * (_percents + 1) / 100;
                    return _percents;
                }
                return 0;
            }
            ++row;
            if (row < _maxRows) {
                col = 0;
                if (_counter == _nextCounter) {
                    ++_percents;
                    _nextCounter = _pixels * (_percents + 1) / 100;
                    return _percents;
                }
                return 0;
            }
            return -1;
        }

        /**
         * Public function for getting next pixel number into secondary Pixel object.
         * The function prints also progress percentage in the console window.
         * @param target target secondary Pixel object to copy the row/column of the next pixel
         * @return true if the work still in progress, -1 if it's done
         */
        public boolean nextPixel(Pixel target) {
            int percents = nextP(target);
            if (percents > 0)
                if (Render.this._print) System.out.printf("\r %02d%%", percents);
            if (percents >= 0)
                return true;
            if (Render.this._print) System.out.printf("\r %02d%%", 100);
            return false;
        }
    }

    /**
     * Constructor for Render class
     * @param _imageWriter
     * @param _scene
     */
    public Render(ImageWriter _imageWriter, Scene _scene) {
        this._imageWriter = _imageWriter;
        this._scene = _scene;
    }

    /**
     * Constructor for Render class
     * @param _imageWriter
     * @param _scene
     * @param _threads
     * @param _print
     * @param _supersampling
     * @param _rayNumber
     */
    public Render(ImageWriter _imageWriter, Scene _scene, int _threads, boolean _print, double _supersampling, int _rayNumber) {
        this(_imageWriter, _scene);
        this._threads = _threads;
        this._print = _print;
        this._superSampling = _supersampling;
        this._rayNumber = _rayNumber;
    }

    public int get_threads() {
        return _threads;
    }

    public void set_threads(int _threads) {
        this._threads = _threads;
    }

    public int getSPARE_THREADS() {
        return SPARE_THREADS;
    }

    public boolean is_print() {
        return _print;
    }

    public void set_print(boolean _print) {
        this._print = _print;
    }

    public double get_superSampling() {
        return _superSampling;
    }

    public void set_superSampling(double _superSampling) {
        this._superSampling = _superSampling;
    }

    public int get_rayNumber() {
        return _rayNumber;
    }

    public void set_rayNumber(int _rayNumber) {
        this._rayNumber = _rayNumber;
    }

    private Color getPixelRaysBeamColor(Camera camera, double distance, int nx, int ny, double width, double height, Pixel pixel) {
        Ray ray = camera.constructRayThroughPixel(nx, ny, pixel.col, pixel.row, distance, width, height);
        List<Ray> rays = camera.constructRandomRaysBeamThroughPixel(nx, ny, pixel.col, pixel.row, distance, width, height, _superSampling, _rayNumber);
        rays.add(ray);
        return calcColor(rays);
    }

    private Color getPixelRayColor(Camera camera, double distance, int nx, int ny, double width, double height, Pixel pixel) {
        Ray ray = camera.constructRayThroughPixel(nx, ny, pixel.col, pixel.row, distance, width, height);
        GeoPoint closestPoint = findClosestIntersection(ray);
        Color pixelColor = _scene.get_background();
        if (closestPoint != null) {
            pixelColor = calcColor(closestPoint, ray);
        }
        return pixelColor;
    }

    private synchronized void printMessage(String msg) {
        synchronized (System.out) {
            System.out.println(msg);
        }
    }

    /**
     * Filling the buffer according to the geometries that are in the scene.
     * This function does not creating the picture file, but create the buffer pf pixels
     */
    public void renderImage(){
        Camera camera = _scene.get_camera();
        Intersectable geometries = _scene.get_geometries();
        java.awt.Color background = _scene.get_background().getColor();

        //Nx and Ny are the width and height of the image.
        int nX = _imageWriter.getNx();
        int nY = _imageWriter.getNy();

        //width and height are the number of pixels in the rows
        //and columns of the view plane
        double height = _imageWriter.getHeight();
        double width = _imageWriter.getWidth();

        double distance = _scene.get_distance();

        final Pixel thePixel = new Pixel(nY, nX);

        Thread[] threads = new Thread[_threads];
        for (int i = _threads - 1; i >= 0; --i) {
            threads[i] = new Thread(() -> {
                Pixel pixel = new Pixel();
                Color resultingColor;
                while (thePixel.nextPixel(pixel)) {
                    if (_superSampling == 0d) { //         without super sampling
                        resultingColor = getPixelRayColor(camera, distance, nX, nY, width, height, pixel);
                    }
                    else {
                        resultingColor = getPixelRaysBeamColor(camera, distance, nX, nY, width, height, pixel);
                    }
                    _imageWriter.writePixel(pixel.col, pixel.row, resultingColor.getColor());
                }
            });
        }

        for (Thread thread : threads) thread.start();

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (Exception e) {
            }
        }
        if (_print) {
            printMessage("100%\n");
        }
    }

    private Color calcColor(List<Ray> rays){
        Color color = Color.BLACK;

        for(Ray ray:rays){
            GeoPoint gp = findClosestIntersection(ray);
            if (gp == null) {
                color = color.add(_scene.get_background());
            }
            else {
                color = color.add(calcColor(gp, ray));
            }
        }

        int size = rays.size();
        return (size == 1) ? color : color.reduce(size);
    }

    /**
     * Calculates a color in a point
     * @param gp geoPoint
     * @param ray
     * @return a color in a point
     */
    private Color calcColor(GeoPoint gp, Ray ray){
        return calcColor(gp, ray, MAX_CALC_COLOR_LEVEL, 1.0).add(
                _scene.get_ambientLight().get_intensity());
    }

    /**
     * Calculates a color in a point
     * @param intersection GeoPoint
     * @param inRay
     * @param level level for recursion
     * @param k
     * @return a color in a point
     */
    private Color calcColor(GeoPoint intersection, Ray inRay, int level, double k){
        Color color = intersection.geometry.get_emission();
        Vector v = intersection.point.subtract(_scene.get_camera().get_p0()).normalize();
        Vector n = intersection.geometry.getNormal(intersection.point);
        Material material = intersection.geometry.get_material();
        int nShininess = material.get_nShininess();
        double kd = material.get_kD();
        double ks = material.get_ks();

        for (LightSource lightSource : _scene.get_lights()) {
            Vector l = lightSource.getL(intersection.point);
            if (sign(n.dotProduct(l)) == sign(n.dotProduct(v))) {
                double ktr = transparency(lightSource, l, n, intersection);
                if (ktr * k > MIN_CALC_COLOR_K) {
                    Color lightIntensity = lightSource.getIntensity(intersection.point).scale(ktr);;
                    color = color.add(calcDiffusive(kd, l, n, lightIntensity),
                            calcSpecular(ks, l, n, v, nShininess, lightIntensity));
                }
            }
        }

        if (level == 1) {
            return Color.BLACK;
        }

        double kr = intersection.geometry.get_material().get_kR();
        double kkr = k * kr;

        if (kkr > MIN_CALC_COLOR_K) {
            Ray reflectedRay = constructReflectedRay(intersection.point, inRay, n);
            GeoPoint reflectedPoint = findClosestIntersection(reflectedRay);
            if (reflectedPoint != null)
                color = color.add(calcColor(reflectedPoint, reflectedRay, level - 1, kkr).scale(kr));
        }

        double kt = intersection.geometry.get_material().get_kT();
        double kkt = k * kt;
        
        if (kkt > MIN_CALC_COLOR_K) {
            Ray refractedRay = constructRefractedRay(intersection.point, inRay, n) ;
            GeoPoint refractedPoint = findClosestIntersection(refractedRay);
            if (refractedPoint != null)
                color = color.add(calcColor(refractedPoint, refractedRay, level - 1, kkt).scale(kt));
        }
        return color;
    }

    /**
     * Find the closest point from the intersection points of the given ray,
     * to the ray's head
     * @param ray rau
     * @return the closest point from the intersection points of the given ray,
     * to the ray's head
     */
    private GeoPoint findClosestIntersection(Ray ray) {
        if (ray == null) {
            return null;
        }

        GeoPoint closestPoint = null;
        double closestDistance = Double.MAX_VALUE;
        Point3D rayP0 = ray.getPoint();

        List<GeoPoint> intersections = _scene.get_geometries().findIntersections(ray);
        if (intersections == null)
            return null;

        for (GeoPoint geoPoint : intersections) {
            double distance = rayP0.distance(geoPoint.point);
            if (distance < closestDistance) {
                closestDistance = distance;
                closestPoint = geoPoint;
            }
        }
        return closestPoint;
    }

    /**
     * Calculate refracted ray of a ray shot from a point
     * @param point specific point
     * @param inRay ray shot from the point
     * @param n normal vector to the ray's head
     * @return a refracted ray of a point
     */
    private Ray constructRefractedRay(Point3D point, Ray inRay, Vector n) {
        return new Ray(point, inRay.getDir().normalize(), n);
    }

    /**
     * Calculate reflected ray of a ray shot from a point
     * @param point specific point
     * @param inRay ray shot from the point
     * @param n normal vector to the ray's head
     * @return a reflected ray of a ray shot from a point
     */
    private Ray constructReflectedRay(Point3D point, Ray inRay, Vector n) {
        Vector v = inRay.getDir();
        double vn = v.dotProduct(n);
        if (vn == 0) {
            return null;
        }
        return new Ray(point, v.subtract(n.scale(2 * vn)), n);
    }

    //TODO check case 0
    /**
     * Calculate Specular component of light reflection.
     *
     * @param ks specular component
     * @param l direction from light to point
     * @param n normal to surface at the point
     * @param v direction from point of view to point
     * @param nShininess shininess level
     * @param lightIntensity light intensity at the point
     * @return specular component light effect at the point
     */
    private Color calcSpecular(double ks, Vector l, Vector n, Vector v, int nShininess, Color lightIntensity) {
        double ln = l.dotProduct(n);
        if(alignZero(ln) == 0){
            throw new IllegalArgumentException("normal vector cannot be orthogonal to L vector");
        }
        Vector R = l.subtract(n.scale(ln * 2));
        double minusVR = v.scale(-1).dotProduct(R);
        return lightIntensity.scale(Math.pow(Math.max(0, minusVR), nShininess) * ks);
    }

    /**
     * Calculate Diffusive component of light reflection.
     *
     * @param kd kd diffusive component
     * @param l L vector
     * @param n normal vector
     * @param lightIntensity light intensity at the point
     * @return diffusive component of light reflection
     */
    private Color calcDiffusive(double kd, Vector l, Vector n, Color lightIntensity) {
        double nl = alignZero(n.dotProduct(l));
        if(nl < 0){
            nl *= -1;
        }
        return lightIntensity.scale(kd * nl);
    }

    /**
     * Check what is the sigh of a number
     * @param number
     * @return if the number is bigger than 0: return true, else: false
     */
    private boolean sign(double number) {
        return number > 0d;
    }

    /**
     * Finding the closest point to the P0 of the camera.
     * @param geoPoints list of points, the function should find from
     * this list the closet point to P0 of the camera in the scene.
     * @return the closest point to the camera
     */
    private GeoPoint getClosestPoint(List<GeoPoint> geoPoints){
        GeoPoint minPoint = geoPoints.get(0);
        double minDis = Double.MAX_VALUE;
        Point3D p0 = this._scene.get_camera().get_p0();

        for(GeoPoint geoPoint : geoPoints){
            double tmp_distance = geoPoint.point.distance(p0);
            if(tmp_distance < minDis){
                minDis = tmp_distance;
                minPoint = geoPoint;
            }
        }
        return minPoint;
    }

    /**
     * Check if a specific point on a geometry is shaded
     * @param l L vector
     * @param n normal vector
     * @param geoPoint the point which is checked
     * @return true- if the point is not shaded, else- false
     */
    private boolean unshaded(LightSource lightSource, Vector l, Vector n, GeoPoint geoPoint){
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(geoPoint.point, lightDirection, n);
        List<GeoPoint> intersections = _scene.get_geometries().findIntersections(lightRay);

        if (intersections == null){
            return true;
        }

        double lightDistance = lightSource.getDistance(geoPoint.point);
        for (GeoPoint gp : intersections) {
            if (alignZero(gp.point.distance(geoPoint.point) - lightDistance) <= 0 &&
                    gp.geometry.get_material().get_kT() == 0)
                return false;
        }
        return true;
    }

    /**
     * Check if a specific point on a geometry is shaded, return degree of transparency
     * @param ls light source
     * @param l L vector
     * @param n normal vector
     * @param geopoint specific point to check if shaded
     * @return degree of transparency
     */
    private double transparency(LightSource ls, Vector l, Vector n, GeoPoint geopoint) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(geopoint.point, lightDirection, n);
        List<GeoPoint> intersections = _scene.get_geometries().findIntersections(lightRay);
        if (intersections == null)
            return 1.0;

        double lightDistance = ls.getDistance(geopoint.point);
        double ktr = 1.0;
        for (GeoPoint gp : intersections) {
            if (alignZero(gp.point.distance(geopoint.point) - lightDistance) <= 0) {
                ktr *= gp.geometry.get_material().get_kT();
                if (ktr < MIN_CALC_COLOR_K) return 0.0;
            }
        }
        return ktr;
    }

    /**
     * Printing the grid with a fixed interval between lines
     * @param interval interval The interval between the lines
     * @param color color for the grid lines
     */
    public void printGrid(int interval, java.awt.Color color){
        double rows = this._imageWriter.getNy();
        double columns = _imageWriter.getNx();

        //Writing the lines
        for (int j = 0; j < rows; j++) {
            for (int i = 0; i < columns; i++) {
                if (i % interval == 0 || j % interval == 0) {
                    _imageWriter.writePixel(i, j, color);
                }
            }
        }
    }

    /**
     * Renderer write to image
     */
    public void writeToImage() {
        _imageWriter.writeToImage();
    }
}
