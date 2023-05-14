package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class PointLight extends Light implements LightSource {

    private Point position;

    public PointLight(Color intensity, Point position) {
        super(intensity);
        this.position = position;
        kC = 1;
        kL = 0;
        kQ = 0;
    }

    public PointLight setkC(double kC) {
        this.kC = kC;
        return this;
    }

    public PointLight setkL(double kL) {
        this.kL = kL;
        return this;
    }

    public PointLight setkQ(double kQ) {
        this.kQ = kQ;
        return this;
    }

    private double kC;
    private double kL;
    private double kQ;


    @Override
    public Color getIntensity(Point p) {
        double distance = p.distance(position);
        return intensity.scale(1/(kC+kL*distance+kQ*distance*distance));
    }


    @Override
    public Vector getL(Point p) {
        return position.subtract(p).normalize();
    }
}
