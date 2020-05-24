package geometries;
import primitives.*;

import java.util.List;
import java.util.Objects;

/**
 * Intersectable interface represents geometry which has potential to have intersection points
 * @author Ruth Miller
 * ruthmiller2000@gmail.com
 */
public interface Intersectable {
    /**
     * GeoPoint class represents a point which associates to a specific geometry
     */
    public static class GeoPoint {
        public Geometry geometry;
        public Point3D point;

        /**
         * Parameter constructor for GeoPoint class
         * @param geometry Type of geometry which the point is associates to
         * @param point a 3DPoint
         */
        public GeoPoint(Geometry geometry, Point3D point) {
            this.geometry = geometry;
            this.point = point;
        }

        //TODO how to check types of geometries
        /**
         * Checks if two Intersectables are equal
         * @param o other Intersectable
         * @return true if the intersectables are equal, else false
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            GeoPoint geoPoint = (GeoPoint) o;
            return point.equals(geoPoint.point) &&
                    geometry.getClass() == geoPoint.geometry.getClass();
        }
    }

    List<GeoPoint> findIntersections(Ray ray);
}
