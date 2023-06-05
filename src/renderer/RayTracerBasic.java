package renderer;

import geometries.Intersectable.GeoPoint;
import lighting.LightSource;
import primitives.*;
import scene.Scene;

import java.util.List;

import static primitives.Util.*;

/**
 * This class implements a basic ray tracer algorithm for rendering images.
 * It calculates the color of each pixel in the image based on the scene geometry, lights, and materials.
 *
 * @author Noa Harel and Talel Ginsberg
 */
public class RayTracerBasic extends RayTracerBase {

    //----------------------------fields--------------------------


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

    /**
     * The initial value for the coefficient of attenuation in the recursive color calculation.
     * It represents no attenuation, resulting in full color contribution at the initial level.
     */
    private static final Double3 INITIAL_K = Double3.ONE;

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
     * Calculates the transparency coefficient for a given intersection point with respect to a light source.
     * The transparency coefficient represents the cumulative transparency of all intersected geometries between
     * the intersection point and the light source.
     *
     * @param geoPoint The intersection point between the ray and the geometry
     * @param ls       The light source
     * @param l        The light direction vector
     * @param n        The surface normal vector
     * @return The transparency coefficient
     */

    private Double3 transparency(GeoPoint geoPoint, LightSource ls, Vector l, Vector n) {
        // Compute the direction from the point to the light source
        Vector lightDirection = l.scale(-1);
        Point point = geoPoint.point;

        // Create a ray from the point towards the light source
        Ray lightRay = new Ray(point, n, lightDirection);

        // Compute the maximum distance to the light source
        double maxDistance = ls.getDistance(point);

        // Find intersections of the ray with geometries in the scene
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);

        // If there are no intersections, the transparency coefficient is 1 (fully transparent)
        if (intersections == null)
            return Double3.ONE;

        // Initialize the transparency coefficient to 1 (fully transparent)
        Double3 ktr = Double3.ONE;

        // Iterate over the intersections and compute the transparency coefficient
        for (var geo : intersections) {
            // Check if the distance between the intersection point and the geometry is within the maximum distance to the light source
            if (point.distance(geo.point) <= maxDistance) {
                // Multiply the transparency coefficient by the transparency factor of the intersected geometry's material
                ktr = ktr.product(geo.geometry.getMaterial().kT);

                // If the transparency coefficient falls below the minimum calculation threshold, return a fully opaque value (0 transparency)
                if (ktr.lowerThan(MIN_CALC_COLOR_K)) {
                    return Double3.ZERO;
                }
            }
        }

        return ktr;
    }

