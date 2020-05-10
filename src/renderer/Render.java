package renderer;

import geometries.Intersectable;
import primitives.*;
import scene.Scene;
import elements.*;

import java.util.List;

/**
 * Render class, rendering an image for writing
 * @author Ruth Miller
 * ruthmiller2000@gmail.com
 */
public class Render {
    private ImageWriter _imageWriter;
    private Scene _scene;

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
                List<Point3D> intersectionPoints = geometries.findIntersections(ray);
                if (intersectionPoints == null)
                    _imageWriter.writePixel(j, i, background);
                else
                {
                    Point3D closestPoint = getClosestPoint(intersectionPoints);
                    _imageWriter.writePixel(j, i, calcColor(closestPoint).getColor());
                }
            }
        }
    }

    private Color calcColor(Point3D p){
        return _scene.get_ambientLight().get_intensity();
    }

    /**
     * Finding the closest point to the P0 of the camera.
     * @param points list of points, the function should find from
     * this list the closet point to P0 of the camera in the scene.
     * @return the closest point to the camera
     */
    private Point3D getClosestPoint(List<Point3D> points){
        Point3D minPoint = points.get(0);
        double minDis = Double.MAX_VALUE;
        Point3D p0 = this._scene.get_camera().get_p0();

        for(Point3D point : points){
            double tmp_distance = point.distance(p0);
            if(tmp_distance < minDis){
                minDis = tmp_distance;
                minPoint = point;
            }
        }
        return minPoint;
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
