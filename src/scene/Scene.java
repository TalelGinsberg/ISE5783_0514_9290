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

    public final Color background;
    public final Geometries geometries;
    public final AmbientLight ambientLight;
    public final List<LightSource> lights;
    //----------------------------fields--------------------------
    private final String name;


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

        /**
         * Constructs a new SceneBuilder object with the given name.
         *
         * @param name the name of the scene.
         */
        public SceneBuilder(String name) {
            this.name = name;
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
