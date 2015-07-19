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
	 */
	int str, intel, dex, will, agil;
	int defense, magicDefense;
	public Equipment(String name, String description, char slot, int str, int intel, int dex, int will, int agil, int def, int mdef) {
		super(name, description);
		this.slot=slot;
		this.str=str;
		this.intel=intel;
		this.dex=dex;
		this.will=will;
		this.agil=agil;
		defense=def;
		magicDefense=mdef;
	}
	public static Equipment parseEquipment(String[] lines){
		return new Equipment(
				lines[0].split("=")[1],								//Name
				lines[1].split("=")[1],								//Description
				lines[2].split("=")[1].toCharArray()[0],			//Slot
				Integer.parseInt(lines[3].split("=")[1].trim()),    //Str
				Integer.parseInt(lines[4].split("=")[1].trim()),    //Int
				Integer.parseInt(lines[5].split("=")[1].trim()),    //Dex
				Integer.parseInt(lines[6].split("=")[1].trim()),    //Will
				Integer.parseInt(lines[7].split("=")[1].trim()),    //Agil
				Integer.parseInt(lines[8].split("=")[1].trim()),    //Defense
				Integer.parseInt(lines[9].split("=")[1].trim())     //Magic Defense
				);
	}

}
