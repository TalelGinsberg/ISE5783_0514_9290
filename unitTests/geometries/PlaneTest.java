package geometries;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing Planes
 *
 * @author Noa Harel and Talel Ginsberg
 */
class PlaneTest {

    /**
     * constructing point for the next tests
     */
    Point p1 = new Point(0, 0, 0);
    Point p2 = new Point(0, 1, 0);
    Point p3 = new Point(1, 0, 0);

    /**
     * Test method for {@link Plane#Plane(Point, Point, Point)}.
     */
    @Test
    public void testConstructor() {
        // ============ Equivalence Partitions Tests ==============
        //TC01: Constructing a plane using three dots and receiving the expected planes
        try {
            new Plane(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0));
        } catch (IllegalArgumentException e) {
            fail("Failed constructing a correct plane");
        }

        // =============== Boundary Values Tests ==================
        //TC01: constructing a plane when two of the dots are the same
        assertThrows(IllegalArgumentException.class, //
                () -> new Plane(new Point(0, 0, 1), new Point(0, 0, 1), new Point(1, 0, 0)),
                "Constructed a plane with two points that are the same");

        //TC02: constructing a plane when all three dots are on the same ray
        assertThrows(IllegalArgumentException.class, //
                () -> new Plane(new Point(0, 0, 1), new Point(0, 0, 2), new Point(0, 0, 3)),
                "Constructed a plane with 3 points that are on the same ray");
    }

    /**
     * Test method for {@link Plane#getNormal()}.
     */
    @Test
    void getNormal() {
        // ============ Equivalence Partitions Tests ==============
        //TC01: Constructing a plane using three dots and receiving the expected normal
        // We created a plane
        Plane plane = new Plane(p1, p2, p3);
        // Check that there are no exceptions while creating normal for plane
        assertDoesNotThrow(() -> plane.getNormal(p1), "");
        // Calculate normal
        Vector result = plane.getNormal(p1);
        // We made sure that the length of the normal is 1
        assertEquals(1, result.length(), 0.000001, "Plane's normal is not a unit vector");
        // We created 3 vectors in planes
        Vector v1 = new Vector(0, 1, 0);
        Vector v2 = new Vector(1, 0, 0);
        Vector v3 = new Vector(-1, 1, 0);
        // We made sure that the normal is really orthogonal to all three vectors in plane
        assertEquals(0, v1.dotProduct(result), "Plane's normal is not orthogonal to one of the edges");
        assertEquals(0, v3.dotProduct(result), "Plane's normal is not orthogonal to one of the edges");
        assertEquals(0, v2.dotProduct(result), "Plane's normal is not orthogonal to one of the edges");

    }


    /**
     * Test method for {@link Plane#findIntersections(Ray)}.
     */
    @Test
    void findIntersections() {
        Plane plane = new Plane(new Point(1, 1, 0), new Vector(0, 0, 1));
        Ray ray1 = new Ray(new Point(1, 1, 0), new Vector(0, 1, 1));
        Ray ray2 = new Ray(new Point(0, 0, -1), new Vector(0, 1, 1));
        Ray ray3 = new Ray(new Point(0, 0, 1), new Vector(0, 1, 1));
        Ray ray4 = new Ray(new Point(0, -1, 0), new Vector(0, 1, 0));
        Ray ray5 = new Ray(new Point(0, 0, 1), new Vector(0, 1, 0));
        Ray ray6 = new Ray(new Point(0, 1, 0), new Vector(0, 0, 1));
        Ray ray7 = new Ray(new Point(0, 0, 3), new Vector(0, 0, 1));
        Ray ray8 = new Ray(new Point(1, 1, -2), new Vector(0, 0, 1));
        Ray ray9 = new Ray(new Point(2, 2, 0), new Vector(0, 1, 1));


        // ============ Equivalence Partitions Tests ==============
        // TC01: The ray cuts the plane at a sharp angle
        List<Point> result1 = plane.findIntersections(ray2);
        assertEquals(1, result1.size(), "Wrong number of points");
        assertEquals(new Point(0, 1, 0), result1.get(0), "ray cuts the plane at a sharp angle");

        // TC02: The ray starts above the plane so there are no cutting points
        List<Point> result2 = plane.findIntersections(ray3);
        assertNull(result2, "Ray starts above the plane");


        // =============== Boundary Values Tests ==================
        // **** Group: A ray parallel to a plane
        // TC03: The ray is inside the plane - throw an exception
        //assertThrows(IllegalArgumentException.class, //
        //        () -> plane.findIntersections(ray4), //
        //        "Calculate a ray contained in a plane as a normal case");
        // if suppose to return NULL -
        assertNull(plane.findIntersections(ray4), "Ray parallel to the plane and inside");

        // TC04: The ray is not inside the plane - no cutting points
        assertNull(plane.findIntersections(ray5), "Ray parallel to the plane and not inside");

        // **** Group: A ray is perpendicular to the plane
        // TC05: The ray starts in the plane
        assertNull(plane.findIntersections(ray6), "Ray orthogonal to the plane and starts inside");
        // TC06: The ray starts above the plane
        assertNull(plane.findIntersections(ray7), "Ray orthogonal to the plane and starts above");
        // TC07: The ray starts under the plane
        List<Point> result7 = plane.findIntersections(ray8);
        assertEquals(1, result1.size(), "Wrong number of points");
        assertEquals(new Point(1, 1, 0), result7.get(0), "Ray orthogonal to the plane and starts under");


        // TC08: A ray that is neither parallel nor perpendicular to the plane but starts inside the plane
        assertNull(plane.findIntersections(ray9), "ray make a sharp angle and starts inside the plane");


        // TC09: A ray that is neither parallel nor perpendicular to the plane but starts inside
        //       the plane when the ray starts exactly at the reference point of the plane
        assertNull(plane.findIntersections(ray1), "ray make a sharp angle and starts on the reference point of the plane");

    }

}