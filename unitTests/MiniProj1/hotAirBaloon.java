package MiniProj1;

import geometries.Plane;
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


        Triangle T1 = new Triangle(I, E, H);
        Triangle T2 = new Triangle(E, H, D);
        Triangle T3 = new Triangle(H, D, G);
        Triangle T4 = new Triangle(D, G, B);
        Triangle T5 = new Triangle(G, B, F);
        Triangle T6 = new Triangle(B, F, C);
        Triangle T7 = new Triangle(F, C, I);
        Triangle T8 = new Triangle(C, I, E);
        Triangle T9 = new Triangle(B, C, D);
        Triangle T10 = new Triangle(C, D, E);



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
                new SpotLight(new Color(40000, 0, 100),
                        new Point(0, 10, 30),
                        new Vector(0, 1, 10)) //
                        .setKl(1E-5).setKq(1.5E-7)
        );


        scene4.lights.add(new SpotLight(new Color(10000, 1000, 1400), new Point(-50, -50, 25), new Vector(1, 1, -0.5))
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
        double k = 1; // Scaling factor

        Point A = new Point(10* k, -50 * k, -16 * k);

        Point B = new Point(-10 * k, -50 * k, -16 * k);
        Point C = new Point(16 * k, -29.36 * k, -16 * k);
        Point D = new Point(-16 * k, -29.36 * k, -16 * k);
          Point sideLeft1 = new Point(-22, -1, -50);
        Point sideLeft2 = new Point(-21.5, -1, -50);
        Point sideLeft1Back = new Point(-28, 5, -60);
        Point sideLeft2Back = new Point(-27.5, 5, -60);

        Point right1 = new Point(20, 2, -50);
        Point right2 = new Point(19.5, 2, -50);
        Point right1Back = new Point(13, -4, -60);
        Point right2Back = new Point(12.5, -4, -60);

        Point C1 = new Point(C.getX() - 0.5, C.getY(), C.getZ());
        Point C2 = new Point(C.getX(), C.getY(), C.getZ());


        Point D1 = new Point(D.getX() + 0.5, D.getY(), D.getZ());
        Point ABack=new Point(A.getX()-5,A.getY(),A.getZ()-100);
        Point BBack=new Point(B.getX()-5,B.getY(),B.getZ()-100);
        Point CBack=new Point(C.getX()-5,C.getY(),C.getZ()-100);
        Point DBack=new Point(D.getX()-5,D.getY(),D.getZ()-100);


        Point F1 = new Point(CBack.getX() - 0.5, CBack.getY(), CBack.getZ());

        Point G1 = new Point(DBack.getX() + 0.5, DBack.getY(), DBack.getZ());
       //BOTTOM
        Triangle Bottom1=new Triangle(A,B,ABack);
        Triangle Bottom2=new Triangle(B,BBack,ABack);

        //FRONT RIGHT
        Triangle T1 = new Triangle(C, A, D);
        Triangle T1Back = new Triangle(CBack, ABack, DBack);

        //FRONT LEFT
        Triangle T2 = new Triangle(A, D, B);
        Triangle T2Back = new Triangle(ABack, DBack, BBack);

        Triangle LeftSideBase1 = new Triangle(A, C, ABack);
        Triangle LeftSideBase2 = new Triangle(C, CBack, ABack);
        Triangle RightSideBase1 = new Triangle(B, D, BBack);
        Triangle RightSideBase2 = new Triangle(D, DBack, BBack);


        Triangle L1 = new Triangle(sideLeft1, sideLeft2, D1);
        Triangle L2 = new Triangle(D1, D, sideLeft2);
        Triangle R1 = new Triangle(right1, right2, C2);
        Triangle R2 = new Triangle(C1, right2, C2);
        Triangle rLittle1T = new Triangle(CBack, F1, right2Back);
        Triangle rLittle2T = new Triangle(right1Back, F1, right2Back);
        Triangle lLittle1T = new Triangle(sideLeft1Back, sideLeft2Back, G1);
        Triangle lLittle2T = new Triangle(DBack, G1, sideLeft2Back);


        /////////////////////////////////////////////////////////////////
        Point p1 = new Point(-100, -50, -200);
        Point p2 = new Point(100, -50, -200);
        Point p3 = new Point(100, -50, -100);

        scene4.geometries.add( //

                new Plane(new Point(0,-55,-750),new Vector(0,0.5,0)).setEmission(new Color(34,70,34))
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)),
                //basket
                //bottom
                Bottom1.setMaterial(new Material().setkD(0.5).setkS(0.3).setnShininess(100)).setEmission(new Color(500, 40, 40)), //
                Bottom2.setMaterial(new Material().setkD(0.5).setkS(1.4).setnShininess(100)).setEmission(new Color(520, 40, 40)), //

                //front
                T1.setMaterial(new Material().setkD(0.5).setkS(0.3).setnShininess(100)).setEmission(new Color(500, 50, 40)), //
                T2.setMaterial(new Material().setkD(0.5).setkS(1.4).setnShininess(100)).setEmission(new Color(520, 50, 40)), //
                //back
                T1Back.setMaterial(new Material().setkD(0.5).setkS(0.3).setnShininess(100)).setEmission(new Color(500, 40, 40)), //
                T2Back.setMaterial(new Material().setkD(0.5).setkS(1.4).setnShininess(100)).setEmission(new Color(520, 40, 40)), //

                //Left side
                LeftSideBase1.setMaterial(new Material().setkD(0.5).setkS(0.3).setnShininess(100)).setEmission(new Color(500, 140, 40)), //
                LeftSideBase2.setMaterial(new Material().setkD(0.5).setkS(1.4).setnShininess(100)).setEmission(new Color(520, 140, 40)), //

                //Right side
                RightSideBase1.setMaterial(new Material().setkD(0.5).setkS(0.3).setnShininess(100)).setEmission(new Color(500, 140, 40)), //
                RightSideBase2.setMaterial(new Material().setkD(0.5).setkS(1.4).setnShininess(100)).setEmission(new Color(520, 140, 40)), //

                //for long lines between balloon and basket
                L1.setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(WHITE)),
                L2.setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(WHITE)),
                R1.setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(WHITE)),
                R2.setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(WHITE)),

                //for small lines between baloon and basket

                rLittle1T.setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(WHITE)),
                rLittle2T.setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(WHITE)),
                lLittle1T.setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(WHITE)),
                lLittle2T.setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(WHITE)),
                //the balloon
                new Sphere(new Point(-2, 28, -50), 35d)
                        .setEmission(new Color(150, 0, 0)) //
                        .setMaterial(new Material().setkD(0.3).setkS(0.05).setnShininess(100).setkT(0.3)),
