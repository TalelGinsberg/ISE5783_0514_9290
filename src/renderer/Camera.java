package renderer;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;


/**
 * class that represents a camera
 *
 * @author Noa Harel and Talel Ginsberg
 */
public class Camera {


    //----------------------------fields--------------------------

    /**
     * place of the camera
     */
    private Point place;

    /**
     * vector of direction up
     */
    private Vector vUp;

    /**
     * vector of direction front
     */
    private Vector vTo;

    /**
     * vector of direction right
     */
    private Vector vRight;

    /**
     * double that represents the height of the view plane
     */
    private double height;

    /**
     * double that represents the width of the view plane
     */
    private double width;

    /**
     * double that represents the distance between camera and view plane
     */
    private double distance;


    //-----------------------------constructor-------------------------

    /**
     * parameters constructor for camera - gets place vector up and vector front, create vector
     * right and check if the vectors are orthogonal
     *
     * @param place place of the camera
     * @param vUp up direction vector of the camera
     * @param vTo front direction vector of the camera
     */
    public Camera(Point place, Vector vUp, Vector vTo) {
        // initialize place, vUp and vTo
        this.place = place;
        this.vUp = vUp.normalize();
        this.vTo = vTo.normalize();

        // the vectors are orthogonal
        if(isZero(alignZero(vTo.dotProduct(vUp)))){
            // create vRight - cross product between vUp and vTo
            vRight = vTo.crossProduct(vUp).normalize();
        }

        // the vectors aren't orthogonal
        else throw new IllegalArgumentException("the vectors are not orthogonal");
    }


    //------------------------------functions---------------------------

    /**
     * creates a ray starting from camera through the center of wanted pixel on view plane
     *
     * @param nX width of row of view plane for camera
     * @param nY height of column of view plane for camera
     * @param j row  of pixel
     * @param i column of pixel
     * @return construct ray from camera through center of wanted pixel
     */
    public Ray constructRay(int nX, int nY, int j, int i){

        // image center
        Point Pc = place.add(vTo.scale(distance));

        // ratio (pixel width & height)
        double Ry = height/nY;
        double Rx = width/nX;

        // pixel[i,j] center
        double yI = -(i-(nY-1)/2.0)*Ry;
        double xJ = -(j-(nX-1)/2.0)*Rx;

        // handle zero vector, because scale can not handle the zero vector so we split it up
        Point pIJ=Pc;
        if (xJ != 0)
            pIJ = pIJ.add(vRight.scale(xJ));
        if (yI != 0)
            pIJ = pIJ.add(vUp.scale(yI));

        // direction vector for ray
        Vector Vij = pIJ.subtract(place);

        //change the direction of the vector, sine the directions were switched from the slides in class
        Vij=new Vector(Vij.getX(),Vij.getZ(),Vij.getY());

        return new Ray(place,Vij);
    }

    //--------------------------------getters----------------------------

    /**
     * getter for place of the camera
     *
     * @return the place of the camera
     */
    public Point getPlace() {
        return place;
    }


    /**
     * getter for vector direction up of camera\
     *
     * @return vector that represents direction up
     */
    public Vector getvUp() {
        return vUp;
    }

    /**
     * getter function for direction front of the camera
     *
     * @return vector that represents direction front
     */
    public Vector getvTo() {
        return vTo;
    }

    /**
     * getter function for right direction vector of the camera
     *
     * @return vector that represents direction right
     */
    public Vector getvRight() {
        return vRight;
    }

    /**
     * getter function for height of the view plane
     *
     * @return double that represents height of the view plane
     */
    public double getHeight() {
        return height;
    }

    /**
     * getter function of width of the view plane
     *
     * @return double that represents width of view plane
     */
    public double getWidth() {
        return width;
    }

    /**
     * getter function for distance between camera and view plane
     *
     * @return double that represents the distance between camera and view plane
     */
    public double getDistance() {
        return distance;
    }

    //-------------------------------setters--------------------------------

    /**
     * setter for the size of the view plane that gets width and
     * height and return the camera itself
     *
     * @param width width of the view plane
     * @param height height of the view plane
     * @return the camera itself
     */
    public Camera setVPSize(double width, double height) {
        this.height = height;
        this.width = width;
        return this;
    }

    /**
     * update function for distance between camera and view plane
     *
     * @param distance new distance between camera and view plane
     * @return  the camera itself
     */
    public Camera setVPDistance(double distance){
        this.distance = distance;
        return this;
    }
}
