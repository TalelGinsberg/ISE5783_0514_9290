package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
/*** Testing Triangles
 * @author Noa Harel and Talel Ginsberg*/
class TriangleTest {

    Point p1=new Point(0,0,0);
    Point p2=new Point(0,1,0);
    Point p3=new Point(1,0,0);
    /**
     * test function for get normal function in triangle
     */
    @Test
    void getNormal() {
        // ============ Equivalence Partitions Tests ==============
        //TC01: Constructing a triangle and receiving the expected normal
        // We created a triangle
        Triangle triangle = new Triangle(p1,p2,p3);
        // Check that there are no exceptions while creating normal for triangle
        assertDoesNotThrow(() -> triangle.getNormal(p1), "");
        // Calculate normal
        Vector result=triangle.getNormal(p1);
        // We made sure that the length of the normal is 1
        assertEquals(1,result.length(),0.000001,"Triangle's normal is not a unit vector");
        // We created the 3 edges of triangle
        Vector v1=new Vector(0,1,0);
        Vector v2=new Vector(1,0,0);
        Vector v3=new Vector(-1,1,0);
        // We made sure that the normal is really orthogonal to all three edges
        assertEquals(0,v1.dotProduct(result),"Triangle's normal is not orthogonal to one of the edges");
        assertEquals(0,v3.dotProduct(result),"Triangle's normal is not orthogonal to one of the edges");
        assertEquals(0,v2.dotProduct(result),"Triangle's normal is not orthogonal to one of the edges");
    }

    @Test
    void findIntersections() {
        Point p1=new Point(1,0,0);
        Point p2=new Point(0,1,0);
        Point p3=new Point(-1,-1,0);
        Triangle triangle=new Triangle(p1,p2,p3);
        // ============ Equivalence Partitions Tests ==============
        //TC01: Point is outside Triangle supposed to return null
        assertNull(triangle.findIntersections(new Ray(new Point(2,0,0),new Vector(0,0,1))),"Ray's line outside triangle");
        //TC02: Point is inside Triangle(1 point)
        List<Point> result=triangle.findIntersections(new Ray(new Point(0,0,0),new Vector(0,0,1)));
        assertEquals(1,result.size(),"Wrong number of points");
        assertEquals(new Point(0,0,0),result.get(0),"Point inside triangle");

    }
}