package wob;

import java.util.LinkedList;

public class Player extends Actor {
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
		
	}

}
