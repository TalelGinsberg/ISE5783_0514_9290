package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

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
}