package primitives;

import java.util.Objects;

/**
 * Class Vector is representing a Vector in the space.
 * The class includes point3D as the head of the vector
 * Operation: addition, subtract, dot product, scale product,
 * normalizing, and finding length.
 *
 * @author Ruth Miller
 * 314920976
 * ruthmiller2000@gmail.com
 */
public class Vector {
    protected Point3D _head;

    /**
     * head getter
     * @return head of vector
     */
    public Point3D get_head() {
        return _head;
    }

    @Override
    public String toString() {
        return _head.toString();
    }

    /**
     * Vector constructor that gets 3 double numbers and create a new vector from them
     * @param _x x coordinate value
     * @param _y y coordinate value
     * @param _z z coordinate value
     */
    public Vector(double _x, double _y, double _z) {
        this(new Coordinate(_x), new Coordinate(_y), new Coordinate(_z));
    }

    /**
     * Vector copy constructor that gets a vector create a new vector from it
     * @param vector
     */
    public Vector(Vector vector) {
        this._head = new Point3D(vector._head);
    }

    /**
     * Vector constructor that gets 3 coordinates and create a new vector from them
     * @param _x x coordinate
     * @param _y y coordinate
     * @param _z z coordinate
     */
    public Vector(Coordinate _x, Coordinate _y, Coordinate _z) {
        this._head = new Point3D(_x, _y, _z);
        if(_head.equals(Point3D.ZERO))
            throw new IllegalArgumentException("cannot create zero vector.");
    }

    /**
     * Vector constructor that gets a point3D and create a new vector from its coordinates
     * @param point3D
     */
    public Vector(Point3D point3D) {
        this._head = new Point3D(point3D);
        if(_head.equals(Point3D.ZERO))
            throw new IllegalArgumentException("cannot create zero vector.");
    }

    /**
     * Calculates addition vector between this vector to other vector
     * @param other vector
     * @return addition vector between two vectors
     */
    public Vector add(Vector other){
        try {
            return new Vector(_head.add(other));
        }
        catch (Exception ex){
            throw ex;
        }
    }

    /**
     * Calculates subtract vector between this vector to other vector
     * @param other vector
     * @return subtract vector between two vectors
     */
    public Vector subtract(Vector other){
        try {
            return _head.subtract(other._head);
        }
        catch (Exception ex){
            throw ex;
        }
    }

    /**
     * Return the vector multiplied by the scale
     * @param num scale number for the product
     * @return the vector multiplied by the scale
     */
    public Vector scale(double num){
        try {
            return new Vector(this._head.get_x().get_coord() * num,
                    this._head.get_y().get_coord() * num,
                    this._head.get_z().get_coord() * num);
        }
        catch (Exception ex){
            throw ex;
        }
    }

    /**
     * Calculates dot product between two vectors
     * @param other vector
     * @return dot product between this vector to other vector
     */
    public double dotProduct(Vector other){
        try {
            Point3D otherHead = other.get_head();
            return this._head.get_x().get_coord() * otherHead.get_x().get_coord() +
                    this._head.get_y().get_coord() * otherHead.get_y().get_coord() +
                    this._head.get_z().get_coord() * otherHead.get_z().get_coord();
        }
        catch (Exception ex){
            throw ex;
        }
    }

    /**
     * Calculates cross product between two vectors
     * @param other vector
     * @return cross product between this vector to other vector
     */
    public Vector crossProduct(Vector other){
        try {
            Point3D otherHead = other.get_head();
            return new Vector(this._head.get_y().get_coord() * otherHead.get_z().get_coord() -
                    this._head.get_z().get_coord() * otherHead.get_y().get_coord(),
                    this._head.get_z().get_coord() * otherHead.get_x().get_coord() -
                            this._head.get_x().get_coord() * otherHead.get_z().get_coord(),
                    this._head.get_x().get_coord() * otherHead.get_y().get_coord()-
                            this._head.get_y().get_coord() * otherHead.get_x().get_coord());
        }
        catch (Exception ex){
            throw ex;
        }
    }

    /**
     * Return the squared vector's length
     * @return the squared vector's length
     */
    public double lengthSquared(){
        return this._head.get_x().get_coord() * this._head.get_x().get_coord() +
                this._head.get_y().get_coord() * this._head.get_y().get_coord() +
                this._head.get_z().get_coord() * this._head.get_z().get_coord();
    }

    /**
     * Return the vector's length
     * @return the vector's length
     */
    public double length(){
        return Math.sqrt(this.lengthSquared());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector vector = (Vector) o;
        return this._head.equals(vector._head);
    }

    /**
     * Normalizes this vector and return it
     * @return this vector, normalized
     */
    public Vector normalize(){
        double myLength = this.length();
        Point3D newHead = new Point3D(this._head.get_x().get_coord()/myLength,
                this._head.get_y().get_coord()/myLength,
                this._head.get_z().get_coord()/myLength);
        this._head = newHead;
        return this;
    }

    /**
     * Return the normalized vector
     * @return normalized vector
     */
    public Vector normalized(){
        Vector myVector = new Vector(this);
        return myVector.normalize();
    }
}
