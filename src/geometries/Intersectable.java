package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.List;
import java.util.Objects;


/**
 * The Intersectable interface represents an object that can be intersected by a Ray.
 * Implementing classes should provide a method to find intersections between the object
 * and a given Ray.
 */
public abstract class Intersectable {



    public List<GeoPoint> findGeoIntersections(Ray ray){return findGoeIntersectionsHelper(ray);}

    protected abstract List<GeoPoint> findGoeIntersectionsHelper(Ray ray);

    /**
     * Finds the intersections between the object and the given Ray.
     *
     * @param ray The Ray to intersect with the object.
     * @return A List of Point objects representing the intersections, or NULL
     * if no intersections were found.
     */
    //public abstract List<Point> findIntersections(Ray ray);


    public List<Point> findIntersections(Ray ray) {
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null : geoList.stream().map(gp -> gp.point).toList();
    }

    /**
     * A helper class for returning a pair of a Geometry object and the Point at which a Ray
     * intersects the object.
     */
    public static class GeoPoint {

        /**
         * The Geometry object at which the Ray intersects.
         */
        public Geometry geometry;

        /**
         * The Point at which the Ray intersects the Geometry object.
         */
        public Point point;

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
