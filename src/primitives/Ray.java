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
     * @param sentP0
     * @param sentDrr
     */
    public Ray(Point sentP0, Vector sentDrr) {
        this.p0 = sentP0;
        this.drr = sentDrr.normalize();
    }

    /**
     * getter for the po
     * @return
     */
    public Point getP0() {
        return p0;
    }

    /**
     * getter for drr
     * @return
     */
    public Vector getDrr() {
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
}
