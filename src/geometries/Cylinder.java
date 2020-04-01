package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

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
    public Vector getNormal(Point3D point3D) {
        double t;
        // if point3D is equal to p0 point in the axis ray, return the direction vector
        try{
            t = alignZero(this._axisRay.getDir().dotProduct(point3D.subtract(this._axisRay.get_p0())));
        }
        catch (Exception e){
            return this._axisRay.getDir();
        }

        // if the point is at one of the bases, return the direction vector
        if(t ==0 || isZero(this._height - Math.abs(t)))
            return this._axisRay.getDir();

        Point3D O = this._axisRay.get_p0().add(this._axisRay.getDir().scale(t));
        return point3D.subtract(O).normalize();
    }

}
