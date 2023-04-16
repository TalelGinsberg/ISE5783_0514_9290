package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
/*** Testing Spheres
 * @author Noa Harel and Talel Ginsberg*/
class SphereTest {

    Point p1 = new Point(0,1,0);

    /**
     * test function for get normal function in sphere
     */
    @Test
    void getNormal() {
        // ============ Equivalence Partitions Tests ==============
        //TC01: Constructing a sphere and receiving the expected normal
        // We created a sphere
        Sphere sphere = new Sphere(new Point(0,0,0), 1);
        // Check that there are no exceptions while creating normal for sphere
        assertDoesNotThrow(() -> sphere.getNormal(p1), "");
        // Calculate normal
        Vector result=sphere.getNormal(p1);
        // We created the expected result
        Vector vector = new Vector(0,1,0);
        // We made sure that the result is equal to the one we expected
        assertTrue(vector.equals(result), "Sphere's normal is not the correct vector");
    }
    /**
     * Test method for {@link geometries.Sphere#findIntersections(primitives.Ray)}.
     */
    @Test
    public void testFindIntersections() {
        Sphere sphere = new Sphere(new Point (1, 0, 0),1d);
        Sphere sphere2 = new Sphere(new Point (0, 0, 0),1d);

        // ============ Equivalence Partitions Tests ==============
        // TC01: Ray's line is outside the sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(-1, 0, 0), new Vector(1, 1, 0))),
                "Ray's line out of sphere");
        // TC02: Ray starts before and crosses the sphere (2 points)
        Point p1 = new Point(0.0651530771650466, 0.355051025721682, 0);
        Point p2 = new Point(1.53484692283495, 0.844948974278318, 0);
        List<Point> result = sphere.findIntersections(new Ray(new Point(-1, 0, 0),
                new Vector(3, 1, 0)));
        assertEquals(2, result.size(), "Wrong number of points");
        if (result.get(0).getX() > result.get(1).getX())
            result = List.of(result.get(1), result.get(0));
        assertEquals(List.of(p1, p2), result, "Ray crosses sphere");
        // TC03: Ray starts inside the sphere (1 point)
        result = sphere2.findIntersections(new Ray(new Point(0,0,0.5),new Vector(0,1,-0.5)));
        assertEquals(1,result.size(),"Wrong number of points");
        assertEquals(new Point(0,1,0),result.get(0),"Ray starts inside the sphere");

        // TC04: Ray starts after the sphere (0 points)
        assertNull(sphere2.findIntersections(new Ray(new Point(0,2,0),new Vector(0,2,-0.5))),


                // =============== Boundary Values Tests ==================

        // **** Group: Ray's line crosses the sphere (but not the center)
        // TC11: Ray starts at sphere and goes inside (1 points)
        result = sphere2.findIntersections(new Ray(new Point(0,0,1),new Vector(0,1,-1)));
        assertEquals(1,result.size(),"Wrong number of points");
        assertEquals(new Point(0,1,0),result.get(0),"Ray starts at sphere and goes inside");

        // TC12: Ray starts at sphere and goes outside (0 points)
        assertNull(sphere2.findIntersections(new Ray(new Point(0,0,-1),new Vector(0,1,-1))),"Ray starts at sphere and goes outside");

        // **** Group: Ray's line goes through the center
        // TC13: Ray starts before the sphere (2 points)
        result = sphere2.findIntersections(new Ray(new Point(0,0,-2),new Vector(0,0,1)));
        assertEquals(2, result.size(), "Wrong number of points");
        if (result.get(0).getX() > result.get(1).getX())
            result = List.of(result.get(1), result.get(0));
        assertEquals(List.of(new Point(0,0,-1), new Point(0,0,1)), result, "Ray starts before the sphere");

        // TC14: Ray starts at sphere and goes inside (1 points)
        result = sphere2.findIntersections(new Ray(new Point(0,0,-1),new Vector(0,0,1)));
        assertEquals(1,result.size(),"Wrong number of points");
        assertEquals(new Point(0,0,1),result.get(0),"Ray starts at sphere and goes inside");

        // TC15: Ray starts inside (1 points)
        result = sphere2.findIntersections(new Ray(new Point(0,0,0.5),new Vector(0,0,1)));
        assertEquals(1,result.size(),"Wrong number of points");
        assertEquals(new Point(0,0,1),result.get(0)," Ray starts inside");


        // TC16: Ray starts at the center (1 points)
        result = sphere2.findIntersections(new Ray(new Point(0,0,1),new Vector(0,0,1)));
        assertEquals(0,result.size(),"Wrong number of points");
        assertEquals(1,result.size(),"Wrong number of points");
        assertEquals(new Point(0,0,1),result.get(0),"Ray starts at sphere and goes inside");

        // TC17: Ray starts at sphere and goes outside (0 points)
        assertNull(sphere2.findIntersections(new Ray(new Point(0,0,0),new Vector(0,0,1))),
                "Ray starts at sphere and goes outside");
        // TC18: Ray starts after sphere (0 points)
        assertNull(sphere2.findIntersections(new Ray(new Point(0,0,-1),new Vector(0,0,1))),"Ray starts after sphere");
        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)
        // TC19: Ray starts before the tangent point
        assertNull(sphere2.findIntersections(new Ray(new Point(0,-1,1),new Vector(0,1,0))),"Ray starts before the tangent point");

        // TC20: Ray starts at the tangent point
        assertNull(sphere2.findIntersections(new Ray(new Point(0,0,1),new Vector(0,1,0))),"Ray starts at the tangent point");

        // TC21: Ray starts after the tangent point
        assertNull(sphere2.findIntersections(new Ray(new Point(0,1,1),new Vector(0,1,0))),"Ray starts after the tangent point");

        // **** Group: Special cases
        // TC19: Ray's line is outside, ray is orthogonal to ray start to sphere's center line
        assertNull(sphere2.findIntersections(new Ray(new Point(0,0,2),new Vector(0,1,0)))," Ray's line is outside, ray is orthogonal to ray start to sphere's center line");

    }
}