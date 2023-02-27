/**
 *
 */


package geometries;

import primitives.Point;
import primitives.Vector;

public class Plane implements Geometry{
    private Point q0;
    private Vector normal;

    @Override
    public String toString() {
        return "Plane{" +
                "q0=" + q0 +
                ", normal=" + normal +
                '}';
    }

    public Point getQ0() {
        return q0;
    }

    /**
     *
     * @param sentQ0
     * @param sentNormal
     */
    public Plane(Point sentQ0, Vector sentNormal) {
        this.q0 = sentQ0;
        this.normal = sentNormal.normalize();
    }



    /**
     *
     * @param x
     * @param y
     * @param z
     */
    public Plane(Point x,Point y,Point z) {
        normal=null;
        q0=x;
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




    /**
     *
     * @return normal of the plane
     */
    public Vector getNormal(){
        return normal;
    }
}
