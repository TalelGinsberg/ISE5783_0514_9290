package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class TubeTest {

    Point p1 = new Point(0,1,0);
    Point p2 = new Point(1,0,2);
    @Test
    void getAxisRay() {
    }

    @Test
    void testToString() {
    }

    @Test
    void getNormal() {

        Tube tube = new Tube(new Ray(new Point(0,0,0),new Vector(0,0,1)),1);


        // ============ Equivalence Partitions Tests ==============

        assertDoesNotThrow(() -> tube.getNormal(p2), "");

        Vector result2 = tube.getNormal(p2);

        assertTrue(result2.equals(new Vector(1,0,0)),"Tube's normal is not the correct vector");



        // =============== Boundary Values Tests ==================

        assertDoesNotThrow(() -> tube.getNormal(p1), "");

        Vector result = tube.getNormal(p1);

        assertTrue(result.equals(new Vector(0,1,0)),"Tube's normal is not the correct vector");

    }
}