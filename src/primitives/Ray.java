package primitives;

import java.util.Objects;

/**
 * Class Ray is representing a ray in the space.
 * The class includes point3D as the start point,
 * and normalized direction vector of a ray
 *
 * @author Ruth Miller
 * 314920976
 * ruthmiller2000@gmail.com
 */
public class Ray {
    private Point3D _p0;
    private Vector dir;

    /**
     * _p0 getter
     * @return start point of a ray
     */
    public Point3D get_p0() {
        return _p0;
    }

    /**
     * dir getter
     * @return direction vector of a ray
     */
    public Vector getDir() {
        return dir;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ray ray = (Ray) o;
        return _p0.equals(ray._p0) &&
                dir.equals(ray.dir);
    }

    @Override
    public String toString() {
        return "start point: " + _p0 +
                ", direction vector: " + dir;
    }

    /**
     * Constructor for ray, gets start point and direction vector, and creates a new ray
     * @param _p0 ray's start point
     * @param dir direction vector
     */
    public Ray(Point3D _p0, Vector dir) {
        this._p0 = _p0;
        this.dir = dir.normalize();
    }

    /**
     * Copy constructor for Ray
     * @param other ray
     */
    public Ray(Ray other) {
        this(other._p0, other.dir);
    }
}
