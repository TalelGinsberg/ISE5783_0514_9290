package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class Cylinder extends Tube{

    private double height;

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

    /**
     *
     * @param axisRay
     * @param height
     */
    public Cylinder(Ray axisRay, double height) {
        super(axisRay);
        this.height = height;
    }

    @Override
    public Vector getNormal(Point p) {
        return null;
    }

}
