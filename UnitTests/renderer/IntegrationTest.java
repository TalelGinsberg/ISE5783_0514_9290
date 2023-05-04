package renderer;


import static org.junit.jupiter.api.Assertions.*;

import geometries.Intersectable;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

public class IntegrationTest {


    /**
     * the function sums the numbers of intersections the camera has with the objects in every pixel in the view plane.
     * @param objects: the geometries we need calculate intersections with
     * @param cam: the camera
     * @param nX: number of columns(width of rows)-resolution
     * @param nY: number of rows(height of columns)-resolution
     * @return the amount of intersections
     */
    public int sumIntersections(Intersectable objects, Camera cam, int nX, int nY)
    {
        int sum = 0;
        for (int i=0;i<nX;i++)
        {
            for (int j=0; j<nY;j++)
            {
                List<Point> intsersections = objects.findIntersections(cam.constructRay(nX,nY,j,i));
                if (intsersections != null)
                    sum += intsersections.size();
            }
        }
        return sum;
    }
    @Test
    void testConstructRayTroughPixelSphere(){

        // TC01 - 2 intersection points
        Sphere sphere = new Sphere(new Point(0,0,-3), 1);
        Camera camera = new Camera(new Point(0,0,0), new Vector(0,0,1), new Vector(0,-1,0)).setVPDistance(1).setVPSize(3,3);
        assertEquals(2,sumIntersections(sphere,camera,3,3),"first test case sphere - 2 intersection points");

        // TC02 - 18 intersection points
        sphere = new Sphere(new Point(0,0,-2.5), 2.5);
        camera = new Camera(new Point(0,0,0.5), new Vector(0,0,1), new Vector(0,-1,0)).setVPSize(3,3).setVPDistance(1);

        assertEquals(18,sumIntersections(sphere,camera,3,3),"second test case sphere - 18 intersection points");


        // TC03 - 10 intersection points
        sphere = new Sphere(new Point(0,0,-2), 2);
        camera = new Camera(new Point(0,0,0.5), new Vector(0,0,1), new Vector(0,-1,0)).setVPSize(3,3).setVPDistance(1);
        assertEquals(10,sumIntersections(sphere,camera,3,3),"third test case sphere - 10 intersection points");

        // TC04 - 9 intersection points-needs to be fixed
        sphere = new Sphere(new Point(0,0,0), 4);
        camera = new Camera(new Point(0,0,0), new Vector(0,0,1), new Vector(0,-1,0)).setVPSize(3,3).setVPDistance(1);
        assertEquals(9,sumIntersections(sphere,camera,3,3),"forth test case sphere - 9 intersection points");

        // TC05 - 0 intersection points
        sphere = new Sphere(new Point(0,0,1), 0.5);
        camera = new Camera(new Point(0,0,0), new Vector(0,0,1), new Vector(0,-1,0)).setVPSize(3,3).setVPDistance(1);
        assertEquals(0,sumIntersections(sphere,camera,3,3),"fifth test case sphere - 0 intersection points");


    }



    @Test
    void testConstructRayTroughPixelTriangle(){

        // TC01 - 1 intersection point
        Triangle triangle = new Triangle(new Point(0,1,-2), new Point(1,-1,-2), new Point(-1,-1,-2));
        Camera camera = new Camera(new Point(0,0,0), new Vector(0,0,1), new Vector(0,-1,0)).setVPSize(3,3).setVPDistance(1);
        assertEquals(1,sumIntersections(triangle,camera,3,3),"first test case triangle - 1 intersection point");

        // TC02 - 2 intersection points
        triangle = new Triangle(new Point(0,20,-2), new Point(1,-1,-2), new Point(-1,-1,-2));
        camera = new Camera(new Point(0,0,0), new Vector(0,0,1), new Vector(0,-1,0)).setVPSize(3,3).setVPDistance(1);
        assertEquals(2,sumIntersections(triangle,camera,3,3),"second test case triangle - 2 intersection point");

    }



    @Test
    void testConstructRayTroughPixelPlane(){

        // TC01 - 9 intersection points, plane orthogonal to view plane
        Plane plane = new Plane(new Point(0,1,-2),new Vector(0,0,1));
        Camera camera = new Camera(new Point(0,0,0), new Vector(0,0,1), new Vector(0,-1,0)).setVPSize(3,3).setVPDistance(1);
        assertEquals(9,sumIntersections(plane,camera,3,3),"first test case plane - 9 intersection points");


        // TC02 - 9 intersection points
        plane = new Plane(new Point(0,1,-2),new Vector(0,-1,3));
        camera = new Camera(new Point(0,0,0), new Vector(0,0,1), new Vector(0,-1,0)).setVPSize(3,3).setVPDistance(1);
        assertEquals(9,sumIntersections(plane,camera,3,3),"second test case plane - 9 intersection points");

        // TC03 - 6 intersection points
        plane = new Plane(new Point(0,-2,0),new Vector(0,-1,-0.5));
        camera = new Camera(new Point(0,0,0), new Vector(0,0,1), new Vector(0,-1,0)).setVPSize(3,3).setVPDistance(1);
        assertEquals(6,sumIntersections(plane,camera,3,3),"third test case plane - 6 intersection points");



    }

}