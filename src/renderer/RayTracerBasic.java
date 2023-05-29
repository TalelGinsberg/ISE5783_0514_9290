package renderer;

import geometries.Intersectable.GeoPoint;
import lighting.LightSource;
import primitives.*;
import scene.Scene;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

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

    /**
     * The maximum level of color calculation recursion.
     * Higher values allow for more accurate shading but increase computation time.
     */
    private static final int MAX_CALC_COLOR_LEVEL = 10;

    /**
     * The minimum value of the reflection/refraction coefficient for color calculation recursion termination.
     * When the reflection/refraction coefficient is below this threshold, the recursion terminates.
     * This helps to prevent infinite recursion and artifacts.
     */
    private static final double MIN_CALC_COLOR_K = 0.001;

    private static  final  Double3 INITIAL_K = Double3.ONE;

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
     * Checks if a point on a surface is unshaded by other objects in the scene, given a light source.
     *
     * @param gp          The geometric point on the surface to check for shading
     * @param lightSource The light source illuminating the scene
     * @param l           The direction vector from the point towards the light source
     * @param n           The surface normal at the point
     * @param nl          The dot product between the surface normal and the light direction
     * @return {@code true} if the point is unshaded, {@code false} otherwise
     */
    private boolean unshaded(GeoPoint gp, LightSource lightSource, Vector l, Vector n, double nl) {
        try {
            // Calculate the direction vector from the point to the light source
            Vector lightDirection = l.scale(-1);

            // Calculate an offset vector to slightly move the point away from the surface
            // This helps avoid self-intersections with the surface itself
            Vector epsVector = n.scale((n.dotProduct(lightDirection) > 0 ? DELTA : -DELTA));

            // Move the point slightly away from the surface in the direction of the light source
            Point point = gp.point.add(epsVector);

            // Create a ray from the point towards the light source
            Ray lightRay = new Ray(point, lightDirection);

            // Find intersections between the ray and the geometry in the scene
            List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);

            // If no intersections found, the point is unshaded
            if (intersections == null)
                return true;

            // Check if any intersection point is closer to the light source than the current point
            for (GeoPoint intersectionPoint : intersections) {
                if(intersectionPoint.geometry.getMaterial().kT.lowerThan(MIN_CALC_COLOR_K) ||
                        lightSource.getDistance(gp.point) > point.distance(intersectionPoint.point))
                    return false;
                /*
                if (lightSource.getDistance(gp.point) > point.distance(intersectionPoint.point))
                    return false;
                 */
            }

            // If no closer intersection found, the point is unshaded
            return true;
        } catch (IllegalArgumentException e) {
            // In case of an exception, consider the point as shaded to be on the safe side
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
    /**
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

     */

/*
    private Color calcColor(GeoPoint gp, Ray ray, int level, Double3 k) {
        Color color = scene.ambientLight.getIntensity()
                .add(calcLocalEffects(gp, ray));
        return 1 == level ? color : color.add(calcGlobalEffects(gp, ray, level, k));
    }

 */

    private Color calcColor(GeoPoint intersection, Ray ray, int level, Double3 k) {
        Color color = calcLocalEffects(intersection, ray);
        return 1 == level ? color
                : color.add(calcGlobalEffects(intersection, ray, level, k));}


    private Color calcColor(GeoPoint geopoint, Ray ray) {
        return calcColor(geopoint, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K)
                .add(scene.ambientLight.getIntensity());
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
                if (unshaded(gp, lightSource, l, n, nl)) {
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

    private Color calcGlobalEffect(Ray ray, int level, Double3 k, Double3 kx) {
        Double3 kkx = k.product(kx);
        if (kkx.lowerThan(MIN_CALC_COLOR_K)) return Color.BLACK;
        GeoPoint gp = findClosestIntersection(ray);
        if (gp == null) return scene.background.scale(kx);
        return isZero(gp.geometry.getNormal(gp.point).dotProduct(ray.getDir()))? Color.BLACK :
                calcColor(gp, ray, (level-1), kkx);
    }

    private Color calcGlobalEffects(GeoPoint gp, Ray ray, int level, Double3 k) {
        Color color = Color.BLACK;
        Vector v = ray.getDir();
        Vector n = gp.geometry.getNormal(gp.point);
        Material material = gp.geometry.getMaterial();
        return calcGlobalEffect(constructReflectedRay(gp.point, v, n),level, k, material.kR)
                .add(calcGlobalEffect(constructRefractedRay(gp.point, v, n),level, k, material.kT));}

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


    /**
     * Constructs a refracted ray based on the given point, incident vector, and surface normal.
     *
     * @param point The point of intersection.
     * @param v     The incident vector.
     * @param n     The surface normal.
     * @return The refracted ray.
     */
    private Ray constructRefractedRay(Point point, Vector v, Vector n) {

        double nv = alignZero(v.dotProduct(n.normalize()));

        // Compute the scaled offset vector to prevent self-intersections and shadow acne.
        // The offset is computed as the incident vector scaled by the DELTA constant.
        Vector delta = v.scale(DELTA);

        // If the dot product of v and the normalized surface normal is negative,
        // reverse the direction of the offset vector to ensure it points inside the object.
        if (nv < 0)
            delta = delta.scale(-1);

        // Compute the refracted ray direction as the normalized surface normal.
        Vector refractedDirection = n.normalize();

        // Create a new ray with the refracted direction starting from the given point with the offset applied.
        return new Ray(point.add(delta), refractedDirection);

    }


    /**
     * Constructs a reflected ray based on the given point, incident vector, and surface normal.
     *
     * @param pointGeo The point of intersection.
     * @param v        The incident vector.
     * @param n        The surface normal.
     * @return The reflected ray.
     */
    private Ray constructReflectedRay(Point pointGeo, Vector v, Vector n) {
        //r = v - 2.(v.n).n
        double vn = alignZero(v.dotProduct(n));

        // If the dot product of v and n is zero, the incident vector is parallel to the surface normal,
        // resulting in no reflection. Return null in this case.
        if (vn == 0) {
            return null;
        }

        // Calculate the reflection direction using the formula: r = v - 2 * (v dot n) * n
        Vector r = v.subtract(n.scale(2 * vn));

        // Create a new ray with the reflected direction starting from the given point.
        return new Ray(pointGeo, r);
    }


    /**
     * Finds the closest intersection point between the given ray and the geometries in the scene.
     *
     * @param ray The ray for which to find the closest intersection.
     * @return The closest intersection point, or null if no intersection is found.
     */
    private GeoPoint findClosestIntersection(Ray ray) {

        // Find all intersections between the ray and the geometries in the scene.
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(ray);

        // If there are no intersections, return null to indicate no intersection found.
        if (intersections == null) {
            return null;
        }

        // Find the closest intersection point from the ray's origin among all the intersections.
        return ray.findClosestGeoPoint(intersections);
    }



    //---------------------------override functions-------------------------

    @Override
    public Color traceRay(Ray ray) {
        // Find the closest intersections between the ray and geometries in the scene
        GeoPoint point  = this.findClosestIntersection(ray);

        // If no intersections are found, return the background color of the scene
        if (point == null)
            return scene.background;

        // Calculate the color at the closest intersection point
        return calcColor(point, ray);
    }
}