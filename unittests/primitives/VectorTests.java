package primitives;

import static org.junit.Assert.*;

import static primitives.Util.isZero;

/**
 * Unit tests for primitives.Vector class
 * @author Ruth Miller
 * 314920976
 * ruthmiller2000@gmail.com
 */
public class VectorTests {

    /**
     * Test primitives.Vector.add
     */
    @org.junit.Test
    public void add() {
        Vector v1 = new Vector(1,2,3);
        Vector v2 = new Vector(-1,-2,-3);
        Vector v3 = new Vector(2,4,6);
        Vector v4 = new Vector(-2,-4,-6);

        // ============ Equivalence Partitions Tests ==============
        Vector vr = v1.add(v3);
        assertEquals(new Vector(3,6,9), vr);

        vr = v2.add(v4);
        assertEquals(new Vector(-3,-6,-9), vr);

        vr = v1.add(v4);
        assertEquals(new Vector(-1,-2,-3), vr);

        // =============== Boundary Values Tests ==================
        try {
            vr = v1.add(v2);
            fail("cannot create zero vector");
        }
        catch (Exception e){
            assertTrue(true);
        }
    }

    /**
     * Test for primitives.Vector.subtract
     */
    @org.junit.Test
    public void subtract() {
        Vector v1 = new Vector(1,2,3);
        Vector v2 = new Vector(-1,-2,-3);
        Vector v3 = new Vector(2,4,6);
        Vector v4 = new Vector(-2,-4,-6);

        // ============ Equivalence Partitions Tests ==============
        Vector vr = v1.subtract(v3);
        assertEquals(new Vector(-1,-2,-3), vr);

        vr = v1.subtract(v2);
        assertEquals(new Vector(2,4,6), vr);

        vr = v2.subtract(v4);
        assertEquals(new Vector(1,2,3), vr);

        vr = v3.subtract(v1);
        assertEquals(new Vector(1,2,3), vr);
        // =============== Boundary Values Tests ==================
        try {
            vr = v1.subtract(v1);
            fail("cannot create zero vector");
        }
        catch (Exception e){
            assertTrue(true);
        }

    }

    /**
     * Test for primitives.Vector.scale
     */
    @org.junit.Test
    public void scale() {
        Vector v1 = new Vector(1,2,3);
        Vector v2 = new Vector(-2,-4,-6);

        // ============ Equivalence Partitions Tests ==============
        Vector vr = v1.scale(2);
        assertEquals(new Vector(2,4,6), vr);

        vr = v1.scale(3.5);
        assertEquals(new Vector(3.5, 7, 10.5), vr);

        vr = v1.scale(-2);
        assertEquals(new Vector(-2,-4,-6), vr);

        vr = v2.scale(4);
        assertEquals(new Vector(-8,-16,-24), vr);

        vr = v2.scale(-4);
        assertEquals(new Vector(8,16,24), vr);

        // =============== Boundary Values Tests ==================
        vr = v1.scale(1);
        assertEquals(new Vector(1,2,3), vr);
        try {
            vr = v1.scale(0);
            fail("cannot create zero vector");
        }
        catch (Exception e){
            assertTrue(true);
        }
    }

    /**
     * Test for primitives.Vector.dotProduct
     */
    @org.junit.Test
    public void dotProduct() {
        Vector v1 = new Vector(1,2,3);
        Vector v2 = new Vector(-1,-2,-3);
        Vector v3 = new Vector(2,4,6);
        Vector v4 = new Vector(-2,-4,-6);

        // ============ Equivalence Partitions Tests ==============
        double result = v1.dotProduct(v3);
        assertEquals(28.0, result, 0.00001);

        result = v1.dotProduct(v2);
        assertEquals(-14.0, result, 0.00001);

        result = v2.dotProduct(v4);
        assertEquals(28.0, result, 0.00001);

        // =============== Boundary Values Tests ==================
        result = v1.dotProduct(new Vector(1,1,1));
        assertEquals(6, result, 0.00001);

        result = v1.dotProduct(v1);
        assertEquals(14,result,0.00001);

        result = v1.dotProduct(new Vector(-3,6,-3));
        assertEquals("ERROR: dotProduct() for orthogonal vectors is not zero", 0, result, 0.00001);
    }

