package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 *  class that represents Cylinder
 *
 * @author Noa Harel and Talel Ginsberg
 */

public class Cylinder extends Tube {

    /**
     * height of the cylinder
     */
    private double height;

    /**
     * parameters constructor
     *
     * @param axisRay sent axis ray for cylinder
     * @param height  sent height for cylinder
     */
    public Cylinder(Ray axisRay, double height, double r) {
        super(axisRay, r);
        this.height = height;
    }

    /**
     * getter method to get height of cylinder
     *
     * @return height of the Cylinder
     */
    public double getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "Cylinder{" +
                "height=" + height +
                ", axisRay=" + axisRay +
                ", radius=" + radius +
                '}';
    }

    @Override
    public Vector getNormal(Point point) {
        // calculated based on what was learnt in the course introduction to computer engineering

        Vector v = point.subtract(this.axisRay.getP0());
        double t = this.axisRay.getDir().dotProduct(v);

        //the point is on the bottom
        if (v.dotProduct(this.axisRay.getDir()) == 0)
        {
            return this.axisRay.getDir();
        }

        Point o = this.axisRay.getPoint(t);

        Vector test = o.subtract(this.axisRay.getP0()).normalize();
        if ((t == this.getHeight()) && (test.equals(this.axisRay.getDir())))//the point is on the top - need to check the direction to confirm that it's not on the opposite side
        {
            return this.axisRay.getDir();
        }
        return (point.subtract(o)).normalize();

    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }
}
