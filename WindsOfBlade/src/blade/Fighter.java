package blade;

import java.util.LinkedList;

public abstract class Fighter extends Entity implements Comparable<Fighter>{
	int level;
	int exp;
	int str, intel, dex, will, agil;
	int hp, maxHp;
	int mp, maxMp;
	int sp, maxSp;
	byte type;
	LinkedList<Skill> moves;
	Equipment[] equips;
	boolean isDead;
	Skill skillUsed;
	Item itemUsed;
	Fighter target;
	int previousDmg;
	public Fighter(int x, int y, Animation animation){
		super(x,y,animation);
		isDead=false;
	}
	/**
	 * Fully restores & revives this
	 */
	public void fullRestore(){
		hp=maxHp;
		mp=maxMp;
		sp=maxSp;
		isDead=false;
	}
	/**
	 * Keeps stats from being over the max
	 */
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
	public String getStats(){
		return String.format("%s HP:%d/%d MP:%d/%d SP:%d/%d",name,hp,maxHp,mp,maxMp,sp,maxSp);
	}
	public int getDefence(){
		return statDef() + getEquipDef();
	}
	public int getMDefence(){
		return statMDef() + getEquipMDef();
	}
	/**
	 * @return Defense calculated from stats
	 */
	private int statDef(){
		return (int)(20*Math.log10(((str+getEquipStr())/10f)+level+10)-20);
	}
	/**
	 * @return MagicDefense calculated from stats
	 */
	private int statMDef(){
		return (int)(20*Math.log10(((intel+getEquipInt())/10f)+level+10)-20);
	}
	/**
	 * @return total STR bonus from equipment
	 */
	public int getEquipStr(){
		int strSum=0;
		for(Equipment e: equips)
			if(e!=null)
				strSum+=e.str;
		return strSum;
	}
	/**
	 * @return total INT bonus from equipment
	 */
	public int getEquipInt(){
		int intSum=0;
		for(Equipment e: equips)
			if(e!=null)
				intSum+=e.intel;
		return intSum;
	}
	/**
	 * @return total DEX bonus from equipment
	 */
	public int getEquipDex(){
		int dexSum=0;
		for(Equipment e: equips)
			if(e!=null)
				dexSum+=e.dex;
		return dexSum;
	}
	/**
	 * @return total WILL bonus from equipment
	 */
	public int getEquipWill(){
		int willSum=0;
		for(Equipment e: equips)
			if(e!=null)
				willSum+=e.will;
		return willSum;
	}
	/**
	 * @return total AGIL bonus from equipment
	 */
	public int getEquipAgil(){
		int agilSum=0;
		for(Equipment e: equips)
			if(e!=null)
				agilSum+=e.agil;
		return agilSum;
	}
	/**
	 * @return total DEF bonus from equipment
	 */
	public int getEquipDef(){
		int defSum=0;
		for(Equipment e: equips)
			if(e!=null)
				defSum+=e.defense;
		return defSum;
	}
	/**
	 * @return total MDEF bonus from equipment
	 */
	public int getEquipMDef(){
		int mDefSum=0;
		for(Equipment e: equips)
			if(e!=null)
				mDefSum+=e.magicDefense;
		return mDefSum;
	}
	public int compareTo(Fighter f){
		return f.agil-agil;
	}
}
