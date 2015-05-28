package wob;

import java.util.LinkedList;

public class Partner extends Actor{
	int level;
	int experience;
	LinkedList<Move> techniques;
	int str, intel, dex, will, agil;
	int hp, maxHp;	//Health
	int mp, maxMp;	//Mana
	int sp, maxSp;	//Stamina
	Equipment helmate, chest, pants, shoes, gloves, rightHand, leftHand, pendant;
	public Partner(int x, int y) {
		super(x,y);
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
}
