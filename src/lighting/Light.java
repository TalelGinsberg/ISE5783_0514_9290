package lighting;

import primitives.Color;

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
