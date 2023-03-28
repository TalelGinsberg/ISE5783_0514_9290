package primitives;


/*** class that represents Vector
 * @author Noa Harel and Talel Ginsberg */
public class Vector extends Point{

    /**
     * parameters constructor
     * @param x
     * @param y
     * @param z
     *
     * @throws IllegalArgumentException if get the zero vector
     */
    public Vector(double x, double y, double z) {
        super(x,y,z);
        if (xyz.equals(Double3.ZERO)) /*exception for the zero vector*/
            throw new IllegalArgumentException("Doesn't support zero vector");
    }

    /**
     * parameters constructor
     * @param sentXyz
     *
     * @throws IllegalArgumentException if get the zero vector
     */
    Vector(Double3 sentXyz) {
        super(sentXyz);
        if (xyz.equals(Double3.ZERO))
            throw new IllegalArgumentException("Doesn't support zero vector");
    }

    @Override
    public String toString() {
        return "Vector{" +
                "xyz=" + xyz +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }


    /**
     * add function - add two vectors
     * @param vector
     * @return      new vector
     */
    public Vector add(Vector vector) {
        return new Vector(vector.xyz.add(this.xyz));
    }

    /**
     * scale function - multiple vector in number
     * @param i  the number
     * @return      the result
     */
    public Vector scale(double i){
        return new Vector(xyz.scale(i));

    }

    /**
     * scalar multiplication
     * @param u  vector
     * @return  the result
     */
    public double dotProduct(Vector u) {
        double result = xyz.d1*u.xyz.d1+ xyz.d2*u.xyz.d2+ xyz.d3*u.xyz.d3;
        if (result == -0.0)
            return 0;
        return result;
    }

    /**
     * Vector multiplication
     * @param u vector
     * @return     the result
     */
    public Vector crossProduct(Vector u) {
        return new Vector(xyz.d2*u.xyz.d3-xyz.d3*u.xyz.d2,xyz.d3*u.xyz.d1-xyz.d1*u.xyz.d3,xyz.d1*u.xyz.d2-xyz.d2*u.xyz.d1);
    }

    /**
     * A function that calculates the length of the vector
     * @return      the result
     */
    public double length() {
        return Math.sqrt(lengthSquared());

    }


    /**
     * A function that calculates the squared length of the vector
     * @return      the result
     */
    public double lengthSquared() {
        return xyz.d1 * xyz.d1 + xyz.d2 * xyz.d2 + xyz.d3 * xyz.d3;

    }


    /**
     * Normalize the vector - turning the vector into the unit vector
     * @return      the unit vector
     */
    public Vector normalize() {
        return scale(1/length());

    }
}
