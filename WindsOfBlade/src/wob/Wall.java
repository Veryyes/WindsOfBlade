package wob;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;

public class Wall extends Entity implements WorldObject{

	public Wall(int x, int y, int width, int height) {
		super(x,y);
		hitBox=new Rectangle2D.Double(x,y,width,height);
	}
	public void update() {
		
	}
	@Override
	public void worldRender(Graphics g) {
		g.setColor(Color.WHITE);
		g.drawRect(x+Camera.xShift,y+Camera.yShift,(int)hitBox.getWidth(),(int)hitBox.getHeight());
	}

}
