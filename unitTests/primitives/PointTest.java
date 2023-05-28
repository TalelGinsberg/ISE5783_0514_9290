package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Testing Points
 *
 * @author Noa Harel and Talel Ginsberg*/

class PointTest {

    Vector v1 = new Vector(1, 2, 3);
    Vector v2 = new Vector(-2, -4, -6);
    Vector v3 = new Vector(1, 1, 1);
    Vector v4=new Vector(-1, -2, -3);
    Point p0=new Point(0,0,0);
    Point p1 = new Point(1, 2, 3);
    Point p2 = new Point(2, 3, 4);

    /**
     * test method for {@link Point#subtract(Point)}
     */
    @Test
    void subtract() {

        // Check that there are no exceptions
        assertThrows(IllegalArgumentException.class,
                ()->v1.subtract(v1),
                "ERROR: Vector - itself does not throw the right exception");

        // ============ Equivalence Partitions Tests ==============

        //TC01: Subtracting two vectors and receiving the expected vector
        // We created the expected result
        Vector result = new Vector(3, 6, 9);

        // We made sure that the result is equal to the one we expected
        assertEquals(result, v1.subtract(v2), "ERROR: Point - Point does not work correctly - sub");

        //TC02: Subtracting two points and receiving the expected vector
        // We made sure that the result is equal to the one we expected
        assertTrue(v3.equals(p2.subtract(p1)),"ERROR: Point - Point does not work correctly - sub2");
    }


    /**
     * test method for {@link Point#add(Vector)}
     */
    @Test
    void add() {
        // ============ Equivalence Partitions Tests ==============

        //TC01: Adding a vector to point  and receiving the expected point
        // We made sure that the result is equal to the one we expected
        assertTrue((p1.add(v4).equals(p0)),"ERROR: Point + Vector does not work correctly");
    }



    /**
     * test method for {@link Point#distance(Point)}.
     */

    @Test
    void distance() {
        Point point1 = new Point(0,0,0);
        Point point2 = new Point(1,2,3);
        Point point3 = new Point(4,4,4);

        // =============== Boundary Values Tests ==================
        // TC01 - one of the points is zero
        assertEquals(Math.sqrt(14), point1.distance(point2),"first case distance points");

        // ============ Equivalence Partitions Tests ==============
        // TC02 - distance between two points
        assertEquals(Math.sqrt(14), point3.distance(point2), "second case distance points");
    }

}