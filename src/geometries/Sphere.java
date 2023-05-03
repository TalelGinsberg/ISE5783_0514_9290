package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static java.lang.Math.sqrt;
import static primitives.Util.*;

import java.util.List;

/*** class that represents Sphere
 * @author Noa Harel and Talel Ginsberg */
public class Sphere extends RadialGeometry {

    private Point center;

    /**
     * parameters construction
     *
     * @param center canter of sphere
     * @param radius radius of sphere
     */
    public Sphere(Point center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    /**
     * getter function for center
     *
     * @return center
     */
    public Point getCenter() {
        return center;
    }

    /**
     * getter function for radius
     *
     * @return radius
     */
    public double getRadius() {
        return radius;
    }

    @Override
    public String toString() {
        return "Sphere{" +
                "center=" + center +
                ", radius=" + radius +
                ", radius=" + radius +
                '}';
    }

    @Override
    public Vector getNormal(Point p) {
        return p.subtract(center);
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        // calculated based on what was learnt in the course introduction to computer engineering

        //if ray begins at center of sphere
        if (center.equals(ray.getP0()))
            return List.of(center.add(ray.getDir().scale(radius)));

        Vector u = center.subtract(ray.getP0());

        double tm = ray.getDir().dotProduct(u);//the shadow of u on ray
        double d = alignZero(sqrt(u.lengthSquared() - tm * tm));//size of second perpendicular vector using pitagoras

        //ray is outside the sphere or tangent to sphere
        if (isZero(d - radius) || d > radius) {
            return null;
        }

        double th = (sqrt(radius * radius - d * d));

        double t1 = alignZero(tm + th);
        double t2 = alignZero(tm - th);

        if (t1<=0 && t2<=0){
            return  null;
        }

        if(t1>0 && t2>0){
            return List.of(ray.getPoint(t1), ray.getPoint(t2));
        }

        if(t1>0){
            return List.of(ray.getPoint(t1));
        }

        if (t2>0){
            return List.of(ray.getPoint(t2));
        }
        return null;
        /**
        // for the point to be good t has to be larger than zero, because if it is equal to zero it is beginning point of ray
        if (t1 == t2 && t1 > 0) {
            return List.of(ray.getPoint(t1));
        }

        if (t1 > 0) {
            if (t2 > 0)
                return List.of(ray.getPoint(t1), ray.getPoint(t2));

            return List.of(ray.getPoint(t1));
        } else if (t2 > 0) {
            return List.of(ray.getPoint(t2));
        }
        return null;
         */
    }
}
