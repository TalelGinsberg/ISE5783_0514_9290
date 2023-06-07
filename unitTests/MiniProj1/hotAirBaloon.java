package MiniProj1;

import geometries.Sphere;
import geometries.Triangle;
import lighting.AmbientLight;
import lighting.DirectionalLight;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import primitives.Color;
import primitives.Material;
import primitives.Point;
import primitives.Vector;
import renderer.Camera;
import renderer.ImageWriter;
import renderer.RayTracerBasic;
import scene.Scene;

import static java.awt.Color.WHITE;
import static java.awt.Color.yellow;

public class hotAirBaloon {

    @Test
    public void miniProj1() {

        /*
        Point B = new Point(1.31,1.32,0);
        Point C = new Point(-1.27,1.18,0);
        Point D = new Point(1.19,-1.22,0);
        Point E = new Point(-1.39,-1.09,0);
        Point F = new Point(-2,2,2.31);
        Point H = new Point(1.98,-1.6,2.24);
        Point I = new Point(-1.75,-1.58,2.26);
        Point G = new Point(2,1.79,2.3);

         */




        double k = 15; // Scaling factor

        Point B = new Point(1.31 * k, 1.32 * k, 1 * k);
        Point C = new Point(-0.34 * k, 1.01 * k, 0 * k);
        Point D = new Point(1.19 * k, -1.22 * k, 1 * k);
        Point E = new Point(-0.74 * k, -1.99 * k, 0.17 * k);
        Point F = new Point(-2 * k, 2 * k, 2.31 * k);
        Point H = new Point(1.98 * k, -1.6 * k, 3.56 * k);
        Point I = new Point(-1.75 * k, -1.58 * k, 2.26 * k);
        Point G = new Point(2 * k, 1.79 * k, 3.49 * k);


        Triangle T1 = new Triangle(I,E,H);
        Triangle T2 = new Triangle(E,H,D);
        Triangle T3 = new Triangle(H,D,G);
        Triangle T4 = new Triangle(D,G,B);
        Triangle T5 = new Triangle(G,B,F);
        Triangle T6 = new Triangle(B,F,C);
        Triangle T7 = new Triangle(F,C,I);
        Triangle T8 = new Triangle(C,I,E);
        Triangle T9 = new Triangle(B,C,D);
        Triangle T10 = new Triangle(C,D,E);




        Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(200, 200).setVPDistance(1000);


        /*
        Camera camera = new Camera(new Point(1000, 0, 0), new Vector(-1, 0, 0), new Vector(0, 0, -1))
                .setVPSize(200, 200)
                .setVPDistance(1000);

         */
        Scene scene4 = new Scene.SceneBuilder("Test scene").setAmbientLight(new AmbientLight(new Color(WHITE), 0.15)).build();


        scene4.geometries.add( //

                T1.setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(50, 0, 20)), //

                T2.setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(0, 20, 20)), //

                T3.setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(50, 20, 0)), //

                T4.setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(50, 10, 20)), //


                T5.setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(40, 20, 20)), //

                T6.setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(50, 20, 30)), //

                T7.setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(50, 50, 20)), //

                T8.setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(0, 30, 20)), //

                T9.setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(10, 20, 20)), //

                T10.setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(50, 20, 20))); //

/*
        scene4.lights.add(
                //new DirectionalLight(new Color(10000,1000,1400), new Vector(1, 1, -0.5)));

                new SpotLight(new Color(10000,1000,1400),
                        new Point(10,20,30),
                        new Vector(0,0,2)) //
                        .setKl(1E-5).setKq(1.5E-7));

 */

        scene4.lights.add(
                new SpotLight(new Color(40,0,100),
                        new Point(0,10,30),
                        new Vector(0,1,10)) //
                        .setKl(1E-5).setKq(1.5E-7)
        );






        scene4.lights.add(new SpotLight(new Color(10000,1000,1400), new Point(-50, -50, 25), new Vector(1, 1, -0.5))
                .setKl(0.001).setKq(0.0001));

        ImageWriter imageWriter = new ImageWriter("hotAirBaloon", 600, 600);
        camera.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene4)) //
                .renderImage() //
                .writeToImage();
    }

}