    /**
     *Test for primitives.Vector.crossProduct
     */
    @org.junit.Test
    public void crossProduct() {
        Vector v1 = new Vector(1,2,3);
        Vector v2 = new Vector(4,5,6);
        Vector v3 = new Vector(-1,-2,-3);
        Vector v4 = new Vector(-4,-5,-6);

        // ============ Equivalence Partitions Tests ==============
        Vector vr = v1.crossProduct(v2);
        assertEquals(new Vector(-3,6,-3), vr);

        vr = v3.crossProduct(v4);
        assertEquals(new Vector(-3,6,-3), vr);

        vr = v1.crossProduct(v4);
        assertEquals(new Vector(3,-6,3), vr);

        vr = v3.crossProduct(v2);
        assertEquals(new Vector(3,-6,3), vr);

        // Test cross-product result orthogonality to its operands
        assertTrue("crossProduct() result is not orthogonal to 1st operand", isZero(vr.dotProduct(v1)));
        assertTrue("crossProduct() result is not orthogonal to 2nd operand", isZero(vr.dotProduct(v3)));

        // =============== Boundary Values Tests ==================
        try {
            vr = v1.crossProduct(v3);
            fail("ERROR: crossProduct() for parallel vectors does not throw an exception");
        }
        catch (Exception e){
            assertTrue(true);
        }
        try {
            vr = v1.crossProduct(v1);
            fail("ERROR: crossProduct() for parallel vectors does not throw an exception");
        }
        catch (Exception e){
            assertTrue(true);
        }
    }

    /**
     * Test for primitives.Vector.lengthSquared
     */
    @org.junit.Test
    public void lengthSquared() {
        Vector v1 = new Vector(1,2,3);
        Vector v2 = new Vector(-2,-4,-6);

        double result = v1.lengthSquared();
        assertEquals("ERROR: lengthSquared() wrong value", 14.0, result, 0.00001);

        result = v2.lengthSquared();
        assertEquals("ERROR: lengthSquared() wrong value", 56.0, result, 0.00001);

        Vector v3 = new Vector(0,0,1);
        result = v3.lengthSquared();
        assertEquals(1,result, 0);
    }

    /**
     * Test for primitives.Vector.length
     */
    @org.junit.Test
    public void length() {
        Vector v1 = new Vector(1,2,3);
        Vector v2 = new Vector(-2,-4,-6);

        double result = v1.length();
        assertEquals("ERROR: length() wrong value", Math.sqrt(14), result, 0.00001);

        result = v2.length();
        assertEquals("ERROR: lengthSquared() wrong value", Math.sqrt(56), result, 0.00001);

        Vector v3 = new Vector(0,0,1);
        result = v3.length();
        assertEquals(1,result, 0);
    }

    /**
     * Test for primitives.Vector.normalize
     */
    @org.junit.Test
    public void normalize() {
        Vector v = new Vector(1, 2, 3);

        Vector vCopy = new Vector(v);
        Vector vCopyNormalize = vCopy.normalize();
        assertSame("ERROR: normalize() function creates a new vector", vCopy, vCopyNormalize);

        assertTrue("ERROR: normalize() result is not a unit vector", isZero(vCopyNormalize.length() - 1));

        double vLength = v.length();
        assertEquals("ERROR: normalize() result is not proper", new Vector(1/vLength, 2/vLength, 3/vLength), vCopyNormalize);
    }

    /**
     * Test for primitives.Vector.normalized
     */
    @org.junit.Test
    public void normalized() {
        Vector v = new Vector(1, 2, 3);

        Vector u = v.normalized();
        assertNotSame("ERROR: normalized() function does not create a new vector", u, v);
    }
}