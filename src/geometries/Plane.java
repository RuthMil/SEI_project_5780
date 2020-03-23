package geometries;

import primitives.*;

/**
 * Plane class is implementing a plane
 *  @author Ruth Miller
 *  314920976
 *  ruthmiller2000@gmail.com
 */
public class Plane implements Geometry {

    private Point3D _p;
    private Vector _normal;

    /**
     * Constructor for Plane class, gets a point and a normal and creates match plane
     * @param _p a point on the plane
     * @param _normal a normal vector to the plane
     */
    public Plane(Point3D _p, Vector _normal) {
        this._p = _p;
        this._normal = _normal;
    }

    /**
     * Constructor for Plane class, gets three points on the required plane and creates it
     * @param _p1 point 1
     * @param _p2 point 2
     * @param _p3 point 3
     */
    public Plane(Point3D _p1, Point3D _p2, Point3D _p3) {
        this._p = _p1;
        this._normal = null;
    }

    /**
     * Return a normal to the plane
     * @param point3D a point on the plane
     * @return a normal to plane
     */
    @Override
    public Vector getNormal(Point3D point3D) {
        return _normal;
    }
}
