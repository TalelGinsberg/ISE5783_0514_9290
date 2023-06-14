package lighting;

import primitives.*;

import java.util.ArrayList;


/**
 * A directional light source is modeled as a light source at infinity, where all rays of light are parallel.
 * The light intensity is constant throughout the entire scene, and its direction is the same for all points.
 *
 * @author Noa Harel and Talel Ginsberg
 */
public class DirectionalLight extends Light implements LightSource {

    //----------------------------fields--------------------------

    /**
     * The normalized direction of the directional light.
     */
    private Vector direction;


    //-----------------------------constructor-------------------------

    /**
     * Constructs a new directional light with the specified color intensity and direction.
     *
     * @param intensity The color intensity of the light.
     * @param direction The normalized direction of the light.
     */
    public DirectionalLight(Color intensity, Vector direction) {
        super(intensity);
        this.direction = direction.normalize();
    }


    //---------------------------override functions-------------------------

    @Override
    public Color getIntensity(Point p) {
        return super.getIntensity();
    }


    @Override
    public Vector getL(Point p) {
        return direction;
    }


    @Override
    public double getDistance(Point point) {
        return Double.POSITIVE_INFINITY;
    }
    public ArrayList<Ray> softShadow(Point p, int rayNum, double d)
    {
        ArrayList<Ray> ans = new ArrayList<Ray>();
        double temp=Double.POSITIVE_INFINITY;
        Ray ray=new Ray(new Point(temp,temp,temp),this.getL(p).normalize());
        ans.add(0,ray);
        return ans;

    }
}
