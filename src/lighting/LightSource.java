package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

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

}
