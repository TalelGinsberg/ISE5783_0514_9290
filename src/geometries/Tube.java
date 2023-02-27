package geometries;

import primitives.Point;
import primitives.Vector;
import primitives.Ray;


/*** class that represents Tube
 * @author Noa Harel and Talel Ginsberg */
public class Tube extends RadialGeometry{

    Ray axisRay;

    /**
     * parameters constructor
     * @param axisRay
     */
    public Tube(Ray axisRay) {
        this.axisRay = axisRay;
    }

    /**
     * getter function for axis ray of the tube
     * @return      axisRay
     */
    public Ray getAxisRay() {
        return axisRay;
    }
    @Override
    public String toString() {
        return "Tube{" +
                "axisRay=" + axisRay +
                ", radius=" + radius +
                '}';
    }

    @Override
    public Vector getNormal(Point p) {
        return null;
    }
}
