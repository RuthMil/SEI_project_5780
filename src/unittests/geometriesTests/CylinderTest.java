package geometriesTests;

import geometries.Cylinder;
import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import static org.junit.Assert.*;


/**
 * Unit tests for geometries.Cylinder class
 * @author Ruth Miller
 * 314920976
 * ruthmiller2000@gmail.com
 */
public class CylinderTest {

    /**
     * Test for geometries.Cylinder.getNormal()
     */
    @Test
    public void getNormal() {
        Cylinder cy = new Cylinder(1.0, new Ray(new Point3D(0,0,1), new Vector(0,1,0)), 1.0);

        // ============ Equivalence Partitions Tests ==============
        assertEquals("Bad normal to cylinder", new Vector(0,0,1), cy.getNormal(new Point3D(0,0.5,2)));

        assertEquals("Bad normal to cylinder", new Vector(0,1,0), cy.getNormal(new Point3D(0,1,1.5)));

        assertEquals("Bad normal to cylinder", new Vector(0,1,0), cy.getNormal(new Point3D(0,-1,1.5)));

        // =============== Boundary Values Tests ==================
        //TODO

    }
}