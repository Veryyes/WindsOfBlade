package wob;

import java.awt.Graphics;

public class InnKeeper extends Npc {
	int price;
	public InnKeeper(int x, int y, String name, int price) {
		super(x,y,name);
		this.price=price;
	}
	public void update(){
		
	}
	public void worldRender(Graphics g){
		super.worldRender(g);
		//TODO draw Inn ui stuff
	}
}
