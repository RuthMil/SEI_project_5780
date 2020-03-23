package geometries;

import primitives.*;

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
        return null;
    }
}
