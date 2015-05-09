package wob;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

public class Wall extends Entity implements WorldObject{

	public Wall(int x, int y, int width, int height) {
		super(x,y);
		hitBox=new Rectangle2D.Double(x,y,width,height);
	}
	public void update() {
		this.x+=Camera.xVelShift;
		this.y+=Camera.yVelShift;
		hitBox=new Rectangle2D.Double(this.x,this.y,hitBox.getWidth(),hitBox.getHeight());
	}
	@Override
	public void worldRender(Graphics g) {
		g.setColor(Color.WHITE);
		g.drawRect(x,y,(int)hitBox.getWidth(),(int)hitBox.getHeight());
	}

}
