package renderer;

import geometries.Intersectable.GeoPoint;
import lighting.LightSource;
import primitives.*;
import scene.Scene;

import java.util.List;

import static primitives.Util.alignZero;


public class RayTracerBasic extends RayTracerBase {

    //-----------------------------constructor-------------------------


    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    //------------------------------functions---------------------------


    private Color calcColor(GeoPoint point, Ray ray) {
        return (scene.getAmbientLight().getIntensity()
                .add(calcLocalEffects(point, ray)));
    }

    private Color calcLocalEffects(GeoPoint gp, Ray ray) {
        Color color = gp.geometry.getEmission();
        Vector v = ray.getDir();
        Vector n = gp.geometry.getNormal(gp.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0)
            return color;
        Material material = gp.geometry.getMaterial();

        for (LightSource lightSource : scene.getLights()) {
            Vector l = lightSource.getL(gp.point);
            double nl = alignZero(n.dotProduct(l));

            // sign(nl) == sign(nv)
            if (nl * nv > 0) {
                    Color iL = lightSource.getIntensity(gp.point);
                    color = color.add(
                            calcDiffusive(material.kD, nl, iL),
                            calcSpecular(material.kS, n, l, nl, v, iL, material.nShininess)
                    );
            }
        }
        return color;
    }

    private Color calcSpecular(Double3 kS, Vector n  , Vector l, double nl, Vector v,Color iL,int nShininess) {
        Vector r = l.subtract(n.scale(nl*2));
        double minusVR =- alignZero(v.dotProduct(r) );
        if (minusVR<=0)
            return Color.BLACK;
        Double3 shine=kS.scale(Math.pow(minusVR,nShininess));
        return iL.scale(shine);

    }

    private Color calcDiffusive(Double3 kD, double nl,Color iL) {
        return iL.scale(kD.scale(Math.abs(nl)));
    }


    //---------------------------override functions-------------------------
    @Override
    public Color traceRay(Ray ray) {
        List<GeoPoint> points = scene.getGeometries().findGeoIntersections(ray);

        if (points == null)
            return scene.getBackground();

        return calcColor(ray.findClosestGeoPoint(points),ray);
    }


}