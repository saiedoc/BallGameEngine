package collision;

import org.lwjgl.util.vector.Vector3f;
import physics.RigidBody;
import toolbox.Maths;

public class GroundCollision {

    private CollisionResponse response = new CollisionResponse();

    public GroundCollision() {

    }

    public void checkCollision(RigidBody marble, Ground ground, float cor) {

        Vector3f closestPoint = ClosestPointBoxPoint(ground, marble.getEntity().getPosition());

        boolean isCollide = OverlapSphereBox(closestPoint, marble);

        if (isCollide && !ground.getBalls().contains(marble)) {

            response.responseGround(marble, closestPoint, ground.getMass(), cor);
         //    ground.getBalls().clear();
            ground.getBalls().add(marble);
            marble.setApplyGravity(false);

        } else if (!OverlapSphereBox(closestPoint, marble)) {
            ground.getBalls().remove(marble);

            marble.setApplyGravity(true);
        }
    }

    public Vector3f ClosestPointBoxPoint(Ground ground, Vector3f point) {
        Vector3f temp = max(point, ground.getMin());
        Vector3f result = min(temp, ground.getMax());

        return result;
    }

    private Vector3f max(Vector3f vec1, Vector3f vec2) {
        return new Vector3f(
                Math.max(vec1.x, vec2.x),
                Math.max(vec1.y, vec2.y),
                Math.max(vec1.z, vec2.z)
        );
    }

    private Vector3f min(Vector3f vec1, Vector3f vec2) {
        return new Vector3f(
                Math.min(vec1.x, vec2.x),
                Math.min(vec1.y, vec2.y),
                Math.min(vec1.z, vec2.z)
        );
    }

    private boolean OverlapSphereBox(Vector3f vec1, RigidBody rg) {
        float distance = (float) Maths.Distance(vec1, rg.getEntity().getPosition());
        return distance < (rg.getRadius());
    }


}
