package primitives;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;
/*** Testing Vectors
 * @author Noa Harel and Talel Ginsberg*/
class VectorTest {

    Vector v1 = new Vector(1, 2, 3);
    Vector v2 = new Vector(-2, -4, -6);
    Vector v3 = new Vector(0, 3, -2);
    /**
     * test function for add function in vector
     */
    @Test
    void add() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: we made sure that an exception is thrown when needed
        assertThrows(IllegalArgumentException.class,
                ()->v1.add(new Vector(-1, -2, -3)),
                "ERROR: Vector + -itself does not throw the right exception");
        //TC02: Adding two vectors and receiving the expected vector
        // We created the expected result
        Vector result = new Vector(-1, -2, -3);
        // We made sure that the result is equal to the one we expected
        assertEquals(result, v1.add(v2), "ERROR: Vector - Vector does not work correctly - add");
    }

    /**
     * test function for dot product function in vector
     */
    @Test
    void dotProduct() {
        // =============== Boundary Values Tests ==================
        //TC01: dot product between orthogonal vectors
        // We made sure that the result is equal to the one we expected
        assertTrue(isZero(v1.dotProduct(v3)),"ERROR: dotProduct() for orthogonal vectors is not zero" );
        // ============ Equivalence Partitions Tests ==============
        //TC01: the dot product between two vectors is expected number
        // We made sure that the result is equal to the one we expected
        assertEquals(-28, v1.dotProduct(v2), 0.00001,  "ERROR: dotProduct() wrong value" );

    }

    /**
     * test function for cross product function in vector
     */
    @Test
    void crossProduct() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: we made sure that an exception is thrown when needed
        assertThrows(Exception.class,()->v1.crossProduct(v2),"ERROR: crossProduct() for parallel vectors does not throw an exception");// test zero vector
        // TC02: cross product between two vectors returns the expected vector
        // Calculate cross product
        Vector result = v1.crossProduct(v3);
        //|result| is 1
        assertTrue(isZero(result.length() - v1.length() * v3.length()),"ERROR: crossProduct() wrong result length");
        // We made sure that the result vector is orthogonal to both vectors
        assertTrue(isZero(result.dotProduct(v1))&&isZero(result.dotProduct(v3)),"ERROR: crossProduct() result is not orthogonal to its operands");
    }

    /**
     * test function for length function in vector
     */
    @Test
    void length() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: length of vector returns the expected number
        // We created a vector
        Vector v1 = new Vector(0, 3, 4);
        // We made sure that the length that we sent is equal to the expected result
        assertEquals(5,v1.length(), 0.00001, "ERROR: length() wrong value");
    }

    /**
     * test function for length squared function in vector
     */
    @Test
    void lengthSquared() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: length squared of vector returns the expected number
        // We made sure that the length squared that we sent is equal to the expected result
        assertEquals(14, v1.lengthSquared(), 0.00001, "ERROR: lengthSquared() wrong value");
    }

    /**
     * test function for normalize function in vector
     */
    @Test
    void normalize() {
        // ============ Equivalence Partitions Tests ==============
        //TC01: Constructing a vector and receiving the expected normal
        // We created a vector
        Vector normalized = v1.normalize();
        //|result| is 1
        assertEquals(1,normalized.length(),0.00001,"ERROR: the normalized vector is not a unit vector");
        // test that the vectors are co-lined
        assertThrows(Exception.class,
                ()->v1.crossProduct(normalized),
                "ERROR: the normalized vector is not parallel to the original one"
                );
        // test that the vector is not opposite to original vector
       assertTrue(v1.dotProduct(normalized) >= 0,"ERROR: the normalized vector is opposite to the original one");
    }
}