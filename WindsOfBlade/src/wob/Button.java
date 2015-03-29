package wob;

import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
public class Button extends Entity implements MouseListener {
	boolean enabled;
	public Button(int x, int y, int width, int height) {
		super(x,y);
		hitBox=new Rectangle2D.Double(x,y,width,height);
		enabled=true;
	}
	public void run(){};
	public void mousePressed(MouseEvent e) {
		Point mousePos = Game.frame.getMousePosition();
		if(enabled&&isInside(mousePos)){
			run();
		}
	}
	public boolean isInside(Point mousePos){
		return (mousePos!=null&&mousePos.x-2>x&&mousePos.x-2<x+hitBox.getWidth()&&mousePos.y-24>y&&mousePos.y-24<y+hitBox.getHeight());
	}
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void update() {
		// TODO Auto-generated method stub
		
	}
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
		
	}
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
