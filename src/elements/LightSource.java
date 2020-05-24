package elements;

import primitives.*;

/**
 * LightSource interface represents a light source with intensity and L vector
 *  @author Ruth Miller
 *  ruthmiller2000@gmail.com
 */
public interface LightSource {
    /**
     * Get a point and return an intensity of the light at this point
     * @param p point3D
     * @return an intensity of the light at this point
     */
    public Color getIntensity(Point3D p);

    /**
     * Get a point and return the L vector at this point
     * @param p point3D
     * @return L vector at this point
     */
    public Vector getL(Point3D p);
}
