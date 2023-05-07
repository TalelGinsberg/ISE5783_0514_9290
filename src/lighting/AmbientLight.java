package lighting;

import primitives.Color;
import primitives.Double3;

public class AmbientLight {

    static public AmbientLight NONE = new AmbientLight(Color.BLACK, Double3.ZERO);
    private final Color intensity;


    public Color getIntensity() {
        return intensity;
    }


    public AmbientLight(Color ia, double ka) {
        intensity = ia.scale(ka);
    }

    public AmbientLight(Color ia, Double3 ka) {
        intensity = ia.scale(ka);
    }
}
