package primitives;

/**
 * Material class represents a material
 * @author Ruth Miller
 * ruthmiller2000@gmail.com
 */
public class Material {
    private double _kD;
    private double _ks;
    private int _nShininess;

    /**
     * Parameter constructor for Material class, gets ks, kd, and shininess and creates a material
     * @param _kD
     * @param _ks
     * @param _nShininess
     */
    public Material(double _kD, double _ks, int _nShininess) {
        this._kD = _kD;
        this._ks = _ks;
        this._nShininess = _nShininess;
    }

    /**
     * Getter for kD
     * @return kD
     */
    public double get_kD() {
        return _kD;
    }

    /**
     * Getter for kS
     * @return kS
     */
    public double get_ks() {
        return _ks;
    }

    /**
     * Getter for shininess
     * @return shininess
     */
    public int get_nShininess() {
        return _nShininess;
    }
}
