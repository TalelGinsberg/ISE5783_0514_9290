package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * This class represents a point light source in a scene.
 * A point light emits light uniformly in all directions from a specific position in space.
 *
 * @author Noa Harel and Talel Ginsberg
 */
public class PointLight extends Light implements LightSource {

    //----------------------------fields--------------------------
    /**
     * The position of the point light in 3D space
     */
    private final Point position;
    /**
     * The constant attenuation coefficient
     */
    private double kC = 1d;
    /**
     * The linear attenuation coefficient
     */
    private double kL = 0d;
    /**
     * The quadratic attenuation coefficient
     */
    private double kQ = 0d;

    //-----------------------------constructor-------------------------

    /**
     * Constructs a point light with the given intensity and position.
     *
     * @param intensity The intensity of the point light
     * @param position  The position of the point light in 3D space
     */
    public PointLight(Color intensity, Point position) {
        super(intensity);
        this.position = position;
    }

    //---------------------------override functions-------------------------
    @Override
    public Color getIntensity(Point p) {
        double d = position.distance(p);
        return intensity.reduce(kC + kL * d + kQ * d * d );
    }

    @Override
    public Vector getL(Point p) {
        return p.subtract(position).normalize();
    }

    @Override
    public double getDistance(Point point) {
        return position.distance(point);
    }

    //--------------------------------getters----------------------------

    /**
     * Sets the constant attenuation coefficient of the point light.
     *
     * @param kC The constant attenuation coefficient
     * @return The updated PointLight object
     */
    public PointLight setKc(double kC) {
        this.kC = kC;
        return this;
    }


    /**
     * Sets the linear attenuation coefficient of the point light.
     *
     * @param kL The linear attenuation coefficient
     * @return The updated PointLight object
     */
    public PointLight setKl(double kL) {
        this.kL = kL;
        return this;
    }


    /**
     * Sets the quadratic attenuation coefficient of the point light.
     *
     * @param kQ The quadratic attenuation coefficient
     * @return The updated PointLight object
     */
    public PointLight setKq(double kQ) {
        this.kQ = kQ;
        return this;
    }
}
