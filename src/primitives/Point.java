package primitives;
import java.lang.Math;
import java.util.Objects;

/**
 * This class will serve all primitive classes based on three numbers, making a Point
 * @author Noa Harel and Talel Ginsberg */
public class Point {

    /** Numbers representing the point */
    Double3 xyz;

    /**
     *  Constructor to initialize Point based on three number values
     * @param x first sent double for point
     * @param y second sent double for point
     * @param z third sent double for point
     */
    public Point(double x, double y, double z) {
        xyz=new Double3(x,y,z);
    }


    /**
     * Constructor to initialize Point based on a Double3
     * @param xyz sent xyz for point
     */
    Point(Double3 xyz) {
        this.xyz = xyz;
    }

    @Override
    public String toString() {
        return "Point{" +
                "xyz=" + xyz +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return Objects.equals(xyz, point.xyz);
    }

    /**
     * Subtract two point
     * @param p1 sent point to subtract point from
     * @return a new vector of the subtracted point
     */
    public Vector subtract(Point p1) {
        return new Vector(xyz.subtract(p1.xyz));
    }

    /**
     * Adds a sent vector to current point creating a point
     * @param vector sent vector to add point to
     * @return a new Point of an addition of this vector and sent vector
     */
    public Point add(Vector vector) {
        return new Point(vector.xyz.add(this.xyz));
    }

    /**
     * distance between current point and sent point
     * @param p sent point to calculate distance to
     * @return distance between current point and sent point
     */
    public double distance(Point p){
        return Math.sqrt(p.distanceSquared(this));
    }
    /**
     * distance between current point and sent point, squared
     * @param p sent point to calculate squared distance to
     * @return distance between current point and sent point,squared
     */
    public double distanceSquared(Point p){
        return (p.xyz.d1-this.xyz.d1)*(p.xyz.d1-this.xyz.d1)+
                (p.xyz.d2-this.xyz.d2)*(p.xyz.d2-this.xyz.d2)+
                (p.xyz.d3-this.xyz.d3)*(p.xyz.d3-this.xyz.d3);
    }

    /**
     * getter for x of xyz
     * @return x
     */
    public double getX() {
        return xyz.d1;
    }
    /**
     * getter for y of xyz
     * @return y
     */
    public double getY() {
        return xyz.d2;
    }
    /**
     * getter for z of xyz
     * @return z
     */
    public double getZ() {
        return xyz.d3;
    }
}
