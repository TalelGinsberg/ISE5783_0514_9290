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
     * @param r
     */
    public Tube(Ray axisRay, double r) {
        this.radius=r;
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

        //להבין!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        Vector v=p.subtract(this.axisRay.getP0());

        if(v.dotProduct(this.axisRay.getDrr())==0)
        {
            return v.normalize();
        }
        double t=this.axisRay.getDrr().dotProduct(v);
        Point o=this.axisRay.getP0().add(this.axisRay.getDrr().scale(t));
        Vector normal=(p.subtract(o)).normalize();
        return normal;
    }
}
