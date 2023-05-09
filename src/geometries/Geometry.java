package geometries;
import primitives.Color;
import primitives.Vector;
import primitives.Point;

/**
 * Interface that will serve all geometries.
 * This abstract class implements the Intersectable interface.
 * It provides a getter for the emission color and an abstract method for getting the normal at a given point.
 *
 * @author Noa Harel and Talel Ginsberg
 */
public abstract class Geometry extends Intersectable {

    /**
     * The color emitted by this geometry.
     */
    protected Color emission = Color.BLACK;

    //--------------------------------getters----------------------------


    /**
     * Getter for the emission color.
     *
     * @return The emission color.
     */
    public Color getEmission() {
        return emission;
    }

    /**
     * Abstract method for getting the normal vector at a given point on this geometry.
     *
     * @param p The point at which to get the normal vector.
     * @return The normal vector at the given point.
     */
    public abstract Vector getNormal(Point p);


    //--------------------------------setters----------------------------

    /**
     * Sets the emission color of this geometry to the specified color and returns this geometry.
     *
     * @param emission The new emission color for this geometry.
     * @return This geometry object.
     */
    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }
}

