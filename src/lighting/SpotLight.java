package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

import static java.lang.Math.max;


/**
 * A type of light source that emits light in a specific direction from a
 * point source and attenuates with distance.
 *
 * @author Noa Harel and Talel Ginsberg
 */
public class SpotLight extends PointLight{

    //----------------------------fields--------------------------

    /**
     * The direction of the spotlight.
     */
    private Vector direction;


    //-----------------------------constructor-------------------------

    /**
     * Constructs a new SpotLight object with the specified intensity, position and direction.
     *
     * @param intensity The intensity of the spot light.
     * @param position The position of the spot light.
     * @param direction The direction of the spotlight.
     */
    public SpotLight(Color intensity, Point position, Vector direction) {
        super(intensity, position);
        this.direction = direction.normalize();
    }


    //---------------------------override functions-------------------------

    @Override
    public Color getIntensity(Point p) {
        return (super.getIntensity(p)).scale(Math.max(0,getL(p).dotProduct(direction)));

    }
}
