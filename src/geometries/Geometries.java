package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.LinkedList;
import java.util.List;

/**
 * class that represents Geometries
 *
 * @author Noa Harel and Talel Ginsberg
 */
public class Geometries extends Intersectable {

    //----------------------------fields--------------------------

    /**
     * list of intersaction point
     */
    private List<Intersectable> list;


    //-----------------------------constructors-------------------------

    /**
     * default constructor for geometries
     */
    public Geometries() {
        //if bvh improvement is used
        if (BVH){
            //create bounding box around geometries
            createBoundingBox();
        }
        this.list = new LinkedList<Intersectable>();
    }

    /**
     * parameter constructor for geometries
     *
     * @param geometries get a list of unknown number of geometries
     */
    public Geometries(Intersectable... geometries) {
        //if bvh improvement is used
        if (BVH){
            //create bounding box around geometries
            createBoundingBox();
        }
        this.list = new LinkedList<Intersectable>();
        for (Intersectable intersectable:geometries){
            list.add(intersectable);
        }
    }

    //------------------------------functions---------------------------

    /**
     * add geometries to the list of the geometries
     *
     * @param geometries get a list of unknown number of geometries to add to the list
     */
    public void add(Intersectable... geometries) {
        for (Intersectable var : geometries) {
            list.add(var);
        }
    }


    //---------------------------override functions-------------------------

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {


        //counter for amount of points that intersected
        int count = 0;
        int z = 0;


        //go through each geometry in geometries and sum up length of points that were received in find intersection
        for (Intersectable var : list) {
            if (var.findGeoIntersectionsHelper(ray) != null)
                count += var.findGeoIntersectionsHelper(ray).size();
        }
        //System.out.println(ray.toString());
        if (count == 0)
            return null;
        List<GeoPoint> points = new LinkedList<GeoPoint>();
        //go through each geometry in geometries and add all points to list of points that were received in find intersection
        for (Intersectable var : list) {
            if (var.findGeoIntersectionsHelper(ray) != null)
                var.findGeoIntersectionsHelper(ray).forEach((x) -> points.add(x));
        }
        return points;
    }
    @Override
    public void createBoundingBox() {
        if (list == null)
            return;

        // Initialize minimum and maximum coordinates to infinity and negative infinity respectively
        double minX = Double.POSITIVE_INFINITY;
        double minY = Double.POSITIVE_INFINITY;
        double minZ = Double.POSITIVE_INFINITY;
        double maxX = Double.NEGATIVE_INFINITY;
        double maxY = Double.NEGATIVE_INFINITY;
        double maxZ = Double.NEGATIVE_INFINITY;

        // Iterate over the geometries in the list
        for (Intersectable geo : list) {
            if (geo.box != null) {
                // Update minimum and maximum coordinates based on the bounding box of each geometry
                minX = Math.min(minX, geo.box._minimums.getX());
                minY = Math.min(minY, geo.box._minimums.getY());
                minZ = Math.min(minZ, geo.box._minimums.getZ());
                maxX = Math.max(maxX, geo.box._maximums.getX());
                maxY = Math.max(maxY, geo.box._maximums.getY());
                maxZ = Math.max(maxZ, geo.box._maximums.getZ());
            }
        }

        // Create a new bounding box using the minimum and maximum coordinates
        box = new BoundingBox(new Point(minX, minY, minZ), new Point(maxX, maxY, maxZ));
    }
}
