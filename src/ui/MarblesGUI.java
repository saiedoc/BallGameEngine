package ui;

import engineTester.MainGameLoop;
import managers.Game;
import managers.ObjectManager;
import org.lwjgl.util.vector.Vector3f;
import physics.RigidBody;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MarblesGUI implements ActionListener {

    private final
    String[] marblesName = new String[]{
            "All","Happy face","Basketball","Sky","Heart-Eyes emoji","Smiling emoji","Space"
            ,"Covid 19","Glass","Colors","Mud"
    };

    private final Color backgroundColor = new Color(195,211,219);
    Color btnClr = new Color(30, 120, 180);
    Color txtClr = new Color(180, 180,180);
    Font font = new Font("Leelawadee UI", Font.CENTER_BASELINE, 100);
    Font font1 = new Font("Leelawadee UI", Font.PLAIN, 15);

    JFrame mainFrame = new JFrame("Marbles");
    JPanel panel = new JPanel();
    JLabel gameTitle = new JLabel("MARBLES");
    JButton newGameBtn = new JButton("New Game");
    JButton quitGameBtn = new JButton("Quit Game");
    JLabel aboutLabel = new JLabel("About");

    JFrame settingsFrame = new JFrame("Settings");
    JLabel gravityLabel = new JLabel("Gravity:");
    JTextField gravityField = new JTextField();
    JLabel selectMarble = new JLabel("Select Marble:");
    JLabel marblesMassLabel = new JLabel("Marbles Mass:");
    JTextField marblesMassField = new JTextField();
    JLabel kg = new JLabel("(kg)");
    JLabel frictionLabel = new JLabel("Friction:");
    JTextField frictionField = new JTextField();
    JLabel CORLabel = new JLabel("COR:");
    JTextField CORField = new JTextField();
    JLabel applyForceLabel = new JLabel("Apply Force");
    JLabel xLabel = new JLabel("X:");
    JTextField xField = new JTextField();
    JLabel yLabel = new JLabel("Y:");
    JTextField yField = new JTextField();
    JLabel zLabel = new JLabel("Z:");
    JTextField zField = new JTextField();
    JButton applyBtn = new JButton("Apply");
    final int INTERVAL = 70;


    JComboBox<String> marblesComboBox ;

    public MarblesGUI(){
        mainGUI();
    }

    private void mainGUI(){
        gameTitle.setBounds(170, 10, 500, 130);
        gameTitle.setFont(font);
        newGameBtn.setBounds(335, 220, 130, 45);
        quitGameBtn.setBounds(335, 280, 130, 45);
        newGameBtn.addActionListener(this);
        quitGameBtn.addActionListener(this);
        newGameBtn.setBackground(btnClr);
        newGameBtn.setForeground(txtClr);
        quitGameBtn.setBackground(btnClr);
        quitGameBtn.setForeground(txtClr);
        aboutLabel.setBounds(380, 350, 45, 15);
        aboutLabel.setForeground(btnClr);
        aboutLabel.setFont(font1);
        panel.setBounds(0,0,800,460);
        panel.setBackground(backgroundColor);
        panel.add(gameTitle,0);
        panel.add(newGameBtn,0);
        panel.add(quitGameBtn,0);
        panel.add(aboutLabel,0);
        panel.setLayout(null);
        mainFrame.setBounds(400, 175, 800,460);
        mainFrame.setBackground(backgroundColor);
        mainFrame.add(panel);
        mainFrame.setExtendedState(JFrame.MAXIMIZED_HORIZ);
        mainFrame.setResizable(false);
        mainFrame.setLayout(null);
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void closeSettings(){
        settingsFrame.dispose();
    }



    public void settingsGUI(){

        // default values
        gravityField.setText("-9.81");
        frictionField.setText("0.1");
        CORField.setText("1");
        marblesMassField.setText("0.0065");
        xField.setText("4");
        yField.setText("1");
        zField.setText("1");



        marblesComboBox = new JComboBox<>(marblesName);
      //  marblesComboBox.addNotify();

        gravityLabel.setBounds(30, 40, 50,15);
        gravityField.setBounds(160, 40, 100, 15);
        selectMarble.setBounds(30,gravityLabel.getY()+INTERVAL,150,15);
        marblesComboBox.setBounds(160,gravityLabel.getY()+INTERVAL,120,20);
        marblesMassLabel.setBounds(30, selectMarble.getY()+INTERVAL, 90, 15);
        marblesMassField.setBounds(160, selectMarble.getY()+INTERVAL, 100,15);
        kg.setBounds(265, selectMarble.getY()+INTERVAL, 25, 15);
        frictionLabel.setBounds(30, marblesMassLabel.getY()+INTERVAL, 150, 15);
        frictionField.setBounds(160, marblesMassLabel.getY()+INTERVAL, 100, 15);
        CORLabel.setBounds(30, frictionLabel.getY()+INTERVAL, 150, 15);
        CORField.setBounds(160, frictionLabel.getY()+INTERVAL, 100, 15);
        applyForceLabel.setBounds(15, CORLabel.getY()+90,100,15);
        xLabel.setBounds(30, applyForceLabel.getY()+40,50,15);
        xField.setBounds(xLabel.getX()+20, applyForceLabel.getY()+40,40,15);
        yLabel.setBounds(xField.getX()+70, applyForceLabel.getY()+40,50,15);
        yField.setBounds(yLabel.getX()+20, applyForceLabel.getY()+40,40,15);
        zLabel.setBounds(yField.getX()+70, applyForceLabel.getY()+40,50,15);
        zField.setBounds(zLabel.getX()+20, applyForceLabel.getY()+40,40,15);
        applyBtn.setBounds(105, 550,100, 35);
        applyBtn.setBackground(btnClr);
        applyBtn.setForeground(txtClr);
        applyBtn.addActionListener(this);





        gameTitle.setBounds(63, 650, 200, 100);
        gameTitle.setFont(new Font("Leelawadee UI", Font.CENTER_BASELINE, 40));
        settingsFrame.setBounds(1230, 0, 320, 850);
        settingsFrame.add(gravityLabel);
        settingsFrame.add(gravityField);
        settingsFrame.add(selectMarble);
        settingsFrame.add(marblesComboBox);
        settingsFrame.add(marblesMassLabel);
        settingsFrame.add(marblesMassField);
        settingsFrame.add(kg);
        settingsFrame.add(frictionLabel);
        settingsFrame.add(frictionField);
        settingsFrame.add(CORLabel);
        settingsFrame.add(CORField);
        settingsFrame.add(applyForceLabel);
        settingsFrame.add(xLabel);
        settingsFrame.add(xField);
        settingsFrame.add(yLabel);
        settingsFrame.add(zLabel);
        settingsFrame.add(zField);
        settingsFrame.add(yField);
        settingsFrame.add(applyBtn);

        settingsFrame.add(gameTitle);
        settingsFrame.setExtendedState(JFrame.MAXIMIZED_HORIZ);
        settingsFrame.setResizable(false);
        settingsFrame.setLayout(null);
        settingsFrame.setUndecorated(true);
        settingsFrame.setVisible(true);
        settingsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(applyBtn)){



            // setting values code.
            float  g = Float.parseFloat(gravityField.getText().toString());
            RigidBody.gravity = new Vector3f(0,g,0);
            RigidBody.COR = Float.parseFloat(CORField.getText().toString());

            int idx = marblesComboBox.getSelectedIndex();

            if(idx == 0){

                for(RigidBody x :Game.allMarbles)
                {
                    float mass = Float.parseFloat(marblesMassField.getText().toString());
                    x.setFrictionCOE(Float.parseFloat(frictionField.getText().toString()));


                    float xf = Float.parseFloat(xField.getText().toString())  * ObjectManager.envFact;
                    float yf = Float.parseFloat(yField.getText().toString())  * ObjectManager.envFact;
                    float zf = Float.parseFloat(zField.getText().toString())  * ObjectManager.envFact;

                    Vector3f force = new Vector3f(xf ,yf,zf);



                    x.setTime(0.0f);
                   x.setMass(mass);
                   x.addForce(force);

                }
            }
            else{

                int mass = (int) Float.parseFloat(marblesMassField.getText().toString());
                float xf = (int) Float.parseFloat(xField.getText().toString()) * ObjectManager.envFact;
                float yf = (int) Float.parseFloat(yField.getText().toString()) * ObjectManager.envFact;
                float zf = (int) Float.parseFloat(zField.getText().toString()) * ObjectManager.envFact;

                System.out.println(xf);
                Vector3f force = new Vector3f(xf,yf,zf);

                for(RigidBody x :Game.allMarbles) { x.setTime(0.0f); }

                Game.allMarbles.get(idx-1).setMass(mass);
                Game.allMarbles.get(idx-1).setFrictionCOE(Float.parseFloat(frictionField.getText().toString()));
                Game.allMarbles.get(idx-1).addForce(force);

            }
            return;
        } else if(e.getSource().equals(newGameBtn)){
            mainFrame.dispose();

            // thread for running GUI
           new Thread(new Runnable() {
               @Override
               public void run() {
                   settingsGUI();
               }
           }).start();


           // thread for running Simulation
           new Thread(new Runnable() {
               @Override
               public void run() {
                   MainGameLoop.startSimulation();
               }
           }).start();
            return;
        }else if(e.getSource().equals(quitGameBtn)){
            mainFrame.dispose();
            return;
        }
    }
}