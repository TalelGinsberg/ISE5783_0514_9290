package primitives;

import org.junit.jupiter.api.Test;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for primitives.Point class
 * @author  Noa Harel and Talel Ginsberg
 */

class PointTest {


    Vector v1 = new Vector(1, 2, 3);
    Vector v2 = new Vector(-2, -4, -6);
    Vector v3 = new Vector(0, 3, -2);
    Point p1 = new Point(1, 2, 3);



    /**
     * Test method for {
     * @link primitives.Point#add(primitives.Point)}
     */
    @Test
    void testToString() {
    }

    /**
     *
     */
    @Test
    void subtract() {


        assertThrows(IllegalArgumentException.class,
                ()->v1.subtract(v1),
                "ERROR: Vector - itself does not throw the right exception");

        Vector result = new Vector(3, 6, 9);
        assertEquals(result, v1.subtract(v2), "ERROR: Point - Point does not work correctly - sub");
        assertTrue(new Vector(1, 1, 1).equals(new Point(2, 3, 4).subtract(p1)),"ERROR: Point - Point does not work correctly - sub2");
    }

    @Test
    void add() {
       assertTrue((p1.add(new Vector(-1, -2, -3)).equals(new Point(0, 0, 0))),"ERROR: Point + Vector does not work correctly");
    }

    @Test
    void distance() {
    }

    @Test
    void distanceSquared() {
    }
}