package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.LinkedList;
import java.util.List;

/**
 *  class that represents Geometries
 *
 * @author Noa Harel and Talel Ginsberg
 */
public class Geometries implements Intersectable{

    /**
     * list of intersaction point
     */
    private List<Intersectable> list;

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

    /**
     * add geometries to the list of the geometries
     * @param geometries get a list of unknown number of geometries to add to the list
     */
    public void add(Intersectable... geometries){
        for (Intersectable var:geometries) {
            list.add(var);
        }
    }



    @Override
    public List<Point> findIntersections(Ray ray) {
        //counter for amount of points that intersected
        int count=0;
        //go through each geometry in geometries and sum up length of points that were received in find intersection
        for (Intersectable var: list) {
            if(var.findIntersections(ray)!=null)
                count +=var.findIntersections(ray).size();
        }
        if(count==0)
            return null;
        List<Point> points=new LinkedList<Point>();
        //go through each geometry in geometries and add all points to list of points that were received in find intersection
        for (Intersectable var: list) {
            if(var.findIntersections(ray)!=null)
                var.findIntersections(ray).forEach((x)->points.add(x));
        }
        return points;
    }
}