//CLOUD UPPER LEFT BIG
                new Sphere(new Point(-55, 20, -80), 8d)
                        .setEmission(new Color(gray)) //
                        .setMaterial(new Material().setkD(0.74).setkS(0.003).setnShininess(100).setkT(0.15)),
                new Sphere(new Point(-62, 18, -80), 6d)
                        .setEmission(new Color(gray)) //
                        .setMaterial(new Material().setkD(0.74).setkS(0.003).setnShininess(100).setkT(0.15)),
                new Sphere(new Point(-48, 18, -80), 6d)
                        .setEmission(new Color(gray)) //
                        .setMaterial(new Material().setkD(0.74).setkS(0.003).setnShininess(100).setkT(0.15)),
                //CLOUD UPPER RIGHT BIG upper

                new Sphere(new Point(50, 58, -20), 8d)
                        .setEmission(new Color(gray)) //
                        .setMaterial(new Material().setkD(0.74).setkS(0.003).setnShininess(100).setkT(0.15)),
                new Sphere(new Point(57, 56, -20), 6d)
                        .setEmission(new Color(gray)) //
                        .setMaterial(new Material().setkD(0.74).setkS(0.003).setnShininess(100).setkT(0.15)),
                new Sphere(new Point(43, 56, -20), 6d)
                        .setEmission(new Color(gray)) //
                        .setMaterial(new Material().setkD(0.74).setkS(0.003).setnShininess(100).setkT(0.15)),
                //CLOUD UPPER RIGHT BIG lower

                new Sphere(new Point(60, 25, 50), 8d)
                        .setEmission(new Color(gray
                        )) //
                        .setMaterial(new Material().setkD(0.74).setkS(0.93).setnShininess(100).setkT(0.15)),
                new Sphere(new Point(67, 23, 50), 6d)
                        .setEmission(new Color(gray)) //
                        .setMaterial(new Material().setkD(0.74).setkS(0.93).setnShininess(100).setkT(0.15)),
                new Sphere(new Point(53, 23, 50), 6d)
                        .setEmission(new Color(gray)) //
                        .setMaterial(new Material().setkD(0.74).setkS(0.93).setnShininess(100).setkT(0.15)),

                //CLOUD MIDDLE RIGHT LITTLE

                new Sphere(new Point(40, 0, 10), 5d)
                        .setEmission(new Color(gray)) //
                        .setMaterial(new Material().setkD(0.74).setkS(0.003).setnShininess(100).setkT(0.15)),
                new Sphere(new Point(45, -1, 10), 3.5d)
                        .setEmission(new Color(gray)) //
                        .setMaterial(new Material().setkD(0.74).setkS(0.003).setnShininess(100).setkT(0.15)),
                new Sphere(new Point(35, -1, 10), 3.5d)
                        .setEmission(new Color(gray)) //
                        .setMaterial(new Material().setkD(0.74).setkS(0.003).setnShininess(100).setkT(0.15)),

