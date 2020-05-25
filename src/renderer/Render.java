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

    // Constant for moving the rays of shadows, reflection and refraction
    private static final double DELTA = 0.1;

    public Render(ImageWriter _imageWriter, Scene _scene) {
        this._imageWriter = _imageWriter;
        this._scene = _scene;
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

        //pixels grid
        for(int i = 0; i < nY; i++){
            for(int j = 0; j < nX; j++){
                Ray ray = camera.constructRayThroughPixel(nX, nY, j, i, distance, width, height);
                List<GeoPoint> intersectionPoints = geometries.findIntersections(ray);
                if (intersectionPoints == null)
                    _imageWriter.writePixel(j, i, background);
                else
                {
                    GeoPoint closestPoint = getClosestPoint(intersectionPoints);
                    _imageWriter.writePixel(j, i, calcColor(closestPoint).getColor());
                }
            }
        }
    }

    /**
     * Calculates a color in a point
     * @param intersection point3D
     * @return a color in a point
     */
    private Color calcColor(GeoPoint intersection){
        Color color = _scene.get_ambientLight().get_intensity();
        color = color.add(intersection.geometry.get_emission());

        Vector v = intersection.point.subtract(_scene.get_camera().get_p0()).normalize();
        Vector n = intersection.geometry.getNormal(intersection.point);
        Material material = intersection.geometry.get_material();
        int nShininess = material.get_nShininess();
        double kd = material.get_kD();
        double ks = material.get_ks();

        for (LightSource lightSource : _scene.get_lights()) {
            Vector l = lightSource.getL(intersection.point);
            if (sign(n.dotProduct(l)) == sign(n.dotProduct(v))) {
                if (unshaded(lightSource, l, n, intersection)){
                    Color lightIntensity = lightSource.getIntensity(intersection.point);
                    color = color.add(calcDiffusive(kd, l, n, lightIntensity),
                            calcSpecular(ks, l, n, v, nShininess, lightIntensity));
                }
            }
        }
        return color;
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
        Vector R = l.subtract(n.scale(l.dotProduct(n) * 2));
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
        Vector delta = n.scale(n.dotProduct(lightDirection) > 0 ? DELTA : - DELTA);
        Point3D point = geoPoint.point.add(delta);
        Ray lightRay = new Ray(point, lightDirection);
        List<GeoPoint> intersections = _scene.get_geometries().findIntersections(lightRay);

        if (intersections == null) {
            return true;
        }

        double lightDistance = lightSource.getDistance(point);
        for (GeoPoint gp : intersections) {
            if (alignZero(gp.point.distance(point) - lightDistance) <= 0) {
                return false;
            }
        }
        return true;
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
