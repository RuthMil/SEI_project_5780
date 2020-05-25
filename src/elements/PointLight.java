package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * PointLight represents omni-directional point source (such as a bulb)
 *  @author Ruth Miller
 *  ruthmiller2000@gmail.com
 */
public class PointLight extends Light implements LightSource{
    protected Point3D _position;
    protected double _kC, _kL, _kQ;

    /**
     * Light parameter constructor, gets intensity of a light, position, kC, kL, kQ
     *
     * @param _intensity
     * @param position
     * @param kC
     * @param kL
     * @param kQ
     */
    public PointLight(Color _intensity, Point3D position, double kC, double kL, double kQ) {
        super(_intensity);
        this._position = position;
        this._kC = kC;
        this._kL = kL;
        this._kQ = kQ;
    }

    /**
     * @param p for calculating distance
     * @return Color at point
     */
    @Override
    public Color getIntensity(Point3D p) {
        double d = p.distance(_position);
        double dSquared = p.distanceSquared(_position);
        return _intensity.reduce(_kC + _kL * d + _kQ * dSquared);
    }

    /**
     * Getter for L vector
     * @param point from this point the L vector is calculating
     * @return L vector
     */
    @Override
    public Vector getL(Point3D point) {
        if (point.equals(_position)) {
            return null;
        }
        return point.subtract(_position).normalized();
    }

    @Override
    public double getDistance(Point3D point) {
        return _position.distance(point);
    }
}
