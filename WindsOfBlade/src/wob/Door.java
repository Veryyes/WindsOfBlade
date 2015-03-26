package wob;

import java.awt.Graphics;

public class Door extends Wall implements WorldObject {
	byte flags;//Locked, Closed
	public Door(int x, int y, int width, int height) {
		super(x,y,width, height);
	}
	public void worldRender(Graphics g) {
		//TODO
		
	}

}
