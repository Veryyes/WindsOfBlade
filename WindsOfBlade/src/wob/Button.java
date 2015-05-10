package wob;

import java.awt.Point;
import java.awt.geom.Rectangle2D;

public class Button extends Entity{
	boolean enabled;
	public Button(int x, int y, int width, int height) {
		super(x,y);
		hitBox=new Rectangle2D.Double(x,y,width,height);
		enabled=true;
	}
	public boolean isInside(Point mousePos){
		return (mousePos!=null&&mousePos.x-2>x&&mousePos.x-2<x+hitBox.getWidth()&&mousePos.y-24>y&&mousePos.y-24<y+hitBox.getHeight());
	}
	public void update() {
		if(enabled&&isInside( Game.frame.getMousePosition())){
			run();
		}
		
	}
	public void run(){};
}
