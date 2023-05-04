package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/***
 * Testing Geometries
 * @author Noa Harel and Talel Ginsberg
 */
class GeometriesTest {

    /**
     * Test method for {@link Geometries#findIntersections(Ray)}.
     */
    @Test
    void findIntersections() {

        // ============ Equivalence Partitions Tests ==============

        //TC01 Some of geometries intersect but not all

        //sphere that intersects ray with 1 point
        Sphere sphere4 = new Sphere(new Point(1,0,1), 1d);
        //triangle that intersects ray with 1 point
        Triangle triangle4 = new Triangle(new Point(1,-3,0),new Point(1,3,0),new Point(1,0,3));
        // plane is the x,y-axis that intersects with 1 point
        Plane plane = new Plane(new Point(1,0,0),new Point(0,1,0),new Point(1,1,0));
        Geometries g1 = new Geometries(sphere4,triangle4,plane);
        assertEquals(2,g1.findIntersections(new Ray(new Point(0,0,1),new Vector(1,0,0))).size(),"Some of geometries intersect but not all");


        // =============== Boundary Values Tests ==================
        Sphere sphere = new Sphere(new Point (1, 0, 0),1d);


        //TC02 LIST IS EMPTY
        Ray r = new Ray(new Point(1,1,1), new Vector(1,1,1));
        Geometries g2=new Geometries();
        assertNull(g2.findIntersections(r),"LIST IS EMPTY");


        //TC03 None of the geometries intersect
        // plane is the x,y-axis that intersects with 1 point

        Geometries g3 = new Geometries(sphere,plane);
        assertNull(g3.findIntersections(new Ray(new Point(0,0,1),new Vector(1,0,0))),"None of the geometries intersect");


        //TC04 - one of the geometries intersects

        ///Point is corner of Triangle(0 point) - triangle
        Triangle triangle=new Triangle(new Point(1,0,0),new Point(0,1,0),new Point(-1,-1,0));

        // create the collection
        Geometries g4 = new Geometries(sphere,triangle);
        // check the number of the intersection point
        assertEquals(1,g4.findIntersections(new Ray(new Point(1,0,0),new Vector(0,0,1))).size(),"Wrong number of points");


        //TC05 - all geometries intersect

        //plane that intersects with 1 point
        Plane plane5 = new Plane(new Point(2,-3,0),new Point(2,3,0),new Point(2,0,5));
        //sphere that intersects ray with 1 point
        //triangle that intersects ray with 1 point
        Geometries g5 = new Geometries(sphere4,triangle4,plane5);
        assertEquals(3,g5.findIntersections(new Ray(new Point(0,0,1),new Vector(1,0,0))).size(),"all geometries intersect");
}
}