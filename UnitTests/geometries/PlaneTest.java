package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class PlaneTest {
    Point p1=new Point(0,0,0);
    Point p2=new Point(0,1,0);
    Point p3=new Point(1,0,0);

    @Test
    public void testConstructor() {



        // ============ Equivalence Partitions Tests ==============

        try {
            new Plane(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0));
        } catch (IllegalArgumentException e) {
            fail("Failed constructing a correct plane");
        }



        // =============== Boundary Values Tests ==================
        assertThrows(IllegalArgumentException.class, //
                () -> new Plane(new Point(0, 0, 1), new Point(0, 0, 1), new Point(1, 0, 0)),
                "Constructed a plane with two points that are the same");

        assertThrows(IllegalArgumentException.class, //
                () -> new Plane(new Point(0, 0, 1), new Point(0, 0, 2), new Point(0, 0, 3)),
                "Constructed a plane with 3 points that are on the same ray");
    }

    @Test
    void getNormal() {

        // ============ Equivalence Partitions Tests ==============

        // we crated a plane
        Plane plane = new Plane(p1,p2,p3);
        // check that there are no exceptions while creating normal for plane
        assertDoesNotThrow(() -> plane.getNormal(p1), "");
        //calculate normal
        Vector result=plane.getNormal(p1);
        //we made sure that the length of the noraml is 1
        assertEquals(1,result.length(),0.000001,"Plane's normal is not a unit vector");
        //we created 3 vectors in planes
        Vector v1=new Vector(0,1,0);
        Vector v2=new Vector(1,0,0);
        Vector v3=new Vector(-1,1,0);
        //we made sure that the normal is really orthogonal to all three vectors in plane
        assertEquals(0,v1.dotProduct(result),"Plane's normal is not orthogonal to one of the edges");
        assertEquals(0,v3.dotProduct(result),"Plane's normal is not orthogonal to one of the edges");
        assertEquals(0,v2.dotProduct(result),"Plane's normal is not orthogonal to one of the edges");

    }
}