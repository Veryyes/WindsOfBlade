package wob;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

public class Portal extends Actor implements WorldObject {
	String destination;
	public Portal(int x, int y, String nextMap) {
		super(x, y);
		animation = new Animation(ImageManager.getImage("res/tiles/portal.png"));
		hitBox=new Rectangle2D.Double(x,y,64,64);
		destination=nextMap;
	}
	public void update() {
		updateLocation();
		animation.update();
		if(hitBox.intersects(Game.player.hitBox)){
			Game.gameStates|=2;
			Game.map = new Map("data/maps/"+destination+".txt");
			Game.gameStates&=~2;
			//TODO Account for x&y shift in maps when moving between them
			//Variable for where to put the player after moving through portal
			//Shift accordingly
		}
	}
	public void worldRender(Graphics g) {
		g.drawImage(animation.getFrame(),x,y,null);
	}

}
