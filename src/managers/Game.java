package managers;

import collision.BoundingBox;
import collision.Ground;
import physics.RigidBody;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private static Game game = null;
    public static List<RigidBody> allMarbles;
    public static ArrayList<BoundingBox> walls = new ArrayList<>();
    public static Ground ground;
    //  private static ObjectManager objectManager;

    public static void startGame() {
        if (Game.game == null) {
            //  Game.objectManager = ObjectManager.getObjectManager();
            Game.game = new Game();
        }

    }



}
