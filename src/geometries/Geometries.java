package geometries;

import primitives.*;

import java.util.LinkedList;
import java.util.List;

/**
 * Geometries class represents list of intersectable geometries for different usages
 * @author Ruth Miller
 * ruthmiller2000@gmail.com
 */
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
    public List<GeoPoint> findIntersections(Ray ray) {
        List<GeoPoint> intersections = new LinkedList<GeoPoint>();
        if (_geometries.isEmpty())
            return null;
        for (Intersectable geo:_geometries
             ) {
            List<GeoPoint> geometryIntersectionPoints = geo.findIntersections(ray);
            if(geometryIntersectionPoints != null)
                for (GeoPoint geoPoint:geometryIntersectionPoints
                     ) {
                    intersections.add(geoPoint);
                }
        }
        return intersections.isEmpty()? null : intersections;
    }
}