//CLOUD MIDDLE RIGHT LITTLE

                new Sphere(new Point(60, -10, 0), 5d)
                        .setEmission(new Color(gray)) //
                        .setMaterial(new Material().setkD(0.64).setkS(0.003).setnShininess(100).setkT(0.15)),
                new Sphere(new Point(65, -11, 0), 3.5d)
                        .setEmission(new Color(gray)) //
                        .setMaterial(new Material().setkD(0.64).setkS(0.003).setnShininess(100).setkT(0.15)),
                new Sphere(new Point(55, -11, 0), 3.5d)
                        .setEmission(new Color(gray)) //
                        .setMaterial(new Material().setkD(0.64).setkS(0.003).setnShininess(100).setkT(0.15)),
                //CLOUD LEFT MIDDLE LITTLE UPPER

                new Sphere(new Point(-60, -10, -100), 5d)
                        .setEmission(new Color(gray)) //
                        .setMaterial(new Material().setkD(0.74).setkS(0.003).setnShininess(100).setkT(0.15)),
                new Sphere(new Point(-65, -11, -100), 3.5d)
                        .setEmission(new Color(gray)) //
                        .setMaterial(new Material().setkD(0.74).setkS(0.003).setnShininess(100).setkT(0.15)),
                new Sphere(new Point(-55, -11, -100), 3.5d)
                        .setEmission(new Color(gray)) //
                        .setMaterial(new Material().setkD(0.74).setkS(0.003).setnShininess(100).setkT(0.15)),

//CLOUD LEFT TOP LITTLE
                new Sphere(new Point(-60, 50, -30), 5d)
                        .setEmission(new Color(gray)) //
                        .setMaterial(new Material().setkD(0.74).setkS(0.003).setnShininess(100).setkT(0.15)),
                new Sphere(new Point(-65, 49, -30), 3.5d)
                        .setEmission(new Color(gray)) //
                        .setMaterial(new Material().setkD(0.74).setkS(0.003).setnShininess(100).setkT(0.15)),
                new Sphere(new Point(-55, 49, -30), 3.5d)
                        .setEmission(new Color(gray)) //
                        .setMaterial(new Material().setkD(0.74).setkS(0.003).setnShininess(100).setkT(0.15)),

