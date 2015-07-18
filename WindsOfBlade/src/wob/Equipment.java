package wob;

public class Equipment extends Item {
	char slot;
	/*
	 * h elmet
	 * c hest
	 * p ants
	 * s hoes
	 * g loves
	 * r ight hand
	 * l eft hand
	 * p endant
	 */
	int str, intel, dex, will, agil;
	int defence, magicDefence;
	public Equipment(String name, String description, char slot, int hp, int mp, int sp, int def, int mdef) {
		super(name, description, hp, mp, sp, true);
		this.slot=slot;
		defence=def;
		magicDefence=mdef;
	}

}
