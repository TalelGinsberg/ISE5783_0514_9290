package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;
/**
 *  Testing Tubes
 *
 * @author Noa Harel and Talel Ginsberg
 * */
class TubeTest {

    /**
     * constructing points for next tests
     */
    Point p1 = new Point(0,1,0);
    Point p2 = new Point(1,0,2);


    /**
     * Test method for {@link Tube#getNormal(Point)}.
     */
    @Test
    void getNormal() {
        // We created a simple tube to check all the tests with
        Tube tube = new Tube(new Ray(new Point(0,0,0),new Vector(0,0,1)),1);
        // ============ Equivalence Partitions Tests ==============
        //TC01: using the tube we constructed receiving the expected normal
        // We made sure that the function does not throw a message
        assertDoesNotThrow(() -> tube.getNormal(p2), "");
        // Calculate normal
        Vector result2 = tube.getNormal(p2);
        // We made sure that the result is equal to the one we expected
        assertTrue(result2.equals(new Vector(1,0,0)),"Tube's normal is not the correct vector");

        // =============== Boundary Values Tests ==================
        //TC01: The connection of the point to center ray creates a vector that is in a 90 degrees angle from center ray
        // We made sure that the function does not throw a message
        assertDoesNotThrow(() -> tube.getNormal(p1), "");
        // Calculate normal
        Vector result = tube.getNormal(p1);
        // We made sure that the result is equal to the one we expected
        assertTrue(result.equals(new Vector(0,1,0)),"Tube's normal is not the correct vector");

    }
}