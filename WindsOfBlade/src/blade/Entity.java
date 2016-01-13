package blade;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Entity {
	public String name;
	Rectangle box;
	Animation animation;
	public Entity(){
		box = new Rectangle();
	}
	public Entity(int x, int y){
		this();
		box.x = x;
		box.y = y;
	}
	public Entity(int x, int y, int w, int h){
		this(x, y);
		box.width=w;
		box.height=h;
	}
	public Entity(int x, int y, Animation animation){
		this(x,y,animation==null?0:animation.getWidth(),animation==null?0:animation.getHeight());
		this.animation=animation;
	}
	public Entity(int x, int y, Image image){
		this(x,y,new Animation(image));
	}
	public Entity(int x, int y, Image ... images){
		this(x,y,new Animation(images));
	}
	public void update(Graphics g){
		box.x += Camera.xVelShift;
		box.y += Camera.yVelShift;
		if(animation!=null){
			animation.update();
			animation.getCurrentFrame().draw(box.x, box.y, g);
		}
	}
}
