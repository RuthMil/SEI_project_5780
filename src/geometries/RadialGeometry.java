package geometries;

/**
 * RadialGeometry Class is implementing geometry that has a radius
 *  @author Ruth Miller
 *  314920976
 *  ruthmiller2000@gmail.com
 */
public abstract class RadialGeometry implements Geometry{
    protected double _radius;

    /**
     * Constructor for RadialGeometry
     * @param _radius radius of geometry
     */
    public RadialGeometry(double _radius) {
        this._radius = _radius;
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
