package primitives;

import geometries.Intersectable.GeoPoint;

import java.util.List;
import java.util.Objects;

import static primitives.Util.isZero;


/**
 * This class represents a ray in 3D space,
 * defined by a starting point and a direction vector.
 *
 * @author Noa Harel and Talel Ginsberg
 */
public class Ray {

    //----------------------------fields--------------------------
    /**
     * Constant for head of ray offset size for shading rays
     */
    private static final double DELTA = 0.1;

    /* point for which the ray starts*/
    private Point p0;
    /* vector for the direction of the ray*/
    private Vector drr;

    //-----------------------------constructor-------------------------

    /**
     * Constructor to initialize a Ray object with a starting point and a direction vector.
     *
     * @param sentP0  The starting point of the ray.
     * @param sentDrr The direction vector of the ray.
     */
    public Ray(Point sentP0, Vector sentDrr) {
        this.p0 = sentP0;
        this.drr = sentDrr.normalize();
    }

    /**
     * Constructor for ray deflected by DELTA
     *
     * @param p origin
     * @param n   normal vector
     * @param drr direction
     */
    public Ray(Point p, Vector n, Vector drr) {
        this.drr = drr.normalize();
        double nv = n.dotProduct(this.drr);
        Vector delta  =n.scale(DELTA);
        if (nv < 0)
            delta = delta.scale(-1);
        this.p0 = p.add(delta);
    }

    //------------------------------functions---------------------------

    /**
     * Calculates the point that is a certain distance away from the starting point of the ray,
     * in the direction of the ray.
     *
     * @param t A scalar value representing the distance from the starting point to the desired point.
     * @return The point that is t units away from the starting point in the direction of the ray.
     */
    public Point getPoint(double t) {
        return p0.add(drr.scale(t));
    }

    /**
     * Finds the closest point in a list of points to a given point on the ray.
     *
     * @param intersections A list of points to search from.
     * @return The closest point to the given point on the ray.
     * If the list is empty, returns null.
     */
    public Point findClosestPoint(List<Point> intersections) {

        return intersections == null ? null :
                findClosestGeoPoint(intersections.stream()
                        .map(point -> new GeoPoint(null, point))
                        .toList())
                        .point;

    }


    /**
     * Finds the closest GeoPoint in a list of GeoPoints to a given point on the ray.
     *
     * @param points A list of GeoPoints to search from.
     * @return The closest GeoPoint to the given point on the ray.
     * If the list is empty, returns null.
     */
    public GeoPoint findClosestGeoPoint(List<GeoPoint> points) {

        if (points == null || isZero(points.size()))
            return null;

        // Initialize the closest geo point to be the first geo point in the list
        GeoPoint closestGeoPoint = points.get(0);

        // Initialize the minimum distance to be the distance from p0 to the first geo point in the list
        double minDistance = p0.distance(closestGeoPoint.point);

        double distance;

        // Iterate over the rest of the points and update the minimum distance and closest point if necessary
        for (int index = 1; index < points.size(); index++) {
            distance = p0.distance(points.get(index).point);
            if (distance < minDistance) {
                minDistance = distance;
                closestGeoPoint = points.get(index);
            }
        }

        // return the closest geo point
        return closestGeoPoint;

    }

    //---------------------------override functions-------------------------

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ray ray = (Ray) o;
        return p0.equals(ray.p0) && drr.equals(ray.drr);
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
