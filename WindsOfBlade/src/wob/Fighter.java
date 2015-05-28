package wob;

public abstract class Fighter extends Actor {
	int level;
	int experience;
	int str, intel, dex, will, agil;
	int hp, maxHp;	//Health
	int mp, maxMp;	//Mana
	int sp, maxSp;	//Stamina
	public Fighter(int x, int y) {
		super(x, y);
	}
}
