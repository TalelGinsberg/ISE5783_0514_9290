package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/*** class that represents Triangle
 * @author Noa Harel and Talel Ginsberg */
public class Triangle extends Polygon{

    /**
     * parameters constructor
     * @param x
     * @param y
     * @param z
     */
    public Triangle(Point x,Point y, Point z) {
        super(x,y,z);
    }


    @Override
    public String toString() {
        return "Triangle{" +
                "vertices=" + vertices +
                ", plane=" + plane +
                '}';
    }
    @Override
    public List<Point> findIntersections(Ray ray) {
        Plane plane=new Plane(vertices.get(0),vertices.get(1).subtract(vertices.get(2)));
        List <Point>points =plane.findIntersections(ray);
        if (points.equals(null))
                return null;
        if
    }
}
