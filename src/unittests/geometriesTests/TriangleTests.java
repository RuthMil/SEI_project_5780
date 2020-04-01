package geometriesTests;

import geometries.Triangle;
import org.junit.Test;
import primitives.Point3D;
import primitives.Vector;

import java.awt.event.MouseAdapter;

import static org.junit.Assert.*;

/**
 * Testing Triangle
 * @author Ruth Miller
 * 314920976
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
}