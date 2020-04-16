package geometries;

import primitives.*;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * Sphere class is implementing a sphere, with a radius and center point
 *  @author Ruth Miller
 *  314920976
 *  ruthmiller2000@gmail.com
 */
public class Sphere extends RadialGeometry {
    protected Point3D _center;

    /**
     * Center getter
     * @return center of a sphere
     */
    public Point3D get_center() {
        return _center;
    }

    /**
     * Constructor for Sphere class, gets a radius and a center point3D, and creates a new sphere
     * @param _radius radius of a sphere
     * @param _center a point3D, the center point of a sphere
     */
    public Sphere(double _radius, Point3D _center) {
        super(_radius);
        this._center = _center;
    }

    @Override
    public String toString() {
        return  "center= " + _center +
                ", radius= " + _radius;
    }

    /**
     * Return a normal to a point on the sphere
     * @param point3D a point on the sphere
     * @return a normal to a point on the sphere
     */
    public Vector getNormal(Point3D point3D){
        return new Vector(point3D.subtract(this._center)).normalized();
    }

    /**
     * find intersections point3D with sphere
     * @param ray ray for casting
     * @return list of intersections point3D or null if there were not found
     */
    @Override
    public List<Point3D> findIntersections(Ray ray) {
        Point3D p0 = ray.getPoint();
        Vector v = ray.getDir();
        Vector u;
        try {
            u = _center.subtract(p0);   // p0 == _center
        } catch (IllegalArgumentException e) {
            return List.of(ray.getTargetPoint(_radius));
        }
        double tm = alignZero(v.dotProduct(u));
        double dSquared = (tm == 0) ? u.lengthSquared() : u.lengthSquared() - tm * tm;
        double thSquared = alignZero(_radius * _radius - dSquared);

        if (thSquared <= 0) return null;

        double th = alignZero(Math.sqrt(thSquared));
        if (th == 0) return null;

        double t1 = alignZero(tm - th);
        double t2 = alignZero(tm + th);
        if (t1 <= 0 && t2 <= 0) return null;
        if (t1 > 0 && t2 > 0) return List.of(ray.getTargetPoint(t1), ray.getTargetPoint(t2)); //P1 , P2
        if (t1 > 0)
            return List.of(ray.getTargetPoint(t1));
        else
            return List.of(ray.getTargetPoint(t2));
    }
}
