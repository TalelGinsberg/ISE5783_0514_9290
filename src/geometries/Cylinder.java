package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/*** class that represents Cylinder
 * @author Noa Harel and Talel Ginsberg*/

public class Cylinder extends Tube{
    private double height;

    /**
     * parameters constructor
     * @param axisRay sent axis ray for cylinder
     * @param height sent height for cylinder
     */
    public Cylinder(Ray axisRay, double height,double r) {
        super(axisRay,r);
        this.height = height;
    }

    /**
     * getter method
     * @return      height
     */
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

    @Override
    public Vector getNormal(Point point)
    {
        // calculated based on what was learnt in the course introduction to computer engineering

        Vector v=point.subtract(this.axisRay.getP0());
        double t=this.axisRay.getDrr().dotProduct(v);
        if(v.dotProduct(this.axisRay.getDrr())==0)//the point is on the bottom
        {
            return this.axisRay.getDrr();
        }
        Point o=this.axisRay.getPoint(t);
        Vector test=o.subtract(this.axisRay.getP0()).normalize();
        if((t==this.getHeight()) && (test.equals(this.axisRay.getDrr())))//the point is on the top - need to check the direction to confirm that it's not on the opposite side
        {
            return this.axisRay.getDrr();
        }
        return (point.subtract(o)).normalize();

    }
    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }
}
