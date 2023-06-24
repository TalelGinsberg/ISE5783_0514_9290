package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static java.lang.Math.sqrt;
import static primitives.Util.*;

import java.util.List;

/**
 * class that represents Sphere
 *
 * @author Noa Harel and Talel Ginsberg
 */
public class Sphere extends RadialGeometry {

    //----------------------------fields--------------------------

    /* center point for sphere */
    private Point center;

    //-----------------------------constructor-------------------------

    /**
     * parameters construction
     *
     * @param center canter of sphere
     * @param radius radius of sphere
     */
    public Sphere(Point center, double radius) {
        //if bvh improvement is used
        if (BVH){
            //create bounding box
            createBoundingBox();
        }
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
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        Point P0 = ray.getP0();
        Vector v = ray.getDir();

        if (P0.equals(center)) {
            return List.of(new GeoPoint(this, ray.getPoint(radius)));
        }
        Vector u = center.subtract(P0);
        double tm = v.dotProduct(u);
        double d = alignZero(Math.sqrt(u.lengthSquared() - tm * tm));
        if (d >= radius) {
            return null;
        }
        double th = alignZero(Math.sqrt(radius * radius - d * d));
        double t1 = alignZero(tm + th);
        double t2 = alignZero(tm - th);
        if (t1 > 0 && t2 > 0) {
            Point P1 = P0.add(v.scale(t1));
            Point P2 = P0.add(v.scale(t2));
            return List.of(new GeoPoint(this, P1), new GeoPoint(this, P2));

        }
        if (t1 > 0) {
            Point P1 = P0.add(v.scale(t1));
            return List.of(new GeoPoint(this, P1));
        }
        if (t2 > 0) {
            Point P2 = P0.add(v.scale(t2));
            return List.of(new GeoPoint(this, P2));
        }
        return null;
    }

    @Override
    public Vector getNormal(Point p) {
        return p.subtract(center).normalize();
    }
    @Override
    public void createBoundingBox() {
        if (center != null) {
            double minX = center.getX() - radius;
            double minY = center.getY() - radius;
            double minZ = center.getZ() - radius;
            double maxX = center.getX() + radius;
            double maxY = center.getY() + radius;
            double maxZ = center.getZ() + radius;

            // Create a new BoundingBox object using the calculated minimum and maximum coordinates
            box = new BoundingBox(new Point(minX, minY, minZ), new Point(maxX, maxY, maxZ));
        }
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
