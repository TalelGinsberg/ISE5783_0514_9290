package geometries;

import primitives.Point;
import primitives.Vector;
import primitives.Ray;

public class Tube extends RadialGeometry{

    Ray axisRay;

    @Override
    public String toString() {
        return "Tube{" +
                "axisRay=" + axisRay +
                ", radius=" + radius +
                '}';
    }

    public Ray getAxisRay() {
        return axisRay;
    }

    /**
     *
     * @param axisRay
     */
    public Tube(Ray axisRay) {
        this.axisRay = axisRay;
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
