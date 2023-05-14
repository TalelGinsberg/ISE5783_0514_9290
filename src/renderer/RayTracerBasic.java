package renderer;

import geometries.Intersectable.GeoPoint;
import lighting.LightSource;
import primitives.*;
import scene.Scene;

import java.util.List;

import static primitives.Util.alignZero;

/**
 *
 * A basic implementation of a ray tracer that extends the RayTracerBase class.
 * This class is responsible for tracing rays through the scene and calculating
 * the color at the intersection points.
 *
 * It uses the Scene object passed to it in the constructor to retrieve information
 * about the geometry, lighting, and other properties of the scene.
 *
 * @author Noa Harel and Talel Ginsberg
 */
public class RayTracerBasic extends RayTracerBase {

    //-----------------------------constructor-------------------------

    /**
     * Creates a new instance of the RayTracerBasic class with the specified scene.
     * @param scene The scene object containing information about the scene to be rendered.
     */
    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    //------------------------------functions---------------------------

    /**
     * Calculates the color at the specified geo point using the ambient light in the scene.
     * @param point The geo point at which to calculate the color.
     * @return The color at the specified geo point.
     */
    private Color calcColor(GeoPoint point, Ray ray) {
        return (scene.getAmbientLight().getIntensity())
                .add(calcLocalEffects(point, ray));

    }

    private Color calcLocalEffects(GeoPoint gp, Ray ray) {
        Color color = gp.geometry.getEmission();
        Vector v = ray.getDir();
        Vector n = gp.geometry.getNormal(gp.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0)
            return color;
        Material mat = gp.geometry.getMaterial();
        for (LightSource lightSource : scene.getLights()) {
            Vector l = lightSource.getL(gp.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) == sing(nv)
                Color iL = lightSource.getIntensity(gp.point);
                color = color.add(iL.scale(calcDiffusive(mat, nl)),
                        iL.scale(calcSpecular(mat, n, l, nl, v)));
            }
        }
        return color;
    }

    private Double3 calcSpecular(Material mat, Vector n, Vector l, double nl, Vector v) {
        Vector r = l.subtract(n.scale(l.dotProduct(n)).scale(2));
        double minusVR = alignZero(v.dotProduct(r) * -1);
        return mat.kS.scale(Math.pow(Math.max(0,minusVR),mat.nShininess));

    }

    private Double3 calcDiffusive(Material mat, double nl) {
        double nlAbs = Math.abs(nl);
        return mat.kD.scale(nlAbs);

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