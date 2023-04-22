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
     * @return
     */
    @Override
    public List<Point> findIntersections(Ray ray) {
        int count=0;

        for (Intersectable var: list) {
            if(var.findIntersections(ray)!=null)
                count +=var.findIntersections(ray).size();
        }
        if(count==0)
            return null;
        List<Point> points=new LinkedList<Point>();
        for (Intersectable var: list) {
            if(var.findIntersections(ray)!=null)
                var.findIntersections(ray).forEach((x)->points.add(x));
        }
        return points;
    }
}
