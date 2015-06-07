package wob;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Rectangle2D;

public class Button extends Entity{
	boolean enabled;
	boolean highlight;
	public Button(int x, int y, int width, int height) {
		super(x,y);
		hitBox=new Rectangle2D.Double(x,y,width,height);
		enabled=true;
		highlight=true;
	}
	public boolean isInside(Point mousePos){
		return (mousePos!=null&&mousePos.x-2>x&&mousePos.x-2<x+hitBox.getWidth()&&mousePos.y-24>y&&mousePos.y-24<y+hitBox.getHeight());
	}
	public void update() {
		if(enabled&&isInside( Game.frame.getMousePosition())){
			run();
		}
	}
	public void render(Graphics g){
		if(enabled&&highlight&&isInside(Game.frame.getMousePosition())){
			highlight(g);
		}
	}
	public void highlight(Graphics g){
		UI.drawRectUI(x-8, y-4, (int)hitBox.getWidth()+16, (int)hitBox.getHeight()+12, false, g);
	}
	public void run(){};
}
