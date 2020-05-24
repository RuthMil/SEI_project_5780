package geometries;

import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;

/**
 * Geometry is interface for all the geometries shapes
 *  @author Ruth Miller
 *  ruthmiller2000@gmail.com
 */
public abstract class Geometry implements Intersectable {
    protected Color _emission;
    protected Material _material;

    /**
     * Parameter constructor for Geometry abstract class
     * @param _emission emission light
     * @param _material material for the geometry
     */
    public Geometry(Color _emission, Material _material) {
        this._emission = _emission;
        this._material = _material;
    }

    /**
     * Parameter constructor for Geometry abstract class
     * @param _emission emission light
     */
    public Geometry(Color _emission) {
        this(_emission, new Material(0.0,0.0,0));
    }

    /**
     * Default constructor for Geometry abstract class, define black for emission
     */
    public Geometry() {
        this(Color.BLACK);
    }

    /**
     * Getter for emission
     * @return
     */
    public Color get_emission() {
        return _emission;
    }

    /**
     * Getter for material
     * @return material of geometry
     */
    public Material get_material() {
        return _material;
    }

    /**
     * Return normal to geometry
     * @param point3D a point on the geometry, that the normal is a normal to the geometry from this point
     * @return normal to geometry
     */
    abstract public Vector getNormal(Point3D point3D);
}
