package wob;

public class Consumable extends Item {
	int hp, mp, sp;
	boolean teamOnly;
	public Consumable(String name, String descript, int hp, int mp, int sp, boolean teamOnly) {
		super(name, descript);
		this.hp = hp;
		this.mp = mp;
		this.sp = sp;
		this.teamOnly = teamOnly;
	}
	public static Consumable parseConsumable(String[] lines){
		return new Consumable(lines[0].split("=")[1],						//name
				lines[1].split("=")[1],							//description
				Integer.parseInt(lines[2].split("=")[1]),		//hp
				Integer.parseInt(lines[3].split("=")[1]),		//mp
				Integer.parseInt(lines[4].split("=")[1]),		//sp
				Boolean.parseBoolean(lines[5].split("=")[1])); //teamOnly
	}
	public void use(Fighter user){
		if(teamOnly == (user instanceof Player || user instanceof Partner)){
			user.hp+=hp;
			user.mp+=mp;
			user.sp+=sp;
			user.clampPoints();
		}
	}
}
