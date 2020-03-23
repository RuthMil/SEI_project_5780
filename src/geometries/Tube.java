package geometries;

import primitives.*;

/**
 * Tube class implementing a tube, with a radius and axis ray
 *  @author Ruth Miller
 *  314920976
 *  ruthmiller2000@gmail.com
 */
public class Tube extends RadialGeometry{
    protected Ray _axisRay;

    /**
     * Ray getter
     * @return a ray of a tube
     */
    public Ray get_axisRay() {
        return _axisRay;
    }

    /**
     * Constructor for Tube class, gets a radius and a ray and creates a new match tube
     * @param _radius
     * @param _axisRay
     */
    public Tube(double _radius, Ray _axisRay) {
        super(_radius);
        this._axisRay = _axisRay;
    }

    @Override
    public String toString() {
        return "axis ray= " + _axisRay +
                ", radius= " + _radius;
    }

    /**
     * Returb a normal from a point3D on a tube
     * @param point3D a point3D on a tube
     * @return a normal from a point3D on a tube
     */
    public Vector getNormal(Point3D point3D){
        return null;
    }
}
