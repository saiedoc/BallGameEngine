package collision;

import models.TexturedModel;
import org.lwjgl.util.vector.Vector3f;
import physics.RigidBody;
import toolbox.Maths;

public class BoxCollision {

    private CollisionResponse response = new CollisionResponse();
    protected TexturedModel model;

    public BoxCollision() {

    }

    public void checkCollision(RigidBody marble, BoundingBox box, float cor) {

        Vector3f closestPoint = ClosestPointBoxPoint(box, marble.getEntity().getPosition());

        boolean isCollide = OverlapSphereBox(closestPoint, marble);


        if (isCollide && !box.getBalls().contains(marble)) {
         //   box.getBalls().clear();
            box.getBalls().add(marble);
            response.response(marble, closestPoint, box.getMass(), cor);


        } else if (!OverlapSphereBox(closestPoint, marble)) {
            box.getBalls().remove(marble);
        }
    }

    public Vector3f ClosestPointBoxPoint(BoundingBox box, Vector3f point) {
        Vector3f temp = max(point, box.getMin());
        Vector3f result = min(temp, box.getMax());
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
