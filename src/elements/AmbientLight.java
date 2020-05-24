package elements;

import primitives.Color;

/**
 * AmbientLight class represents an ambient light for a scene,
 * by: light intensity and exclusion coefficient
 * @author Ruth Miller
 * ruthmiller2000@gmail.com
 */
public class AmbientLight extends Light {
    /**
     * AmbientLight parameter constructor, gets light intensity (Ia) and Exclusion coefficient (Ka)
     * @param Ia light intensity
     * @param Ka Exclusion coefficient
     */
    public AmbientLight(Color Ia, double Ka) {
        super(Ia.scale(Ka));
    }
}
