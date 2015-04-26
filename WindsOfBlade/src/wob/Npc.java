package wob;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;

public class Npc extends Actor implements WorldObject{
	String name;
	String[] conversation;
	public Npc(int x, int y) {
		super(x,y);
		hitBox=new Rectangle2D.Double(x,y,64,64);
	}
	@Override
	public void update() {
		this.x+=Camera.xVelShift;
		this.y+=Camera.yVelShift;
		hitBox=new Rectangle2D.Double(this.x,this.y,hitBox.getWidth(),hitBox.getHeight());
	}
	@Override
	public void worldRender(Graphics g) {
		g.drawImage(ImageManager.tempNPC,x,y,null);
		
	}

}
