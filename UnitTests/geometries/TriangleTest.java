package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class TriangleTest {

    Point p1=new Point(0,0,0);
    Point p2=new Point(0,1,0);
    Point p3=new Point(1,0,0);
    @Test
    void testToString() {

        // ============ Equivalence Partitions Tests ==============

        // we created a triangle
        Triangle triangle = new Triangle(p1,p2,p3);
        // check that there are no exceptions while creating normal for triangle
        assertDoesNotThrow(() -> triangle.getNormal(p1), "");
        //calculate normal
        Vector result=triangle.getNormal(p1);
        //we made sure that the length of the normal is 1
        assertEquals(1,result.length(),0.000001,"Triangle's normal is not a unit vector");
        //we created the 3 edges of triangle
        Vector v1=new Vector(0,1,0);
        Vector v2=new Vector(1,0,0);
        Vector v3=new Vector(-1,1,0);
        //we made sure that the normal is really orthogonal to all three edges
        assertEquals(0,v1.dotProduct(result),"Triangle's normal is not orthogonal to one of the edges");
        assertEquals(0,v3.dotProduct(result),"Triangle's normal is not orthogonal to one of the edges");
        assertEquals(0,v2.dotProduct(result),"Triangle's normal is not orthogonal to one of the edges");
    }
}