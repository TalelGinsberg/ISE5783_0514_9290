package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/*** class that represents Sphere
 * @author Noa Harel and Talel Ginsberg */
public class Sphere extends RadialGeometry{

    private Point center;
    //private double radius;


    /**
     * parameters construction
     * @param center  canter of sphere
     * @param radius  radius of sphere
     */
    public Sphere(Point center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    /**
     * getter function for center
     * @return      center
     */
    public Point getCenter() {
        return center;
    }

    /**
     * getter function for radius
     * @return      radius
     */
    public double getRadius() {
        return radius;
    }

    @Override
    public String toString() {
        return "Sphere{" +
                "center=" + center +
                ", radius=" + radius +
                ", radius=" + radius +
                '}';
    }

    @Override
    public Vector getNormal(Point p) {
        return p.subtract(center);
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }
}
