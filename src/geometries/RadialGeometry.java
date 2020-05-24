package geometries;

import primitives.Color;
import primitives.Material;

/**
 * RadialGeometry Class is implementing geometry that has a radius
 *  @author Ruth Miller
 *  ruthmiller2000@gmail.com
 */
public abstract class RadialGeometry extends Geometry{
    protected double _radius;

    /**
     * Constructor for RadialGeometry
     * @param _radius radius of geometry
     */
    public RadialGeometry(double _radius) {
        this._radius = _radius;
    }

    /**
     * Constructor for RadialGeometry
     * @param emission emission light
     * @param _radius radius of geometry
     */
    public RadialGeometry(Color emission, double _radius){
        this(_radius);
        this._emission = emission;
    }

    /**
     * Constructor for RadialGeometry
     * @param material material of geometry
     * @param emission emission light
     * @param _radius radius of geometry
     */
    public RadialGeometry(Material material, Color emission, double _radius){
        this(emission, _radius);
        this._material = material;
    }

    /**
     * Radius getter
     * @return
     */
    public double get_radius() {
        return _radius;
    }

    /**
     * Copy constructor for RadialGeometry class
     * @param radGeometry radial geometry
     */
    public RadialGeometry(RadialGeometry radGeometry) {
        this._radius = radGeometry._radius;
    }
}
