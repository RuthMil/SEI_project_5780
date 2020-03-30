package unittests.geometriesTests;

import geometries.Plane;
import org.junit.Test;
import primitives.Point3D;
import primitives.Vector;
import static org.junit.Assert.*;
import static primitives.Util.isZero;

/**
 * Unit tests for geometries.Plane class
 * @author Ruth Miller
 * 314920976
 * ruthmiller2000@gmail.com
 */
public class PlaneTest {

    /**
     * Test for geometries.Plane.getNormal()
     */
    @Test
    public void getNormal() {
        Vector point = new Vector(1,2,3);
        Plane pl = new Plane(new Point3D(1,2,3), new Point3D(4,5,6), new Point3D(-3,5,4));
        Vector normal = pl.getNormal(new Point3D(1,2,3));
        System.out.println(normal);
        assertTrue("the normal is not orthogonal to direct vector on the plane", isZero(normal.dotProduct(new Vector(1,2,3).subtract(new Vector(-3,5,4)))));
    }
}