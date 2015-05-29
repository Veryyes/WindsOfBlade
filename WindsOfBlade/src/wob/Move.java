package wob;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
/*
 *  Store information on different attacks, their effects, and elemental type
 */
public class Move {
	public static LinkedList<Move> database;
	String name;
	int base;
	int accuracy;
	String description;
	String type;
	boolean physical;
	String effect;
	int hp, mp, sp;
	public Move(String name, int base, int accuracy, String descrip, String type,boolean physical, String effect, int hp, int mp, int sp ) {
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
		fw.write("\nname="+m.name+"\nbase="+m.base+"\naccuracy="+m.accuracy+"\ndescription="+m.description+"\ntype="+m.type+"\nphysical="+m.physical+"\neffect="+m.effect+";");
		fw.close();
		database.add(m);
	}
	public int physicalDamage(Fighter user, Fighter target){
		return (int)((sp/(mp+sp))*user.str*.5*Math.log(base));
	}
	public int magicDamage(Fighter user, Fighter target){
		return (int)((mp/(mp+sp))*user.intel*.5*Math.log(base));
	}
	public boolean hit(Fighter user, Fighter target){
		return Math.random() < accuracy+(.0005*user.dex)-(.001*target.agil);
	}
	public static boolean attackHit(Fighter user, Fighter target){
		return Math.random() < 90+(.0005*user.dex)-(.001*target.agil);
	}
	public static int attackDamageDelt(Fighter user, Fighter target){
		return (int) (user.str*.5);
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
		database = new LinkedList<Move>();
		BufferedReader bf = new BufferedReader(new FileReader(new File("data/moves.txt")));
		int item = 0;
		String rawdata="";
		while((item=bf.read())!=-1)
			rawdata+=(char)item;
		bf.close();
		rawdata=rawdata.trim();
		String[] moves = rawdata.split(";");
		for(String str:moves){
			str=str.trim();
			String[] properties = str.split("\n");
			database.add(parseMove(properties));
		}
		System.out.println("[INFO] Moves Loaded");
	}
	private static Move parseMove(String[] lines){
		return (new Move(lines[0].split("=")[1].trim(),
						Integer.parseInt(lines[1].split("=")[1].trim()),
						Integer.parseInt(lines[2].split("=")[1].trim()),
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
}
