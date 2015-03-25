package wob;

import java.awt.Graphics;
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
		name="Hero";
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
	public Player(String savefile){
		//TODO need to make a savefile so i can parse it first.
	}
	
	public void worldRender(Graphics g) {
		//TODO draw this kid.
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
}
