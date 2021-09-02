package toolbox;

import org.lwjgl.util.vector.Vector3f;

public class NumericalIntegrator {

    public  static  Vector3f  copy(Vector3f vec)
    {
      return  new Vector3f(vec.x,vec.y,vec.z);
    }


    public static Vector3f Multiply(double scalar,Vector3f vector3f){

        float nX,nY,nZ;

        nX = (float) (scalar * vector3f.getX());
        nY = (float) (scalar * vector3f.getY());
        nZ = (float) (scalar * vector3f.getZ());

        return new Vector3f(nX, nY, nZ);


    }



    public static class Force{


        public static Vector3f MassAndAcceleration(double mass, Vector3f acceleration){

            Vector3f acc = copy(acceleration);
            Vector3f ans =  (Vector3f) acc.scale((float) mass);
            return new Vector3f(ans.x,ans.y,ans.z);

        }

        public static Vector3f AerodynamicDrag(Vector3f velocity,float radius){

            Vector3f newVelocity = copy(velocity);
            Vector3f velocitySquared   = new Vector3f(newVelocity.x*newVelocity.x,newVelocity.y*newVelocity.y,newVelocity.z*newVelocity.z);
            Vector3f ans =  (Vector3f) velocitySquared.scale(((-0.5f)*1.225f* 3.14f * radius * radius * 0.4350f));
            return new Vector3f(ans.x,ans.y,ans.z);
        }

    }

    public static class Conversion{

        public static float VectorToScalar(Vector3f vector){

            return (float) Math.sqrt(Math.pow(vector.x,2)+Math.pow(vector.y,2)+Math.pow(vector.z,2));
        }


    }

    public static class Acceleration{


        public static Vector3f MassAndForce(double mass,Vector3f Force){

            Vector3f newForce = copy(Force);
            return  (Vector3f) newForce.scale((float) ((float) 1/mass));
        }

        public static Vector3f VelocityAndTime(double time,Vector3f velocity){


            Vector3f newVelocity = copy(velocity);

            float x = (float) (newVelocity.x/(Math.pow(time*100,2)));
            float y = (float) (newVelocity.y/(Math.pow(time*100,2)));
            float z = (float) (newVelocity.z/(Math.pow(time*100,2)));
            return new Vector3f(x,y,z);

        }


        public static float frictionAcceleration(float frictionCOE){
            return frictionCOE * 9.810f;

        }



    }



    public static class Velocity {

         public static Vector3f AccelerationAndTime(Vector3f initialVelocity, Vector3f acceleration,float time){

             Vector3f newAcceleration = copy(acceleration);
             Vector3f newInitialVelocity = copy(initialVelocity);

             float newTime = time * 10;
             return newInitialVelocity.translate(((Vector3f) newAcceleration.scale(newTime)).x,((Vector3f) newAcceleration.scale(newTime)).y,((Vector3f) newAcceleration.scale(newTime)).z);


         }

         public static Vector3f DistanceAndTime(Vector3f distance3f,float time){
             Vector3f distance = copy(distance3f);
             return (Vector3f) distance.scale(1/time*1000);
         }


    }


    public static class distance{


        public static float AccelerationAndTime(Vector3f initialVelocity, Vector3f acceleration,float time){

            Vector3f newAcceleration = copy(acceleration);
            Vector3f newInitialVelocity = copy(initialVelocity);

            Vector3f AccelerationTimeSquared = (Vector3f) newAcceleration.scale((float) (Math.pow(time,2)/2));
            Vector3f distanceV3F = ((Vector3f) newInitialVelocity.scale(time)).translate(AccelerationTimeSquared.x,AccelerationTimeSquared.y,AccelerationTimeSquared.z);

            return (float) Math.sqrt(Math.pow(distanceV3F.x,2) + Math.pow(distanceV3F.y,2) + Math.pow(distanceV3F.z,2));

        }


        public static Vector3f AccelerationAndTimeVec(Vector3f initialVelocity, Vector3f acceleration,float time){


            Vector3f newAcceleration = copy(acceleration);
            Vector3f newInitialVelocity = copy(initialVelocity);

            Vector3f AccelerationTimeSquared = (Vector3f) newAcceleration.scale((float) (Math.pow(time,2)/2));

            return ((Vector3f) newInitialVelocity.scale(time)).translate(AccelerationTimeSquared.x,AccelerationTimeSquared.y,AccelerationTimeSquared.z);

        }

