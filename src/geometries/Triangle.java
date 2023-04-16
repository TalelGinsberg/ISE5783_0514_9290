package geometries;

import primitives.Point;
import primitives.Ray;

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
        return null;
    }
}
