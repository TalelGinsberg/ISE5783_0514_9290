package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * class that represents Triangle
 *
 * @author Noa Harel and Talel Ginsberg
 */
public class Triangle extends Polygon{

    //-----------------------------constructor-------------------------

    /**
     * parameters constructor
     *
     * @param x first sent point for triangle
     * @param y second sent point for triangle
     * @param z third sent point for triangle
     */
    public Triangle(Point x,Point y, Point z) {
        super(x,y,z);
    }

    //---------------------------override functions-------------------------
    @Override
    public String toString() {
        return "Triangle{" +
                "vertices=" + vertices +
                ", plane=" + plane +
                '}';
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        try{
        //We are creating 3 triangles, with 2 vertices and the intersection point, only if sum of area of all 3 triangles
        // is equal to area of original triangle it is an intersection point


        //create plane with all 3 points of triangle,meaning the plane contains the triangle
        Vector v1=vertices.get(1).subtract(vertices.get(0));
        Vector v2=vertices.get(2).subtract(vertices.get(0));
        Vector normal = v1.crossProduct(v2);
        Plane plane=new Plane(vertices.get(0),normal);
        //finds intersection point between ray and plane we created
        List <Point>points =plane.findIntersections(ray);
        if (points==null)
                return null;
        //original triangle
        Vector V=vertices.get(1).subtract(vertices.get(0));
        Vector U= vertices.get(2).subtract(vertices.get(0));
        double areaTriangle=0.5*(V.crossProduct(U)).length();

        //triangle with intersection point, first and second vertices
        Vector V1=vertices.get(1).subtract(vertices.get(0));
        Vector U1= points.get(0).subtract(vertices.get(0));
        Vector v10 = V1.crossProduct(U1);
        double areaTriangle1=0.5*(v10).length();

        //triangle with intersection point, second and third vertices
        Vector V2=vertices.get(1).subtract(points.get(0));
        Vector U2= vertices.get(2).subtract(points.get(0));
        double areaTriangle2=0.5*(V2.crossProduct(U2)).length();

        //triangle with intersection point, first and third vertices
        Vector V3=points.get(0).subtract(vertices.get(0));
        Vector U3= vertices.get(2).subtract(vertices.get(0));
        double areaTriangle3=0.5*(V3.crossProduct(U3)).length();

        //if sum of area of all 3 triangle is equal to area of original triangle it is an intersection point
        if (areaTriangle3+areaTriangle1+areaTriangle2==areaTriangle)
            return points;
        return null;}
        catch (IllegalArgumentException e)
        {
            return null;
        }

    }
}
