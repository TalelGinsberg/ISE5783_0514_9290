package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

import static java.lang.Math.max;

public class SpotLight extends PointLight{

    public SpotLight(Color intensity, Point position, Vector direction) {
        super(intensity, position);
        this.direction = direction.normalize();
    }

    private Vector direction;

    @Override
    public Color getIntensity(Point p) {
        return (super.getIntensity(p)).scale(Math.max(0,getL(p).dotProduct(direction)));

    }
}
