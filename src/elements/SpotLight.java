package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * SpotLight represents point light source with direction (such as a luxo lamp)
 *  @author Ruth Miller
 *  ruthmiller2000@gmail.com
 */
public class SpotLight extends PointLight{
    private Vector _direction;

    /**
     * Light parameter constructor, gets intensity of a light, position, kC, kL, kQ
     *
     * @param _intensity
     * @param position
     * @param direction
     * @param kC
     * @param kL
     * @param kQ
     */
    public SpotLight(Color _intensity, Point3D position, Vector direction, double kC, double kL, double kQ) {
        super(_intensity, position, kC, kL, kQ);
        this._direction = direction.normalized();
    }

    /**
     * @param p for calculating distance
     * @return Color at point
     */
    @Override
    public Color getIntensity(Point3D p) {
        double d = p.distance(_position);
        double dSquared = p.distanceSquared(_position);
        return _intensity.scale(Math.max(0, _direction.dotProduct(getL(p)))).reduce(_kC + _kL * d + _kQ * dSquared);
    }

    /**
     * Getter for L vector
     * @param point from this point the L vector is calculating
     * @return L vector
     */
    @Override
    public Vector getL(Point3D point) {
        return super.getL(point);
    }
}
