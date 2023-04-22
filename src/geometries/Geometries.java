package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.LinkedList;
import java.util.List;

public class Geometries implements Intersectable{

    private List<Intersectable> list;

    public Geometries() {
        this.list = new LinkedList<Intersectable>();
    }

    public Geometries(Intersectable... geometries) {
        this.list = List.of(geometries);
    }

    public void add(Intersectable... geometries){
        for (Intersectable var:geometries) {
            list.add(var);
        }
    }

    /**
     * @param ray The Ray to intersect with the object.
     * @return list of intersection points
     */
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
