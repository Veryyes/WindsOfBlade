package wob;

import java.awt.Graphics;

public class Npc extends Actor implements WorldObject{
	String name;
	String[] conversation;
	public Npc(int x, int y) {
		super(x,y);
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void worldRender(Graphics g) {
		g.drawImage(ImageManager.tempNPC,x,y,null);
		
	}

}
