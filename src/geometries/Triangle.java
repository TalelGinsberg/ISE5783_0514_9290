package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

import java.util.List;

/**
 * class that represents Triangle
 *
 * @author Noa Harel and Talel Ginsberg
 */
public class Triangle extends Polygon{

    //-----------------------------constructor-------------------------

    /**
     * parameters constructor
     *
     * @param x first sent point for triangle
     * @param y second sent point for triangle
     * @param z third sent point for triangle
     */
    public Triangle(Point x,Point y, Point z) {
        super(x,y,z);
    }

    //---------------------------override functions-------------------------
    @Override
    public String toString() {
        return "Triangle{" +
                "vertices=" + vertices +
                ", plane=" + plane +
                '}';
    }

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        List<Point> points = this.plane.findIntersections(ray);
        // if there is no point intersected in the plane
        if (points == null)
            return null;
        // if there is a point - assign it to Point P
        Point P = points.get(0);

        // now we check if the point is in the triangle
        // the equation P = wA + uB + vC while A,B,C is the vertices of the triangle
        // and w,u,v is the barycentric coordinates that use to define
        // the position of a point P on the plane of the triangle formed by the vertices of the triangle
        // The point is within the triangle if 0≤u,v,w≤1 because in this project we don't want
        // the point to bo on edges or vertices of the triangle we need to check if 0<u,v,w<1 instead.

        Point A = this.vertices.get(0);
        Point B = this.vertices.get(1);
        Point C = this.vertices.get(2);

        // the magnitude of the cross product can be interpreted as the area of the parallelogram, divided by 2 - the triangle area
        double AreaTriangleABC = (((B.subtract(A)).crossProduct(C.subtract(A))).length())/2;

        // if the area of the triangle CAP is zero, it means u is zero - the point is on the side of the triangle
        // which is something we are not considering as an intersection point - so we'll return null
        double AreaTriangleCAP;
        try{
            AreaTriangleCAP = (((A.subtract(C)).crossProduct(P.subtract(C))).length())/2;
        }
        catch(IllegalArgumentException e) {
            return null;
        }

        // if the area of the triangle ABP is zero, it means u is zero - the point is on the side of the triangle
        // which is something we are not considering as an intersection point - so we'll return null
        double AreaTriangleABP;
        try{ AreaTriangleABP = (((B.subtract(A)).crossProduct(P.subtract(A))).length())/2;}
        catch(IllegalArgumentException e){
            return null;
        }

        // if the area of the triangle BCP is zero, it means u is zero - the point is on the side of the triangle
        // which is something we are not considering as an intersection point - so we'll return null
        double AreaTriangleBCP;
        try {
            AreaTriangleBCP = (((C.subtract(B)).crossProduct(P.subtract(B))).length())/2;
        }
        catch(IllegalArgumentException e){
            return null;
        }

        // after we calculated the areas we can calculate u,v,w
        double u = AreaTriangleCAP/AreaTriangleABC;
        double v = AreaTriangleABP/AreaTriangleABC;
        double w = AreaTriangleBCP/AreaTriangleABC;

        // now we check if P is actually inside the area of the triangle - if so, we'll return P
        if (Util.isZero(u+v+w-1))
            return List.of(new GeoPoint(this, P));
        else
            return null;
    }
}
