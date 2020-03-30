package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * Cylinder class is implementing a cylinder, with a radius, axis ray, and height
 *  @author Ruth Miller
 *  314920976
 *  ruthmiller2000@gmail.com
 */
public class Cylinder extends Tube {
    protected double _height;

    @Override
    public String toString() {
        return "height= " + _height +
                ", " + super.toString();
    }

    /**
     * Height getter
     * @return an height of a cylinder
     */
    public double get_height() {
        return _height;
    }

    /**
     * Constructor for Cylinder class, gets a radius, ray and height and creates a new match cylinder
     * @param _radius
     * @param _axisRay
     * @param _height
     */
    public Cylinder(double _radius, Ray _axisRay, double _height) {
        super(_radius, _axisRay);
        this._height = _height;
    }

    /**
     * Return a normal to the cylinder
     * @param point3D a point that the normal will start from it
     * @return a normal to cylinder
     */
    public Vector getNormal(Point3D point3D){
        return null;
    }

}
