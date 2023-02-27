package primitives;

import java.util.Objects;

public class Ray {
    private Point p0;
    private Vector drr;


    /**
     *
     * @param sentP0
     * @param sentDrr
     */
    public Ray(Point sentP0, Vector sentDrr) {
        this.p0 = sentP0;
        this.drr = sentDrr.normalize();
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
