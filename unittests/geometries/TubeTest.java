package geometries;

import static org.junit.Assert.*;

import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * Unit tests for geometries.Tube class
 * @author Ruth Miller
 * 314920976
 * ruthmiller2000@gmail.com
 */
public class TubeTest {

    /**
     * Test method for {@link geometries.Tube#getNormal(primitives.Point3D)}.
     */
    @Test
    public void getNormal() {

        // ============ Equivalence Partitions Tests ==============
        Tube tube = new Tube(1, new Ray(new Point3D(0,0,1), new Vector(0,0,1)));
        Vector normal = tube.getNormal(new Point3D(1,0,0));
        assertEquals("Bad normal to tube", new Vector(1,0,0), normal);
    }
}