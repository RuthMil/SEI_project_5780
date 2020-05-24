package geometries;

import primitives.*;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * Cylinder class is implementing a cylinder, extends tube, with a radius, axis ray, and height
 *  @author Ruth Miller
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
     * Constructor for Cylinder class, gets an emission, a radius, ray and height and creates a new match cylinder
     * @param emission emission light
     * @param _radius
     * @param _axisRay
     * @param _height
     */
    public Cylinder(Color emission, double _radius, Ray _axisRay, double _height) {
        this(_radius, _axisRay, _height);
        this._emission = emission;
    }

    /**
     * Constructor for Cylinder class, gets an emission, a radius, ray and height and creates a new match cylinder
     * @param material material of the cylinder
     * @param emission emission light
     * @param _radius
     * @param _axisRay
     * @param _height
     */
    public Cylinder(Material material, Color emission, double _radius, Ray _axisRay, double _height) {
        this(emission, _radius, _axisRay, _height);
        this._material = material;
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
