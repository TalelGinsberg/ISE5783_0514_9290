package primitives;
import java.lang.Math;

import java.util.Objects;

/**
 *
 */
public class Point {
    Double3 xyz;

    /**
     *
     * @param x
     * @param y
     * @param z
     */
    public Point(double x, double y, double z) {
        xyz=new Double3(x,y,z);
    }


    /**
     *
     * @param xyz
     */
    Point(Double3 xyz) {
        this.xyz = xyz;
    }


    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Point{" +
                "xyz=" + xyz +
                '}';
    }





    /**
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return Objects.equals(xyz, point.xyz);
    }

    @Override
    public int hashCode() {
        return Objects.hash(xyz);
    }



    public Vector subtract(Point p1) {
        return new Vector(xyz.subtract(p1.xyz));
    }

    public Point add(Vector vector) {
        return new Point(vector.xyz.add(this.xyz));
    }

    public double distance(Point p){
        return Math.sqrt(p.distanceSquared(this));
    }

    public double distanceSquared(Point p){
        return (p.xyz.d1-this.xyz.d1)*(p.xyz.d1-this.xyz.d1)+
                (p.xyz.d2-this.xyz.d2)*(p.xyz.d2-this.xyz.d2)+
                (p.xyz.d3-this.xyz.d3)*(p.xyz.d3-this.xyz.d3);
    }
}
