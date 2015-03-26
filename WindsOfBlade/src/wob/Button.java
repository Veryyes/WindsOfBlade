package wob;

import java.awt.geom.Rectangle2D;

public class Button extends Entity {

	public Button(int x, int y, int width, int height) {
		super(x,y);
		hitBox=new Rectangle2D.Double(x,y,width,height);
	}
	public void run(){
		
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
