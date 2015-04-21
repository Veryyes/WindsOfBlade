package wob;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import java.util.LinkedList;

public class Player extends Actor implements WorldObject{
	String name;
	int level;
	int experience;
	int money;
	LinkedList<Item> inventory;
	int str, intel, dex, will, agil;
	int hp, maxHp;	//Health
	int mp, maxMp;	//Mana
	int sp, maxSp;	//Stamina
	Equipment helmate, chest, pants, shoes, gloves, rightHand, leftHand, pendant;
	public Player() {
		super(Game.frameWidth/2-32,Game.frameHeight/2-32);//TODO take out x&y parameters b/c player is stationary and world moves
		name="Hero";
		image = ImageManager.player;
		hitBox = new Rectangle2D.Double(this.x+8,this.y+16,48,48);
		level=1;
		experience=0;
		money=0;
		str=10;
		intel=10;
		dex=10;
		will=10;
		agil=10;
		hp=10;
		maxHp=10;
		mp=10;
		maxMp=10;
		sp=10;
		maxSp=10;
	}
	public Player(int x, int y, String savefile){
		super(x,y);
		//TODO need to make a savefile so i can parse it first.
	}
	
	public void worldRender(Graphics g) {
		g.drawImage(ImageManager.player,x,y,null);
	}
	@Override
	public void update() {
		
		
	}
	
}
