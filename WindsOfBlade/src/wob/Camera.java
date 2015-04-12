package wob;

public class Camera {
	static int xShift;
	static int yShift;
	static int xVelShift;
	static int yVelShift;
	public Camera() {
		
	}
	public static void update(){//TODO Magnitude of velocity is not always 4; do trig
		xVelShift=0;
		yVelShift=0;
		if(KeyInputManager.isPressed('W')){
			yVelShift=3;
		}
		if(KeyInputManager.isPressed('S')){
			yVelShift=-3;
		}
		if(KeyInputManager.isPressed('A')){
			xVelShift=3;
		}
		if(KeyInputManager.isPressed('D')){
			xVelShift=-3;
		}
		xShift+=xVelShift;
		yShift+=yVelShift;
	}

}
