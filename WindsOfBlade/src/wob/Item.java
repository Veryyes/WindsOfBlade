package wob;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

public class Item implements Comparable<Item>{
	//public static LinkedList<Item> database;
	public static Item[] database;
	String name;
	String description;
	int hp, mp, sp, dmg;
	boolean teamOnly;
	public Item(String name, String descript, int hp, int mp, int sp, boolean teamOnly) {
		this.name=name;
		description = descript;
		this.hp = hp;
		this.mp = mp;
		this.sp = sp;
		this.teamOnly = teamOnly;
	}
	public static void saveItem(Item i) throws IOException{
		FileWriter fw = new FileWriter(new File("data/items.txt"),true);
		fw.write("\nname="+i.name+"\ndescription="+i.description+"\nhp="+i.hp+"\nmp="+i.mp+"\nsp="+i.sp+"\nteamOnly="+i.teamOnly+";\n");
		fw.close();
		//database.add(i);
		Item[] temp = new Item[database.length+1];
		byte shift = 0;
		for(int j=0;j<database.length;j++){
			if(i.compareTo(database[j])>=0){
				temp[j]=i;
				shift=1;
			}else
				temp[j+shift]=database[j];
		}
		database = temp;
	}
	public static void loadItems() throws IOException{
		//database = new LinkedList<Item>();
		BufferedReader br = new BufferedReader(new FileReader(new File("data/items.txt")));
		int item;
		String rawData="";
		while((item=br.read())!=-1)
			rawData+=(char)item;
		br.close();
		String items[] = rawData.split(";");
		database = new Item[items.length];
		for(int i=0;i<database.length;i++){
			String[] properties = items[i].trim().split("\n");
			database[i] = parseItem(properties);
		}
		Arrays.sort(database);
		System.out.println("[INFO] Items Loaded");
	}
	public static Item parseItem(String[] lines){
		return (new Item(lines[0].split("=")[1],						//name
						lines[1].split("=")[1],							//description
						Integer.parseInt(lines[2].split("=")[1]),		//hp
						Integer.parseInt(lines[3].split("=")[1]),		//mp
						Integer.parseInt(lines[4].split("=")[1]),		//sp
						Boolean.parseBoolean(lines[5].split("=")[1]))); //teamOnly
	}
	@Override
	public int compareTo(Item o) {
		return this.name.compareTo(o.name);
	}
}
