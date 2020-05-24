package geometries;

import primitives.*;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * Plane class is implementing a plane
 *  @author Ruth Miller
 *  ruthmiller2000@gmail.com
 */
public class Plane extends Geometry {

    protected Point3D _p;
    protected Vector _normal;

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
     * Constructor for Plane class, gets an emission, a point and a normal and creates match plane
     * @param emission
     * @param _p
     * @param _normal
     */
    public Plane(Color emission, Point3D _p, Vector _normal) {
        this(_p, _normal);
        this._emission = emission;
    }

    /**
     * Constructor for Plane class, gets three points on the required plane and creates it
     * @param _p1 point 1
     * @param _p2 point 2
     * @param _p3 point 3
     */
    public Plane(Point3D _p1, Point3D _p2, Point3D _p3) {
        this._p = _p1;
        Vector v1 = new Vector(_p2.subtract(_p1));
        Vector v2 = new Vector(_p3.subtract(_p1));
        this._normal = v1.crossProduct(v2).normalize();
    }

    /**
     * Constructor for Plane class, gets an emission, and three points on the required plane and creates it
     * @param emission emission light
     * @param _p1 point 1
     * @param _p2 point 2
     * @param _p3 point 3
     */
    public Plane(Color emission, Point3D _p1, Point3D _p2, Point3D _p3) {
        this(_p1, _p2, _p3);
        this._emission = emission;
    }

    /**
     * Constructor for Plane class, gets an emission, and three points on the required plane and creates it
     * @param material material of the plane
     * @param emission emission light
     * @param _p1 point 1
     * @param _p2 point 2
     * @param _p3 point 3
     */
    public Plane(Material material, Color emission, Point3D _p1, Point3D _p2, Point3D _p3) {
        this(emission, _p1, _p2, _p3);
        this._material = material;
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

    /**
     * find intersections point3D with plane
     * @param ray ray for casting
     * @return list of intersections point3D or null if there were not found
     */
    @Override
    public List<GeoPoint> findIntersections(Ray ray) {
        Vector p0Q;
        try {
            p0Q = _p.subtract(ray.getPoint());
        } catch (IllegalArgumentException e) {
            return null; // ray starts from point Q - no intersections
        }

        double nv = _normal.dotProduct(ray.getDir());
        if (isZero(nv)) // ray is parallel to the plane - no intersections
            return null;

        double t = alignZero(_normal.dotProduct(p0Q) / nv);

        return t <= 0 ? null : List.of(new GeoPoint(this, ray.getTargetPoint(t)));
    }
}
