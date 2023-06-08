package MiniProj1;

import geometries.Sphere;
import geometries.Triangle;
import lighting.AmbientLight;
import lighting.DirectionalLight;
import lighting.PointLight;
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

import static java.awt.Color.*;

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



/*

        Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(200, 200).setVPDistance(1000);


 */

        Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(200, 200).setVPDistance(1000);


        Scene scene4 = new Scene.SceneBuilder("Test scene").setAmbientLight(new AmbientLight(new Color(WHITE), 0.15)).build();


        scene4.geometries.add( //

                //T1.setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(50, 0, 20)), //

                //T2.setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(0, 20, 20)), //

                //T3.setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(50, 20, 0)), //

                //T4.setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(50, 10, 20)), //


                //T5.setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(40, 20, 20)), //

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
                new SpotLight(new Color(40000,0,100),
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
    @Test
    public void air() {
        Camera camera = new Camera(
                new Point(0, 0, 1000),
                new Vector(0, 0, -1),
                new Vector(0, 1, 0)) //
                .setVPSize(150, 150)
                .setVPDistance(1000);
        Scene scene4 = new Scene.SceneBuilder("Test scene").setAmbientLight(new AmbientLight(new Color(WHITE), 0.15)).setBackground(new Color(135, 206, 250)).build();
        double k = 12; // Scaling factor

        Point A = new Point(-1.34 * k, 0.74 * k, 0 * k);

        Point B = new Point(-1.34* k, -1.1 * k, 0 * k);
        Point C = new Point(-1.34 * k, 1.5 * k, 1.72 * k);
        Point D = new Point(-1.41 * k, -1.44 * k, 1.72 * k);
        Point F = new Point(-2.31 * k, 0.85 * k, 2 * k);
        Point G = new Point(-1.83 * k, -0.85 * k, 2 * k);
        Point sideLeft1=new Point(-35,24,-50);
        Point sideLeft2=new Point(-34.5,24,-50);

        Point right1=new Point(35,24,-50);
        Point right2=new Point(34.5,24,-50);
        Point rLittle1=new Point(14.5,-3,-50);
        Point rLittle2=new Point(14,-3,-50);
        Point lLittle1=new Point(-14,-3,-50);
        Point lLittle2=new Point(-14.5,-3,-50);

        A=new Point(A.getY(),A.getZ()-50,A.getX());
        B=new Point(B.getY()+3,B.getZ()-50,B.getX());

        C=new Point(C.getY()-2,C.getZ()-50,C.getX());
        Point C1=new Point(C.getX()-0.5,C.getY(),C.getZ());
        Point C2=new Point(C.getX(),C.getY(),C.getZ());
        Point CBorderSide = new Point(C.getX()+0.3, C.getY(), C.getZ());

        D=new Point(D.getY(),D.getZ()-50,D.getX());
        Point D1=new Point(D.getX()+0.5,D.getY(),D.getZ());
        Point DBorderSide = new Point(D.getX()-0.3, D.getY(), D.getZ());


        F=new Point(F.getY(),F.getZ()-50.5, F.getX());
        Point F1=new Point(F.getX()-0.5,F.getY(),F.getZ());
        Point FBorder=new Point(F.getX(),F.getY()+0.4,F.getZ());
        Point FBorderSide = new Point(F.getX()+0.3, F.getY(), F.getZ());

        G=new Point(G.getY(),G.getZ()-50, G.getX());
        Point G1=new Point(G.getX()+0.5,G.getY(),G.getZ());
        Point GBorder=new Point(G.getX(),G.getY()+0.4,G.getZ());
        Point GBorderSide = new Point(G.getX()-0.3, G.getY(), G.getZ());



        //FRONT RIGHT
        Triangle T1 = new Triangle(C,A,D);
        //FRONT LEFT
        Triangle T2 = new Triangle(A,D,B);
        Triangle T3 = new Triangle(C,F,D);
        Triangle T4 = new Triangle(F,G,D);

        Triangle L1=new Triangle(sideLeft1,sideLeft2,D1);
        Triangle L2=new Triangle(D1,D,sideLeft2);
        Triangle R1=new Triangle(right1,right2,C2);
        Triangle R2=new Triangle(C1,right2,C2);
        Triangle rLittle1T=new Triangle(F,F1,rLittle1);
        Triangle rLittle2T=new Triangle(rLittle2,F1,rLittle1);
        Triangle lLittle1T=new Triangle(lLittle1,lLittle2,G1);
        Triangle lLittle2T=new Triangle(G,G1,lLittle2);

        Triangle borderR1=new Triangle(CBorderSide,C1,FBorderSide);
        Triangle borderR2=new Triangle(F1,C1,FBorderSide);

        Triangle borderL1=new Triangle(GBorderSide,G1,D1);
        Triangle borderL2=new Triangle(GBorderSide,DBorderSide,D1);

        Triangle borderBack1=new Triangle(G,GBorder,F);
        Triangle borderBack2=new Triangle(F,FBorder,GBorder);


        scene4.geometries.add( //
                T1.setMaterial(new Material().setkD(0.7).setkS(0.5).setnShininess(100)).setEmission(new Color(500, 20, 30)), //
                T2.setMaterial(new Material().setkD(0.7).setkS(0.5).setnShininess(100)).setEmission(new Color(520, 40, 50)), //

                T3.setMaterial(new Material().setkD(0.4).setkS(0.5).setnShininess(100)).setEmission(new Color(550, 70, 80)), //
                T4.setMaterial(new Material().setkD(0.4).setkS(0.5).setnShininess(100)).setEmission(new Color(500, 20, 30)),

                L1.setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(WHITE)),
                L2.setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(WHITE)),

                R1.setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(WHITE)),
                R2.setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(WHITE)),

                borderR1.setMaterial(new Material().setkD(1.2).setkS(0.5).setnShininess(100)).setEmission(new Color(YELLOW)), //
                borderR2.setMaterial(new Material().setkD(1.2).setkS(0.5).setnShininess(100)).setEmission(new Color(YELLOW)), //

                borderL1.setMaterial(new Material().setkD(1.2).setkS(0.5).setnShininess(100)).setEmission(new Color(YELLOW)), //
                borderL2.setMaterial(new Material().setkD(1.2).setkS(0.5).setnShininess(100)).setEmission(new Color(YELLOW)), //

                borderBack1.setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(YELLOW)), //
                borderBack2.setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(YELLOW)), //

                rLittle1T.setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(WHITE)),
                rLittle2T.setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(WHITE)),

                lLittle1T.setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(WHITE)),
                lLittle2T.setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(WHITE)),

                new Sphere(new Point(0, 28, -50), 35d)
                        .setEmission(new Color(150, 0, 0)) //
                        .setMaterial(new Material().setkD(0.3).setkS(0.05).setnShininess(100).setkT(0.3))

                );
        scene4.lights.add(
                new  SpotLight(new Color(500, 600, 400),
                        new Point(-100, -100, 500),
                        new Vector(-1, -1, -2)) //
                        .setKl(0.0004).setKq(0.00000006));
        scene4.lights.add(
                new PointLight(new Color(BLUE), new Point(0,-20,0))
        );



        ImageWriter imageWriter = new ImageWriter("TRY", 600, 600);
        camera.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene4)) //
                .renderImage() //
                .writeToImage();
    }


}
