package wob;

public class Camera {
	int xShift;
	int yShift;
	int xVelShift;
	int yVelShift;
	public Camera() {
		
	}
	public void update(){
		xShift+=xVelShift;
		yShift+=yVelShift;
	}

}
