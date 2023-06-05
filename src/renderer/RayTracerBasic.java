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


    private Double3 transparency(GeoPoint geoPoint, LightSource ls, Vector l, Vector n) {
        // Pay attention to your method of distance screening
        Vector lightDirection = l.scale(-1); // from point to light source
        Point point = geoPoint.point;
        Ray lightRay = new Ray(point, n, lightDirection);

        double maxdistance = ls.getDistance(point);
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);
        if (intersections == null)
            return Double3.ONE;

        // Remove intersection points that are farther away from the light source than the current point
        intersections.removeIf(intersectionPoint -> point.distance(intersectionPoint.point) >= maxdistance);


        Double3 ktr = Double3.ONE;
        // loop over intersections and for each intersection which is closer to the
        // point than the light source multiply ktr by 𝒌𝑻 of its geometry.
        // Performance:
        // if you get close to 0 –it’s time to get out( return 0)
        for (var geo : intersections) {
            // if (ls.getDistance(geoPoint.point) >= geoPoint.point.distance(geo.point)) {
            ktr = ktr.product(geo.geometry.getMaterial().kT);
            if (ktr.lowerThan(MIN_CALC_COLOR_K)) {
                return Double3.ZERO;
            }
        }

        return ktr;
    }


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

            /*
             for (GeoPoint intersectionPoint : intersections) {
                if (lightSource.getDistance(gp.point) > point.distance(intersectionPoint.point))
                    return false;
             }
             */
            // If no closer intersection found, the point is unshaded
            return true;
        } catch (IllegalArgumentException e) {
            // In case of an exception, consider the point as shaded to be on the safe side
            return false;
        }
    }


    /*  שלב 7 בהתחלה
    private Color calcColor(GeoPoint gp, Ray ray, int level, Double3 k) {
        Color color = scene.ambientLight.getIntensity()
                .add(calcLocalEffects(gp, ray));
        return 1 == level ? color : color.add(calcGlobalEffects(gp, ray, level, k));
    }
    */


    private Color calcColor(GeoPoint intersection, Ray ray, int level, Double3 k) {

        Color color = calcLocalEffects(intersection, ray, k);

        return 1 == level ? color
                : color.add(calcGlobalEffects(intersection, ray, level, k));


    }

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

    private Color calcLocalEffects(GeoPoint gp, Ray ray, Double3 k) {
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
                //if (unshaded(gp, lightSource, l, n, nl)){
                Double3 ktr = transparency(gp, lightSource, l, n);
                if (!ktr.product(k).lowerThan(MIN_CALC_COLOR_K)) {

                    Color iL = lightSource.getIntensity(gp.point).scale(ktr); // Intensity of the light source at the intersection point

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


        GeoPoint gp = findClosestIntersection(ray);
        return (gp == null ? scene.background : calcColor(gp, ray, level - 1, kx)).scale(k);


    }



    private Color calcGlobalEffects(GeoPoint gp, Ray ray, int level, Double3 k) {

        Vector v = ray.getDir();
        Vector n = gp.geometry.getNormal(gp.point);
        Material material = gp.geometry.getMaterial();
        Color color = Color.BLACK;
        Double3 kkr = material.kR.product(k);
        if (!kkr.lowerThan(MIN_CALC_COLOR_K))
            color = color.add(calcGlobalEffect(constructReflectedRay(gp.point, v, n), level, material.kR, kkr));
        Double3 kkt = material.kT.product(k);
        if (!kkt.lowerThan(MIN_CALC_COLOR_K))
            color = color.add(
                    calcGlobalEffect(constructRefractedRay(gp.point, v, n), level, material.kT, kkt));
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
        // Calculate the diffuse reflection of light on the surface using the Lambert reflection model
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

        return new Ray(point, n, v);

        //שלב 7 בהתחלה
        /*   שלב 7 בהתחלה
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

         */

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