/*
    private boolean unshaded(GeoPoint gp, LightSource lightSource, Vector l, Vector n, double nl) {

        try {
            // Calculate the direction vector from the point to the light source
            Vector lightDirection = l.scale(-1);

            // Calculate an offset vector to slightly move the point away from the surface
            // This helps avoid self-intersections with the surface itself
            //Vector epsVector = n.scale((n.dotProduct(lightDirection) > 0 ? DELTA : -DELTA));


            // Move the point slightly away from the surface in the direction of the light source
            //Point point = gp.point.add(epsVector);

            // Create a ray from the point towards the light source
            Ray lightRay = new Ray(gp.point, n, lightDirection);

            // Find intersections between the ray and the geometry in the scene
            List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);

            // If no intersections found, the point is unshaded
            if (intersections == null)
                return true;

            // Check if any intersection point is closer to the light source than the current point
            for (GeoPoint intersectionPoint : intersections) {
                if (lightSource.getDistance(gp.point) < gp.point.distance(intersectionPoint.point)) {
                    // Remove intersection points that are farther away from the light source than the current point
                    intersections.remove(intersectionPoint);
                }
            }

            Double3 tr = Double3.ONE;
            for (var geo : intersections) {
                // Calculate the transmission coefficient (tr) for each intersection point
                tr = tr.product(geo.geometry.getMaterial().kT);

                // Check if the transmission coefficient is below the minimum threshold (MIN_CALC_COLOR_K)
                if (tr.lowerThan(MIN_CALC_COLOR_K)) {
                    // If the transmission coefficient is below the threshold, the point is considered fully transparent
                    // and further calculations are not required
                    return false;
                }
            }


//             for (GeoPoint intersectionPoint : intersections) {
//                if (lightSource.getDistance(gp.point) > point.distance(intersectionPoint.point))
//                    return false;
//             }

            // If no closer intersection found, the point is unshaded

            return true;
        } catch (IllegalArgumentException e) {
            // In case of an exception, consider the point as shaded to be on the safe side
            return false;
        }
    }
 */

    /**
     * Calculates the color of a given intersection point based on the local and global effects.
     * This method takes into account the maximum recursion level and the transparency coefficient.
     *
     * @param intersection The intersection point between the ray and the geometry
     * @param ray          The ray that intersected with the geometry
     * @param level        The current recursion level
     * @param k            The transparency coefficient
     * @return The color of the intersection point
     */
    private Color calcColor(GeoPoint intersection, Ray ray, int level, Double3 k) {
        // Calculate the color considering the local effects at the intersection point
        Color color = calcLocalEffects(intersection, ray, k);

        // Check if the recursion level has reached the maximum limit
        if (1 == level)
            return color;

        // Add the color contributions from the global effects
        color = color.add(calcGlobalEffects(intersection, ray, level, k));

        return color;
    }

    /**
     * Calculates the color of a given intersection point based on the local and global effects.
     * This method takes into account the maximum recursion level and the initial transparency coefficient.
     *
     * @param geopoint The intersection point between the ray and the geometry
     * @param ray      The ray that intersected with the geometry
     * @return The color of the intersection point
     */
    private Color calcColor(GeoPoint geopoint, Ray ray) {
        // Calculate the color considering the local and global effects
        Color color = calcColor(geopoint, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K);

        // Add the intensity of the ambient light to the calculated color
        color = color.add(scene.ambientLight.getIntensity());

        return color;
    }


    /**
     * Calculates the local effects at a given intersection point, such as diffuse and specular reflections.
     *
     * @param gp  The intersection point
     * @param ray The ray that intersected with the geometry
     * @param k   The coefficient for global effects
     * @return The color of the local effects at the intersection point
     */
    private Color calcLocalEffects(GeoPoint gp, Ray ray, Double3 k) {
        // Start with the emission color of the geometry
        Color color = gp.geometry.getEmission();

        // View direction vector
        Vector v = ray.getDir();

        // Normal vector at the intersection point
        Vector n = gp.geometry.getNormal(gp.point);

        // Dot product between the normal and view direction vectors
        double nv = alignZero(n.dotProduct(v));

        // If the dot product is close to zero, the view direction and normal are orthogonal,
        // so there is no contribution from local effects
        if (nv == 0)
            return color;

        // Material of the intersected geometry
        Material material = gp.geometry.getMaterial();

        // Iterate over all light sources in the scene
        for (LightSource lightSource : scene.lights) {
            // Light direction vector at the intersection point
            Vector l = lightSource.getL(gp.point);

            // Dot product between the normal and light direction vectors
            double nl = alignZero(n.dotProduct(l));

            // Check if the light is on the same side as the view direction
            if (nl * nv > 0) {
                //if (unshaded(gp, lightSource, l, n, nl)){

                // Calculate the transparency coefficient
                Double3 ktr = transparency(gp, lightSource, l, n);

                // If the transparency coefficient is not too small
                if (!ktr.product(k).lowerThan(MIN_CALC_COLOR_K)) {
                    // Intensity of the light source at the intersection point
                    Color iL = lightSource.getIntensity(gp.point).scale(ktr);

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
     * Calculates the global effect of reflection or refraction for a given ray.
     *
     * @param ray   The ray for which to calculate the global effect.
     * @param level The current recursion level.
     * @param k     The coefficient of the global effect.
     * @param kx    The coefficient of the global effect multiplied by the material's reflection or refraction coefficient.
     * @return The color representing the global effect.
     */
    private Color calcGlobalEffect(Ray ray, int level, Double3 k, Double3 kx) {
        // Find the closest intersection point between the ray and the scene
        GeoPoint gp = findClosestIntersection(ray);

        // If there is no intersection, return the background color of the scene
        // Otherwise, calculate the color at the intersection point recursively
        // using the calcColor method and decrease the recursion level by 1
        Color color = (gp == null ? scene.background : calcColor(gp, ray, level - 1, kx));

        // Scale the calculated color by the global effect coefficient, k
        return color.scale(k);
    }

    /**
     * Calculates the global effects (reflection and refraction) on the color at the given intersection point.
     * This method recursively traces rays for reflection and refraction to determine the overall color at the intersection point.
     * It takes into account the material properties of the intersected geometry, such as reflection coefficient (kR) and
     * refraction coefficient (kT), to compute the reflection and refraction effects.
     *
     * @param gp    The intersection point on the geometry.
     * @param ray   The ray that intersected the geometry.
     * @param level The recursion level, indicating the number of reflection and refraction bounces.
     * @param k     The accumulated attenuation factor, representing the amount of light that has been absorbed.
     *              It is multiplied by the material's reflection (kR) or refraction (kT) coefficient at each recursion level.
     * @return The color with global effects.
     */
    private Color calcGlobalEffects(GeoPoint gp, Ray ray, int level, Double3 k) {
        // Get the direction of the incident ray
        Vector v = ray.getDir();

        // Get the normal vector at the intersection point
        Vector n = gp.geometry.getNormal(gp.point);

        // Get the material of the intersected geometry
        Material material = gp.geometry.getMaterial();

        // Initialize the color with no global effects
        Color color = Color.BLACK;

        // Calculate the reflection effect
        Double3 kkr = material.kR.product(k);
        // Check if the reflection coefficient is above the minimum threshold for calculation
        if (!kkr.lowerThan(MIN_CALC_COLOR_K)) {
            // Construct the reflected ray from the intersection point
            Ray reflectedRay = constructReflectedRay(gp.point, v, n);
            // Calculate the color with reflection recursively
            color = color.add(calcGlobalEffect(reflectedRay, level, material.kR, kkr));
        }

        // Calculate the refraction effect
        Double3 kkt = material.kT.product(k);
        // Check if the refraction coefficient is above the minimum threshold for calculation
        if (!kkt.lowerThan(MIN_CALC_COLOR_K)) {
            // Construct the refracted ray from the intersection point
            Ray refractedRay = constructRefractedRay(gp.point, v, n);
            // Calculate the color with refraction recursively
            color = color.add(calcGlobalEffect(refractedRay, level, material.kT, kkt));
        }

        return color;
    }


    /**
     * Calculates the specular color based on the specular coefficient, the surface normal, light direction, view direction, light intensity, and shininess factor.
     *
     * @param kS         The specular coefficient, representing the material's surface shininess.
     * @param n          The surface normal vector.
     * @param l          The direction vector from the surface to the light source.
     * @param nl         The dot product between the surface normal and the light direction.
     * @param v          The view direction vector.
     * @param iL         The intensity of the light source.
     * @param nShininess The shininess factor, controlling the size of the specular highlight.
     * @return The specular color.
     */
    private Color calcSpecular(Double3 kS, Vector n, Vector l, double nl, Vector v, Color iL, int nShininess) {
        // Calculate the reflection vector using the surface normal and the light direction.
        Vector r = l.subtract(n.scale(nl * 2));

        // Calculate the dot product between the view direction and the reflection vector.
        double minusVR = -alignZero(v.dotProduct(r));

        // If the dot product is less than or equal to 0, return black color (no specular reflection).
        if (minusVR <= 0)
            return Color.BLACK;

        // Calculate the specular coefficient raised to the power of the shininess factor.
        Double3 shine = kS.scale(Math.pow(minusVR, nShininess));

        // Multiply the light intensity with the specular coefficient to obtain the specular color.
        return iL.scale(shine);
    }


    /**
     * Calculates the diffusive color based on the diffuse coefficient, the normal-light vector dot product, and the light intensity.
     *
     * @param kD The diffuse coefficient, representing the material's surface color.
     * @param nl The dot product between the surface normal and the light direction.
     * @param iL The intensity of the light source.
     * @return The diffusive color.
     */
    private Color calcDiffusive(Double3 kD, double nl, Color iL) {
        // Calculate the diffuse coefficient by scaling the material's surface color with the dot product.
        Double3 diffuseCoefficient = kD.scale(Math.abs(nl));

        // Multiply the light intensity with the diffuse coefficient to obtain the diffusive color.
        Color diffuseColor = iL.scale(diffuseCoefficient);

        // Return the diffusive color.
        return diffuseColor;
    }


    /**
     * Constructs a refracted ray based on the point of intersection, incident vector, and surface normal.
     * The refracted ray is created using the Snell's law.
     *
     * @param point The point of intersection.
     * @param v     The incident vector.
     * @param n     The surface normal.
     * @return A new Ray object representing the refracted ray.
     */
    private Ray constructRefractedRay(Point point, Vector v, Vector n) {
        return new Ray(point, n, v);
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
        return new Ray(pointGeo, n, r);
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
        GeoPoint point = this.findClosestIntersection(ray);

        // If no intersections are found, return the background color of the scene
        if (point == null)
            return scene.background;

        // Calculate the color at the closest intersection point
        return calcColor(point, ray);
    }
}