package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static java.lang.Math.sqrt;
import static primitives.Util.*;

import java.util.List;

/**
 * class that represents Sphere
 * @author Noa Harel and Talel Ginsberg
 */
public class Sphere extends RadialGeometry {

    //----------------------------fields--------------------------

    /*center point for sphere*/
    private Point center;

    //-----------------------------constructor-------------------------

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


    //---------------------------override functions-------------------------
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
        try{
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

            //for the point to be good t has to be larger than zero, because if it is equal to zero it is beginning point of ray

            /*0 points*/
            if (t1<=0 && t2<=0){
                return  null;
            }

            /*2 points*/
            if(t1>0 && t2>0){
                return List.of(ray.getPoint(t1), ray.getPoint(t2));
            }

            /*1 point*/
            if(t1>0){
                return List.of(ray.getPoint(t1));
            }

            /*1 point*/
            if (t2>0){
                return List.of(ray.getPoint(t2));
            }
            return null;
        }
        catch (IllegalArgumentException e){return null;}
    }


    //--------------------------------getters----------------------------

    /**
     * getter function for center
     *
     * @return center point of this sphere
     */
    public Point getCenter() {
        return center;
    }

    /**
     * getter function for radius
     *
     * @return radius for this raduis
     */
    public double getRadius() {
        return radius;
    }
}
