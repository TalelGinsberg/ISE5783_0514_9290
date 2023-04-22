package geometries;

import primitives.Point;
import primitives.Vector;
import primitives.Ray;

import java.util.List;


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
        // calculated based on what was learnt in the course introduction to computer engineering

        Vector v=p.subtract(this.axisRay.getP0());

        if(v.dotProduct(this.axisRay.getDrr())==0)
        {
            return v.normalize();
        }
        double t=this.axisRay.getDrr().dotProduct(v);
        Point o=this.axisRay.getPoint(t);
        Vector normal=(p.subtract(o)).normalize();
        return normal;
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }
}
