package geometries;
import primitives.Vector;
import primitives.Point;

/*** interface that will serve all geometries
 * @author Noa Harel and Talel Ginsberg */
public interface Geometry {

    /**
     * getter for normal
     * @param p
     * @return      normal
     */
    public Vector getNormal(Point p);
}
