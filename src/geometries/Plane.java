
package geometries;

import primitives.Point;
import primitives.Vector;

/*** class that represents Plane
 * @author Noa Harel and Talel Ginsberg */
public class Plane implements Geometry{
    private Point q0;
    private Vector normal;


    /**
     * parameters constructor
     * @param sentQ0
     * @param sentNormal
     */
    public Plane(Point sentQ0, Vector sentNormal) {
        this.q0 = sentQ0;
        this.normal = sentNormal.normalize();
    }

    /**
     * parameters constructor
     * @param x
     * @param y
     * @param z
     */
    public Plane(Point x,Point y,Point z) {
        q0=x;
        Vector v1 = x.subtract(y);
        Vector v2 = x.subtract(z);
        normal = v1.crossProduct(v2);;
    }


    /**
     * getter function for q0
     * @return      q0
     */
    public Point getQ0() {
        return q0;
    }

    /**
     * getter function for normal
     * @return normal of the plane
     */
    public Vector getNormal(){
        return normal;
    }
    @Override
    public String toString() {
        return "Plane{" +
                "q0=" + q0 +
                ", normal=" + normal +
                '}';
    }

    @Override
    public Vector getNormal(Point p) {
        return null;
    }



}
