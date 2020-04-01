package geometries;

import primitives.Point3D;

/**
 * Triangle class represents a triangle in 3D Cartesian coordinate
 * @author Ruth Miller
 * 314920976
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
}
