package renderer;

import org.junit.jupiter.api.Test;
import elements.*;
import geometries.*;
import primitives.*;
import scene.Scene;

/**
 * Unit test for super sampling improvement (anti-aliasing)
 *
 * @author Ruth Miller
 * ruthmiller2000@gmail.com
 */
public class SuperSamplingTest {
    @Test
    public void MultiGeometriesWithSuperSampling() {
        Scene scene = new Scene("Test Scene");

        // standard look
        scene.set_camera(new Camera(
                new Point3D(0, 0, -1000),
                new Vector(0, 0, 1),
                new Vector(0, -1, 0)));

        // look from up
//        scene.set_camera(
//                new Camera(
//                        new Point3D(0, -1500, -1),
//                        new Vector(0, 1, 0),
//                        new Vector(0, 0, 1))
//        );

        // look from side
//        scene.set_camera(
//                new Camera(
//                        new Point3D(500, 0, -2000),
//                        new Point3D(0, 0, -200).subtract(new Point3D(500,0,-2000)).normalize(),
//                        new Vector(0, -1, 0))
//        );

        scene.set_distance(1000);

        scene.set_background(new Color(140, 255, 251));
        scene.set_ambientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

        scene.addGeometries(
                new Sphere(
                        new Color(255, 242, 0),
                        new Material(0.4, 0.3, 100, 0.8, 0),
                        80, new Point3D(0, -185, 0)),
                new Sphere(
                        new Color(7, 133, 42),
                        new Material(0.4, 0.3, 50, 0.4, 0),
                        75, new Point3D(-75, -140, 0)),
                new Sphere(
                        new Color(255, 5, 163),
                        new Material(0.4, 0.3, 100),
                        75, new Point3D(75, -140, 0)),
                new Triangle(
                        new Color(149, 98, 68),
                        new Material(0.8, 1, 10000, 0, 1),
                        new Point3D(-153, -107, 0),
                        new Point3D(153, -107, 0),
                        new Point3D(0, 205, 0)),
                new Triangle(
                        new Color(149, 98, 68),
                        new Material(0.8, 1, 10000, 0, 1),
                        new Point3D(-153, -107, 0),
                        new Point3D(0, -107, -255),
                        new Point3D(0, 205, 0)),
                new Triangle(
                        new Color(149, 98, 68),
                        new Material(0.8, 1, 10000, 0, 1),
                        new Point3D(153, -107, 0),
                        new Point3D(0, -107, -255),
                        new Point3D(0, 205, 0)),
                new Triangle(
                        Color.BLACK,
                        new Material(0.8, 1, 10000, 0, 1),
                        new Point3D(500, 200, -100),
                        new Point3D(-500, 200, -100),
                        new Point3D(1800, 200, -700)),
                new Triangle(
                        Color.BLACK,
                        new Material(0.8, 1, 10000, 0, 1),
                        new Point3D(-500, 200, -100),
                        new Point3D(1800, 200, -700),
                        new Point3D(-1800, 200, -700)));

        scene.addLights(new DirectionalLight(new Color(10, 10, 10), new Vector(1, -1, 0)),
                new SpotLight(
                        new Color(500, 400, 400),
                        new Point3D(0, -300, -100),
                        new Vector(-2, 2, -3), 1, 0.00001, 0.000005),
                new SpotLight(
                        new Color(650, 400, 300),
                        new Point3D(-300, -300, 100),
                        new Vector(2, 2, -3), 1.0, 0.00001, 0.000005),
                new SpotLight(
                        new Color(400, 400, 1020),
                        new Point3D(-400, -300, -120),
                        new Vector(2, 2, -3), 1, 0.00001, 0.000005),
                new SpotLight(
                        new Color(400, 400, 1020),
                        new Point3D(-300, -300, -100),
                        new Vector(2, 2, 2), 1, 0.00001, 0.000005));

        ImageWriter imageWriter = new ImageWriter(
                "superSamplingIceCreamTest", 1000, 1000, 1000, 1000);
        Render render = new Render(imageWriter, scene);
        render.set_superSampling(1d);
        render.set_rayNumber(50);
        render.renderImage();
        render.writeToImage();
    }
}
