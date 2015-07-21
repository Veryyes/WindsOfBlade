package wob;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

/*
 *  Store information on different attacks, their effects, and elemental type
 */
public class Move implements Comparable<Move>{
	//public static LinkedList<Move> database;
	public static Move[] database;
	String name;
	byte base;
	byte accuracy;
	String description;
	String type;
	boolean physical;
	String effect;
	int hp, mp, sp;
	public Move(String name, byte base, byte accuracy, String descrip, String type,boolean physical, String effect, int hp, int mp, int sp ) {
		this.name=name;
		this.base=base;
		this.accuracy=accuracy;
		this.description =descrip;
		this.type=type;
		this.effect=effect;
		this.physical=physical;
		this.hp=hp;
		this.mp=mp;
		this.sp=sp;
	}
	/*
	 * Adds a new move to files
	 */
	public static void saveMove(Move m) throws IOException{
		FileWriter fw = new FileWriter(new File("data/moves.txt"),true);
		fw.write("\nname="+m.name+"\nbase="+m.base+"\naccuracy="+m.accuracy+"\ndescription="+m.description+"\ntype="+m.type+"\nphysical="+m.physical+"\neffect="+m.effect+"\nhp="+m.hp+"\nmp="+m.mp+"\nsp="+m.sp+";\n");
		fw.close();
		//database.add(m);
		Move[] temp = new Move[database.length+1];
		byte shift = 0;
		for(int i=0;i<database.length;i++){
			if(m.compareTo(database[i])>=0){
				temp[i]=m;
				shift=1;
			}else
				temp[i+shift]=database[i];
		}
		database = temp;
	}
	//Techniques
	public boolean hit(Fighter user, Fighter target){//Accuracy Check
		return Math.random() < accuracy+(.0005*(user.dex+user.getBonusStat("DEX")))-(.001*(target.agil+target.getBonusStat("AGIL")));
	}
	private int physicalDamage(Fighter user, Fighter target){//Physical Portion of Technique
		return (int)((sp/(mp+sp))*(user.str+user.getBonusStat("STR"))*.5*Math.log(base)*Type.effectiveness(this,target));
	}
	private int magicDamage(Fighter user, Fighter target){//Magical Portion of Technique
		return (int)((mp/(mp+sp))*(user.intel+user.getBonusStat("INT"))*.5*Math.log(base)*Type.effectiveness(this,target));
	}
	public int getDamageDone(Fighter user, Fighter target){//Damage Calcuations 
		int netPhysicalDamage = physicalDamage(user,target)-target.getDefence();
		int netMagicDamage = magicDamage(user,target)-target.getMagicDefence();
		int netDamage = 0;
		if(netPhysicalDamage>0)
			netDamage = netPhysicalDamage;
		if(netMagicDamage>0)
			netDamage += netMagicDamage;
		return (int) (netDamage*Type.effectiveness(this, target));
	}
	//Normal Attack
	public static boolean attackHit(Fighter user, Fighter target){
		return Math.random() < 90+(.0005*(user.dex+user.getBonusStat("DEX")))-(.001*(target.agil+target.getBonusStat("AGIL")));
	}
	public static int attackDamageDelt(Fighter user, Fighter target){
		if((user.str+user.getBonusStat("STR"))*.5-target.getDefence()>0)
			return (int)((user.str+user.getBonusStat("STR"))*.5-target.getDefence());
		return 0;
	}
	public void consume(Fighter user){
		user.hp-=hp;
		user.mp-=mp;
		user.sp-=sp;
	}
	/*
	 * Reads from file all the moves & adds them to database
	 */
	public static void loadMoves() throws IOException{
		//database = new LinkedList<Move>();
		BufferedReader bf = new BufferedReader(new FileReader(new File("data/moves.txt")));
		int item = 0;
		String rawdata="";
		while((item=bf.read())!=-1)
			rawdata+=(char)item;
		bf.close();
		rawdata=rawdata.trim();
		String[] moves = rawdata.split(";");
		database = new Move[moves.length];
		for(int i=0;i<database.length;i++){
			String[] properties = moves[i].trim().split("\n");
			database[i] = parseMove(properties);
		}
		Arrays.sort(database);
		System.out.println("[INFO] Moves Loaded");
	}
	private static Move parseMove(String[] lines){
		return (new Move(lines[0].split("=")[1].trim(),
						Byte.parseByte(lines[1].split("=")[1].trim()),
						Byte.parseByte(lines[2].split("=")[1].trim()),
						lines[3].split("=")[1].trim(),
						lines[4].split("=")[1].trim(),
						Boolean.parseBoolean(lines[5].split("=")[1].trim()),
						lines[6].split("=")[1].trim(),
						Integer.parseInt(lines[7].split("=")[1].trim()),
						Integer.parseInt(lines[8].split("=")[1].trim()),
						Integer.parseInt(lines[9].split("=")[1].trim())));
	}
	public String toString(){
		return(name);
	}
	public int compareTo(Move m) {
		return this.name.compareTo(m.name);
	}
	
}
