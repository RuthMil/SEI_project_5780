package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.LinkedList;
import java.util.List;

public class Geometries implements Intersectable {
    List<Intersectable>  _geometries;

    public Geometries(Intersectable... geometries){

    }

    /**
     * Default constructor for Geometries class
     */
    public Geometries() {
        this._geometries = new LinkedList<Intersectable>();
    }

    /**
     * Add new geometries to the geometries list
     * @param geometries geometries to add to the geometries list
     */
    public void add(Intersectable... geometries){
        for (Intersectable geo:geometries
             ) {
            _geometries.add(geo);
        }
    }

    /**
     * find intersections point3D with the geometries list
     * @param ray ray for casting
     * @return list of intersections point3D or null if there were not found
     */
    @Override
    public List<Point3D> findIntersections(Ray ray) {
        List<Point3D> intersections = new LinkedList<Point3D>();
        if (_geometries.isEmpty())
            return null;
        for (Intersectable geo:_geometries
             ) {
            List<Point3D> geometryIntersectionPoints = geo.findIntersections(ray);
            if(geometryIntersectionPoints != null)
                for (Point3D point:geometryIntersectionPoints
                     ) {
                    intersections.add(point);
                }
        }
        return intersections.isEmpty()? null : intersections;
    }
}
