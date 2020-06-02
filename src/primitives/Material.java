package primitives;

/**
 * Material class represents a material
 * @author Ruth Miller
 * ruthmiller2000@gmail.com
 */
public class Material {
    private double _kD;
    private double _ks;
    private double _kT;
    private double _kR;
    private int _nShininess;

    /**
     * Parameter constructor for Material class, gets ks, kd, shininess, kT, kR. Creates a material
     * @param _kD
     * @param _ks
     * @param _kT
     * @param _kR
     * @param _nShininess
     */
    public Material(double _kD, double _ks, int _nShininess, double _kT, double _kR) {
        this._kD = _kD;
        this._ks = _ks;
        this._nShininess = _nShininess;
        this._kT = _kT;
        this._kR = _kR;
    }

    /**
     * Parameter constructor for Material class, gets ks, kd, and shininess and creates a material
     * @param _kD
     * @param _ks
     * @param _nShininess
     */
    public Material(double _kD, double _ks, int _nShininess) {
        this(_kD, _ks, _nShininess, 0,0);
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
     * Getter for kT, transparency
     * @return kT
     */
    public double get_kT() {
        return _kT;
    }

    /**
     * Getter for kR, reflection
     * @return
     */
    public double get_kR() {
        return _kR;
    }

    /**
     * Getter for shininess
     * @return shininess
     */
    public int get_nShininess() {
        return _nShininess;
    }
}
