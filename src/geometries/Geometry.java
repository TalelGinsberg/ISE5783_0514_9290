package geometries;
import primitives.Color;
import primitives.Material;
import primitives.Vector;
import primitives.Point;

/**
 * The Geometry class is an abstract class that serves as a base class for all geometries in a 3D scene. It implements the Intersectable interface, providing functionality for determining intersections between geometries and rays in the scene.
 *
 * The class contains the following methods:
 *
 * - getEmission(): A getter method for the emission color of the geometry.
 * - getNormal(Point p): An abstract method for getting the normal vector at a given point on the geometry.
 * - setEmission(Color emission): A method for setting the emission color of the geometry to a specified color and returning the geometry.
 *
 * This class is designed to be subclassed by other geometries that implement the getNormal method, which is specific to the geometry being modeled.
 *
 * This class is authored by Noa Harel and Talel Ginsberg.
 *
 * @author Noa Harel and Talel Ginsberg
 */
public abstract class Geometry extends Intersectable {

    /**
     * The color emitted by this geometry.
     */
    private Color emission = Color.BLACK;

    /**
     * This field represents the material properties of this Geometry object.
     * A Material object contains information about the material's properties,
     * such as its color and texture.
     */
    private Material material=new Material();

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
     * Returns the material of this geometry.
     *
     * @return the material of this geometry
     */
    public Material getMaterial() {
        return material;
    }

    //--------------------------------functions----------------------------


    /**
     * Abstract method for getting the normal vector at a given point on this geometry.
     *
     * @param p The point at which to get the normal vector.
     * @return The normal vector at the given point.
     */
    public abstract Vector getNormal(Point p);

    //--------------------------------setters----------------------------


    /**
     * Sets the material of this geometry.
     * @param material The material to set.
     * @return This geometry instance.
     */
    public Geometry setMaterial(Material material) {
        this.material = material;
        return this;
    }

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

