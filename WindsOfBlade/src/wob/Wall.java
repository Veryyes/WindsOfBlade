package wob;

import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;

public class Wall extends Entity {

	public Wall(int x, int y, int width, int height) {
		super(x,y);
		hitBox=new Rectangle2D.Double(x,y,width,height);
	}
	public void update() {
		
	}

}
