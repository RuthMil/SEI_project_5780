package elements;

import primitives.Color;

/**
 * AmbientLight class represents an ambient light for a scene,
 * by: light intensity and exclusion coefficient
 * @author Ruth Miller
 * ruthmiller2000@gmail.com
 */
public class AmbientLight {
    protected Color _intensity;

    /**
     *
     * @param Ia light intensity
     * @param Ka Exclusion coefficient
     */
    public AmbientLight(Color Ia, double Ka) {
        this._intensity = Ia.scale(Ka);
    }

    /**
     * Intensity Getter
     * @return intensity of ambient light
     */
    public Color get_intensity() {
        return _intensity;
    }
}
