package engineTester;

import java.util.*;


import collision.BoundingBox;
import entities.Entity;
import guis.GuiRenderer;
import guis.GuiTexture;
import managers.Game;
import managers.ObjectManager;
import models.RawModel;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import physics.RigidBody;
import physics.Time;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.MasterRenderer;
import entities.Camera;
import entities.Light;
import textures.ModelTexture;
import ui.MarblesGUI;

public class MainGameLoop {


    private static MarblesGUI marbleGUI;

    public static void startSimulation(){

        DisplayManager.createDisplay();
        Loader loader = new Loader();

        Light light = new Light(new Vector3f(20000, 20000, 2000), new Vector3f(1, 1, 1));


        MasterRenderer renderer = new MasterRenderer(loader);
        List<GuiTexture> guis = new ArrayList<GuiTexture>();
        GuiTexture gui = new GuiTexture(loader.loadTexture("guitest"), new Vector2f(-0.8f, -0.7f), new Vector2f(0.1f, 0.15f));
        //guis.add(gui);

        GuiRenderer guiRenderer = new GuiRenderer(loader);





        /**
         * work space
         * */



        /**
         * Creating Marbles
         * **/


        RigidBody marble1 = ObjectManager.createMarble("happy face", loader, new Vector3f(20, 10, -16), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), 1, 3);
//        RigidBody marble2 = ObjectManager.createMarble("basketball", loader, new Vector3f(15, 10, -15), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), 1, 3);
//        RigidBody marble3 = ObjectManager.createMarble("right", loader, new Vector3f(17, 10, -20), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), 1, 3);
//        RigidBody marble4 = ObjectManager.createMarble("Heart-Eyes emoji", loader, new Vector3f(22, 10, -11), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), 1, 3);
//        RigidBody marble5 = ObjectManager.createMarble("Smiling emoji", loader, new Vector3f(18, 10, -30), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), 1, 3);
//        RigidBody marble6 = ObjectManager.createMarble("space", loader, new Vector3f(16, 10, -25), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), 1, 3);
//        RigidBody marble7 = ObjectManager.createMarble("Covid19", loader, new Vector3f(27, 10, -26), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), 1, 3);
//        RigidBody marble8 = ObjectManager.createMarble("glass", loader, new Vector3f(21, 10, -28), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), 1, 3);
//        RigidBody marble9 = ObjectManager.createMarble("colors", loader, new Vector3f(25, 10, -29), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), 1, 3);
//        RigidBody marble10 = ObjectManager.createMarble("mud", loader, new Vector3f(23, 15, -30), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), 1, 3);

        /**
         *  more marble
         * */
     /*   RigidBody marble11 = ObjectManager.createMarble("white", loader, new Vector3f(30, 15, -34), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), 1, 3);
        RigidBody marble12 = ObjectManager.createMarble("white", loader, new Vector3f(35, 15, -15), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), 1, 3);
        RigidBody marble13 = ObjectManager.createMarble("white", loader, new Vector3f(32, 15, -15), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), 1, 3);
        RigidBody marble14 = ObjectManager.createMarble("white", loader, new Vector3f(25, 15, -15), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), 1, 3);
        RigidBody marble15 = ObjectManager.createMarble("grass", loader, new Vector3f(12, 15, -15), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), 1, 3);
        RigidBody marble16 = ObjectManager.createMarble("white", loader, new Vector3f(20, 2525, -16), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), 1, 3);
        RigidBody marble17 = ObjectManager.createMarble("white", loader, new Vector3f(15, 25, -15), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), 1, 3);
        RigidBody marble18 = ObjectManager.createMarble("white", loader, new Vector3f(17, 25, -20), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), 1, 3);
        RigidBody marble19 = ObjectManager.createMarble("white", loader, new Vector3f(20, 25, -11), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), 1, 3);
        RigidBody marble20 = ObjectManager.createMarble("white", loader, new Vector3f(18, 25, -26), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), 1, 3);
        RigidBody marble21 = ObjectManager.createMarble("white", loader, new Vector3f(16, 25, -25), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), 1, 3);
        RigidBody marble22 = ObjectManager.createMarble("white", loader, new Vector3f(20, 25, -26), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), 1, 3);
        RigidBody marble23 = ObjectManager.createMarble("white", loader, new Vector3f(21, 25, -28), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), 1, 3);
        RigidBody marble24 = ObjectManager.createMarble("white", loader, new Vector3f(25, 25, -29), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), 1, 3);
        RigidBody marble25 = ObjectManager.createMarble("white", loader, new Vector3f(23, 25, -28), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), 1, 3);
        RigidBody marble26 = ObjectManager.createMarble("white", loader, new Vector3f(30, 25, -29), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), 1, 3);
        RigidBody marble27 = ObjectManager.createMarble("white", loader, new Vector3f(28, 25, -15), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), 1, 3);
        RigidBody marble28 = ObjectManager.createMarble("white", loader, new Vector3f(27, 25, -15), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), 1, 3);
        RigidBody marble29 = ObjectManager.createMarble("white", loader, new Vector3f(25, 25, -15), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), 1, 3);
        RigidBody marble30 = ObjectManager.createMarble("grass", loader, new Vector3f(12, 25, -15), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), 1, 3);
*/