//CLOUD LEFT TOP LITTLE
                new Sphere(new Point(-30, 65, -5), 5d)
                        .setEmission(new Color(gray)) //
                        .setMaterial(new Material().setkD(0.74).setkS(0.003).setnShininess(100).setkT(0.15)),
                new Sphere(new Point(-35, 64, -5), 3.5d)
                        .setEmission(new Color(gray)) //
                        .setMaterial(new Material().setkD(0.74).setkS(0.003).setnShininess(100).setkT(0.15)),
                new Sphere(new Point(-25, 64, -5), 3.5d)
                        .setEmission(new Color(gray)) //
                        .setMaterial(new Material().setkD(0.74).setkS(0.003).setnShininess(100).setkT(0.15)),

//CLOUD LEFT TOP LITTLE
                new Sphere(new Point(-75, 74, 5), 5d)
                        .setEmission(new Color(gray)) //
                        .setMaterial(new Material().setkD(0.74).setkS(0.003).setnShininess(100).setkT(0.15)),
                new Sphere(new Point(-80, 73, 5), 3.5d)
                        .setEmission(new Color(gray)) //
                        .setMaterial(new Material().setkD(0.74).setkS(0.003).setnShininess(100).setkT(0.15)),
                new Sphere(new Point(-70, 73, 5), 3.5d)
                        .setEmission(new Color(gray)) //
                        .setMaterial(new Material().setkD(0.74).setkS(0.003).setnShininess(100).setkT(0.15)),


//CLOUD LEFT TOP LITTLE
                new Sphere(new Point(20, 68, -50), 5d)
                        .setEmission(new Color(gray)) //
                        .setMaterial(new Material().setkD(0.74).setkS(0.003).setnShininess(100).setkT(0.15)),
                new Sphere(new Point(25, 67, -50), 3.5d)
                        .setEmission(new Color(gray)) //
                        .setMaterial(new Material().setkD(0.74).setkS(0.003).setnShininess(100).setkT(0.15)),
                new Sphere(new Point(15, 67, -50), 3.5d)
                        .setEmission(new Color(gray)) //
                        .setMaterial(new Material().setkD(0.74).setkS(0.003).setnShininess(100).setkT(0.15)),

