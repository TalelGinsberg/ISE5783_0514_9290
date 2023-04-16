package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.List;



/**
 * The Intersectable interface represents an object that can be intersected by a Ray.
 * Implementing classes should provide a method to find intersections between the object
 * and a given Ray.
 */
public interface Intersectable {

    /**
     * Finds the intersections between the object and the given Ray.
     *
     * @param ray The Ray to intersect with the object.
     * @return A List of Point objects representing the intersections, or NULL
     * if no intersections were found.
     */
    List<Point> findIntersections(Ray ray);
}
