
package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.*;

import java.util.List;

/**
 * class that represents Plane
 *
 * @author Noa Harel and Talel Ginsberg
 */
public class Plane extends Geometry {

    //----------------------------fields--------------------------

    /**
     * point that represents the points on the plane
     */
    private Point q0;

    /**
     * vector that represents the normal of the plane
     */
    private Vector normal;


    //-----------------------------constructors-------------------------

    /**
     * parameters constructor
     *
     * @param sentQ0     sent point for plane
     * @param sentNormal sent normal for plane
     */
    public Plane(Point sentQ0, Vector sentNormal) {
        this.q0 = sentQ0;
        this.normal = sentNormal.normalize();
    }

    /**
     * parameters constructor
     *
     * @param x first point
     * @param y second point
     * @param z third point
     */
    public Plane(Point x, Point y, Point z) {
        q0 = x;

        Vector v1 = x.subtract(y);
        Vector v2 = x.subtract(z);

        normal = v1.crossProduct(v2);
    }

    //---------------------------override functions-------------------------

    @Override
    public String toString() {
        return "Plane{" +
                "q0=" + q0 +
                ", normal=" + normal +
                '}';
    }

    @Override
    public Vector getNormal(Point p) {
       //according to  Software engineering conventions
        // return getNormal();
        // but for better performance we use the following
        return normal;
    }

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        Point p0 = ray.getP0();
        Vector v = ray.getDir();
        if (this.q0.equals(p0)){
            return null;
        }
        Vector n = this.normal;
        Vector p0_q0 = this.q0.subtract(p0);

        // numerator
        double nP0Q0 = alignZero(n.dotProduct(p0_q0));
        // t should be greater than zero
        if (isZero(nP0Q0)){
            return null;
        }

        // denominator
        double nv = alignZero(n.dotProduct(v));
        // ray is lying in the plane axis
        if (isZero(nv)){
            return null;
        }

        double t = alignZero(nP0Q0/nv);

        // t need to be greater than zero
        if (t <= 0){
            return null;
        }
        Point point = ray.getPoint(t);
        return List.of(new GeoPoint(this, point));
    }


    //--------------------------------getters----------------------------

    /**
     * getter function for q0
     *
     * @return q0
     */
    public Point getQ0() {
        return q0;
    }

    /**
     * getter function for normal
     *
     * @return normal of the plane
     */
    public Vector getNormal() {
        return normal;
    }
}
