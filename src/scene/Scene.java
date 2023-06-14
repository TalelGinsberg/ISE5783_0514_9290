package scene;

import lighting.AmbientLight;
import geometries.Geometries;
//import lighting.Light;
//import lighting.LightSource;
import lighting.LightSource;
import primitives.Color;

import java.util.LinkedList;
import java.util.List;


/**
 * Represents a 3D scene with a name, background color, geometries, and ambient light.
 *
 * @author Noa Harel and Talel Ginsberg
 */
public class Scene {
    //----------------------------fields--------------------------
    /**The background color of the scene*/
    public final Color background;
    /**The collection of geometries in the scene*/

    public final Geometries geometries;

    /**The ambient light in the scene*/
    public final AmbientLight ambientLight;

    /**The light sources in the scene*/
    public final List<LightSource> lights;

    /**The name of the scene*/
    private final String name;
    public double delta;
    public int softShadow;
    public boolean adaptiveSuperSampling;




    //-----------------------------constructor-------------------------

    /**
     * Constructs a new Scene object using a SceneBuilder.
     *
     * @param builder the SceneBuilder to use for constructing the Scene object.
     */
    public Scene(SceneBuilder builder) {
        name = builder.name;
        background = builder.background;
        ambientLight = builder.ambientLight;
        geometries = builder.geometries;
        lights = builder.lights;
        delta=builder.delta;
        softShadow=builder.softShadow;
        adaptiveSuperSampling=builder.adaptiveSuperSampling;

    }

    //--------------------------------getters----------------------------

    /**
     * Returns the name of the scene.
     *
     * @return the name of the scene.
     */
    public String getName() {
        return name;
    }


    //-------------------------------builder--------------------------------

    /**
     * A builder class for constructing Scene objects.
     */
    public static class SceneBuilder {

        private final String name;
        private List<LightSource> lights = new LinkedList<>();
        private Color background = Color.BLACK;
        private AmbientLight ambientLight = AmbientLight.NONE;
        private Geometries geometries = new Geometries();
        private double delta=0;
        private int softShadow=0;
        private boolean adaptiveSuperSampling;


        /**
         * Constructs a new SceneBuilder object with the given name.
         *
         * @param name the name of the scene.
         */
        public SceneBuilder(String name) {
            this.name = name;
        }
        /**
         * set the number of rays in soft shadow
         * @param rayNum number of rays in a row
         * @return scene
         */
        public SceneBuilder setSoftShadow(int rayNum) {
            softShadow=rayNum;
            return this;
        }
        public SceneBuilder setDelta(double d) {
            delta = d;
            return this;
        }
        /**
         * set if the scene use adaptive Super-sampling
         * @param a
         * @return scene
         */
        public SceneBuilder setAdaptiveSuperSampling(boolean a) {
            this.adaptiveSuperSampling=a;
            return this;
        }

        /**
         * Sets the background color of the scene and returns the updated SceneBuilder object.
         *
         * @param background the background color to set.
         * @return the updated SceneBuilder object.
         */
        public SceneBuilder setBackground(Color background) {
            this.background = background;
            return this;
        }

        public SceneBuilder setLights(List<LightSource> lights) {
            this.lights = lights;
            return this;
        }

        /**
         * Sets the ambient light of the scene and returns the updated SceneBuilder object.
         *
         * @param ambientLight the ambient light to set.
         * @return the updated SceneBuilder object.
         */
        public SceneBuilder setAmbientLight(AmbientLight ambientLight) {
            this.ambientLight = ambientLight;
            return this;
        }

        /**
         * Sets the geometries in the scene and returns the updated SceneBuilder object.
         *
         * @param geometries the geometries to set.
         * @return the updated SceneBuilder object.
         */
        public SceneBuilder setGeometries(Geometries geometries) {
            this.geometries = geometries;
            return this;
        }

        /**
         * Builds a new Scene object using the current values of the SceneBuilder.
         *
         * @return a new Scene object.
         */
        public Scene build() {
            return new Scene(this);
        }

        /**
         * Reads a Scene object from an XML file and returns the updated SceneBuilder object.
         *
         * @param filename the name of the XML file to read from.
         * @return the updated SceneBuilder object.
         */
        public SceneBuilder readXmlFile(String filename) {
            //todo
            return this;
        }
    }
}
