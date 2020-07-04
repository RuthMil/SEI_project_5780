package renderer;

import org.junit.jupiter.api.Test;
import elements.*;
import geometries.*;
import primitives.*;
import renderer.*;
import scene.Scene;

public class SuperSamplingTest {
    @Test
    public void MultiGeometriesWithSuperSampling() {
        Scene scene = new Scene("Test Scene");
        scene.set_camera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.set_distance(1000);
        scene.set_background(Color.BLACK);
        scene.set_ambientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

        scene.addGeometries(new Sphere(new Color(255,127,39), new Material(0.8, 0.8, 30, 0.9, 0.0),
                100, new Point3D(-100, -30, 50)),
                new Triangle(Color.BLACK, new Material(0.8, 1, 10000, 0, 1),
                        new Point3D(500, 200, -100), new Point3D(-500, 200, -100), new Point3D(1800, 200, -700)),
                new Triangle(Color.BLACK, new Material(0.8, 1, 10000, 0, 1),
                        new Point3D(-500, 200, -100), new Point3D(1800, 200, -700), new Point3D(-1800, 200, -700)));
        /**
        scene.addGeometries(
                new Sphere(new Color(255, 0, 0), new Material(0.4, 0.3, 100), 25,
                        new Point3D(25,180,0)),
                new Sphere(new Color(255, 0, 102), new Material(0.4, 0.3, 100), 20,
                        new Point3D(-40,100,20)),
                new Sphere(new Color(153, 0, 153), new Material(0.4, 0.3, 100), 30,
                        new Point3D(40,80,0)),
                new Sphere(new Color(0, 0, 255), new Material(0.4, 0.3, 100), 20,
                        new Point3D(100,20,30)),
                new Sphere(new Color(0, 204, 255), new Material(0.5, 0.5, 100), 15,
                        new Point3D(-35, 0,0)),
                new Sphere(new Color(0, 255, 0), new Material(0.5, 0.5, 100), 15,
                        new Point3D(50,-50,0)),
                new Sphere(new Color(0, 153, 0), new Material(0.8, 0.8, 30), 20,
                        new Point3D(-10,-80,10)),
                new Sphere(new Color(255, 255, 0), new Material(0.4, 0.3, 100), 25,
                        new Point3D(-60,-140,0)),
                new Sphere(new Color(255, 153, 0), new Material(0.4, 0.3, 100), 17,
                        new Point3D(55,-180,0)),
                new Sphere(Color.BLACK, new Material(0.8, 0.8, 30, 0.9, 0), 100,
                        new Point3D(-28,-300,0)),
                new Triangle(new Color(255, 0, 0), new Material(0.4, 0.3, 100),
                        new Point3D(-128,-300,0), new Point3D(-28,-200,0), new Point3D(-28,-250,0)),
                new Triangle(new Color(255, 0, 0), new Material(0.4, 0.3, 100),
                        new Point3D(72,-300,0), new Point3D(-28,-200,0), new Point3D(-28,-250,0)),
                new Triangle(new Color(255, 0, 0), new Material(0.4, 0.3, 100),
                        new Point3D(-128,-300,0), new Point3D(-28,-400,0), new Point3D(-28,-350,0)),
                new Triangle(new Color(255, 0, 0), new Material(0.4, 0.3, 100),
                        new Point3D(72,-300,0), new Point3D(-28,-400,0), new Point3D(-28,-350,0)),

                new Triangle(Color.BLACK, new Material(0.8, 1, 10000, 0, 1),
                        new Point3D(500, 200, -100), new Point3D(-500, 200, -100), new Point3D(1800, 200, -700)),
                new Triangle(Color.BLACK, new Material(0.8, 1, 10000, 0, 1),
                        new Point3D(-500, 200, -100), new Point3D(1800, 200, -700), new Point3D(-1800, 200, -700)));
         **/

        scene.addLights(new DirectionalLight(new Color(10, 10, 10), new Vector(1, 1, 1)),
                new SpotLight(new Color(400, 400, 1020), new Point3D(-300, -300, -100), new Vector(2, 2, 2), 1, 0.00001, 0.000005));

        ImageWriter imageWriter = new ImageWriter("superSamplingImageTest", 1000,1000,1000,1000);
        Render render = new Render(imageWriter, scene);
        render.set_superSampling(0.5d);
        render.set_rayNumber(50);
        render.renderImage();
        render.writeToImage();
    }
}
