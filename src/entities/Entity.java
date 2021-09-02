package entities;

import models.RawModel;
import models.TexturedModel;
import org.lwjgl.util.vector.Vector3f;
import renderEngine.DisplayManager;
import terrains.Terrain;
import toolbox.ForceEffector;

public class Entity {

    protected TexturedModel textureModel;
    protected RawModel rawModel;
    protected Vector3f position;
    protected float rotX, rotY, rotZ;
    protected Vector3f scale;


    public Entity() {

    }

    public Entity(TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ, Vector3f scale) {
        this.textureModel = model;
        this.position = position;
        this.rotX = rotX;
        this.rotY = rotY;
        this.rotZ = rotZ;
        this.scale = scale;

    }


    public void IncPosition(float dx, float dy, float dz) {
        this.position.x += dx;
        this.position.y += dy;
        this.position.z += dz;

    }

    public void IncPositionV(Vector3f vector3f) {
        this.position.y += vector3f.y;
        this.position.x += vector3f.x;
        this.position.z += vector3f.z;
        //  this.position.translate(vector3f.x,vector3f.y,vector3f.z);

    }

    public void IncRotationV(Vector3f vector3f) {
        this.rotX += vector3f.x;
        this.rotY += vector3f.y;
        this.rotZ += vector3f.z;
    }

    public void IncRotation(float dx, float dy, float dz) {
        this.rotX += dx;
        this.rotY += dy;
        this.rotZ += dz;

    }


    public TexturedModel getModel() {
        return textureModel;
    }

    public void setTextureModel(TexturedModel textureModel) {
        this.textureModel = textureModel;
    }

    public RawModel getRawModel() {
        return rawModel;
    }

    public void setRawModel(RawModel rawModel) {
        this.rawModel = rawModel;
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public float getRotX() {
        return rotX;
    }

    public void setRotX(float rotX) {
        this.rotX = rotX;
    }

    public float getRotY() {
        return rotY;
    }

    public void setRotY(float rotY) {
        this.rotY = rotY;
    }

    public float getRotZ() {
        return rotZ;
    }

    public void setRotZ(float rotZ) {
        this.rotZ = rotZ;
    }

    public Vector3f getScale() {
        return scale;
    }

    public void setScale(Vector3f scale) {
        this.scale = scale;
    }

}
