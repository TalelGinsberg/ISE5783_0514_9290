package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class PointLight extends Light implements LightSource {

    Point position;

    private double kC;
    private double kL;
    private double kQ;


    public PointLight(Color intensity, Point position) {
        super(intensity);
        this.position = position;
        kC=1d;
        kL=0d;
        kQ=0d;
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


    @Override
    public Color getIntensity(Point p) {

        double distance = position.distance(p);
        double distancedSquared= position.distanceSquared(p);
        return intensity.scale(1/(kC+kL*distance+kQ*distancedSquared));
    }


    @Override
    public Vector getL(Point p) {
        return p.subtract(position).normalize();
    }
}
