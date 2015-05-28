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
			//Camera.xShift=0;
			
			/*
			 * TODO everything above here refers to the entrance
			 * the xShift and yShift must be based on the exit portal >_>
			 */
			//Camera.xShift=0;
			//Camera.yShift=0;
		//	Camera.xShift = -160;
			//Camera.yShift=(int) -(Game.player.y+64*Math.sin(Math.toRadians(90))-y1);
			//Camera.xShift=-x+Game.player.x;//-(int)(x-Game.player.x+64*Math.cos(Math.toRadians(exitDirection)));
			//Camera.yShift=-y+Game.player.y;//-(int)(y-Game.player.y+64*Math.sin(Math.toRadians(exitDirection)));
			Game.map = new Map("data/maps/"+destination+".txt");
			/*
			 * TODO x1 & y1 need to be of the exit portal
			 */
			Camera.xShift= Game.player.x-exit.x;
			Camera.yShift= Game.player.y-exit.y;
			Game.map.shiftObjects(Camera.xShift, Camera.yShift);
			Game.gameStates&=~2;
			//TODO Account for x&y shift in maps when moving between them
			//Variable for where to put the player after moving through portal
			//Shift accordingly
			//
		}
	}
	public void worldRender(Graphics g) {
		g.drawImage(animation.getFrame(),x,y,null);
	}

}
