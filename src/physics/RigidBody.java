package physics;

import collision.*;
import entities.Entity;
import managers.Game;
import managers.ObjectManager;
import org.lwjgl.util.vector.Vector3f;
import toolbox.ForceEffector;
import toolbox.NumericalIntegrator;

import java.util.ArrayList;
import java.util.List;

public class RigidBody {

    private String id;
    private float mass;
    private boolean applyGravity = true;
    public static Vector3f gravity = new Vector3f(0, -9.810f, 0);
    public float frictionCOE = 0.1f;
    public static float COR = 1.0f;
    private Entity entity;
    private float inertia;
    private float time = 0.0f;
    private Vector3f angularAcceleration = new Vector3f(0, 0, 0);
    private float friction = NumericalIntegrator.Acceleration.frictionAcceleration(frictionCOE);
    private float angularFriction = NumericalIntegrator.Torque.AngularFriction(friction, 10);
    private Vector3f accelerationOfbody = new Vector3f(0, 0, 0);
    private Vector3f accelerationOfRbody = new Vector3f(0, 0, 0);
    private Vector3f iniVelocity = new Vector3f(0, 0, 0);
    private ForceEffector forceEffector = new ForceEffector();


    public Vector3f netForce;

    public Vector3f rnetForce;

    private List<RigidBody> responselist = new ArrayList<>();


    private SphereCollision sphereCollision;
    private BoxCollision boxCollision;
    private GroundCollision groundCollision;
    private final float radius = 1.00000000f;


    private ArrayList<Vector3f> forces = new ArrayList<>();
    private ArrayList<Vector3f> rforces = new ArrayList<>();
    private ArrayList<Vector3f> torques = new ArrayList<>();

    public void setMass(float mass) {
        this.mass = mass;
    }

    public List<RigidBody> getResponselist() {
        return responselist;
    }


    public void setResponselist(List<RigidBody> responselist) {
        this.responselist = responselist;
    }

    public RigidBody() {
    }


    public RigidBody(Entity entity, float mass, float inertia) {
        this.entity = entity;
        this.mass = mass;
        this.inertia = inertia;
        this.sphereCollision = new SphereCollision();
        this.boxCollision = new BoxCollision();
        this.groundCollision = new GroundCollision();
    }

    public void setFrictionCOE(float frictionCOE) {
        this.frictionCOE = frictionCOE;
        this.friction = NumericalIntegrator.Acceleration.frictionAcceleration(frictionCOE);
    }

    public void checkSphereCollision(RigidBody marble) {
        sphereCollision.checkCollision(this, marble, COR);

    }

    public void checkBoxCollision(BoundingBox box) {
        boxCollision.checkCollision(this, box, 1f);
    }

    public void checkGroundCollision(Ground ground) {
        groundCollision.checkCollision(this, ground, 1f);
    }


    public Vector3f getAccelerationOfbody() {

        return new Vector3f(accelerationOfbody.x, accelerationOfbody.y, accelerationOfbody.z);
    }

    public ArrayList<Vector3f> getForces() {
        return forces;
    }

    public void setAccelerationOfbody(Vector3f accelerationOfbody) {
        this.accelerationOfbody = accelerationOfbody;
    }


    public float getRadius() {
        return radius;
    }

    public Vector3f calculateNetForce() {
        Vector3f net = new Vector3f(0, 0, 0);
        for (Vector3f vec : forces) {
            net.translate(vec.x, vec.y, vec.z);
        }
        return net;
    }

    public Vector3f getAngularAcceleration() {
        return angularAcceleration;
    }

    public void setAngularAcceleration(Vector3f angularAcceleration) {
        this.angularAcceleration = angularAcceleration;
    }

    public boolean isApplyGravity() {
        return applyGravity;
    }

    public void setApplyGravity(boolean applyGravity) {
        this.applyGravity = applyGravity;
    }

    public Vector3f calculateNetTorque() {

        Vector3f net = new Vector3f(0, 0, 0);
        for (Vector3f vec : torques) {
            net.translate(vec.x, vec.y, vec.z);
        }
        Vector3f newAng = NumericalIntegrator.AngularAcceleration.TorqueAndInertia(net, (float) inertia);
        angularAcceleration.translate(newAng.x, newAng.y, newAng.z);
        return net;

    }