        public static float VelocityAndTime(Vector3f initialVelocity, Vector3f velocity,float time){

            Vector3f newVelocity = copy(velocity);
            Vector3f newInitialVelocity = copy(initialVelocity);

            Vector3f VelocityAddInitVelocity = newVelocity.translate(newInitialVelocity.x,newInitialVelocity.y,newInitialVelocity.z);
            Vector3f distanceV3F = (Vector3f) VelocityAddInitVelocity.scale(time/2);
            return (float) Math.sqrt(Math.pow(distanceV3F.x,2) + Math.pow(distanceV3F.y,2) + Math.pow(distanceV3F.z,2));

        }


        public static Vector3f VecVelocityAndTime(Vector3f initialVelocity, Vector3f velocity,float time){

            Vector3f newVelocity = copy(velocity);
            Vector3f newInitialVelocity = copy(initialVelocity);

            Vector3f VelocityAddInitVelocity = newVelocity.translate(newInitialVelocity.x,newInitialVelocity.y,newInitialVelocity.z);
            return (Vector3f) VelocityAddInitVelocity.scale(time/2);

        }

    }


    public static class Torque {


        public static Vector3f AngularAccAndInertia(float MomentOfInertia, Vector3f angularAcceleration) {

            Vector3f newAngularAcceleration = copy(angularAcceleration);
            return (Vector3f) newAngularAcceleration.scale(MomentOfInertia);

        }

        public static Vector3f AngularVelAndInertiaAndTime(Vector3f AngularVelocity,float Inertia,float time){

            Vector3f newAngularVelocity = copy(AngularVelocity);
            return (Vector3f)  newAngularVelocity.scale(Inertia/time);
        }

        public static Vector3f ForceAndRadius(Vector3f force,float radius){

            Vector3f newForce = copy(force);
            return (Vector3f) newForce.scale(radius);
        }

        public static float AngularFriction(float friction,float radius){

            return friction * radius;

        }

    }


    public static class AngularAcceleration{


        public static Vector3f TorqueAndInertia(Vector3f Torque,float Inertia){

            Vector3f newTorque = copy(Torque);
            return (Vector3f) newTorque.scale(1/Inertia);


        }


    }


    public static class AngularVelocity{

        public static Vector3f AngularAccAndTime(Vector3f initialAngularVelocity, Vector3f AngularAcc,float time){

            Vector3f newAngularAcc = copy(AngularAcc);
            Vector3f newInitialAngularVelocity = copy(initialAngularVelocity);
            return newInitialAngularVelocity.translate(((Vector3f) newAngularAcc.scale(time)).x,((Vector3f) newAngularAcc.scale(time)).y,((Vector3f) newAngularAcc.scale(time)).z);

        }

        public static Vector3f RotationAndTime(Vector3f rotation3f,float time){

            Vector3f distance = copy(rotation3f);
            return (Vector3f) distance.scale(1/time);

        }


    }

    public static class Rotation{

        public static Vector3f AngularAccelerationAndTimeVec(Vector3f initialVelocity, Vector3f acceleration,float time){

            Vector3f newAcceleration = copy(acceleration);
            Vector3f newInitialVelocity = copy(initialVelocity);

            Vector3f AccelerationTimeSquared = (Vector3f) newAcceleration.scale((float) (Math.pow(time,2)/2));

            return ((Vector3f) newInitialVelocity.scale(time)).translate(AccelerationTimeSquared.x,AccelerationTimeSquared.y,AccelerationTimeSquared.z);

        }

    }


    public static class Collision{

        public static float FirstBodyVelocity(float mass1,float mass2,float collisionCoefficient,Vector3f velocity){

            Vector3f newVelocity = copy(velocity);


            float vel = Conversion.VectorToScalar(newVelocity);
             return ((mass1 - collisionCoefficient*mass2)/(mass1 + mass2)) * vel;


        }

        public static float SecondBodyVelocity(float mass1,float mass2,float collisionCoefficient,Vector3f velocity){

            float vel = Conversion.VectorToScalar(velocity);

            return (((1 + collisionCoefficient)*mass1)/(mass1 + mass2)) * vel;

        }



    }



}
