package geometriesTests;

import geometries.Sphere;
import org.junit.Test;
import primitives.Point3D;
import primitives.Vector;

import static org.junit.Assert.*;
import static primitives.Util.isZero;

/**
 * Unit tests for geometries.Sphere class
 * @author Ruth Miller
 * 314920976
 * ruthmiller2000@gmail.com
 */
public class SphereTest {

    /***
     * Test for geometries.Sphere.getNormal()
     */
    @Test
    public void getNormal() {

        // ============ Equivalence Partitions Tests ==============
        Point3D center = new Point3D(0 ,0,1);
        Sphere sp = new Sphere(2, center);
        Vector n = sp.getNormal(new Point3D(0,0,2));
        assertEquals(new Vector(0,0,1), n);
    }
}