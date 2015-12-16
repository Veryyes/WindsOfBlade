package blade;

import java.awt.geom.Line2D;
import java.util.LinkedList;

public class Camera {
	static int xShift=5*64;
	static int yShift;
	static int xVelShift;
	static int yVelShift;
	static boolean canMove = true;
	public static void update(){//TODO Magnitude of velocity is not always 3; do trig
		xVelShift=0;
		yVelShift=0;
		if(canMove){
			if(KeyManager.isPressed('W')){
				yVelShift=3;
			}
			if(KeyManager.isPressed('S')){
				yVelShift=-3;
			}
			if(KeyManager.isPressed('A')){
				xVelShift=3;
			}
			if(KeyManager.isPressed('D')){
				xVelShift=-3;
			}
		}
		collisionCheck(Game.map.entities);
		xShift+=xVelShift;
		yShift+=yVelShift;
	}
	private static void collisionCheck(LinkedList<Entity> list){
		for(Entity e: list){
			if(new Line2D.Float(Player.top.x1-xVelShift,Player.top.y1-yVelShift,Player.top.x2+-xVelShift,Player.top.y2-yVelShift).intersects(e.box)&&(yVelShift>0)){
				yVelShift=0;
			}
			if(new Line2D.Float(Player.bottom.x1-xVelShift,Player.bottom.y1-yVelShift,Player.bottom.x2+-xVelShift,Player.bottom.y2-yVelShift).intersects(e.box)&&(yVelShift<0)){
				yVelShift=0;
			}
			if(new Line2D.Float(Player.left.x1-xVelShift,Player.left.y1-yVelShift,Player.left.x2-xVelShift,Player.left.y2-yVelShift).intersects(e.box)&&(xVelShift>0)){
				xVelShift=0;
			}
			if(new Line2D.Float(Player.right.x1-xVelShift,Player.right.y1-yVelShift,Player.right.x2+-xVelShift,Player.right.y2-yVelShift).intersects(e.box)&&(xVelShift<0)){
				xVelShift=0;
			}
		}
	}
	public static boolean isMoving(){
		return xVelShift+yVelShift!=0;
	}
}
