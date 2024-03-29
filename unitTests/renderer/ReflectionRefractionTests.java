/**
 * 
 */
package renderer;

import static java.awt.Color.*;

import geometries.Geometries;
import geometries.Plane;
import org.junit.jupiter.api.Test;


import geometries.Sphere;
import geometries.Triangle;
import lighting.AmbientLight;
import lighting.SpotLight;
import primitives.*;
import renderer.*;
import scene.Scene;

/** Tests for reflection and transparency functionality, test for partial
 * shadows
 * (with transparency)
 * @author dzilb and last function Noa and Talel */
public class ReflectionRefractionTests {
   private Scene scene = new Scene.SceneBuilder("Test scene").build();

   /** Produce a picture of a sphere lighted by a spotlight */
   @Test
   public void twoSpheres() {
      Camera camera = new Camera(
              new Point(0, 0, 1000),
              new Vector(0, 0, -1),
              new Vector(0, 1, 0)) //
         .setVPSize(150, 150)
              .setVPDistance(1000);

      scene.geometries.add( //
                           new Sphere(new Point(0, 0, -50), 50d)
                                   .setEmission(new Color(BLUE)) //
                              .setMaterial(new Material().setkD(0.4).setkS(0.3).setnShininess(100).setkT(0.3)),
                           new Sphere(new Point(0, 0, -50), 25d).setEmission(new Color(RED)) //
                              .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)));
      scene.lights.add( //
                       new SpotLight(new Color(1000, 600, 0),
                               new Point(-100, -100, 500),
                               new Vector(-1, -1, -2)) //
                          .setKl(0.0004).setKq(0.0000006));

      camera.setImageWriter(new ImageWriter("refractionTwoSpheres", 500, 500)) //
         .setRayTracer(new RayTracerBasic(scene)) //
         .renderImage() //
         .writeToImage();
   }

   /** Produce a picture of a sphere lighted by a spot light */
   @Test
   public void twoSpheresOnMirrors() {
      Camera camera = new Camera(new Point(0, 0, 10000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
         .setVPSize(2500, 2500).setVPDistance(10000); //
      Scene scene2 = new Scene.SceneBuilder("Test scene").setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1)).build();


      scene2.geometries.add( //
                           new Sphere(new Point(-950, -900, -1000), 400d).setEmission(new Color(0, 50, 100)) //
                              .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)
                                 .setkT(new Double3(0.5, 0, 0))),
                           new Sphere(new Point(-950, -900, -1000), 200d).setEmission(new Color(100, 50, 20)) //
                              .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
                           new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500),
                                        new Point(670, 670, 3000)) //
                              .setEmission(new Color(20, 20, 20)) //
                              .setMaterial(new Material().setkR(1)),
                           new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500),
                                        new Point(-1500, -1500, -2000)) //
                              .setEmission(new Color(20, 20, 20)) //
                              .setMaterial(new Material().setkR(new Double3(0.5, 0, 0.4))));

      scene2.lights.add(new SpotLight(new Color(1020, 400, 400), new Point(-750, -750, -150), new Vector(-1, -1, -4)) //
         .setKl(0.00001).setKq(0.000005));

      ImageWriter imageWriter = new ImageWriter("reflectionTwoSpheresMirrored", 500, 500);
      camera.setImageWriter(imageWriter) //
         .setRayTracer(new RayTracerBasic(scene2)) //
         .renderImage() //
         .writeToImage();
   }

   /** Produce a picture of a two triangles lighted by a spot light with a
    * partially
    * transparent Sphere producing partial shadow */
   @Test
   public void trianglesTransparentSphere() {
      Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
         .setVPSize(200, 200).setVPDistance(1000);
      Scene scene3 = new Scene.SceneBuilder("Test scene").setAmbientLight(new AmbientLight(new Color(WHITE), 0.15)).build();


      scene3.geometries.add( //
                           new Triangle(new Point(-150, -150, -115), new Point(150, -150, -135),
                                        new Point(75, 75, -150)) //
                              .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(60)), //
                           new Triangle(new Point(-150, -150, -115), new Point(-70, 70, -140), new Point(75, 75, -150)) //
                              .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(60)), //
                           new Sphere(new Point(60, 50, -50), 30d).setEmission(new Color(BLUE)) //
                              .setMaterial(new Material().setkD(0.2).setkS(0.2).setnShininess(30).setkT(0.6)));

      scene3.lights.add(new SpotLight(new Color(700, 400, 400), new Point(60, 50, 0), new Vector(0, 0, -1)) //
         .setKl(4E-5).setKq(2E-7));

      ImageWriter imageWriter = new ImageWriter("refractionShadow", 600, 600);
      camera.setImageWriter(imageWriter) //
         .setRayTracer(new RayTracerBasic(scene3)) //
         .renderImage()
              .writeToImage();
   }

   /** Produce a picture of a two triangles lighted by a spot light with a
    * partially
    * transparent Sphere producing partial shadow */
   @Test
   public void multipleObjects() {
      Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
              .setVPSize(200, 200).setVPDistance(1000);
      Scene scene4 = new Scene.SceneBuilder("Test scene").setAmbientLight(new AmbientLight(new Color(WHITE), 0.15)).build();


      Point a = new Point(0, -33, -55);
      Point b = new Point(14, 5, -75);
      Point c = new Point(-14, 5, -75);
      Point d = new Point(8,5,-45);

      scene4.geometries.add( //
              new Sphere(new Point(6, 10, -50), 9d).setEmission(new Color(47, 97,   14)) //
                      .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)),
              new Sphere(new Point(0, 23, -50), 9d).setEmission(new Color (129, 59, 9)) //
                      .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)),

              new Triangle(a, b, c) //
                      .setMaterial(new Material().setkD(0.5).setkS(0.9).setnShininess(100)).setEmission(new Color(150, 20, 30)), //
              new Triangle(a,b,d) //
                      .setMaterial(new Material().setkD(0.9).setkS(0.5).setnShininess(100)).setEmission(new Color(65, 35, 35)), //
              new Triangle(a,c,d) //
                      .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(50, 20, 20)), //
              new Plane(new Point(0,0,-100), new Vector(0,0,1))
                      .setEmission(new Color(gray))//
                      .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)), //
// ,

              new Sphere(new Point(-6, 10, -50), 9d).setEmission(new Color(yellow)) //
                      .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)));

      scene4.lights.add( //
              new SpotLight(new Color(500, 600, 400),
                      new Point(-100, -100, 500),
                      new Vector(-1, -1, -2)) //
                      .setKl(0.004).setKq(0.0000006).setSize(30));

      ImageWriter imageWriter = new ImageWriter("iceCream", 600, 600);
      camera.setImageWriter(imageWriter) //
              .setRayTracer(new RayTracerBasic(scene4).setNumOfRays(1000)) //
              .renderImage() //
              .writeToImage();
   }

   }
