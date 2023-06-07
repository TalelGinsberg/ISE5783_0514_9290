package MiniProj1;

import geometries.Triangle;
import lighting.AmbientLight;
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

public class donuts {
    @Test
    public void minProj1() {

        /*   נקודות רגילות מהאפליקציה

         */
        double scalingFactor = 20.0;

        Point A = new Point(0 * scalingFactor, 2 * scalingFactor, 0);
        Point B = new Point(-1.05 * scalingFactor, 1.46 * scalingFactor, 0);
        Point C = new Point(-1.51 * scalingFactor, 0.49 * scalingFactor, 0);
        Point D = new Point(-1.34 * scalingFactor, -0.58 * scalingFactor, 0);
        Point E = new Point(-0.71 * scalingFactor, -1.33 * scalingFactor, 0);
        Point F = new Point(0.39 * scalingFactor, -1.49 * scalingFactor, 0);
        Point G = new Point(1.58 * scalingFactor, -0.91 * scalingFactor, 0);
        Point H = new Point(1.81 * scalingFactor, 0.4 * scalingFactor, 0);
        Point I = new Point(1.25 * scalingFactor, 1.54 * scalingFactor, 0);
        Point J = new Point(0.2 * scalingFactor, 0.96 * scalingFactor, 0.55 * scalingFactor);
        Point K = new Point(-0.27 * scalingFactor, 0.64 * scalingFactor, 0.54 * scalingFactor);
        Point L = new Point(-0.5 * scalingFactor, 0 * scalingFactor, 0.62 * scalingFactor);
        Point M = new Point(0.01 * scalingFactor, -0.52 * scalingFactor, 0.47 * scalingFactor);
        Point N = new Point(0.64 * scalingFactor, -0.43 * scalingFactor, 0.5 * scalingFactor);
        Point O = new Point(0.93 * scalingFactor, 0.21 * scalingFactor, 0.52 * scalingFactor);
        Point P = new Point(0.72 * scalingFactor, 0.67 * scalingFactor, 0.64 * scalingFactor);
        Point Q = new Point(0.65 * scalingFactor, 1.85 * scalingFactor, 0);
        Point R = new Point(0.17 * scalingFactor, -0.15 * scalingFactor, 0);
        Point S = new Point(-0.21 * scalingFactor, -0.3 * scalingFactor, 0.53 * scalingFactor);
        Point T = new Point(0.43 * scalingFactor, -0.1 * scalingFactor, 0);
        Point U = new Point(0.55 * scalingFactor, 0.24 * scalingFactor, 0);
        Point V = new Point(0.29 * scalingFactor, 0.51 * scalingFactor, 0);
        Point W = new Point(-0.07 * scalingFactor, 0.50 * scalingFactor, 0);
        Point Z = new Point(-0.19 * scalingFactor, 0.08 * scalingFactor, 0);
        Point A1 = new Point(-0.14 * scalingFactor, 0.3 * scalingFactor, 0);
        Point B1 = new Point(-0.17 * scalingFactor, 0.28 * scalingFactor, 0.06 * scalingFactor);




/* ההזה של האלכסון, אבל נמרח
// Change the y-coordinates of the points to create a slant effect
        double slantFactor = 0; // Adjust the slant factor as per your desired slant angle

        A = new Point(A.getX(), A.getY() + slantFactor * A.getX(), A.getZ());
        B = new Point(B.getX(), B.getY() + slantFactor * B.getX(), B.getZ());
        C = new Point(C.getX(), C.getY() + slantFactor * C.getX(), C.getZ());
        D = new Point(D.getX(), D.getY() + slantFactor * D.getX(), D.getZ());
        E = new Point(E.getX(), E.getY() + slantFactor * E.getX(), E.getZ());
        F = new Point(F.getX(), F.getY() + slantFactor * F.getX(), F.getZ());
        G = new Point(G.getX(), G.getY() + slantFactor * G.getX(), G.getZ());
        H = new Point(H.getX(), H.getY() + slantFactor * H.getX(), H.getZ());
        I = new Point(I.getX(), I.getY() + slantFactor * I.getX(), I.getZ());
        J = new Point(J.getX(), J.getY() + slantFactor * J.getX(), J.getZ());
        K = new Point(K.getX(), K.getY() + slantFactor * K.getX(), K.getZ());
        L = new Point(L.getX(), L.getY() + slantFactor * L.getX(), L.getZ());
        M = new Point(M.getX(), M.getY() + slantFactor * M.getX(), M.getZ());
        N = new Point(N.getX(), N.getY() + slantFactor * N.getX(), N.getZ());
        O = new Point(O.getX(), O.getY() + slantFactor * O.getX(), O.getZ());
        P = new Point(P.getX(), P.getY() + slantFactor * P.getX(), P.getZ());
        Q = new Point(Q.getX(), Q.getY() + slantFactor * Q.getX(), Q.getZ());
        R = new Point(R.getX(), R.getY() + slantFactor * R.getX(), R.getZ());
        S = new Point(S.getX(), S.getY() + slantFactor * S.getX(), S.getZ());
        T = new Point(T.getX(), T.getY() + slantFactor * T.getX(), T.getZ());
        U = new Point(U.getX(), U.getY() + slantFactor * U.getX(), U.getZ());
        V = new Point(V.getX(), V.getY() + slantFactor * V.getX(), V.getZ());
        W = new Point(W.getX(), W.getY() + slantFactor * W.getX(), W.getZ());
        Z = new Point(Z.getX(), Z.getY() + slantFactor * Z.getX(), Z.getZ());
        A1 = new Point(A1.getX(), A1.getY() + slantFactor * A1.getX(), A1.getZ());
        B1 = new Point(B1.getX(), B1.getY() + slantFactor * B1.getX(), B1.getZ());


 */
        //היפוך Xו Z, רואים ממש מהצד
        /*
        double scalingFactor = 20.0;

        Point A = new Point(0, 0, 2 * scalingFactor);
        Point B = new Point(0, 1.46 * scalingFactor, -1.05 * scalingFactor);
        Point C = new Point(0, 0.49 * scalingFactor, -1.51 * scalingFactor);
        Point D = new Point(0, -0.58 * scalingFactor, -1.34 * scalingFactor);
        Point E = new Point(0, -1.33 * scalingFactor, -0.71 * scalingFactor);
        Point F = new Point(0, -1.49 * scalingFactor, 0.39 * scalingFactor);
        Point G = new Point(0, -0.91 * scalingFactor, 1.58 * scalingFactor);
        Point H = new Point(0, 0.4 * scalingFactor, 1.81 * scalingFactor);
        Point I = new Point(0, 1.54 * scalingFactor, 1.25 * scalingFactor);
        Point J = new Point(0.55 * scalingFactor, 0.96 * scalingFactor, 0.2 * scalingFactor);
        Point K = new Point(0.54 * scalingFactor, 0.64 * scalingFactor, -0.27 * scalingFactor);
        Point L = new Point(0.62 * scalingFactor, 0, -0.5 * scalingFactor);
        Point M = new Point(0.47 * scalingFactor, -0.52 * scalingFactor, 0.01 * scalingFactor);
        Point N = new Point(0.5 * scalingFactor, -0.43 * scalingFactor, 0.64 * scalingFactor);
        Point O = new Point(0.52 * scalingFactor, 0.21 * scalingFactor, 0.93 * scalingFactor);
        Point P = new Point(0.64 * scalingFactor, 0.67 * scalingFactor, 0.72 * scalingFactor);
        Point Q = new Point(0, 1.85 * scalingFactor, 0.65 * scalingFactor);
        Point R = new Point(0, -0.15 * scalingFactor, 0.17 * scalingFactor);
        Point S = new Point(0.53 * scalingFactor, -0.3 * scalingFactor, -0.21 * scalingFactor);
        Point T = new Point(0, -0.1 * scalingFactor, 0.43 * scalingFactor);
        Point U = new Point(0, 0.24 * scalingFactor, 0.55 * scalingFactor);
        Point V = new Point(0, 0.51 * scalingFactor, 0.29 * scalingFactor);
        Point W = new Point(0, 0.50 * scalingFactor, -0.07 * scalingFactor);
        Point Z = new Point(0, 0.08 * scalingFactor, -0.19 * scalingFactor);
        Point A1 = new Point(0, 0.3 * scalingFactor, -0.14 * scalingFactor);
        Point B1 = new Point(0.06 * scalingFactor, 0.28 * scalingFactor, -0.17 * scalingFactor);



         */
        Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(200, 200).setVPDistance(1000);
        Scene scene4 = new Scene.SceneBuilder("Test scene").setAmbientLight(new AmbientLight(new Color(WHITE), 0.15)).build();


        scene4.geometries.add( //

                new Triangle(D, L, E) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(50, 20, 20)),

                new Triangle(L,M,E) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(50, 20, 20)),

