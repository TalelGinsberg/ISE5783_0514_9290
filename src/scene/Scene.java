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
    private final String name;
    private final Color background;
    private final Geometries geometries;
    private AmbientLight ambientLight;

    private final List<LightSource> GetLight;


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
        GetLight = builder.lights;
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

    /**
     * Returns the background color of the scene.
     *
     * @return the background color of the scene.
     */
    public Color getBackground() {
        return background;
    }

    /**
     * Returns the ambient light of the scene.
     *
     * @return the ambient light of the scene.
     */
    public AmbientLight getAmbientLight() {
        return ambientLight;
    }

    /**
     * Returns the geometries in the scene.
     *
     * @return the geometries in the scene.
     */
    public Geometries getGeometries() {
        return geometries;
    }

    public List<LightSource> getLights() {return GetLight;}

    //-------------------------------setters--------------------------------

    /**
     * Sets the ambient light of the scene and returns the updated Scene object.
     *
     * @param ambientLight the ambient light to set.
     * @return the updated Scene object.
     */
    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
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
            //            validateObject(scene);
            return new Scene(this);
        }

        private void validateObject(Scene scene) {
            //nothing to do
        }

        /**
         * Reads a Scene object from an XML file and returns the updated SceneBuilder object.
         *
         * @param filename the name of the XML file to read from.
         * @return the updated SceneBuilder object.
         */
        public SceneBuilder readXmlFile(String filename) {
            //to do
            return this;
        }
    }
}
