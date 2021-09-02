package collision;

import org.lwjgl.util.vector.Vector3f;
import physics.RigidBody;
import terrains.Terrain;
import toolbox.Maths;

public class SphereCollision {

    private CollisionResponse  response = new CollisionResponse();

    public void checkSphereTerrainCollision(RigidBody marble, float cor, Terrain terrain){
        Vector3f marblePosition = marble.getEntity().getPosition();
        float pointHeight = terrain.getHeightOfTerrain( marblePosition.x, marblePosition.z);

        if (Maths.Distance(marble.getEntity().getPosition(), new Vector3f(
                marblePosition.x, pointHeight, marblePosition.z)) < marble.getRadius()) {
            marble.getEntity().getPosition().setY(pointHeight + marble.getRadius());
        }
    }

    public void checkCollision(RigidBody marble1, RigidBody marble2, float cor) {
         boolean isCollide = OverlapSphereSphere(marble1, marble2);
        if (isCollide && (!marble1.getResponselist().contains(marble2) ||
                !marble2.getResponselist().contains(marble1))) {
            response.response(marble1,marble2, cor);
            marble1.getResponselist().clear();
            marble2.getResponselist().clear();
            marble1.getResponselist().add(marble2);
            marble2.getResponselist().add(marble1);
        }
        else  if(!OverlapSphereSphere(marble1, marble2)&&marble1.getResponselist().contains(marble2)&&marble2.getResponselist().contains(marble1)){

           marble1.getResponselist().remove(marble2);
           marble2.getResponselist().remove(marble1);
        }

    }



    public Vector3f ClosestPointSpherePoint(RigidBody sphere, Vector3f point) {
        Vector3f result = null;

        result = Vector3f.sub(sphere.getEntity().getPosition(), point, result);
        result.normalise();

        result.scale(sphere.getRadius());

        Vector3f.add(sphere.getEntity().getPosition(), result, result);

        return result;
    }

    public boolean OverlapSphereSphere(RigidBody sphere1, RigidBody sphere2) {
        double distance = Maths.Distance(sphere1.getEntity().getPosition(), sphere2.getEntity().getPosition());
        return distance < (sphere1.getRadius() + sphere2.getRadius());
    }

}
