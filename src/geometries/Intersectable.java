package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.List;
import java.util.Objects;


/**
 * The Intersectable interface represents an object that can be intersected by a Ray.
 * Implementing classes should provide a method to find intersections between the object
 * and a given Ray.
 *
 * @author Noa Harel and Talel Ginsberg
 */
public abstract class Intersectable {



    /**
     * Finds the intersection points between this object and a given ray, in the form of GeoPoints.
     *
     * @param ray The ray to intersect with.
     * @return A list of GeoPoints representing the intersection points, or null if there are no intersections.
     */
    public final List<GeoPoint> findGeoIntersections(Ray ray){return findGoeIntersectionsHelper(ray);}

    /**
     * A helper method for finding the intersection points between this object and a given ray, in the form of GeoPoints.
     *
     * @param ray The ray to intersect with.
     * @return A list of GeoPoints representing the intersection points, or null if there are no intersections.
     */
    protected abstract List<GeoPoint> findGoeIntersectionsHelper(Ray ray);


    /**
     * Finds the intersection points between this object and a given ray, in the form of Points.
     *
     * @param ray The ray to intersect with.
     * @return A list of Points representing the intersection points, or null if there are no intersections.
     */
    public final List<Point> findIntersections(Ray ray) {
        List<GeoPoint> geoList = findGeoIntersections(ray);
        return geoList == null ? null
                : geoList.stream().map(geoPoint ->  geoPoint.point).toList();
    }


    /**
     * A helper class for returning a pair of a Geometry object and the Point at which a Ray
     * intersects the object.
     */
    public static class GeoPoint {

        //----------------------------fields--------------------------

        /**
         * The Geometry object at which the Ray intersects.
         */
        public Geometry geometry;

        /**
         * The Point at which the Ray intersects the Geometry object.
         */
        public Point point;

        //-----------------------------constructors-------------------------

        /**
         * Constructs a new GeoPoint with the specified Geometry object and Point.
         *
         * @param geometry The Geometry object at which the Ray intersects.
         * @param point The Point at which the Ray intersects the Geometry object.
         */
        public GeoPoint(Geometry geometry, Point point) {
            this.geometry = geometry;
            this.point = point;
        }


        //---------------------------override functions-------------------------

        @Override
        public String toString() {
            return "GeoPoint{" +
                    "geometry=" + geometry +
                    ", point=" + point +
                    '}';
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            GeoPoint geoPoint = (GeoPoint) o;
            return Objects.equals(geometry, geoPoint.geometry) && Objects.equals(point, geoPoint.point);
        }
    }
}
