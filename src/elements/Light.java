package elements;

import primitives.Color;

/**
 * Light class represents a light with intensity
 *  @author Ruth Miller
 *  ruthmiller2000@gmail.com
 */
public class Light {
    protected Color _intensity;

    /**
     * Light parameter constructor, gets intensity of a light
     * @param _intensity
     */
    public Light(Color _intensity) {
        this._intensity = _intensity;
    }

    /**
     * Intensity getter
     * @return intensity
     */
    public Color get_intensity() {
        return new Color(_intensity);
    }
}

