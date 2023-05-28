package renderer;

import geometries.Intersectable.GeoPoint;
import lighting.LightSource;
import primitives.*;
import scene.Scene;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * This class implements a basic ray tracer algorithm for rendering images.
 * It calculates the color of each pixel in the image based on the scene geometry, lights, and materials.
 *
 * @author Noa Harel and Talel Ginsberg
 */
public class RayTracerBasic extends RayTracerBase {

    //----------------------------fields--------------------------

    /**
     * Constant for head of ray offset size for shading rays
     */
    private static final double DELTA = 0.1;

    //-----------------------------constructor-------------------------

    /**
     * Constructs a RayTracerBasic object with the given scene.
     *
     * @param scene The scene to be rendered
     */
    public RayTracerBasic(Scene scene) {
        super(scene);
    }


    //------------------------------functions---------------------------

    /**
     * Checking the absence of shading between a point and the light source
     *
     * @param gp
     * @param l
     * @param n
     * @return
     */
    private boolean unshaded(GeoPoint gp, LightSource lightSource, Vector l, Vector n,double nl) {
        try {
            Vector lightDirection = l.scale(-1); // from point to light source
            Vector epsVector = n.scale(n.dotProduct(lightDirection) > 0 ? DELTA : -DELTA);
            Point point = gp.point.add(epsVector);
            Ray lightRay = new Ray(point, lightDirection);
            List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);
            if (intersections==null)
                return true;
            for (GeoPoint intersectionPoint:intersections) {
                if (lightSource.getDistance(intersectionPoint.point) > gp.point.distance(intersectionPoint.point))
                    return false;
            }
            return true;
        }
        catch (IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * Calculates the color of the given intersection point by considering the ambient light and local effects
     * such as diffuse and specular reflections.
     *
     * @param point The intersection point on the geometry
     * @param ray   The ray that intersected with the geometry
     * @return The color at the intersection point
     */
    private Color calcColor(GeoPoint point, Ray ray) {
        // Calculate the color using ambient light and local effects
        // Start by getting the intensity of the ambient light from the scene's ambient light source
        Color ambientIntensity = scene.ambientLight.getIntensity();

        // Calculate the local effects at the intersection point
        Color localEffects = calcLocalEffects(point, ray);

        // Combine the ambient light intensity and the local effects to get the final color
        Color color = ambientIntensity.add(localEffects);

        // Return the calculated color
        return color;
    }

    /**
     * Calculates the local effects at a given intersection point, such as diffuse and specular reflections.
     *
     * @param gp  The intersection point
     * @param ray The ray that intersected with the geometry
     * @return The color of the local effects at the intersection point
     */
    private Color calcLocalEffects(GeoPoint gp, Ray ray) {
        Color color = gp.geometry.getEmission(); // Start with the emission color of the geometry

        Vector v = ray.getDir(); // View direction vector
        Vector n = gp.geometry.getNormal(gp.point); // Normal vector at the intersection point

        double nv = alignZero(n.dotProduct(v)); // Dot product between the normal and view direction vectors

        // If the dot product is close to zero, the view direction and normal are orthogonal,
        // so there is no contribution from local effects
        if (nv == 0)
            return color;

        Material material = gp.geometry.getMaterial(); // Material of the intersected geometry

        // Iterate over all light sources in the scene
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(gp.point); // Light direction vector at the intersection point
            double nl = alignZero(n.dotProduct(l)); // Dot product between the normal and light direction vectors

            // Check if the light is on the same side as the view direction
            if (nl * nv > 0) {
                if (unshaded(gp,lightSource, l, n,nl)) {
                Color iL = lightSource.getIntensity(gp.point); // Intensity of the light source at the intersection point

                // Calculate the contributions of diffuse and specular reflections
                color = color.add(
                        calcDiffusive(material.kD, nl, iL),
                        calcSpecular(material.kS, n, l, nl, v, iL, material.nShininess)
                );
            }
        }
    }
        return color;
}

    /**
     * Calculates the specular reflection color at a given intersection point.
     *
     * @param kS         The specular reflection coefficient of the material
     * @param n          The surface normal vector at the intersection point
     * @param l          The direction vector towards the light source
     * @param nl         The dot product between the surface normal and light direction vectors
     * @param v          The view direction vector
     * @param iL         The intensity of the light source at the intersection point
     * @param nShininess The shininess factor of the material
     * @return The color resulting from the specular reflection at the intersection point
     */
    private Color calcSpecular(Double3 kS, Vector n, Vector l, double nl, Vector v, Color iL, int nShininess) {
        Vector r = l.subtract(n.scale(nl * 2)); // Calculate the reflection direction vector
        double minusVR = -alignZero(v.dotProduct(r)); // Calculate the dot product between the view direction and reflection direction vectors
        // If the dot product is less than or equal to zero, the reflection is in the opposite direction of the view,
        // so there is no contribution from specular reflection
        if (minusVR <= 0)
            return Color.BLACK;

        Double3 shine = kS.scale(Math.pow(minusVR, nShininess)); // Calculate the specular reflection color based on the shininess factor
        return iL.scale(shine); // Scale the color by the intensity of the light source
    }


    /**
     * Calculates the diffuse reflection color at a given intersection point.
     *
     * @param kD The diffuse coefficient of the material
     * @param nl The dot product between the normal and light direction vectors
     * @param iL The intensity of the light source
     * @return The calculated diffuse reflection color at the given intersection point
     */
    private Color calcDiffusive(Double3 kD, double nl, Color iL) {
        // Calculate the diffuse reflection of light on the surface using the Lambertian reflection model
        // Start by scaling the diffuse coefficient with the absolute value of the dot product between the surface normal and the light direction
        Double3 diffuseCoefficient = kD.scale(Math.abs(nl));

        // Scale the intensity of the light source with the diffuse coefficient to get the resulting color
        Color diffuseColor = iL.scale(diffuseCoefficient);

        // Return the calculated diffuse color
        return diffuseColor;
    }


    //---------------------------override functions-------------------------

    @Override
    public Color traceRay(Ray ray) {
        List<GeoPoint> points = scene.geometries.findGeoIntersections(ray); // Find intersections between the ray and geometries in the scene

        if (points == null)
            return scene.background; // If no intersections are found, return the background color of the scene

        return calcColor(ray.findClosestGeoPoint(points), ray); // Calculate the color at the closest intersection point
    }
}