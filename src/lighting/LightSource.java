package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;
import primitives.Ray;

import java.util.List;


/**
 * An interface representing a light source in a 3D scene.
 *
 * @author Noa Harel and Talel Ginsberg
 */
public interface LightSource {

    /**
     * Returns the intensity of the light at the specified point.
     *
     * @param p The point at which to evaluate the intensity of the light.
     * @return The intensity of the light at the specified point.
     */
    public Color getIntensity(Point p);

    /**
     * Returns the direction of the light at the specified point.
     *
     * @param p The point at which to evaluate the direction of the light.
     * @return The direction of the light at the specified point.
     */
    public Vector getL(Point p);

    /**
     * Return the distance between the light source and specified point.
     *
     * @param point The point at which to evaluate the distance  between the light.
     * @return the distance between the light source and specified point.
     */
    double getDistance(Point point);

    /**calculate rays from the light environment toward the point of geometry to calculate the soft shadow
     *
     * @param p the point on the geometry
     * @param rayNum number of ray
     * @param d the distance between the start of the rays
     * @return rays from the light to the point
     */
    public List<Ray> softShadow(Point p, int rayNum, double d);

}
