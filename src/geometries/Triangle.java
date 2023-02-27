package geometries;

import primitives.Point;

public class Triangle extends Polygon{

    /**
     *
     * @param x
     * @param y
     * @param z
     */
    public Triangle(Point x,Point y, Point z) {
        super(x,y,z);
    }
    @Override
    public String toString() {
        return "Triangle{" +
                "vertices=" + vertices +
                ", plane=" + plane +
                '}';
    }


}
