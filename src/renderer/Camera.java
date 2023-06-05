package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.MissingResourceException;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;


/**
 * The Camera class represents a camera in a virtual 3D space. The camera has a place,
 * direction vectors up and front, and three more direction vectors derived from these: right,
 * left, and down. The camera also has a height and width that represent the size of the
 * image on the view plane, a distance that represents the distance between the camera and
 * the view plane, an ImageWriter that represents a class that writes the image to a file,
 * and a RayTracerBase that represents a ray tracer for the base.
 * <p>
 * The constructor takes the camera's place, direction vectors up and front, and creates the
 * right direction vector by taking the cross product of the up and front vectors. If the up
 * and front vectors are not orthogonal, an exception is thrown.
 * <p>
 * The constructRay method takes the pixel's row and column and the number of rows and columns in
 * the image and creates a ray from the camera that passes through the center of the given pixel.
 * <p>
 * The renderImage method checks that all the necessary fields are set and then iterates
 * over each pixel in the image, casts a ray for each pixel using constructRay, and sets the
 * color of the pixel based on the object it intersects with using the RayTracerBase. If a field is
 * missing, a MissingResourceException is thrown. If the renderImage method has not been implemented
 * yet, an UnsupportedOperationException is thrown.
 *
 * @author Noa Harel and Talel Ginsberg
 */
public class Camera {


    //----------------------------fields--------------------------

    /**
     * position coordinates of the camera in 3D space
     */
    private Point position;

    /**
     * position vector of direction up
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
    /**
     * ImageWriter that represents writing an image
     */
    private ImageWriter imageWriter;

    /**
     * RayTracerBase that represents tracer for ray base
     */
    private RayTracerBase rayTracer;


    //-----------------------------constructor-------------------------

