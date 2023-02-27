
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
        normal=null;
        q0=x;
    }


    /**
     * getter function for q0
     * @return      q0
     */
    public Point getQ0() {
        return q0;
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


    /**
     * getter function for normal
     * @return normal of the plane
     */
    public Vector getNormal(){
        return normal;
    }
}
