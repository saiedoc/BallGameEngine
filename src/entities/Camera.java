package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

import java.security.Key;

public class Camera {



	private float distanceFromGround = 80;
	private float angleAroundGround = 0;



	private Vector3f position = new Vector3f(0,40,25);
	private float pitch = 60;
	private float yaw ;
	private float roll;
	private float mouseSensitivity = 0.1f;
	Entity mainGround;


	public Camera(Entity mainGround){
		this.mainGround = mainGround;
	}
	
	public void move(){

		calculateZoom();
		calaculatePitch();
		calculateAngleAroundGround();
		float hd = calculateHorziontalDistance();
		float vd = calculateVerticalDistance();
		calculateCameraPos(hd,vd);
		yaw = 180 - (mainGround.rotY + angleAroundGround);


	}

    private float calculateHorziontalDistance(){
		return  (float) (distanceFromGround * Math.cos(Math.toRadians(pitch)));
	}

	private float calculateVerticalDistance(){
		return  (float) (distanceFromGround * Math.sin(Math.toRadians(pitch)));
	}

	public Vector3f getPosition() {
		return position;
	}

	public float getPitch() {
		return pitch;
	}

	public float getYaw() {
		return yaw;
	}

	public float getRoll() {
		return roll;
	}

	private void calculateCameraPos(float hd , float vd){

		float theta = mainGround.getRotY() + angleAroundGround;
		float offsetX = (float) (hd * Math.sin(Math.toRadians(theta)));
		float offsetZ = (float) (hd * Math.cos(Math.toRadians(theta)));
		position.x = mainGround.getPosition().x - offsetX;
		position.z = mainGround.getPosition().z - offsetZ;
		position.y = mainGround.getPosition().y + vd;

	}

	public float getMouseSensitivity() {
		return mouseSensitivity;
	}

	public void setMouseSensitivity(float mouseSensitivity) {
		this.mouseSensitivity = mouseSensitivity;
	}

	private void calculateZoom(){

		if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
		float zoomLevel = Mouse.getDWheel() * 0.1f;
		distanceFromGround -= zoomLevel;}
	}

	private void calaculatePitch(){
		if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
		float pitchChange = Mouse.getDY() * 0.1f;
		pitch -= pitchChange;}
	}

	private void calculateAngleAroundGround(){

		if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
		float angleChange = Mouse.getDX() * 0.3f;
		angleAroundGround -= angleChange;}
	}



}
