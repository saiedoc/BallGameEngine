package collision;

import org.lwjgl.util.vector.Vector3f;
import physics.RigidBody;
import physics.Time;
import toolbox.NumericalIntegrator;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class CollisionResponse {

    Clip clip;

    public CollisionResponse() {
        try {
            File file = new File("res\\hit.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }


    public void response(RigidBody object1, RigidBody object2,float COR) {
        clip.flush();               // is to restart the Clip object so it
        clip.setFramePosition(0);
        clip.start();
        Vector3f r1 = new Vector3f(1, 1, 1);
        Vector3f r2 = new Vector3f(1, 1, 1);

        Vector3f v1f = new Vector3f(0, 0, 0);
        Vector3f v2f = new Vector3f(0, 0, 0);
        Vector3f w1 = new Vector3f(0, 0, 0);
        Vector3f w2 = new Vector3f(0, 0, 0);
        Vector3f fw1 = new Vector3f(0, 0, 0);
        Vector3f fw2 = new Vector3f(0, 0, 0);
        Vector3f f1 = new Vector3f(0, 0, 0);
        Vector3f f2 = new Vector3f(0, 0, 0);

        v1f = calVelocity1(object1.getEntity().getPosition(),object2.getEntity().getPosition(),object1.getAccelerationOfbody(),object2.getAccelerationOfbody(),
                object1.getMass(),object2.getMass(),COR);
        v2f = calVelocity2(object1.getEntity().getPosition(),object2.getEntity().getPosition(),object1.getAccelerationOfbody(),object2.getAccelerationOfbody(),
                object1.getMass(),object2.getMass(),COR);


        Vector3f acc1 = copy(v1f);
        Vector3f acc2 = copy(v2f);


        if (v1f.x == 0)
            r1.x = 0;
        if (v1f.y == 0)
            r1.y = 0;
        if (v1f.z == 0)
            r1.z = 0;

        if (v2f.x == 0)
            r2.x = 0;
        if (v2f.y == 0)
            r2.y = 0;
        if (v2f.z == 0)
            r2.z = 0;

        Vector3f.cross(v1f, r1, w1);
        Vector3f.cross(v2f, r2, w2);

        f1 = NumericalIntegrator.Force.MassAndAcceleration(object1.getMass(), acc1);
        f2 = NumericalIntegrator.Force.MassAndAcceleration(object2.getMass(), acc2);
        fw1 = NumericalIntegrator.Torque.AngularVelAndInertiaAndTime(w1, (float) 1, Time.getTime());
        fw2 = NumericalIntegrator.Torque.AngularVelAndInertiaAndTime(w2, (float) 1, Time.getTime());


        object1.setAccelerationOfbody(new Vector3f(0, 0, 0));
        object2.setAccelerationOfbody(new Vector3f(0, 0, 0));


        object1.addForce(f1);
        object2.addForce(f2);
       // object1.setAngularAcceleration(new Vector3f(0, 0, 0));
       // object2.setAngularAcceleration(new Vector3f(0, 0, 0));
     //   object1.addTorque(fw1);
       // object2.addTorque(fw2);


    }

    public void response(RigidBody object1, Vector3f closestPoint, float mass, float COR) {
        clip.flush();               // is to restart the Clip object so it
        clip.setFramePosition(0);
        clip.start();

        Vector3f r1 = new Vector3f(1, 1, 1);

        Vector3f v1f = new Vector3f(0, 0, 0);

        Vector3f w1 = new Vector3f(0, 0, 0);

        Vector3f fw1 = new Vector3f(0, 0, 0);

        Vector3f f1 = new Vector3f(0, 0, 0);

        v1f = calVelocity1(object1.getEntity().getPosition(),closestPoint,object1.getAccelerationOfbody()
                ,new Vector3f(0,0,0),object1.getMass(),mass,COR);

        Vector3f acc1 = copy(v1f);
        System.out.println("BEFORE : "+object1.getAccelerationOfbody());
        System.out.println("AFTER : "+acc1);

        if (v1f.x == 0)
            r1.x = 0;
        if (v1f.y == 0)
            r1.y = 0;
        if (v1f.z == 0)
            r1.z = 0;


        //Vector3f.cross(v1f, r1, w1);
        f1 = NumericalIntegrator.Force.MassAndAcceleration(object1.getMass(), acc1);
        fw1 = NumericalIntegrator.Torque.AngularVelAndInertiaAndTime(w1, (float) 1, Time.getTime());

        System.out.println("FORCE : "+f1);
        object1.setAccelerationOfbody(new Vector3f(0, 0, 0));

        object1.addForce(f1);

      //  object1.setAngularAcceleration(new Vector3f(0, 0, 0));
      //  object1.addTorque(fw1);
    }

    public void responseGround(RigidBody object1, Vector3f closestPoint, float mass, float COR) {
        clip.flush();               // is to restart the Clip object so it
        clip.setFramePosition(0);
        clip.start();

        Vector3f r1 = new Vector3f(1, 1, 1);

        Vector3f v1f = new Vector3f(0, 0, 0);

        Vector3f w1 = new Vector3f(0, 0, 0);

        Vector3f f1 = new Vector3f(0, 0, 0);

        v1f = calVelocity1(object1.getEntity().getPosition(),closestPoint,object1.getAccelerationOfbody()
                ,new Vector3f(0,0,0),object1.getMass(),mass,1);


        Vector3f acc1 = v1f;


        if (v1f.x == 0)
            r1.x = 0;
        if (v1f.y == 0)
            r1.y = 0;
        if (v1f.z == 0)
            r1.z = 0;


        Vector3f.cross(v1f, r1, w1);

        f1 = NumericalIntegrator.Force.MassAndAcceleration(object1.getMass(), acc1);


        object1.setAccelerationOfbody(new Vector3f(0, 0, 0));

        object1.addForce(f1);


    }


    public Vector3f copy(Vector3f vec) {
        return new Vector3f(vec.x, vec.y, vec.z);
    }

    public Vector3f calVelocity1(Vector3f position1,Vector3f position2,Vector3f velocity1,
                                 Vector3f velocity2,double mass1,double mass2,float COR)
    {
        Vector3f vector3f = new Vector3f();
        Vector3f vv1 = new Vector3f(-1,0,0);
        Vector3f vv2 = new Vector3f(-0.38f,0,0);
        Vector3f.cross(vv1,vv2,vector3f);
        System.out.println("RESULT : " +vector3f);

        float x = position1.x - position2.x;
        float y = position1.y - position2.y;
        float z = position1.z - position2.z;
        Vector3f x1Minusx2 = new Vector3f(x, y, z); // x1-x2
        Vector3f v1Minusv2 = (Vector3f) new Vector3f(velocity1.x - velocity2.x, velocity1.y - velocity2.y, velocity1.z - velocity2.z).scale(COR); // COR*(v1-v2)
        double x1x2 = Math.pow(Math.sqrt(Math.pow(x1Minusx2.x, 2) + Math.pow(x1Minusx2.y, 2) + Math.pow(x1Minusx2.z, 2)), 2); // ||x1-x2||^2
        double v1x1 = Vector3f.dot(v1Minusv2, x1Minusx2); // <v1-v2,x1-x2>
        double m1 = (2 * mass2) / (mass1+ mass2); // 2*m2/m1+m2
        double p11 = m1 * (v1x1 / x1x2); // mass result * dot product result for object 1
        Vector3f pv1 = (Vector3f) x1Minusx2.scale((float) p11);

        return new Vector3f(velocity1.x - pv1.x, velocity1.y - pv1.y, velocity1.z - pv1.z);
    }

    public Vector3f calVelocity2(Vector3f position1,Vector3f position2,Vector3f velocity1,
                                 Vector3f velocity2,double mass1,double mass2,float COR)
    {

        float x = position2.x - position1.x;
        float y = position2.y - position1.y;
        float z = position2.z - position1.z;
        Vector3f x2Minusx1 = new Vector3f(x, y, z); // x1-x2
        Vector3f v2Minusv1 = (Vector3f) new Vector3f(velocity2.x - velocity1.x, velocity2.y - velocity1.y, velocity2.z - velocity1.z).scale(COR); // COR*(v1-v2)
        double x2x1 = Math.pow(Math.sqrt(Math.pow(x2Minusx1.x, 2) + Math.pow(x2Minusx1.y, 2) + Math.pow(x2Minusx1.z, 2)), 2); // ||x1-x2||^2
        double v2x2 = Vector3f.dot(v2Minusv1, x2Minusx1); // <v1-v2,x1-x2>
        double m2 = (2 * mass1) / (mass1+ mass2); // 2*m2/m1+m2
        double p22 = m2 * (v2x2 / x2x1); // mass result * dot product result for object 1
        Vector3f pv2 = (Vector3f) x2Minusx1.scale((float) p22);

        return new Vector3f(velocity2.x - pv2.x, velocity2.y - pv2.y, velocity2.z - pv2.z);
    }

}
