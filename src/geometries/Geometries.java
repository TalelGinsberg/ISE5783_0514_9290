package geometries;

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
        this.list = new LinkedList<Intersectable>();
    }

    /**
     * parameter constructor for geometries
     *
     * @param geometries get a list of unknown number of geometries
     */
    public Geometries(Intersectable... geometries) {
        this.list = List.of(geometries);
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


}
