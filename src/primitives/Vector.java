package primitives;

public class Vector extends Point{

    public Vector(double x, double y, double z) {
        super(x,y,z);
        if (xyz.equals(Double3.ZERO))
            throw new IllegalArgumentException("Doesn't support zero vector");
    }

    Vector(Double3 sentXyz) {
        super(sentXyz);
        if (xyz.equals(Double3.ZERO))
            throw new IllegalArgumentException("Doesn't support zero vector");


    }


    /**
     *
     * @param vector
     * @return
     */
    public Vector add(Vector vector) {
        return new Vector(vector.xyz.add(this.xyz));
    }

    /**
     *
     * @param i
     * @return
     */
    public Vector scale(double i){
        return new Vector(xyz.scale(i));

    }
    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }



    public double dotProduct(Vector u) {
        return xyz.d1*u.xyz.d1+ xyz.d2*u.xyz.d2+ xyz.d3*u.xyz.d3;
    }

    public Vector crossProduct(Vector u) {
        return new Vector(xyz.d2*u.xyz.d3-xyz.d3*u.xyz.d2,xyz.d3*u.xyz.d1-xyz.d1*u.xyz.d3,xyz.d1*u.xyz.d2-xyz.d2*u.xyz.d1);
    }

    public double length() {
        return Math.sqrt(lengthSquared());

    }

    public Vector normalize() {
        return scale(1/length());

    }

    @Override
    public String toString() {
        return "Vector{" +
                "xyz=" + xyz +
                '}';
    }

    public double lengthSquared() {
        return xyz.d1 * xyz.d1 + xyz.d2 * xyz.d2 + xyz.d3 * xyz.d3;

    }
}
