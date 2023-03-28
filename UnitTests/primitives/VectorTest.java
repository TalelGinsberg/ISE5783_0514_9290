package primitives;

import org.junit.jupiter.api.Test;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

class VectorTest {

    Vector v1 = new Vector(1, 2, 3);
    Vector v2 = new Vector(-2, -4, -6);
    Vector v3 = new Vector(0, 3, -2);



    @Test
    void testToString() {
    }

    @Test
    void add() {
        assertThrows(IllegalArgumentException.class,
                ()->v1.add(new Vector(-1, -2, -3)),
                "ERROR: Vector + -itself does not throw the right exception");
        Vector result = new Vector(-1, -2, -3);
        assertEquals(result, v1.add(v2), "ERROR: Vector - Vector does not work correctly - add");


    }

    @Test
    void scale() {
    }

    @Test
    void dotProduct() {

        assertTrue(isZero(v1.dotProduct(v3)),"ERROR: dotProduct() for orthogonal vectors is not zero" );

        assertEquals(-28, v1.dotProduct(v2), 0.00001,  "ERROR: dotProduct() wrong value" );

    }

    @Test
    void crossProduct() {

        assertThrows(Exception.class,()->v1.crossProduct(v2),"ERROR: crossProduct() for parallel vectors does not throw an exception");// test zero vector
        Vector vr = v1.crossProduct(v3);
        assertTrue(isZero(vr.length() - v1.length() * v3.length()),"ERROR: crossProduct() wrong result length");
        assertTrue(isZero(vr.dotProduct(v1))&&isZero(vr.dotProduct(v3)),"ERROR: crossProduct() result is not orthogonal to its operands");
    }

    @Test
    void length() {
        Vector example = new Vector(0, 3, 4);
        assertEquals(5,example.length(), 0.00001, "ERROR: length() wrong value");
    }

    @Test
    void lengthSquared() {
        assertEquals(14, v1.lengthSquared(), 0.00001, "ERROR: lengthSquared() wrong value");
    }

    @Test
    void normalize() {
        Vector normalized = v1.normalize();
        assertEquals(1,normalized.length(),0.00001,"ERROR: the normalized vector is not a unit vector");
        assertThrows(Exception.class,// test that the vectors are co-lined
                ()->v1.crossProduct(normalized),
                "ERROR: the normalized vector is not parallel to the original one"
                );
       assertTrue(v1.dotProduct(normalized) >= 0,"ERROR: the normalized vector is opposite to the original one");
    }
}