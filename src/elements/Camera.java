package elements;

import primitives.*;

import static primitives.Util.isZero;

/**
 * Camera class
 * @author Ruth Miller
 */
public class Camera {
    protected Point3D _p0;
    protected Vector _vUp;
    protected Vector _vTo;
    protected Vector _vRight;

    public Camera(Point3D _p0, Vector _vTo, Vector _vUp) {
        if (_vUp.dotProduct(_vTo) != 0)
            throw new IllegalArgumentException("the vectors _vPo, _vTo must be orthogonal");

        this._p0 = new Point3D(_p0);
        this._vTo = _vTo.normalized();
        this._vUp = _vUp.normalized();
        this._vRight = this._vTo.crossProduct(this._vUp).normalize();
    }

    /**
     * _p0 Getter
     * @return _p0
     */
    public Point3D get_p0() {
        return _p0;
    }

    /**
     * _vUp Getter
     * @return _vUp
     */
    public Vector get_vUp() {
        return _vUp;
    }

    /**
     * _vTo Getter
     * @return _vTo
     */
    public Vector get_vTo() {
        return _vTo;
    }

    /**
     * _vRight Getter
     * @return _vRight
     */
    public Vector get_vRight() {
        return _vRight;
    }

    /**
     * constructs ray from the camera through a specific pixel into a screen
     * @param nX number of pixels on X axis
     * @param nY number of pixels on Y axis
     * @param j column index in the view plane
     * @param i row index in the view plane
     * @param screenDistance distance between the camera and thw screen
     * @param screenWidth width of the screen
     * @param screenHeight height of the screen
     * @return a ray from the camera through a specific pixel into a screen
     */
    public Ray constructRayThroughPixel (int nX, int nY,
                                         int j, int i, double screenDistance,
                                         double screenWidth, double screenHeight){
        if (isZero(screenDistance))
        {
            throw new IllegalArgumentException("distance cannot be 0");
        }

        Point3D Pc = _p0.add(_vTo.scale(screenDistance));

        double Ry = screenHeight / nY;
        double Rx = screenWidth / nX;

        double yi = (i - nY / 2d) * Ry + Ry / 2;
        double xj = (j - nX / 2d) * Rx + Rx / 2;

        Point3D Pij = Pc;

        if (! isZero(xj))
        {
            Pij = Pij.add(_vRight.scale(xj));
        }
        if (! isZero(yi))
        {
            Pij = Pij.add(_vUp.scale(-yi));
        }

        Vector Vij = Pij.subtract(_p0);

        return new Ray(new Point3D(_p0), Vij.normalize());
    }
}
