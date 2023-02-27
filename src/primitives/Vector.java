package primitives;


/*** class that represents Vector
 * @author Noa Harel and Talel Ginsberg */
public class Vector extends Point{

    /**
     * parameters constructor
     * @param x
     * @param y
     * @param z
     */
    public Vector(double x, double y, double z) {
        super(x,y,z);
        if (xyz.equals(Double3.ZERO)) /*exception for the zero vector*/
            throw new IllegalArgumentException("Doesn't support zero vector");
    }

    /**
     * parameters constructor
     * @param sentXyz
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
        return xyz.d1*u.xyz.d1+ xyz.d2*u.xyz.d2+ xyz.d3*u.xyz.d3;
    }

    /**
     *
     * @param u
     * @return
     */
    public Vector crossProduct(Vector u) {
        return new Vector(xyz.d2*u.xyz.d3-xyz.d3*u.xyz.d2,xyz.d3*u.xyz.d1-xyz.d1*u.xyz.d3,xyz.d1*u.xyz.d2-xyz.d2*u.xyz.d1);
    }

    public double length() {
        return Math.sqrt(lengthSquared());

    }

    public Vector normalize() {
        return scale(1/length());

    }



    public double lengthSquared() {
        return xyz.d1 * xyz.d1 + xyz.d2 * xyz.d2 + xyz.d3 * xyz.d3;

    }
}