        /**
         * adding Force for each marble object
         * **/

//        marble1.addForce(new Vector3f(35, 0, 1f));
//        marble2.addForce(new Vector3f(35, 0, 1f));
//        marble3.addForce(new Vector3f(3500, 0, 350f));
//        marble4.addForce(new Vector3f(3500, 0, 350f));
//        marble5.addForce(new Vector3f(3500, 0, 350f));
//        marble6.addForce(new Vector3f(3500, 0, 350f));
//        marble7.addForce(new Vector3f(3500, 0, 350f));
//        marble8.addForce(new Vector3f(3500, 0, 350f));
//        marble9.addForce(new Vector3f(3500, 0, 350f));
//        marble10.addForce(new Vector3f(3500, 0, 350f));

        /**
         * more marvble
         * */


      /*  marble11.addForce(new Vector3f(3500, 0, 350f));
        marble12.addForce(new Vector3f(3500, 0, 350f));
        marble13.addForce(new Vector3f(3500, 0, 350f));
        marble14.addForce(new Vector3f(3500, 0, 350f));
        marble15.addForce(new Vector3f(3500, 0, 350f));
        marble16.addForce(new Vector3f(3500, 0, 350f));
        marble17.addForce(new Vector3f(3500, 0, 350f));
        marble18.addForce(new Vector3f(3500, 0, 350f));
        marble19.addForce(new Vector3f(3500, 0, 350f));
        marble20.addForce(new Vector3f(3500, 0, 350f));
        marble21.addForce(new Vector3f(3500, 0, 350f));
        marble22.addForce(new Vector3f(3500, 0, 350f));
        marble23.addForce(new Vector3f(3500, 0, 350f));
        marble24.addForce(new Vector3f(3500, 0, 350f));
        marble25.addForce(new Vector3f(3500, 0, 350f));
        marble26.addForce(new Vector3f(3500, 0, 350f));
        marble27.addForce(new Vector3f(3500, 0, 350f));
        marble29.addForce(new Vector3f(3500, 0, 350f));
        marble28.addForce(new Vector3f(3500, 0, 350f));
        marble30.addForce(new Vector3f(3500, 0, 350f));
*/


        /**
         * Creating Walls
         * **/

        ObjectManager.createWall("offwhite",loader, new Vector3f(-25, 1, -10), 1, 13, 100,"right"); // right x=0,zs=50 // x = -25 , zs = 100
        ObjectManager.createWall("offwhite",loader, new Vector3f(75, 1, -10), 1, 13, 100,"left"); // left x=50,zs=50 // x = 75 , zs = 100
        ObjectManager.createWall("gray",loader, new Vector3f(25, 1, -60), 100, 13, 1,"down"); // down z=-35,xs=50 // z = 60 , xs = 100
        ObjectManager.createWall("gray",loader, new Vector3f(25, 1, 40), 100, 13, 1,"top"); // top z=15,xs=50 // z = 40 , xs = 100


        /**
         *  Hole of Game
         * **/
        Entity hole = ObjectManager.createCube("black",loader,new Vector3f(5,0.8f,10));

        /**
         * Creating Game Ground
         * **/

        ObjectManager.createGround("stone",loader, new Vector3f(25, 0, -10), 100, 100);

        Game.startGame(); // starting game objects

        Camera camera = new Camera(Game.ground.getEntity());


        while (!Display.isCloseRequested()) {


            if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
                marbleGUI.closeSettings();
                break;
            }

            camera.move();

            renderer.processEntity(hole);

            for (RigidBody x : Game.allMarbles) {
                renderer.processEntity(x.getEntity());
                x.move();
            }

            for (BoundingBox x : Game.walls) {
                renderer.processEntity(x.getEntity());
            }


            renderer.processEntity(Game.ground.getEntity());


            ObjectManager.updateCollisions();

            renderer.render(light, camera);

            guiRenderer.render(guis);

            DisplayManager.updateDisplay();



            Time.IncTime();


        }
        marbleGUI.closeSettings();
        guiRenderer.cleanUp();
        renderer.cleanUp();
        loader.cleanUp();
        DisplayManager.closeDisplay();

    }

    public static void main(String[] args) {

       marbleGUI =  new MarblesGUI();


    }

}

