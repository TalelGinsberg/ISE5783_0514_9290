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
     * @param x
     * @param y
     * @param z
     */
    public Point(double x, double y, double z) {
        xyz=new Double3(x,y,z);
    }


    /**
     * Constructor to initialize Point based on a Double3
     * @param xyz
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
     * @param p1
     * @return a new vector of the subtracted point
     */
    public Vector subtract(Point p1) {
        return new Vector(xyz.subtract(p1.xyz));
    }

    /**
     * Adds a sent vector to current point creating a point
     * @param vector
     * @return a new Point of an addition of this vector and sent vector
     */
    public Point add(Vector vector) {
        return new Point(vector.xyz.add(this.xyz));
    }

    /**
     * distance between current point and sent point
     * @param p
     * @return distance between current point and sent point
     */
    public double distance(Point p){
        return Math.sqrt(p.distanceSquared(this));
    }
    /**
     * distance between current point and sent point, squared
     * @param p
     * @return distance between current point and sent point,squared
     */
    public double distanceSquared(Point p){
        return (p.xyz.d1-this.xyz.d1)*(p.xyz.d1-this.xyz.d1)+
                (p.xyz.d2-this.xyz.d2)*(p.xyz.d2-this.xyz.d2)+
                (p.xyz.d3-this.xyz.d3)*(p.xyz.d3-this.xyz.d3);
    }
}