                new Triangle(E,M,F) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(50, 20, 20)),


                new Triangle(M,N,F) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(50, 20, 20)),


                new Triangle(N,G,F) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(50, 20, 20)),


                new Triangle(N,O,G) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(50, 20, 20)),


                new Triangle(O,H,G) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(50, 20, 20)),

                new Triangle(O,P,H) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(50, 20, 20)),


                new Triangle(P,I,H) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(50, 20, 20)),


                new Triangle(J,I,P) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(50, 20, 20)),


                new Triangle(Q,I,J) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(50, 20, 20)),


                new Triangle(A,Q,J) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(50, 20, 20)),


                new Triangle(B,A,J) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(50, 20, 20)),


                new Triangle(B,K,J) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(50, 20, 20)),



                new Triangle(B,C,K) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(50, 20, 20)),


                new Triangle(C,D,L) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(50, 20, 20)),


                new Triangle(K,L,C) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(50, 20, 20)),


                new Triangle(O,U,P) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(50, 20, 20)),


                new Triangle(U,V,P) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(50, 20, 20)),


                new Triangle(V,P,J) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(50, 20, 20)),


                new Triangle(V,K,J) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(50, 20, 20)),


                new Triangle(W,V,K) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(50, 20, 20)),


                new Triangle(A1,W,K) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(50, 20, 20)),


                new Triangle(Z,L,A1) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(50, 20, 20)),


                new Triangle(A1,L,K) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(50, 20, 20)),


                new Triangle(Z,S,L) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(50, 20, 20)),


                new Triangle(S,M,R) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(50, 20, 20)),


                new Triangle(Z,S,R) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(50, 20, 20)),


                new Triangle(M,R,N) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(50, 20, 20)),


                new Triangle(R,T,N) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(50, 20, 20)),


                new Triangle(T,N,O) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(50, 20, 20)),


                new Triangle(T,U,O) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(50, 20, 20)));
       /* A = new Point(-A.getX(), A.getY(), A.getZ());
        B = new Point(-B.getX(), B.getY(), B.getZ());
        C = new Point(-C.getX(), C.getY(), C.getZ());
        D = new Point(-D.getX(), D.getY(), D.getZ());
        E = new Point(-E.getX(), E.getY(), E.getZ());
        F = new Point(-F.getX(), F.getY(), F.getZ());
        G = new Point(-G.getX(), G.getY(), G.getZ());
        H = new Point(-H.getX(), H.getY(), H.getZ());
        I = new Point(-I.getX(), I.getY(), I.getZ());
        J = new Point(-J.getX(), J.getY(), J.getZ());
        K = new Point(-K.getX(), K.getY(), K.getZ());
        L = new Point(-L.getX(), L.getY(), L.getZ());
        M = new Point(-M.getX(), M.getY(), M.getZ());
        N = new Point(-N.getX(), N.getY(), N.getZ());
        O = new Point(-O.getX(), O.getY(), O.getZ());
        P = new Point(-P.getX(), P.getY(), P.getZ());
        Q = new Point(-Q.getX(), Q.getY(), Q.getZ());
        R = new Point(-R.getX(), R.getY(), R.getZ());
        S = new Point(-S.getX(), S.getY(), S.getZ());
        T = new Point(-T.getX(), T.getY(), T.getZ());
        U = new Point(-U.getX(), U.getY(), U.getZ());
        V = new Point(-V.getX(), V.getY(), V.getZ());
        W = new Point(-W.getX(), W.getY(), W.getZ());
        Z = new Point(-Z.getX(), Z.getY(), Z.getZ());
        A1 = new Point(-A1.getX(), A1.getY(), A1.getZ());



        */
        scene4.geometries.add( //

                new Triangle(D, L, E) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(50, 20, 20)),

                new Triangle(L,M,E) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(50, 20, 20)),

                new Triangle(E,M,F) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(50, 20, 20)),


                new Triangle(M,N,F) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(50, 20, 20)),


                new Triangle(N,G,F) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(50, 20, 20)),


                new Triangle(N,O,G) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(50, 20, 20)),


                new Triangle(O,H,G) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(50, 20, 20)),

                new Triangle(O,P,H) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(50, 20, 20)),


                new Triangle(P,I,H) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(50, 20, 20)),


                new Triangle(J,I,P) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(50, 20, 20)),


                new Triangle(Q,I,J) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(50, 20, 20)),


                new Triangle(A,Q,J) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(50, 20, 20)),


                new Triangle(B,A,J) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(50, 20, 20)),


                new Triangle(B,K,J) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(50, 20, 20)),



                new Triangle(B,C,K) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(50, 20, 20)),


                new Triangle(C,D,L) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(50, 20, 20)),


                new Triangle(K,L,C) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(50, 20, 20)),


                new Triangle(O,U,P) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(50, 20, 20)),


                new Triangle(U,V,P) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(50, 20, 20)),


                new Triangle(V,P,J) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(50, 20, 20)),


                new Triangle(V,K,J) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(50, 20, 20)),


                new Triangle(W,V,K) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(50, 20, 20)),


                new Triangle(A1,W,K) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(50, 20, 20)),


                new Triangle(Z,L,A1) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(50, 20, 20)),


                new Triangle(A1,L,K) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(50, 20, 20)),


                new Triangle(Z,S,L) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(50, 20, 20)),


                new Triangle(S,M,R) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(50, 20, 20)),


                new Triangle(Z,S,R) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(50, 20, 20)),


                new Triangle(M,R,N) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(50, 20, 20)),


                new Triangle(R,T,N) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(50, 20, 20)),


                new Triangle(T,N,O) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(50, 20, 20)),


                new Triangle(T,U,O) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setEmission(new Color(50, 20, 20)));

        scene4.lights.add( //
                new SpotLight(new Color(500, 600, 400),
                        new Point(-100, -100, 500),
                        new Vector(-1, -1, -2)) //
                        .setKl(0.0004).setKq(0.0000006));

        ImageWriter imageWriter = new ImageWriter("DONUT", 600, 600);
        camera.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene4)) //
                .renderImage() //
                .writeToImage();

    }
}
