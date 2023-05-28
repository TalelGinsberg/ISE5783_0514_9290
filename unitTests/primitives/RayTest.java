package primitives;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Testing Ray
 *
 * @author Noa Harel and Talel Ginsberg
 */

class RayTest {

    /**
     * Test method for {@link RayTest#findClosestPoint()}
     */
    @Test
    void findClosestPoint() {
        // creating ray to check closest point for
        Ray ray=new Ray(new Point(1,0,0),new Vector(0,0,1));

        // ============ Equivalence Partitions Tests ==============
        //TC01- EP test, the closest point is in the middle of the list

        // list of points to check the closest one to the ray
        List<Point> pointList1 = List.of(new Point(1,0,8),new Point(1,0,2),new Point(1,0,4));

        // making sure that the received point from the function is as expected
        assertEquals(new Point(1,0,2),ray.findClosestPoint(pointList1),"EP test, the closest point is in the middle of the list");


        // =============== Boundary Values Tests ==================
        //TC02-empty list, is supposed to return null
        assertNull(ray.findClosestPoint(null),"empty list, is supposed to return null");


        //TC03- the closest point is in the beginning of the list

        // list of points to check the closest one to the ray
        List<Point> pointList3 = List.of(new Point(1,0,2),new Point(1,0,8),new Point(1,0,4));

        // making sure that the received point from the function is as expected
        assertEquals(new Point(1,0,2),ray.findClosestPoint(pointList1),"the closest point is in the beginning of the list");


        //TC04- the closest point is in the end of the list

        // list of points to check the closest one to the ray
        List<Point> pointList4 = List.of(new Point(1,0,8),new Point(1,0,4),new Point(1,0,2));

        // making sure that the received point from the function is as expected
        assertEquals(new Point(1,0,2),ray.findClosestPoint(pointList1),"the closest point is in the end of the list");
    }
}