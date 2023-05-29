package geometries;

import primitives.Point;
import primitives.Vector;
import primitives.Ray;

import java.util.List;


/**
 * class that represents Tube
 *
 * @author Noa Harel and Talel Ginsberg
 */
public class Tube extends RadialGeometry {

    //----------------------------fields--------------------------

    /**
     * a ray that represents the ray of the tube
     */
    Ray axisRay;


    //-----------------------------constructor-------------------------

    /**
     * parameters constructor
     *
     * @param axisRay   sent axis ray for tube
     * @param newRadius sent radius for tube
     */
    public Tube(Ray axisRay, double newRadius) {
        this.radius = newRadius;
        this.axisRay = axisRay;

    }


    //---------------------------override functions-------------------------
    @Override
    public String toString() {
        return "Tube{" +
                "axisRay=" + axisRay +
                ", radius=" + radius +
                '}';
    }

    @Override
    public Vector getNormal(Point p) {
        // calculated based on what was learnt in the course introduction to computer engineering

        Vector v = p.subtract(this.axisRay.getP0());

        if (v.dotProduct(this.axisRay.getDir()) == 0) {
            return v.normalize();
        }
        double t = this.axisRay.getDir().dotProduct(v);
        Point o = this.axisRay.getPoint(t);
        return (p.subtract(o)).normalize();
    }

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        return null;
    }

    //--------------------------------getters----------------------------

    /**
     * getter function for axis ray of the tube
     *
     * @return axisRay
     */
    public Ray getAxisRay() {
        return axisRay;
    }
}
