package wob;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class Item implements Comparable<Item>{
	public static Item[] database;
	protected String name;
	protected String description;
	int amount;
	public Item(String name, String descript) {
		this.name=name;
		description = descript;
		amount=1;
	}
	public String getName(){
		return name;
	}
	public String getDescript(){
		return description;
	}
	public static Item createItem(String name, int amount){
		return createItem(new Item(name,null),amount);
	}
	public static Item createItem(String name){
		return createItem(new Item(name,null),1);
	}
	public static Item createItem(Item i, int amount){
		Item item = database[Arrays.binarySearch(database, i)];
		item.amount=amount;
		if(item instanceof Equipment)
			return ((Equipment)item).clone();
		else if(item instanceof Consumable)
			return ((Consumable)item).clone();
		return item.clone();
	}
	public static void saveItem(Item i) throws IOException{
		FileWriter fw = new FileWriter(new File("data/items.txt"),true);
		if(i instanceof Consumable){
			Consumable c = (Consumable)i;
			fw.write("\nname="+i.name+"\ndescription="+i.description+"\nhp="+c.hp+"\nmp="+c.mp+"\nsp="+c.sp+"\nteamOnly="+c.teamOnly+";\n");
		}else if(i instanceof Equipment){
			Equipment e = (Equipment)i;
			fw.write("\nname="+i.name+"\ndescription="+i.description+"\nslot="+e.slot+"\nstr="+e.str+"\nint="+e.intel+"\ndex="+e.dex+"\nwill="+e.will+"\nagil="+e.agil+"\ndef="+e.defense+"\nmagicdef="+e.magicDefense);
		}else
			fw.write("\nname="+i.name+"\ndescription="+i.description+";\n");
		fw.close();
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
		BufferedReader br = new BufferedReader(new FileReader(new File("data/items.txt")));
		int item;
		String rawData="";
		while((item=br.read())!=-1)
			rawData+=(char)item;
		br.close();
		String items[] = rawData.trim().split(";");
		database = new Item[items.length];
		for(int i=0;i<database.length;i++){
			String[] properties = items[i].trim().split("\n");
			if(properties.length==6)
				database[i] = Consumable.parseConsumable(properties);
			else if(properties.length==10)
				database[i] = Equipment.parseEquipment(properties);
			else{
				database[i] = parseItem(properties);
			}
		}
		Arrays.sort(database);
		System.out.println("[INFO] Items Loaded");
	}
	public static Item parseItem(String[] lines){
		if(lines.length==1)
			lines = new String[]{lines[0]," "};	
		return (new Item(lines[0].split("=")[1].trim(),	    //name
						lines[1].split("=")[1].trim()));	//description
	}
	public int compareTo(Item o) {
		return this.name.compareTo(o.name);
	}
	public Item clone(){
		Item i = new Item(this.name,this.description);
		i.amount=this.amount;
		return i;
	}
}
