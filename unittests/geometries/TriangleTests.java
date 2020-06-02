package geometries;

import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Testing Triangle
 * @author Ruth Miller
 * ruthmiller2000@gmail.com
 */
public class TriangleTests {

    /**
     * Test method for {@link geometries.Triangle#getNormal(primitives.Point3D)}.
     */
    @Test
    public void getNormal() {

        // ============ Equivalence Partitions Tests ==============
        Triangle tr = new Triangle(new Point3D(0,0,1), new Point3D(1,0,0), new Point3D(0,1,0));
        double sqrtLen = Math.sqrt(1d / 3);
        Vector normal = tr.getNormal(new Point3D(0,0,1));
        assertEquals("Bad normal to triangle", new Vector(sqrtLen, sqrtLen, sqrtLen), normal);
    }

    @Test
    public void findIntersections() {
        Triangle triangle = new Triangle(new Point3D(1,0,0), new Point3D(0,1,0),
                Point3D.ZERO);

        // ============ Equivalence Partitions Tests ==============
        //TC01: the ray begins inside the triangle
        assertNull("Ray from triangle outside",
                triangle.findIntersections(new Ray(new Point3D(0.5,0.5,0), new Vector(1,0,0))));

        //TC02: the ray begins outside against edge
        assertNull("Ray from outside of triangle against edge",
                triangle.findIntersections(new Ray(new Point3D(0.5,-1,0), new Vector(0,1,0))));

        //TC03: the ray begins outside against vertex
        assertNull("Ray from outside of triangle against vertex",
                triangle.findIntersections(new Ray(new Point3D(2,0,0), new Vector(-1,0,0))));

        // =============== Boundary Values Tests ==================
        //TC11: the ray begins on edge
        assertNull("Ray begins on edge against outside",
                triangle.findIntersections(new Ray(new Point3D(0.5,0,0), new Vector(0,0,1))));

        //TC12: the ray begins in vertex
        assertNull("Ray begins in vertex against outside",
                triangle.findIntersections(new Ray(new Point3D(0,1,0), new Vector(0,0,1))));

        //TC13: the ray begins on edge's continuation
        assertNull("Ray begins on edge's continuation against outside",
                triangle.findIntersections(new Ray(new Point3D(0,2,0), new Vector(0,-1,0))));
    }
}