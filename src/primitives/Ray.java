package primitives;

import java.util.Objects;

/**
 * This class will serve all primitive classes based on a point and vector
 * @author Noa Harel and Talel Ginsberg */
public class Ray {


    /* point*/
    private Point p0;
    /* vector*/
    private Vector drr;

    /**
     *  Constructor to initialize Point based on a point and vector
     * @param sentP0 point for ray
     * @param sentDrr  vector for ray
     */
    public Ray(Point sentP0, Vector sentDrr) {
        this.p0 = sentP0;
        this.drr = sentDrr.normalize();
    }

    /**
     * getter for the po
     * @return point of vector
     */
    public Point getP0() {
        return p0;
    }

    /**
     * getter for drr
     * @return vector of ray
     */
    public Vector getDir() {
        return drr;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ray ray = (Ray) o;
        return Objects.equals(p0, ray.p0) && Objects.equals(drr, ray.drr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(p0, drr);
    }

    @Override
    public String toString() {
        return "Ray{" +
                "p0=" + p0 +
                ", drr=" + drr +
                '}';
    }

    public Point getPoint(double t){
        return p0.add(drr.scale(t));
    }
}
