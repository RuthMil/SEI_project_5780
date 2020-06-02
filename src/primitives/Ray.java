package primitives;

import java.util.Objects;

import static primitives.Util.isZero;

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
    protected Point3D _p0;
    protected Vector dir;

    // Constant for moving the rays of shadows, reflection and refraction
    private static final double DELTA = 0.1;

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
     * Constructor for Ray, gets head point, direction and normal to direction,
     * creates a new ray with the head point + normal.scale(±DELTA)
     * @param head head point of the ray
     * @param direction direction of a ray
     * @param normal normal to the direction of the ray
     */
    public Ray(Point3D head, Vector direction, Vector normal){
        this.dir = new Vector(direction).normalized();

        double vn = direction.dotProduct(normal);

        Vector normalDelta = normal.scale((vn > 0 ? DELTA : -DELTA));
        this._p0 = head.add(normalDelta);
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

    /**
     * @param length
     * @return new Point3D
     */
    public Point3D getTargetPoint(double length) {
        return isZero(length ) ? _p0 : _p0.add(dir.scale(length));
    }

    /**
     * Getter for the point from which the ray starts.
     * @return A new Point3D that represents the
     * point from which the ray starts.
     */
    public Point3D getPoint() {
        return new Point3D(_p0);
    }
}
