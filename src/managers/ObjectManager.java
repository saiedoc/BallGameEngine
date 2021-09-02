package managers;

import collision.BoundingBox;
import collision.Ground;
import entities.Entity;
import models.RawModel;
import models.TexturedModel;
import org.lwjgl.util.vector.Vector3f;
import physics.RigidBody;
import renderEngine.Loader;
import renderEngine.OBJLoader;
import textures.ModelTexture;

import java.awt.print.Book;
import java.util.ArrayList;

public class ObjectManager {

    private static final ObjectManager objectManager = new ObjectManager();

    public static final float envFact = 1f;

    public static ObjectManager getObjectManager() {

        return ObjectManager.objectManager;
    }

    private ObjectManager() {
        Game.allMarbles = new ArrayList<RigidBody>();
    }

    private static void addMarble(RigidBody marble) {
        Game.allMarbles.add(marble);
    }

    private static void addWall(BoundingBox box) {
        Game.walls.add(box);
    }

    public static void updateCollisions() {
        for (int i = 0; i < Game.allMarbles.size(); i++) {
            RigidBody marble1 = Game.allMarbles.get(i);

            for (int j = 0; j < Game.allMarbles.size(); j++) {
                RigidBody marble2 = Game.allMarbles.get(j);
                if (!marble1.equals(marble2)) {
                    marble1.checkSphereCollision(marble2);
                }
            }
            for (int j = 0; j < Game.walls.size(); j++) {

                BoundingBox box = Game.walls.get(j);
                marble1.checkBoxCollision(box);
            }
        }
        for (int i = 0; i < Game.allMarbles.size(); i++) {
            Game.allMarbles.get(i).checkGroundCollision(Game.ground);
        }
    }

    public static RigidBody createMarble(String ballTexture, Loader loader, Vector3f position, Vector3f rotation, Vector3f scale, float mass, float inertia) {

        TexturedModel model = getModel("ball", ballTexture, loader,false);
        Entity entity = new Entity(model, position, rotation.x, rotation.y, rotation.z, scale);
        RigidBody rigidBody = new RigidBody(entity, mass, inertia);
        rigidBody.setId(ballTexture);
        addMarble(rigidBody);
        return rigidBody;

    }


    public static Entity createCube(String ballTexture, Loader loader, Vector3f position) {

        TexturedModel model = getModel("abd_cube", ballTexture, loader,false);
        return new Entity(model, position, 0,0,0, new Vector3f(5,1,5));

    }



    public static BoundingBox createWall(String boxTexture, Loader loader, Vector3f center, float xs, float ys, float zs,String name) {

        TexturedModel model = getModel("abd_cube", boxTexture, loader,true);
        BoundingBox box = new BoundingBox(model,center,xs,ys,zs,name);
        addWall(box);
        return box;
    }

    public static Ground createGround(String boxTexture, Loader loader, Vector3f center, float width, float height){

        if(Game.ground == null) {
            TexturedModel model = getModel("abd_cube", boxTexture, loader,true);
            Game.ground = new Ground(model,center,width,height);
        }
        return Game.ground;
    }

    public static TexturedModel getModel(String modelName, String texture, Loader loader,boolean isFake) {

        RawModel boxModel = OBJLoader.loadObjModel(modelName, loader);
        TexturedModel model = new TexturedModel(boxModel, new ModelTexture(loader.loadTexture(texture)));
        model.getTexture().setUseFakeLighting(isFake);
        model.getTexture().setReflectivity(0);
        model.getTexture().setShineDamper(10000);
        return model;
    }



}
