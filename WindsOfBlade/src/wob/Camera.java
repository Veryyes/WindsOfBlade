package wob;

import java.awt.geom.Line2D;
import java.util.LinkedList;

public class Camera {
	static int xShift;
	static int yShift;
	static int xVelShift;
	static int yVelShift;
	public Camera() {
		
	}
	public static void update(){//TODO Magnitude of velocity is not always 3; do trig
		xVelShift=0;
		yVelShift=0;
		if(KeyInputManager.isPressed('W')){
			yVelShift=3;
		}
		if(KeyInputManager.isPressed('S')){
			yVelShift=-3;
		}
		if(KeyInputManager.isPressed('A')){
			xVelShift=3;
		}
		if(KeyInputManager.isPressed('D')){
			xVelShift=-3;
		}
		collisionChecks(Game.map.walls);
		collisionChecks(Game.map.npcs);
		xShift+=xVelShift;
		yShift+=yVelShift;
	}
	/*
	 *  can collide with any list of Entities now
	 */
	@SuppressWarnings("rawtypes")
	private static void collisionChecks(Iterable list){
		for(Object o: list){
			Entity e = (Entity)o;
			if(new Line2D.Double(Player.topLine.x1-xVelShift,Player.topLine.y1-yVelShift,Player.topLine.x2+-xVelShift,Player.topLine.y2-yVelShift).intersects(e.hitBox)&&(yVelShift>0)){
				yVelShift=0;
			}
			if(new Line2D.Double(Player.botLine.x1-xVelShift,Player.botLine.y1-yVelShift,Player.botLine.x2+-xVelShift,Player.botLine.y2-yVelShift).intersects(e.hitBox)&&(yVelShift<0)){
				yVelShift=0;
			}
			if(new Line2D.Double(Player.leftLine.x1-xVelShift,Player.leftLine.y1-yVelShift,Player.leftLine.x2-xVelShift,Player.leftLine.y2-yVelShift).intersects(e.hitBox)&&(xVelShift>0)){
				xVelShift=0;
			}
			if(new Line2D.Double(Player.rightLine.x1-xVelShift,Player.rightLine.y1-yVelShift,Player.rightLine.x2+-xVelShift,Player.rightLine.y2-yVelShift).intersects(e.hitBox)&&(xVelShift<0)){
				xVelShift=0;
			}
		}
	}
}
