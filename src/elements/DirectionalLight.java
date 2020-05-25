package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * DirectionalLight represents Light source is far away, with a direction - like Sun
 *  @author Ruth Miller
 *  ruthmiller2000@gmail.com
 */
public class DirectionalLight extends Light implements LightSource{
    private Vector _direction;

    /**
     * Light parameter constructor, gets intensity of a light, and direction vector
     *
     * @param _intensity
     * @param direction
     */
    public DirectionalLight(Color _intensity, Vector direction) {
        super(_intensity);
        this._direction = direction.normalized();
    }

    @Override
    public Color getIntensity(Point3D p) {
        return super.get_intensity();
    }

    @Override
    public Vector getL(Point3D p) {
        return new Vector(_direction);
    }

    @Override
    public double getDistance(Point3D point) {
        return Double.POSITIVE_INFINITY;
    }
}
