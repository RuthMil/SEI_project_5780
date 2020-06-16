package scene;

import elements.*;
import geometries.*;
import primitives.*;

import java.util.LinkedList;
import java.util.List;

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
    private List<LightSource> _lights;
    /**
     * Scene Constructor
     * @param _name name of scene
     */
    public Scene(String _name) {
        this._name = _name;
        this._geometries = new Geometries();
        this._lights = new LinkedList<LightSource>();
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
     * Getter for lights
     * @return lights in a scene
     */
    public List<LightSource> get_lights() {
        return _lights;
    }

    /**
     * Add geometries to the geometries in the scene
     * @param geometries one or more Intersectable
     */
    public void addGeometries(Intersectable... geometries){
        this._geometries.add(geometries);
    }

    /**
     * Add the light sources in a scene
     * @param lights light source in a scene
     */
    public void addLights(LightSource... lights) {
        if(lights == null){
            return;
        }

        for (LightSource lightSource: lights
             ) {
            _lights.add(lightSource);
        }
    }
}
