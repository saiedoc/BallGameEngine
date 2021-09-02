package collision;

import entities.Entity;
import models.TexturedModel;
import org.lwjgl.util.vector.Vector3f;
import physics.RigidBody;

import java.util.ArrayList;
import java.util.List;

public class Ground {

    private TexturedModel model;
    private Entity entity;

    private float mass = 3f;

    private Vector3f min;
    private Vector3f max;

    private Vector3f center;

    private List<RigidBody> balls = new ArrayList<>();

    public Ground(TexturedModel model, Vector3f center, float width, float height) {

        this.center = new Vector3f(center.x,center.y+0.5f,center.z);

        this.entity = new Entity(model,this.center,0,0,0,new Vector3f(width,1,height));


        this.min = new Vector3f(
                this.center.x - (width / 2),
                this.center.y - 0.5f,
                this.center.z - (height / 2));

        this.max = new Vector3f(
                this.center.x + (width / 2),
                this.center.y + 0.5f,
                this.center.z + (height / 2));
    }

    public Vector3f getMax() {
        return max;
    }

    public Vector3f getMin() {
        return min;
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