//OUT OF PICTURE
                new Sphere(new Point(100, 35, -50), 8d)
                        .setEmission(new Color(gray)) //
                        .setMaterial(new Material().setkD(0.74).setkS(0.93).setnShininess(100).setkT(0.15)),
                new Sphere(new Point(107, 33, -50), 6d)
                        .setEmission(new Color(gray)) //
                        .setMaterial(new Material().setkD(0.74).setkS(0.93).setnShininess(100).setkT(0.15)),
                new Sphere(new Point(93, 33, -50), 6d)
                        .setEmission(new Color(gray)) //
                        .setMaterial(new Material().setkD(0.74).setkS(0.93).setnShininess(100).setkT(0.15)),


                new Sphere(new Point(100, 0, -50), 8d)
                        .setEmission(new Color(gray)) //
                        .setMaterial(new Material().setkD(0.74).setkS(0.93).setnShininess(100).setkT(0.15)),
                new Sphere(new Point(107, -2, -50), 6d)
                        .setEmission(new Color(gray)) //
                        .setMaterial(new Material().setkD(0.74).setkS(0.93).setnShininess(100).setkT(0.15)),
                new Sphere(new Point(93, -2, -50), 6d)
                        .setEmission(new Color(gray)) //
                        .setMaterial(new Material().setkD(0.74).setkS(0.93).setnShininess(100).setkT(0.15)),
                new Plane(new Point(5,-80,-300), new Vector(5,-240,-20))
                        .setEmission(new Color(185, 266, 500).scale(0.35))//
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)),


                //little ball
                new Sphere(new Point(-8,-35,-16),2).setEmission(new Color(139, 69, 19)).setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)),//,
                new Triangle(new Point(-7.75,-29.36,-15),new Point(-8.25,-29.36,-15),new Point(-8.25,-35,-15)).setEmission(new Color(black)),
                new Triangle(new Point(-7.75,-29.36,-15),new Point(-7.75,-35,-15),new Point(-8.25,-35,-15)).setEmission(new Color(black)),


                new Sphere(new Point(-0,-35,-16),2).setEmission(new Color(139, 69, 19)).setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)),//,
                new Triangle(new Point(-0.25,-29.36,-15),new Point(0.25,-29.36,-15),new Point(0.25,-35,-15)).setEmission(new Color(black)),
                new Triangle(new Point(-0.25,-29.36,-15),new Point(-0.25,-35,-15),new Point(0.25,-35,-15)).setEmission(new Color(black)),

                new Sphere(new Point(8,-35,-16),2).setEmission(new Color(139, 69, 19)).setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)),//,
                new Triangle(new Point(7.75,-29.36,-15),new Point(8.25,-29.36,-15),new Point(8.25,-35,-15)).setEmission(new Color(black)),
                new Triangle(new Point(7.75,-29.36,-15),new Point(7.75,-35,-15),new Point(8.25,-35,-15)).setEmission(new Color(black)),

                new Sphere(new Point(72,45,0),8).setEmission(new Color(yellow)).setMaterial(new Material().setkD(0.1).setkS(0).setnShininess(100))

        );
        ///scene4.lights.add(new SpotLight(new Color(500, 600, 400),
           //     new Point(-100, -100, 500),
             //   new Vector(-1, -1, -2)) //
        // .setKl(0.0004).setKq(0.0000006));

        //scene4.lights.add(new DirectionalLight(new Color(200,300,100).scale(0.8),new Vector(20,-18,20)));

        scene4.lights.add(new DirectionalLight(new Color(200,300,100).scale(0.8),new Vector(-1,-2,-8)));
        scene4.lights.add(new PointLight(new Color(orange),new Point(72,45,0)));
        //scene4.lights.add(new PointLight( new Color(100,100,100),new Point( -8,-35,-16)));

        /*
        //scene4.lights.add(new SpotLight(new Color(255,255,20),new Point(40, -40, -50), new Vector(1,-1,0)));
        //scene4.lights.add(new SpotLight(new Color(255,255,20),new Point(47, -42, -50), new Vector(1,-1,0)));
        //scene4.lights.add(new SpotLight(new Color(255,255,20),new Point(33, -42, -50), new Vector(1,-1,0)));
        */


        //scene4.lights.add(new SpotLight(new Color(255,255,20),new Point(20, 68, -50), new Vector(1,-1,0)));
        //scene4.lights.add(new SpotLight(new Color(255,255,20),new Point(25, 67, -50), new Vector(1,-1,0)));
        //scene4.lights.add(new SpotLight(new Color(255,255,20),new Point(15, 67, -50), new Vector(1,-1,0)));

        //scene4.lights.add(new PointLight(new Color(255,255,20),new Point(20, 68, -50)));
        //scene4.lights.add(new PointLight(new Color(255,255,20),new Point(25, 67, -50)));
        //scene4.lights.add(new PointLight(new Color(255,255,20),new Point(15, 67, -50)));

        /*
        //scene4.lights.add(new SpotLight(new Color(255,255,20),new Point(30, -75, -50), new Vector(1,-1,0)));
        //scene4.lights.add(new SpotLight(new Color(255,255,20),new Point(35, -76, -50), new Vector(1,-1,0)));
        //scene4.lights.add(new SpotLight(new Color(255,255,20),new Point(25, -76, -50), new Vector(1,-1,0)));


        //scene4.lights.add(new PointLight(new Color(255,255,20),new Point(30, -75, -50)));
        //scene4.lights.add(new PointLight(new Color(255,255,20),new Point(35, -76, -50)));
        //scene4.lights.add(new PointLight(new Color(255,255,20),new Point(25, -76, -50)));

         */

        ///scene4.lights.add(new PointLight(new Color(BLUE), new Point(0,-10,0)));
        ImageWriter imageWriter = new ImageWriter("TRY", 600, 600);
        camera.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene4)) //
                .renderImage() //
                .writeToImage();
    }


}