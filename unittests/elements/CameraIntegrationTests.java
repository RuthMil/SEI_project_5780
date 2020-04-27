package elements;

import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CameraIntegrationTests {
    Camera cam1 = new Camera(Point3D.ZERO, new Vector(0,0,1), new Vector(0,-1,0));
    Camera cam2 = new Camera(new Point3D(0,0,-0.5), new Vector(0,0,1), new Vector(0,-1,0));

    @Test
    void constructRayThroughPixelWithSphere() {
        int counter = 0;
        int Nx = 3;
        int Ny = 3;
        List<Point3D> pointsList;

        //TC01: intersection points with a sphere with radius = 1, center point: (0,0,3)

        Sphere sphere1 = new Sphere(1, new Point3D(0,0,3));
        for(int i = 0; i < Nx; i++) {
            for (int j = 0; j < Ny; j++) {
                pointsList = sphere1.findIntersections(cam1.constructRayThroughPixel(Nx, Ny, j, i,1,3,3));
                if(pointsList != null)
                    counter += pointsList.size();
            }
        }
        assertEquals(2, counter, "number of intersection point with the sphere must be 2");

        //TC02: intersection points with a sphere with radius = 2.5, center point: (0,0,2.5)

        Sphere sphere2 = new Sphere(2.5, new Point3D(0,0,2.5));
        counter = 0;
        for(int i = 0; i < Nx; i++) {
            for (int j = 0; j < Ny; j++) {
                pointsList = sphere2.findIntersections(cam2.constructRayThroughPixel(Nx, Ny, j, i,1,3,3));
                if(pointsList != null)
                    counter += pointsList.size();
            }
        }
        assertEquals(18, counter, "number of intersection point with the sphere must be 18");

        //TC03: intersection points with a sphere with radius = 2, center point: (0,0,2)

        Sphere sphere3 = new Sphere(2, new Point3D(0,0,2));
        counter = 0;
        for(int i = 0; i < Nx; i++) {
            for (int j = 0; j < Ny; j++) {
                pointsList = sphere3.findIntersections(cam2.constructRayThroughPixel(Nx, Ny, j, i,1,3,3));
                if(pointsList != null)
                    counter += pointsList.size();
            }
        }
        assertEquals(10, counter, "number of intersection point with the sphere must be 10");

        //TC04: intersection points with a sphere with radius = 4, center point: (0,0,2)

        Sphere sphere4 = new Sphere(4, new Point3D(0,0,2));
        counter = 0;
        for(int i = 0; i < Nx; i++) {
            for (int j = 0; j < Ny; j++) {
                pointsList = sphere4.findIntersections(cam1.constructRayThroughPixel(Nx, Ny, j, i,1,3,3));
                if(pointsList != null)
                    counter += pointsList.size();
            }
        }
        assertEquals(9, counter, "number of intersection point with the sphere must be 9");

        //TC05: intersection points with a sphere with radius = 0.5, center point: (0,0,-1)

        Sphere sphere5 = new Sphere(0.5, new Point3D(0,0,-1));
        counter = 0;
        for(int i = 0; i < Nx; i++) {
            for (int j = 0; j < Ny; j++) {
                pointsList = sphere5.findIntersections(cam1.constructRayThroughPixel(Nx, Ny, j, i,1,3,3));
                if(pointsList != null)
                    counter += pointsList.size();
            }
        }
        assertEquals(0, counter, "number of intersection point with the sphere must be zero");
    }

    @Test
    void constructRayThroughPixelWithPlane(){
        int counter = 0;
        int Nx = 3;
        int Ny = 3;
        List<Point3D> pointsList;

        //TC11: intersection points with a plane with point(0,0,10), normal vector (cam1._vTo().scale(-1))

        Plane plane1 = new Plane(new Point3D(0,0,10), new Vector(cam1.get_vTo().scale(-1)));
        for (int i = 0; i < Nx; i++){
            for(int j = 0; j < Ny; j++){
                pointsList = plane1.findIntersections(cam1.constructRayThroughPixel(Nx, Ny, j, i, 1, 3,3));
                if(pointsList != null)
                    counter += pointsList.size();
            }
        }
        assertEquals(9, counter, "number of intersection point with the plane must be 9");

        //TC12: TODO
        counter = 0;
        Plane plane2 = new Plane(new Point3D(0,0,10), new Vector(cam1.get_vTo().scale(-1)));
        for (int i = 0; i < Nx; i++){
            for(int j = 0; j < Ny; j++){
                pointsList = plane2.findIntersections(cam1.constructRayThroughPixel(Nx, Ny, j, i, 1, 3,3));
                if(pointsList != null)
                    counter += pointsList.size();
            }
        }
        assertEquals(9, counter, "number of intersection point with the plane must be 9");

        //TC13: TODO
        counter = 0;
    }

    @Test
    void constructRayThroughPixelWithTriangle(){
        int counter = 0;
        int Nx = 3;
        int Ny = 3;
        List<Point3D> pointsList;

        //TC21: intersection points with a triangle with points: (0,-1,2), (1, 1, 2), (-1, 1, 2)

        Triangle triangle1 = new Triangle(new Point3D(0,-1,2), new Point3D(1,1,2), new Point3D(-1,1,2));
        for (int i = 0; i < Nx; i++){
            for(int j = 0; j < Ny; j++){
                pointsList = triangle1.findIntersections(cam1.constructRayThroughPixel(Nx, Ny, j, i, 1, 3,3));
                if(pointsList != null)
                    counter += pointsList.size();
            }
        }

        assertEquals(1, counter, "number of intersection point with the triangle must be 1");

        //TC22: intersection points with a triangle with points: (0,-20,2), (1, 1, 2), (-1, 1, 2)

        counter = 0;
        Triangle triangle2 = new Triangle(new Point3D(0,-20,2), new Point3D(1,1,2), new Point3D(-1,1,2));
        for (int i = 0; i < Nx; i++){
            for(int j = 0; j < Ny; j++){
                pointsList = triangle2.findIntersections(cam1.constructRayThroughPixel(Nx, Ny, j, i, 1, 3,3));
                if(pointsList != null)
                    counter += pointsList.size();
            }
        }

        assertEquals(2, counter, "number of intersection point with the triangle must be 2");
    }
}