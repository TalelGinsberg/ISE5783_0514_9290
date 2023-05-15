package lighting;

import primitives.Color;


/**
 * The lighting package contains classes and interfaces related to lighting
 * calculations and light sources in a 3D scene.
 *
 * @author Noa Harel and Talel Ginsberg
 */
abstract class Light {

    //----------------------------fields--------------------------

    /**
     * The intensity (color and brightness) of the light source.
     */
    protected final Color intensity;


    //-----------------------------constructor-------------------------

    /**
     * Constructs a new Light object with the specified intensity.
     *
     * @param intensity The intensity of the light source.
     */
    protected Light(Color intensity) {
        this.intensity = intensity;

    }

    //--------------------------------getters----------------------------

    /**
     * Returns the intensity of the light source.
     *
     * @return The intensity of the light source.
     */
    public Color getIntensity() {
        return intensity;
    }



}
