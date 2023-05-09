package renderer;

import geometries.Intersectable.GeoPoint;
import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;

import java.util.List;

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
     * Calculates the color at the specified point using the ambient light in the scene.
     * @param point The point at which to calculate the color.
     * @return The color at the specified point.
     */
    private Color calcColor(GeoPoint point) {
        return (scene.getAmbientLight().getIntensity())
                .add(point.geometry.getEmission());

    }



    //---------------------------override functions-------------------------
    @Override
    public Color traceRay(Ray ray) {

        List<GeoPoint> points = scene.getGeometries().findGeoIntersections(ray);

        if (points == null)
            return scene.getBackground();

        return calcColor(ray.findClosestGeoPoint(points));
    }
}