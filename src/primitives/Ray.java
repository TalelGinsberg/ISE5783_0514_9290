package primitives;

import geometries.Intersectable.GeoPoint;

import java.util.List;
import java.util.Objects;

import static primitives.Util.isZero;

/**
 * This class will serve all primitive classes based on a point and vector
 *
 * @author Noa Harel and Talel Ginsberg
 */
public class Ray {

    //----------------------------fields--------------------------

    /* point for which the ray starts*/
    private Point p0;
    /* vector for the direction of the ray*/
    private Vector drr;

    //-----------------------------constructor-------------------------

    /**
     *  Constructor to initialize Point based on a point and vector
     *
     * @param sentP0 point for ray
     * @param sentDrr  vector for ray
     */
    public Ray(Point sentP0, Vector sentDrr) {
        this.p0 = sentP0;
        this.drr = sentDrr.normalize();
    }

    //------------------------------functions---------------------------


    /**
     * calculates the point=p0+t*drr
     *
     * @param t a double to scale the vector with
     * @return the point of the ray plus the ray scaled with t
     */
    public Point getPoint(double t){
        return p0.add(drr.scale(t));
    }


    /**
     * Finds the closest point in a list of points to a given point.
     *
     * @param points A list of points to search from
     * @return The closest point to the given point
     *         If the list is empty, returns null.
     */
    public Point findClosestPoint(List<Point> points){

        if (isZero(points.size()))
            return null;

        // Initialize the minimum distance to be the distance from p0 to the first point in the list
        double minDistance = p0.distance(points.get(0));

        // Initialize the closest point to be the first point in the list
        Point closestPoint = points.get(0);

        // Iterate over the rest of the points and update the minimum distance and closest point as necessary
        for (int index = 1; index < points.size(); index++) {
            double distance = p0.distance(points.get(index));
            if (distance < minDistance) {
                minDistance = distance;
                closestPoint = points.get(index);
            }
        }

        // return the closest point
        return closestPoint;

    }
    /**
     * Finds the closest geo point in a list of points to a given point.
     *
     * @param points A list of geo points to search from
     * @return The closest geo point to the given point
     *         If the list is empty, returns null.
     */
    public Point findClosestPoint(List<Point> intersections) {
        return intersections == null ? null
                : findClosestGeoPoint(intersections.stream()
                .map((p) ‐> new GeoPoint(null, p)).toList()).point;
    }
    public GeoPoint findClosestGeoPoint(List<GeoPoint> points){

        if (isZero(points.size()))
            return null;

        // Initialize the minimum distance to be the distance from p0 to the first geo point in the list
        double minDistance = p0.distance(points.get(0).point);

        // Initialize the closest geo point to be the first geo point in the list
        GeoPoint closestPoint = points.get(0);

        // Iterate over the rest of the points and update the minimum distance and closest point as necessary
        for (int index = 1; index < points.size(); index++) {
            double distance = p0.distance(points.get(index).point);
            if (distance < minDistance) {
                minDistance = distance;
                closestPoint = points.get(index);
            }
        }

        // return the closest geo point
        return closestPoint;

    }



    //---------------------------override functions-------------------------

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ray ray = (Ray) o;
        return Objects.equals(p0, ray.p0) && Objects.equals(drr, ray.drr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(p0, drr);
    }

    @Override
    public String toString() {
        return "Ray{" +
                "p0=" + p0 +
                ", drr=" + drr +
                '}';
    }

    //--------------------------------getters----------------------------

    /**
     * getter for the po, the starting point of the ray
     *
     * @return point of vector
     */
    public Point getP0() {
        return p0;
    }

    /**
     * getter for drr,the direction vector of ray
     *
     * @return vector of ray
     */
    public Vector getDir() {
        return drr;
    }

}
