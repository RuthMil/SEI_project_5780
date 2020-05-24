package geometries;

import primitives.*;

import java.util.List;

import static primitives.Util.isZero;

/**
 * Triangle class represents a triangle in 3D Cartesian coordinate
 * @author Ruth Miller
 * ruthmiller2000@gmail.com
 */
public class Triangle extends Polygon {

    /**
     * Triangle constructor based on vertices list. The list must be ordered by edge path
     * @param vertices list of vertices according to their order by edge path
     * @throws IllegalArgumentException in any case of illegal combination of
     *                                  vertices:
     *                                  <ul>
     *                                  <li>Less than 3 vertices</li>
     */
    public Triangle(Point3D... vertices) {
        super(vertices);
    }

    /**
     * Triangle constructor based on vertices list. The list must be ordered by edge path
     * @param emission emission light
     * @param vertices list of vertices according to their order by edge path
     * @throws IllegalArgumentException in any case of illegal combination of
     *                                  vertices:
     *                                  <ul>
     *                                  <li>Less than 3 vertices</li>
     */
    public Triangle(Color emission, Point3D... vertices){
        this(vertices);
        this._emission = emission;
    }

    /**
     * Triangle constructor based on vertices list. The list must be ordered by edge path
     *
     * @param material material of the triangle
     * @param emission emission light
     * @param vertices list of vertices according to their order by edge path
     * @throws IllegalArgumentException in any case of illegal combination of
     *                                  vertices:
     *                                  <ul>
     *                                  <li>Less than 3 vertices</li>
     */
    public Triangle(Color emission, Material material, Point3D... vertices){
        this(emission, vertices);
        this._material = material;
    }

    /**
     * find intersections point3D with triangle
     * @param ray ray for casting
     * @return list of intersections point3D or null if there were not found
     */
    @Override
    public List<GeoPoint> findIntersections(Ray ray) {
        List<GeoPoint> intersections = _plane.findIntersections(ray);
        for (GeoPoint geoPoint: intersections
             ) {
            geoPoint.geometry = this;
        }
        if (intersections == null) return null;

        Point3D p0 = ray.getPoint();
        Vector v = ray.getDir();

        Vector v1 = _vertices.get(0).subtract(p0);
        Vector v2 = _vertices.get(1).subtract(p0);
        Vector v3 = _vertices.get(2).subtract(p0);

        double s1 = v.dotProduct(v1.crossProduct(v2).normalize());
        if (isZero(s1)) return null;
        double s2 = v.dotProduct(v2.crossProduct(v3).normalize());
        if (isZero(s2)) return null;
        double s3 = v.dotProduct(v3.crossProduct(v1).normalize());
        if (isZero(s3)) return null;

        return ((s1 > 0 && s2 > 0 && s3 > 0) || (s1 < 0 && s2 < 0 && s3 < 0)) ? intersections : null;
    }
}
