package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;
import java.util.Objects;


/**
 * The Intersectable class represents an abstract 3D
 * object that can be intersected by a ray.
 * This class provides methods for finding the intersection
 * points between the object and a given ray,
 * as well as a helper class for returning a pair of the
 * intersected Geometry object and the intersection point.
 *
 * @author Noa Harel and Talel Ginsberg
 */
public abstract class Intersectable {
    // for bvh use
    public static boolean BVH=true;

    /**
     * class representing boundary box
     */
    public class BoundingBox {
        public Point _minimums;
        public Point _maximums;

        /**
         * Constructs a bounding box with the specified minimum and maximum points.
         *
         * @param minimums The minimum point of the bounding box.
         * @param maximums The maximum point of the bounding box.
         */
        public BoundingBox(Point minimums, Point maximums) {
            _minimums = minimums;
            _maximums = maximums;
        }
    }

    //bounding box for intersectable
    public BoundingBox box;

    /**
     * return true if ray intersects object
     *
     * @param ray ray to check
     * @return whether ray intersects box
     * code taken from scratchapixel.com
     * https://www.scratchapixel.com/lessons/3d-basic-rendering
     * /introductionacceleration-structure/bounding-volume-hierarchy-BVH-part1
     */
    public boolean intersectingBoundingBox(Ray ray) {
        if (!BVH || box == null)
            return true;
        Vector dir = ray.getDir();
        Point p0 = ray.getP0();

        // Calculate the intersection intervals on the x-axis
        double xMin = (box._minimums.getX() - p0.getX()) / dir.getX();
        double xMax = (box._maximums.getX() - p0.getX()) / dir.getX();

        // Ensure xMin is smaller than xMax
        if (xMin > xMax) {
            double temp = xMin;
            xMin = xMax;
            xMax = temp;
        }

        // Calculate the intersection intervals on the y-axis
        double yMin = (box._minimums.getY() - p0.getY()) / dir.getY();
        double yMax = (box._maximums.getY() - p0.getY()) / dir.getY();

        // Ensure yMin is smaller than yMax
        if (yMin > yMax) {
            double temp = yMin;
            yMin = yMax;
            yMax = temp;
        }

        // Check for non-overlapping intervals on the x-axis and y-axis
        if ((xMin > yMax) || (yMin > xMax))
            return false;

        // Update xMin to the maximum of yMin and xMin
        if (yMin > xMin)
            xMin = yMin;

        // Update xMax to the minimum of yMax and xMax
        if (yMax < xMax)
            xMax = yMax;

        // Calculate the intersection intervals on the z-axis
        double zMin = (box._minimums.getZ() - p0.getZ()) / dir.getZ();
        double zMax = (box._maximums.getZ() - p0.getZ()) / dir.getZ();

        // Ensure zMin is smaller than zMax
        if (zMin > zMax) {
            double temp = zMin;
            zMin = zMax;
            zMax = temp;
        }

        // Check for non-overlapping intervals on the x-axis and z-axis
        if ((xMin > zMax) || (zMin > xMax))
            return false;

        // Update xMin to the maximum of zMin and xMin
        if (zMin > xMin)
            xMin = zMin;

        // Update xMax to the minimum of zMax and xMax
        if (zMax < xMax)
            xMax = zMax;

        return true;
    }

    /**
     * create the boundary box for the objects
     */
    public abstract void createBoundingBox();


    /**
     * Finds the intersection points between this object and a given ray, in the form of GeoPoints.
     *
     * @param ray The ray to intersect with.
     * @return A list of GeoPoints representing the intersection points, or null if there are no intersections.
     */
    public final List<GeoPoint> findGeoIntersections(Ray ray) {

        if (BVH && !intersectingBoundingBox(ray))
        {
            return null;
        }
        return findGeoIntersectionsHelper(ray);
    }

    /**
     * A helper method for finding the intersection points between this object and a given ray,
     * in the form of GeoPoints.
     *
     * @param ray The ray to intersect with.
     * @return A list of GeoPoints representing the intersection points, or null if there are no intersections.
     */
    protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray);


    /**
     * Finds the intersection points between this object and a given ray, in the form of Points.
     *
     * @param ray The ray to intersect with.
     * @return A list of Points representing the intersection points, or null if there are no intersections.
     */
    public final List<Point> findIntersections(Ray ray) {
        List<GeoPoint> geoList = findGeoIntersections(ray);
        return geoList == null ? null
                : geoList.stream().map(geoPoint -> geoPoint.point).toList();
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
        public final Geometry geometry;

        /**
         * The Point at which the Ray intersects the Geometry object.
         */
        public final Point point;

        //-----------------------------constructors-------------------------

        /**
         * Constructs a new GeoPoint with the specified Geometry object and Point.
         *
         * @param geometry The Geometry object at which the Ray intersects.
         * @param point    The Point at which the Ray intersects the Geometry object.
         */
        public GeoPoint(Geometry geometry, Point point) {
            this.geometry = geometry;
            this.point = point;
        }

        public Geometry setBVH(boolean bvh) {
            BVH = bvh;
            return this.geometry;
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
