package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;

import java.util.List;

public class RayTracerBasic extends RayTracerBase{
    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    @Override
    public Color traceRay(Ray ray) {

        List<Point> points = scene.getGeometries().findIntersections(ray);
        //System.out.println(points);
        if (points==null)
            return scene.getBackground();
        return calcColor(ray.findClosestPoint(points));
    }
    private Color calcColor(Point point)
    {
        return scene.getAmbientLight().getIntensity();
    }
}
