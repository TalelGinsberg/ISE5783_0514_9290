package geometries;

import primitives.Point;
import primitives.Vector;

/*** class that represents Sphere
 * @author Noa Harel and Talel Ginsberg */
public class Sphere extends RadialGeometry{

    private Point center;



    /**
     * parameters construction
     * @param center
     * @param radius
     */
    public Sphere(Point center, double radius) {
        this.center = center;
        this.radius = radius;
    }
    @Override
    public String toString() {
        return "Sphere{" +
                "center=" + center +
                ", radius=" + radius +
                ", radius=" + radius +
                '}';
    }

    private double radius;


    /**
     *
     * @return
     */
    public Point getCenter() {
        return center;
    }

    /**
     *
     * @return
     */
    public double getRadius() {
        return radius;
    }


    /**
     *
     * @param p
     * @return
     */
    @Override
    public Vector getNormal(Point p) {
        return null;
    }
}
