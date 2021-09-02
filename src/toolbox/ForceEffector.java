package toolbox;

import entities.Entity;
import org.lwjgl.util.vector.Vector;
import org.lwjgl.util.vector.Vector3f;
import physics.Time;
import renderEngine.DisplayManager;

import java.util.ArrayList;
import java.util.Date;

public class ForceEffector {

    private final Vector3f iniAngVelocity = new Vector3f(0,0,0);
    private final Vector3f iniDistance = new Vector3f(0,0,0);
    private final Vector3f Gravity = new Vector3f(0,-9.810f,0);


    public  void applyMotion(Entity entity, Vector3f iniVelocity, Vector3f acceleration, float mass, float time) {
      Vector3f force =  NumericalIntegrator.distance.AccelerationAndTimeVec(iniVelocity,acceleration,time);
      entity.IncPositionV(force);
    }

    public void applyRotation(Entity entity,Vector3f AngularAcceleration, float Inertia, float time){

        Vector3f force =  NumericalIntegrator.Rotation.AngularAccelerationAndTimeVec(iniAngVelocity,AngularAcceleration,time);
        entity.IncRotationV(force);
    }

}
