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
	Equipment[] equipment;
	public static final byte HELMET=0;
	public static final byte CHEST=1;
	public static final byte PANTS=2;
	public static final byte SHOES=3;
	public static final byte GLOVES=4;
	public static final byte RIGHT=5;	
	public static final byte LEFT=6;	
	public Fighter(int x, int y) {
		super(x, y);
		equipment = new Equipment[7];
		techniques = new LinkedList<Move>();
	}
	public int getDefence(){
		return (int)(20*Math.log10(((str+getBonusStat("STR"))/10f)+level+10)-20)+getBonusStat("DEF");
	}
	public int getMagicDefence(){
		return (int)(20*Math.log10(((intel+getBonusStat("INT"))/10f)+level+10)-20)+getBonusStat("MDEF");
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
	public int getBonusStat(String stat){
		int value=0;
		switch(stat){
		case "STR":
			for(int i=0;i<equipment.length;i++)
				if(equipment[i]!=null)
					value+=equipment[i].str;
			return value;
		case "INTEL":
		case "INT":
			for(int i=0;i<equipment.length;i++)
				if(equipment[i]!=null)
					value+=equipment[i].intel;
			return value;
		case "DEX":
			for(int i=0;i<equipment.length;i++)
				if(equipment[i]!=null)
					value+=equipment[i].dex;
			return value;
		case "WILL":
			for(int i=0;i<equipment.length;i++)
				if(equipment[i]!=null)
					value+=equipment[i].will;
			return value;
		case "AGIL":
			for(int i=0;i<equipment.length;i++)
				if(equipment[i]!=null)
					value+=equipment[i].agil;
			return value;
		case "DEF":
			for(int i=0;i<equipment.length;i++)
				if(equipment[i]!=null)
					value+=equipment[i].defense;
			return value;
		case "MDEF":
			for(int i=0;i<equipment.length;i++)
				if(equipment[i]!=null)
					value+=equipment[i].magicDefense;
			return value;
		default:
			return 0;
		}
	}
}
