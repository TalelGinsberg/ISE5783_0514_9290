package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class SphereTest {

    Point p1 = new Point(0,1,0);
    @Test
    void getCenter() {
    }

    @Test
    void getRadius() {
    }

    @Test
    void testToString() {
    }


    @Test
    void getNormal() {


        // ============ Equivalence Partitions Tests ==============

        // we crated a sphere
        Sphere sphere = new Sphere(new Point(0,0,0), 1);
        // check that there are no exceptions while creating normal for plane
        assertDoesNotThrow(() -> sphere.getNormal(p1), "");
        //calculate normal
        Vector result=sphere.getNormal(p1);
        //we created the expected result
        Vector vector = new Vector(0,1,0);
        //we made sure that the result is equal to the one we excpected
        assertTrue(vector.equals(result), "Sphere's normal is not the correct vector");
    }
}