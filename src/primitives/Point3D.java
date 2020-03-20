package primitives;

/**
 * Class Point3D is representing a point3D in the space.
 * The class includes three coordinate of the point.
 * Operation: addition, subtract, squared distance
 * between two point3D, and distance.
 *
 * @author Ruth Miller
 * 314920976
 * ruthmiller2000@gmail.com
 */
public class Point3D {
    private final Coordinate _x;
    private final Coordinate _y;
    private final Coordinate _z;
    public static Point3D ZERO = new Point3D(0,0,0);

    @Override
    public String toString() {
        return "(" +
                _x +
                ", " + _y +
                ", " + _z +
                ')';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point3D point3D = (Point3D) o;
        return _x.equals(point3D._x) &&
                _y.equals(point3D._y) &&
                _z.equals(point3D._z);
    }

    /**
     * Constructor that gets 3 Cooridinates and creates a new Point3D
     * @param _x x coordinate
     * @param _y y coordinate
     * @param _z z coordinate
     */
    public Point3D(Coordinate _x, Coordinate _y, Coordinate _z) {
        this._x = _x;
        this._y = _y;
        this._z = _z;
    }

    /**
     * Contructor that gets 3 double numbers and creates a new Point3D
     * @param _x x coordinate value
     * @param _y y coordinate value
     * @param _z z coordinate value
     */
    public Point3D(double _x, double _y, double _z) {
        this(new Coordinate(_x), new Coordinate(_y), new Coordinate(_z));
    }

    /**
     * Copy constructor for Point3D
     * @param other point3D for the vector's head
     */
    public Point3D(Point3D other) {
        this(other._x, other._y, other._z);
    }

    /**
     * x coordinate getter
     * @return x coordinate in the point3D
     */
    public Coordinate get_x() {
        return new Coordinate(_x);
    }

    /**
     * y coordinate getter
     * @return y coordinate in the point3D
     */
    public Coordinate get_y() {
        return new Coordinate(_y);
    }

    /**
     * z coordinate getter
     * @return z coordinate in the point3D
     */
    public Coordinate get_z() {
        return new Coordinate(_z);
    }

    /**
     * addition between this point to other vector
     * @param other vector
     * @return addition point
     */
    public Point3D add(Vector other){
        Point3D otherHead = other.get_head();
        return new Point3D(this._x.get_coord() + otherHead.get_x().get_coord(),
                this._x.get_coord() + otherHead.get_x().get_coord(),
                this._x.get_coord() + otherHead.get_x().get_coord());
    }

    /**
     * subtract between points
     * @param other vector
     * @return vector from the other vector to this vector
     */
    public  Vector subtract(Point3D other){
        return new Vector(other.get_x().get_coord() - this._x.get_coord(),
                other.get_y().get_coord() - this._y.get_coord(),
                other.get_z().get_coord() - this._z.get_coord());
    }

    /**
     * calculates the squared distance between this points to other point
     * @param point3D
     * @return squared distance between two points
     */
    public double distanceSquared(Point3D point3D){
        return (this._x.get_coord() + point3D.get_x().get_coord()) *
                (this._x.get_coord() + point3D.get_x().get_coord()) +
                (this._y.get_coord() + point3D.get_y().get_coord()) *
                        (this._y.get_coord() + point3D.get_y().get_coord()) +
                (this._z.get_coord() + point3D.get_z().get_coord()) *
                        (this._z.get_coord() + point3D.get_z().get_coord());
    }

    /**
     * calculates the distance between this points to other point
     * @param point3D
     * @return distance between two points
     */
    public double distance(Point3D point3D) {
        return Math.sqrt(this.distanceSquared(point3D));
    }

}
