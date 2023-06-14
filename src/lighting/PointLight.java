package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;
import primitives.Ray;

import java.util.ArrayList;

/**
 * This class represents a point light source in a scene.
 * A point light emits light uniformly in all directions from a specific position in space.
 *
 * @author Noa Harel and Talel Ginsberg
 */
public class PointLight extends Light implements LightSource {

    //----------------------------fields--------------------------
    /**
     * The position of the point light in 3D space
     */
    private final Point position;
    /**
     * The constant attenuation coefficient
     */
    private double kC = 1d;
    /**
     * The linear attenuation coefficient
     */
    private double kL = 0d;
    /**
     * The quadratic attenuation coefficient
     */
    private double kQ = 0d;

    //-----------------------------constructor-------------------------

    /**
     * Constructs a point light with the given intensity and position.
     *
     * @param intensity The intensity of the point light
     * @param position  The position of the point light in 3D space
     */
    public PointLight(Color intensity, Point position) {
        super(intensity);
        this.position = position;
    }

    //---------------------------override functions-------------------------
    @Override
    public Color getIntensity(Point p) {
        double d = position.distance(p);
        return intensity.reduce(kC + kL * d + kQ * d * d );
    }

    @Override
    public Vector getL(Point p) {
        return p.subtract(position).normalize();
    }

    @Override
    public double getDistance(Point point) {
        return position.distance(point);
    }

    //--------------------------------getters----------------------------

    /**
     * Sets the constant attenuation coefficient of the point light.
     *
     * @param kC The constant attenuation coefficient
     * @return The updated PointLight object
     */
    public PointLight setKc(double kC) {
        this.kC = kC;
        return this;
    }


    /**
     * Sets the linear attenuation coefficient of the point light.
     *
     * @param kL The linear attenuation coefficient
     * @return The updated PointLight object
     */
    public PointLight setKl(double kL) {
        this.kL = kL;
        return this;
    }


    /**
     * Sets the quadratic attenuation coefficient of the point light.
     *
     * @param kQ The quadratic attenuation coefficient
     * @return The updated PointLight object
     */
    public PointLight setKq(double kQ) {
        this.kQ = kQ;
        return this;
    }

    @Override
    public ArrayList<Ray> softShadow(Point p, int rayNum, double d)
    {
        //Creates a plane that its normal is the vector between the position and the point on the geometric object.
        //The length of the vectors that define the plane is delta
        Vector n = getL(p);//n- vector from the light to the point
        Vector v1 = n.normalize().scale(-1).normalize();//v1- vector normal to n
        Vector v2 = v1.crossProduct(n).scale(-1).normalize();//v2- vector normal to v1 and to n
        Vector up=new Vector(v1.scale(d).getX(),v1.scale(d).getY(),v1.scale(d).getZ());
        Vector side=new Vector(v2.scale(d).getX(),v2.scale(d).getY(),v2.scale(d).getZ());

        ArrayList<Ray> ans = new ArrayList<Ray>();
        Point first;

        first=this.position.add(up.scale(((int)rayNum/2))).add(side.scale(((int)rayNum/2)));//The first point on the grid that is on the plain
        //(The center of the grid is the position of the light)

        Point temp;
        for (int i=0;i<(rayNum*rayNum);i++) {
            //Goes through all the points on the grid and takes out from each point a ray to the point on the geometric object
            temp=first;
            if((int)(i/rayNum)==0) {
                if(i%rayNum==0) {
                    temp=first;
                }
                else {
                    temp=first.add((side).scale(-i%rayNum));
                }
            }
            if(i%rayNum==0 && (int)(i/rayNum)!=0) {
                temp=first.add(up.scale(-(int)(i/rayNum)));
            }
            if((int)(i/rayNum)!=0 && i%rayNum!=0) {
                temp=first.add(up.scale(-(int)(i/rayNum)));
                temp=temp.add((side).scale(-i%rayNum));
            }
            ans.add(i, new Ray(temp,p.subtract(temp).normalize()));
        }
        return ans;

    }

}
