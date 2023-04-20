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
        Vector v1=vertices.get(1).subtract(vertices.get(0));
        Vector v2=vertices.get(2).subtract(vertices.get(0));
        Plane plane=new Plane(vertices.get(0),v1.crossProduct(v2));
        List <Point>points =plane.findIntersections(ray);
        if (points==null)
                return null;
        Vector V=vertices.get(1).subtract(vertices.get(0));
        Vector U= vertices.get(2).subtract(vertices.get(0));
        double areaTriangle=0.5*(V.crossProduct(U)).length();
        Vector V1=vertices.get(1).subtract(vertices.get(0));
        Vector U1= points.get(0).subtract(vertices.get(0));
        double areaTriangle1=0.5*(V1.crossProduct(U1)).length();
        Vector V2=vertices.get(1).subtract(points.get(0));
        Vector U2= vertices.get(2).subtract(points.get(0));
        double areaTriangle2=0.5*(V2.crossProduct(U2)).length();
        Vector V3=points.get(0).subtract(vertices.get(0));
        Vector U3= vertices.get(2).subtract(vertices.get(0));
        double areaTriangle3=0.5*(V3.crossProduct(U3)).length();
        if (areaTriangle3+areaTriangle1+areaTriangle2==areaTriangle)
            return points;
        return null;

    }
}
