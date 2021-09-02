package collision;


import entities.Entity;
import models.TexturedModel;
import org.lwjgl.util.vector.Vector3f;
import physics.RigidBody;

import java.util.ArrayList;
import java.util.List;

public class BoundingBox {

    private String name;
    private TexturedModel model;
    private Entity entity;

    private float mass = 3f;

    private Vector3f min;
    private Vector3f max;

    private Vector3f center;


    private List<RigidBody> balls = new ArrayList<>();

    public BoundingBox(TexturedModel model, Vector3f center, float xs, float ys, float zs,String id) {

        name = id;
        this.center = new Vector3f(center.x, center.y + (ys / 2), center.z);
        this.entity = new Entity(model, this.center, 0, 0, 0, new Vector3f(xs, ys, zs));
        this.center = center;

        this.min = new Vector3f(
                (this.center.x - xs),
                (this.center.y - ys),
                (this.center.z - zs));

        this.max = new Vector3f(
                (this.center.x + xs),
                (this.center.y + ys),
                (this.center.z + zs));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Vector3f getMax() {
        return max;
    }

    public Vector3f getMin() {
        return min;
    }

    public Vector3f[] GetCorners() {
        Vector3f[] corners = new Vector3f[8];

        corners[0] = new Vector3f(min.x, max.y, max.z);
        corners[1] = new Vector3f(max.x, max.y, max.z);
        corners[2] = new Vector3f(max.x, min.y, max.z);
        corners[3] = new Vector3f(min.x, min.y, max.z);
        corners[4] = new Vector3f(min.x, max.y, min.z);
        corners[5] = new Vector3f(max.x, max.y, min.z);
        corners[6] = new Vector3f(max.x, min.y, min.z);
        corners[7] = new Vector3f(min.x, min.y, min.z);

        return corners;
    }

    public float getMass() {
        return mass;
    }

    public Entity getEntity() {
        return entity;
    }

    public List<RigidBody> getBalls() {
        return balls;
    }
}
