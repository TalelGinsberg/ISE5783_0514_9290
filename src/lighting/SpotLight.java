package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

import static java.lang.Math.max;

public class SpotLight extends PointLight{

    public SpotLight(Color intensity, Point position, Vector direction) {
        super(intensity, position);
        this.direction = direction;
    }

    private Vector direction;

    @Override
    public Color getIntensity(Point p) {
        return super.getIntensity(p).scale(max(0,direction.dotProduct(getL(p))));
    }
}
