package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 * Geometry is interface for all the geometries shapes
 *  @author Ruth Miller
 *  314920976
 *  ruthmiller2000@gmail.com
 */
public interface Geometry extends Intersectable {
    /**
     * Return normal to geometry
     * @param point3D a point on the geometry, that the normal is a normal to the geometry from this point
     * @return normal to geometry
     */
    public Vector getNormal(Point3D point3D);
}
