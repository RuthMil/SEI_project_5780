package scene;

import elements.AmbientLight;
import elements.Camera;
import geometries.Geometries;
import geometries.Intersectable;
import primitives.Color;

/**
 * Scene class represents a scene by: name, background color,
 * ambient light, geometries, camera and distance
 * @author Ruth Miller
 * ruthmiller2000@gmail.com
 */
public class Scene {
    private String _name;
    private Color _background;
    private AmbientLight _ambientLight;
    private Geometries _geometries;
    private Camera _camera;
    private double _distance;

    /**
     * Scene Constructor
     * @param _name name of scene
     */
    public Scene(String _name) {
        this._name = _name;
        this._geometries = new Geometries();
    }

    /**
     * Name Getter
     * @return name of scene
     */
    public String get_name() {
        return _name;
    }

    /**
     * Background Getter
     * @return background color
     */
    public Color get_background() {
        return _background;
    }

    /**
     * AmbientLight Getter
     * @return ambient light of scence
     */
    public AmbientLight get_ambientLight() {
        return _ambientLight;
    }

    /**
     * Geometries Getter
     * @return geometries in the scene
     */
    public Geometries get_geometries() {
        return _geometries;
    }

    /**
     * Camera Getter
     * @return camera of scence
     */
    public Camera get_camera() {
        return _camera;
    }

    /**
     * Distance Getter
     * @return distance from camera to scene
     */
    public double get_distance() {
        return _distance;
    }

    /**
     * Background Setter
     * @param _background background for setting
     */
    public void set_background(Color _background) {
        this._background = _background;
    }

    /**
     * AmbientLight Setter
     * @param _ambientLight ambientLight for setting
     */
    public void set_ambientLight(AmbientLight _ambientLight) {
        this._ambientLight = _ambientLight;
    }

    /**
     * Camera Setter
     * @param _camera camera for setting
     */
    public void set_camera(Camera _camera) {
        this._camera = _camera;
    }

    /**
     * Distance Setter
     * @param _distance distance for setting
     */
    public void set_distance(double _distance) {
        this._distance = _distance;
    }

    /**
     * Add geometries to the geometries in the scene
     * @param geometries one or more Intersectable
     */
    public void addGeometries(Intersectable... geometries){
        this._geometries.add(geometries);
    }
}
