package geometries;

import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

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

        // ============ Equivalence Partitions Tests ==============
        Vector point = new Vector(1,2,3);
        Plane pl = new Plane(new Point3D(1,2,3), new Point3D(4,5,6), new Point3D(-3,5,4));
        Vector normal = pl.getNormal(new Point3D(1,2,3));
        assertTrue("the normal is not orthogonal to direct vector on the plane", isZero(normal.dotProduct(new Vector(1,2,3).subtract(new Vector(-3,5,4)))));
    }

    @Test
    public void findIntsersections() {

        Plane plane = new Plane(new Point3D(1,1,0), new Vector(0,0,1));

        // ============ Equivalence Partitions Tests ==============
        //TC01: ray intersects the plane
        assertEquals("ray intersects the plane", List.of(new Point3D(1,0,0)),
                plane.findIntersections(new Ray(new Point3D(0,0,-1), new Vector(1,0,1))));

        //TC02: ray does'nt intersect the plane
        assertNull("ray does'nt intersect the plane", plane.findIntersections(new Ray(new Point3D(0,0,-1), new Vector(1,0,-1))));

        // =============== Boundary Values Tests ==================
        // **** Group: ray is parallel to the plane
        //TC11: the ray is included in the plane

        //TC12: the ray is not included in the plane
        assertNull("the ray is parallel and not included in the plane", plane.findIntersections(new Ray(new Point3D(1,1,1), new Vector(0,1,0))));

        //**** Group: ray is orthogonal to the plane
        //TC13: the ray is before the plane
        assertEquals("the ray is orthogonal and before the plane", List.of(new Point3D(1,1,0)),
                plane.findIntersections(new Ray(new Point3D(1,1,-1), new Vector(0,0,1))));

        //TC14: the ray is in the plane
        assertNull("the ray is orthogonal and in the plane",
                plane.findIntersections(new Ray(new Point3D(1,1,0), new Vector(0,0,1))));

        //TC15: the ray is after the plane
        assertEquals("the ray is orthogonal and after the plane", List.of(new Point3D(1,1,0)),
                plane.findIntersections(new Ray(new Point3D(1,1,1), new Vector(0,0,-1))));
        //TC16: the ray is neither orthogonal nor parallel to and begins at the plane
        assertNull("the ray is neither orthogonal nor parallel to and begins at the plane",
                plane.findIntersections(new Ray(new Point3D(2,2,0), new Vector(1,1,1))));

        //TC17: the ray is neither orthogonal nor parallel to the plane and begins in
        //the same point which appears as reference point in the plane
        assertNull("the ray is neither orthogonal nor parallel to the plane and begins in " +
                "the same point which appears as reference point in the plane", plane.findIntersections(new Ray(new Point3D(1,1,0), new Vector(1,1,1))));
    }
}