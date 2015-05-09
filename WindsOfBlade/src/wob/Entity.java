package wob;

import java.awt.geom.Rectangle2D;

public abstract class Entity {
	int x;
	int y;
	Rectangle2D.Double hitBox;
	public Entity(int x, int y) {
		this.x=x;
		this.y=y;
	}
	public abstract void update();
	public void updateLocation(){
		this.x+=Camera.xVelShift;
		this.y+=Camera.yVelShift;
		hitBox=new Rectangle2D.Double(this.x,this.y,hitBox.getWidth(),hitBox.getHeight());
	}
}
