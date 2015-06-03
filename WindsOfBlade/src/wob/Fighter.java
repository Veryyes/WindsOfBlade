package wob;

public abstract class Fighter extends Actor {
	int level;
	int experience;
	int str, intel, dex, will, agil;
	int hp, maxHp;	//Health
	int mp, maxMp;	//Mana
	int sp, maxSp;	//Stamina
	int type;
	public Fighter(int x, int y) {
		super(x, y);
	}
	public int getDefence(){
		return (int) (20*Math.log10((str/10f)+level+10)-20);
	}
	public int getMagicDefence(){
		return (int) (20*Math.log10((intel/10f)+level+10)-20);
	}
}
