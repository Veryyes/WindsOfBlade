package wob;

import java.util.LinkedList;

public abstract class Fighter extends Actor {
	int level;
	int experience;
	int str, intel, dex, will, agil;
	int hp, maxHp;	//Health
	int mp, maxMp;	//Mana
	int sp, maxSp;	//Stamina
	int type;
	int lastDamageTaken;
	LinkedList<Item> inventory;
	LinkedList<Move> techniques;
	Move selectedTechnique;
	Item selectedItem;
	Fighter target;
	boolean isDead;
	boolean dmgCalc;
	boolean missed;
	public Fighter(int x, int y) {
		super(x, y);
	}
	public int getDefence(){
		return (int) (20*Math.log10((str/10f)+level+10)-20);
	}
	public int getMagicDefence(){
		return (int) (20*Math.log10((intel/10f)+level+10)-20);
	}
	public void resetTurn(){
		selectedTechnique=null;
		selectedItem=null;
		target=null;
		lastDamageTaken=0;
		dmgCalc=false;
		missed=false;
	}
	public void fullRestore(){
		hp=maxHp;
		mp=maxMp;
		sp=maxSp;
		isDead=false;
	}
	public void clampPoints(){
		if(hp>maxHp)
			hp=maxHp;
		else if(hp<0)
			hp=0;
		if(mp>maxMp)
			mp=maxMp;
		else if(mp<0)
			mp=0;
		if(sp>maxSp)
			sp=maxSp;
		else if(sp<0)
			sp=0;
	}
}
