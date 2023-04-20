package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static java.lang.Math.sqrt;
import static primitives.Util.*;

import java.util.List;

/*** class that represents Sphere
 * @author Noa Harel and Talel Ginsberg */
public class Sphere extends RadialGeometry{

    private Point center;
    //private double radius;


    /**
     * parameters construction
     * @param center  canter of sphere
     * @param radius  radius of sphere
     */
    public Sphere(Point center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    /**
     * getter function for center
     * @return      center
     */
    public Point getCenter() {
        return center;
    }

    /**
     * getter function for radius
     * @return      radius
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

        if (center.equals(ray.getP0()))
            return List.of(center.add(ray.getDrr().scale(radius)));

        Vector u = center.subtract(ray.getP0());
        double tm = ray.getDrr().dotProduct(u);
        double d = sqrt(u.lengthSquared()-tm*tm);
        if (d>=radius)
            return null;
        double th = (sqrt(radius*radius-d*d));
        double t1 = tm+th;
        double t2 = tm-th;

        if (t1 == t2 && t1>0){
            return List.of(ray.getP0().add(ray.getDrr().scale(t1)));
        }
        if (t1>0) {
            if (t2 > 0)
                return List.of(ray.getP0().add(ray.getDrr().scale(t1)), ray.getP0().add(ray.getDrr().scale(t2)));
            else
                return List.of(ray.getP0().add(ray.getDrr().scale(t1)));
        } else if (t2>0) {
            return List.of(ray.getP0().add(ray.getDrr().scale(t2)));
        }
        return null;




/**
        Point p0 = ray.getP0();
        Vector v = ray.getDrr();

        if(p0.equals(center))
            return List.of(center.add(v.scale(radius)));
        Vector u = center.subtract(p0);

        double tm = alignZero(v.dotProduct(u));
        double d = alignZero(Math.sqrt(u.lengthSquared() - tm * tm));

        if(d>=radius)
            return null;

        double th = alignZero(Math.sqrt(radius*radius -d*d));
        if (th<=0)
            return null;

        double t1 = alignZero(tm + th);
        double t2 = alignZero(tm - th);

        if (t1 > 0 && t2 > 0)
        {
            Point p1 = p0.add(v.scale(t1));
            Point p2 = p0.add(v.scale(t2));
            return List.of(p1,p2);
        }
        if (t1 > 0)
        {
            Point p1 = p0.add(v.scale(t1));
            return List.of(p1);
        }
        if (t2 > 0)
        {
            Point p2 = p0.add(v.scale(t2));
            return List.of(p2);
        }

        return null;**/
    }



}
