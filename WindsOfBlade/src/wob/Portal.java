package wob;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Rectangle2D;

public class Portal extends Actor implements WorldObject {
	String destination;
	Point exit;
	public Portal(int x, int y, String nextMap, Point exit) {
		super(x, y);
		animation = new Animation(ImageManager.getImageList("res/tiles/portal/portal.png",20));
		hitBox=new Rectangle2D.Double(x,y,64,64);
		destination=nextMap;
		this.exit=exit;
	}
	public void update() {
		
		updateLocation();
		animation.update();
		if(hitBox.intersects(Game.player.hitBox)){
			Game.gameStates|=2;
			Game.map = new Map("data/maps/"+destination+".txt");
			Camera.xShift= Game.player.x-exit.x;
			Camera.yShift= Game.player.y-exit.y;
			Game.map.shiftObjects(Camera.xShift, Camera.yShift);
			Game.gameStates&=~2;
		}
	}
	public void worldRender(Graphics g) {
		g.drawImage(animation.getFrame(),x,y,null);
	}

}
