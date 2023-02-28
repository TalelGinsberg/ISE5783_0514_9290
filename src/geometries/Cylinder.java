package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/*** class that represents Cylinder
 * @author Noa Harel and Talel Ginsberg*/

public class Cylinder extends Tube{
    private double height;

    /**
     * parameters constructor
     * @param axisRay
     * @param height
     */
    public Cylinder(Ray axisRay, double height) {
        super(axisRay);
        this.height = height;
    }

    /**
     * getter method
     * @return      height
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
    public Vector getNormal(Point p) {
        return null;
    }

}