    public Vector3f getAngularVelocityOfBody() {
        return NumericalIntegrator.AngularVelocity.AngularAccAndTime(new Vector3f(0, 0, 0), angularAcceleration, (float) time);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void addForce(Vector3f force) {

        addRforce(force);
        forces.add(force);
        Vector3f newAcc = NumericalIntegrator.Acceleration.MassAndForce(mass, force);
        accelerationOfbody = accelerationOfbody.translate(newAcc.x, newAcc.y, newAcc.z);
        clearForce();
    }

    public double getMass() {
        return mass;
    }

    public Entity getEntity() {
        return entity;
    }

    public void clearForce() {
        forces.clear();
    }

    public void clearRForce() {
        rforces.clear();
    }

    public Vector3f calculateNNetForce() {
        Vector3f vec = new Vector3f(0, 0, 0);
        for (Vector3f x : rforces) {
            vec.translate(x.x, x.y, x.z);
        }
        return vec;
    }

    public void addRforce(Vector3f force) {

        Vector3f acc = NumericalIntegrator.Acceleration.MassAndForce(mass, force);
        Vector3f velocity = NumericalIntegrator.Velocity.AccelerationAndTime(new Vector3f(0, 0, 0), acc, time);
        Vector3f are = NumericalIntegrator.Force.AerodynamicDrag(velocity, radius);

        accelerationOfRbody = NumericalIntegrator.Acceleration.MassAndForce(mass, are);
        rforces.add(are);

        clearRForce();

    }

    public Vector3f getVelocityOfBody() {
        return NumericalIntegrator.Velocity.AccelerationAndTime(new Vector3f(0, 0, 0), accelerationOfbody, (float) time);

    }

    public void setTime(float time) {
        this.time = time;
    }

    private void makeTimeStep() {
        time += 0.00005d;
    }

    private void getNetForce() {

        netForce = calculateNetForce();
        rnetForce = calculateNNetForce();
        netForce.translate(rnetForce.x, rnetForce.y, rnetForce.z);

    }

    public void addTorque(Vector3f torque) {
        torques.add(torque);
        Vector3f newAcc = NumericalIntegrator.AngularAcceleration.TorqueAndInertia(torque, (float) inertia);
        angularAcceleration = angularAcceleration.translate(newAcc.x, newAcc.y, newAcc.z);
        clearTorque();

    }

    private void applyFriction() {
        if (accelerationOfbody.x < 0.0f) {

            if (Math.abs(accelerationOfbody.x) < friction) {
                accelerationOfbody.x = 0.0f;
                angularAcceleration.z = 0.0f;
                angularAcceleration.y = 0.0f;
            } else {
                accelerationOfbody.translate(friction, 0, 0);
                angularAcceleration.translate(0, 0, angularFriction);
            }

        } else if (accelerationOfbody.x > 0.0f) {

            if (Math.abs(accelerationOfbody.x) < friction) {
                accelerationOfbody.x = 0.0f;
                angularAcceleration.z = 0.0f;
                angularAcceleration.y = 0.0f;
            } else {
                accelerationOfbody.translate(-friction, 0, 0);
                angularAcceleration.translate(0, 0, -angularFriction);
            }


        }

        if (accelerationOfbody.z < 0.0f) {

            if (Math.abs(accelerationOfbody.z) < friction) {
                accelerationOfbody.z = 0.0f;
                angularAcceleration.x = 0.0f;
                angularAcceleration.y = 0.0f;
            } else {
                accelerationOfbody.translate(0, 0, friction);
                angularAcceleration.translate(angularFriction, 0, 0);
            }

        } else if (accelerationOfbody.z > 0.0f) {

            if (Math.abs(accelerationOfbody.z) < friction) {
                accelerationOfbody.z = 0.0f;
                angularAcceleration.x = 0.0f;
                angularAcceleration.y = 0.0f;
            } else {
                accelerationOfbody.translate(0, 0, -friction);
                angularAcceleration.translate(-angularFriction, 0, 0);
            }

        }

    }

    private void applyAeroDynamic() {

        float x = 0.0f;
        float y = 0.0f;
        float z = 0.0f;


        if (accelerationOfRbody.x < 0.0f) {

            if (accelerationOfbody.x < 0.0f) {
                x = -accelerationOfRbody.x;
            } else x = accelerationOfRbody.x;
        } else if (accelerationOfRbody.x > 0.0f) {

            if (accelerationOfbody.x > 0.0f) {
                x = -accelerationOfRbody.x;
            } else x = accelerationOfRbody.x;

        }


        if (accelerationOfRbody.y < 0.0f) {

            if (accelerationOfbody.y < 0.0f) {
                y = -accelerationOfRbody.y;
            } else y = accelerationOfRbody.y;
        } else if (accelerationOfRbody.y > 0.0f) {

            if (accelerationOfbody.y > 0.0f) {
                y = -accelerationOfRbody.y;
            } else y = accelerationOfRbody.y;

        }


        if (accelerationOfRbody.z < 0.0f) {

            if (accelerationOfbody.z < 0.0f) {
                z = -accelerationOfRbody.z;
            } else z = accelerationOfRbody.z;
        } else if (accelerationOfRbody.z > 0.0f) {

            if (accelerationOfbody.z > 0.0f) {
                z = -accelerationOfRbody.z;
            } else z = accelerationOfRbody.z;

        }

        accelerationOfbody.x += x / ObjectManager.envFact;
        accelerationOfbody.y += y / ObjectManager.envFact;
        accelerationOfbody.z += z / ObjectManager.envFact;


    }

    private void clearTorque() {
        torques.clear();
    }

    public void move() {

        makeTimeStep();
        getNetForce();

        if (applyGravity) {
            accelerationOfbody.translate(gravity.x, gravity.y, gravity.z);

        } else if (accelerationOfbody.y < 3.50f) {
            accelerationOfbody.y = 0.0f;
        }
//        if (entity.getPosition().y > 3.5f) {
//            applyAeroDynamic();
//        }
        if (entity.getPosition().y < 2f) {
            applyFriction();
            accelerationOfRbody.x = 0.0f;
            accelerationOfRbody.y = 0.0f;
            accelerationOfRbody.z = 0.0f;
        }
        if (entity.getPosition().y < 0.0f) {
            entity.getPosition().y = 50.0f;
            accelerationOfbody.y = 50;
        }


        forceEffector.applyMotion(entity, iniVelocity, accelerationOfbody, mass, time);
        forceEffector.applyRotation(entity, angularAcceleration, inertia, time);


    }

}
