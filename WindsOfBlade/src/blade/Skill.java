package blade;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Skill implements Comparable<Skill>{
	public static Skill[] database;
	String name;
	byte base;
	byte accuracy;
	String description;
	byte type;
	String effect;
	int hp, mp, sp;
	public Skill(String name, byte base, byte accuracy, String description, byte type, String effect, int hp, int mp, int sp ) {
		this.name=name;
		this.base=base;
		this.accuracy=accuracy;
		this.description = description;
		this.type=type;
		this.effect=effect;
		this.hp=hp;
		this.mp=mp;
		this.sp=sp;
	}
	@Override
	public int compareTo(Skill o) {
		return this.name.compareTo(o.name);
	}
	public String use(Fighter user, Fighter target){
		user.hp-=hp;
		user.mp-=mp;
		user.sp-=sp;
		if(Math.random() < accuracy+(.0005*(user.dex+user.getEquipDex()))-(.001*(target.agil+target.getEquipAgil()))){
			int damage, magicDamage;
			if((damage=getDamage(user, target)-target.getDefence())<0)
				damage=0;
			if((magicDamage=getMagicDamage(user,target)-target.getMDefence())<0)
				magicDamage=0;
			damage+=magicDamage;
			damage*=Type.effectiveness(this.type,target.type);
			if(damage<=0)
				return user.name+"'s attack had no effect on "+target.name;
			else{
				target.hp-=damage;
				return user.name+"'s attack did "+damage+" damage to "+target.name;
			}
		}else
			return user.name+"'s attack missed!";
	}
	private int getDamage(Fighter user, Fighter target){//Physical Portion of Technique
		return (int)((sp/(mp+sp))*(user.str+user.getEquipStr())*.5*Math.log(base));
	}
	private int getMagicDamage(Fighter user, Fighter target){//Magical Portion of Technique
		return (int)((mp/(mp+sp))*(user.intel+user.getEquipInt())*.5*Math.log(base));
	}
	public static void loadSkills() throws IOException{
		BufferedReader bf = new BufferedReader(new FileReader(new File("data/moves.txt")));
		int item = 0;
		String rawdata="";
		while((item=bf.read())!=-1)
			rawdata+=(char)item;
		bf.close();
		rawdata=rawdata.trim();
		String[] moves = rawdata.split(";");
		database = new Skill[moves.length];
		for(int i=0;i<database.length;i++){
			String[] properties = moves[i].trim().split("\n");
			database[i] = parseSkill(properties);
		}
		Arrays.sort(database);
		System.out.println("[INFO] Moves Loaded");
	}
	private static Skill parseSkill(String[] lines){
		return (new Skill(lines[0].split("=")[1].trim(),
						Byte.parseByte(lines[1].split("=")[1].trim()),
						Byte.parseByte(lines[2].split("=")[1].trim()),
						lines[3].split("=")[1].trim(),
						Type.parseType(lines[4].split("=")[1].trim()),
						lines[5].split("=")[1].trim(),
						Integer.parseInt(lines[6].split("=")[1].trim()),
						Integer.parseInt(lines[7].split("=")[1].trim()),
						Integer.parseInt(lines[8].split("=")[1].trim())));
	}
}
