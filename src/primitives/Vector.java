package primitives;


/**
 * class that represents Vector
 *
 * @author Noa Harel and Talel Ginsberg
 */
public class Vector extends Point {

    //-----------------------------constructor-------------------------

    /**
     * parameters constructor
     *
     * @param x sent first double
     * @param y sent first double
     * @param z sent first double
     */
    public Vector(double x, double y, double z) {
        this(new Double3(x, y, z));
    }

    /**
     * parameters constructor
     *
     * @param sentXyz for vector
     * @throws IllegalArgumentException get the zero vector - send an exception
     */
    Vector(Double3 sentXyz) {
        super(sentXyz);
        if (xyz.equals(Double3.ZERO))
            throw new IllegalArgumentException("Doesn't support zero vector");
    }


    //------------------------------functions---------------------------

    /**
     * add function - add two vectors
     *
     * @param vector the other vector
     * @return new vector
     */
    public Vector add(Vector vector) {
        return new Vector(vector.xyz.add(this.xyz));
    }

    /**
     * scalar multiplication
     *
     * @param u vector
     * @return the result
     */
    public double dotProduct(Vector u) {
        double result = xyz.d1 * u.xyz.d1 + xyz.d2 * u.xyz.d2 + xyz.d3 * u.xyz.d3;
        if (result == -0.0)
            return 0;
        return result;
    }

    /**
     * Vector multiplication
     *
     * @param u vector
     * @return the result
     */
    public Vector crossProduct(Vector u) {
        if ((xyz.d1 / u.xyz.d1 == xyz.d2 / u.xyz.d2) && (xyz.d1 / u.xyz.d1 == xyz.d2 / u.xyz.d2))
            //return crossProduct(new Vector(u.xyz.d1+1,u.xyz.d2,u.xyz.d3));
            throw new IllegalArgumentException("for parallel vectors ");

        return new Vector(xyz.d2 * u.xyz.d3 - xyz.d3 * u.xyz.d2, xyz.d3 * u.xyz.d1 - xyz.d1 * u.xyz.d3, xyz.d1 * u.xyz.d2 - xyz.d2 * u.xyz.d1);

    }

    /**
     * A function that calculates the length of the vector
     *
     * @return the result
     */
    public double length() {
        return Math.sqrt(lengthSquared());

    }

    /**
     * A function that calculates the squared length of the vector
     *
     * @return the result
     */
    public double lengthSquared() {
        return xyz.d1 * xyz.d1 + xyz.d2 * xyz.d2 + xyz.d3 * xyz.d3;

    }


    /**
     * Normalize the vector - turning the vector into the unit vector
     *
     * @return the unit vector
     */
    public Vector normalize() {
        return new Vector(xyz.reduce(length()));

    }

    /**
     * scale function - multiple vector in number
     *
     * @param i the number
     * @return the result
     */
    public Vector scale(double i) {
        return new Vector(xyz.scale(i));
    }


    //---------------------------override functions-------------------------

    @Override
    public String toString() {
        return "Vector{" +
                "xyz=" + xyz +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector vector = (Vector) o;
        return xyz.equals(vector.xyz);
    }


}
