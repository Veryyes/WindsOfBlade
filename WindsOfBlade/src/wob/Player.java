package wob;

import java.awt.Graphics;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;

public class Player extends Fighter implements WorldObject{
	int money;
	LinkedList<Item> inventory;
	LinkedList<Move> techniques;
	LinkedList<Partner> party;
	Equipment helmate, chest, pants, shoes, gloves, rightHand, leftHand, pendant;
	static Line2D.Double topLine,botLine,leftLine,rightLine;
	public Player() {
		super(Game.frameWidth/2-32,Game.frameHeight/2-32);
		name="Heroine";
		animation = new Animation(ImageManager.getImage("res/sprites/player/tempPlayer.png"));
		hitBox = new Rectangle2D.Double(this.x+8,this.y+16,48,48);
		topLine = new Line2D.Double(x+8,y+16,x+8+48,y+16);
		botLine = new Line2D.Double(x+8,y+16+48,x+8+48,y+16+48);
		leftLine = new Line2D.Double(x+8,y+16,x+8,y+16+48);
		rightLine = new Line2D.Double(x+8+48,y+16,x+8+48,y+16+48);
		inventory = new LinkedList<Item>();
		techniques = new LinkedList<Move>();
		party = new LinkedList<Partner>();
		level=1;
		experience=0;
		money=0;
		str=10;
		intel=10;
		dex=10;
		will=10;
		agil=10;
		hp=20;
		maxHp=20;
		mp=10;
		maxMp=10;
		sp=10;
		maxSp=10;
		type=Type.NORMAL;
		for(Move m: Move.database)
			techniques.add(m);
	}
	
	public void worldRender(Graphics g) {
		g.drawImage(animation.getFrame(),x,y,null);
	}
	public void update() {
		animation.update();
	}
	
}