    /**
     * parameters constructor for camera - gets place vector up and vector front, create vector
     * right and check if the vectors are orthogonal
     *
     * @param position place of the camera
     * @param vUp      up direction vector of the camera
     * @param vTo      front direction vector of the camera
     */
    public Camera(Point position, Vector vTo, Vector vUp) {
        // initialize place, vUp and vTo
        this.position = position;

        this.vUp = vUp.normalize();
        this.vTo = vTo.normalize();


        // the vectors are orthogonal
        if (isZero(alignZero(vTo.dotProduct(vUp)))) {
            // create vRight - cross product between vUp and vTo
            vRight = this.vTo.crossProduct(this.vUp);
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
     * @param j  row  of pixel
     * @param i  column of pixel
     * @return construct ray from camera through center of wanted pixel
     */
    public Ray constructRay(int nX, int nY, int j, int i) {

        // image center
        Point Pc = position.add(vTo.scale(distance));

        // ratio (pixel width & height)
        double Ry = height / nY;
        double Rx = width / nX;

        // pixel[i,j] center
        double yI = alignZero(-(i - (nY - 1) / 2d) * Ry);
        double xJ = alignZero((j - (nX - 1) / 2d) * Rx);


        // handle zero vector, because scale can not handle the zero vector so we split it up
        Point pIJ = Pc;

        if (!isZero(xJ))
            pIJ = pIJ.add(vRight.scale(xJ));

        if (!isZero(yI))
            pIJ = pIJ.add(vUp.scale(yI));


        // direction vector for ray
        Vector Vij = pIJ.subtract(position);


        return new Ray(position, Vij.normalize());
    }

    /**
     * Renders an image using the camera's settings.
     *
     * @throws MissingResourceException      if any of the required camera settings are missing
     * @throws UnsupportedOperationException if the method has not been implemented yet
     */
    public Camera renderImage() {
        try {

            //place has not been set
            if (position == null)
                throw new MissingResourceException("missing point place for camera", "Camera", "place");

            //vTo has not been set
            if (vTo == null)
                throw new MissingResourceException("missing vTo place for camera", "Camera", "vTo");

            //vUp has not been set
            if (vUp == null)
                throw new MissingResourceException("missing vUp place for camera", "Camera", "vUp");

            //vRight has not been set
            if (vRight == null)
                throw new MissingResourceException("missing vRight place for camera", "Camera", "vRight");

            //imageWriter has not been set
            if (imageWriter == null)
                throw new MissingResourceException("missing imageWriter place for camera", "Camera", "imageWriter");

            //rayTracerBase has not been set
            if (rayTracer == null)
                throw new MissingResourceException("missing RayTracerBase place for camera", "Camera", "rayTracerBase");

            //width has not been set
            if (width == 0.0)
                throw new MissingResourceException("missing width place for camera", "Camera", "width");

            //height has not been set
            if (height == 0.0)
                throw new MissingResourceException("missing height place for camera", "Camera", "height");


            //distance has not been set
            if (distance == 0.0)
                throw new MissingResourceException("missing distance place for camera", "Camera", "distance");

            //goes through every pixel in view plane  and casts ray, meaning creates a ray for every pixel and sets the color
            int nY = imageWriter.getNy();
            int nX = imageWriter.getNx();
            for (int row = 0; row < nY; row++) {
                for (int column = 0; column < nX; column++) {
                    castRay(nX, nY, row, column);
                }
            }
        }
        //if one of the resources was not set
        catch (MissingResourceException e) {
            throw new UnsupportedOperationException("renderImage - value not set yet" + e.getKey());
        }
        return this;
    }

    /**
     * Casts a ray from the camera through a pixel in the image, and writes the color of the intersection point to the
     * corresponding pixel in the image.
     *
     * @param nX     the number of pixels in the x-direction of the image
     * @param nY     the number of pixels in the y-direction of the image
     * @param column the column number of the pixel to cast the ray through
     * @param row    the row number of the pixel to cast the ray through
     * @throws MissingResourceException if the imageWriter or rayTracerBase have not been set
     */
    private void castRay(int nX, int nY, int column, int row) {

        //create the ray
        Ray ray = constructRay(nX, nY, row, column);
        //calculates the color of pixel in ray using traceRay method from Class TraceRay
        Color pixelColor = rayTracer.traceRay(ray);
        //writes the color of the pixel to image
        imageWriter.writePixel(row, column, pixelColor);
    }

    /**
     * Prints a grid on the camera's image using the specified interval and color.
     *
     * @param interval the interval between the grid lines
     * @param color    the color to use for the grid lines
     * @throws MissingResourceException if the imageWriter has not been set
     */
    public void printGrid(int interval, Color color) {

        // imageWriter has not been set
        if (imageWriter == null)
            throw new MissingResourceException("missing imageWriter-func printGrid", "Camera", "imageWriter");

        /*nested loop that goes through every pixel in grid and colors it*/
        for (int row = 0; row < imageWriter.getNy(); row++) {
            for (int column = 0; column < imageWriter.getNx(); column++) {
                /*for lines on net that are horizontal, for lines that are all net*/
                if ((row % (imageWriter.getNy() / 10) == 0) || ((row + 1) % (imageWriter.getNy() / 10) == 0))
                    imageWriter.writePixel(row, column, color);
                else
                    /*for vertical lines, since we are going through the pixels horizontally we will only reach 2 net
                     dots, every 50 pixels, as opposed to the whole line being net
                     */
                    if ((column % (imageWriter.getNx() / 10) == 0) || ((column + 1) % (imageWriter.getNx() / 10) == 0))
                        imageWriter.writePixel(row, column, color);
            }
        }
    }

    /**
     * Writes the camera's image to a file using the imageWriter.
     *
     * @throws MissingResourceException if the imageWriter has not been set
     */
    public void writeToImage() {
        // imageWriter has not been set
        if (imageWriter == null)
            throw new MissingResourceException("missing imageWriter-func writeToImage", "Camera", "imageWriter");
        imageWriter.writeToImage();
    }

    //--------------------------------getters----------------------------

    /**
     * getter for place of the camera
     *
     * @return the place of the camera
     */
    public Point getPosition() {
        return position;
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
     * @param width  width of the view plane
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
     * @return the camera itself
     */
    public Camera setVPDistance(double distance) {
        this.distance = distance;
        return this;
    }

    /**
     * set function for rayTracerBase - builder design pattern
     *
     * @param imageWriter sent imageWriter to set
     * @return this camera that function was called from
     */
    public Camera setImageWriter(ImageWriter imageWriter) {
        this.imageWriter = imageWriter;
        return this;
    }

    /**
     * set function for rayTracerBase - builder design pattern
     *
     * @param rayTracer sent rayTracerBase to set
     * @return this camera that function was called from
     */
    public Camera setRayTracer(RayTracerBase rayTracer) {
        this.rayTracer = rayTracer;
        return this;
    }
}
