package blade;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

public class Item{
	protected static File[] database;
	protected String name;
	protected String description;
	protected Animation animation;
	int amount;
	static Window itemDetail = new Window(null, "");
	public Item(String name, String description, Animation animation){
		this.name=name;
		this.description=description;
		if(animation.getFrame(0)==null)
			animation = new Animation(ImageManager.get("res/items/ETC_null.png"));
		this.animation=animation;
		amount=0;
	}
	/**
	 * Loads all the Files of items in database
	 * @throws IOException
	 */
	public static void loadItems() throws IOException{
		LinkedList<File> files = new LinkedList<File>();
		FileIO.loadFiles("data/items", files);
		database = new File[files.size()];
		int i=0;
		for(File f:files){
			database[i] = f;
			i++;
		}
		itemDetail.visible=false;
		/*
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
		System.out.println("[INFO] Items Loaded");*/
	}
	/**
	 * Creates an Item object from the data in the file given
	 * @param f The file containing the item data
	 * @return An Item object with the file data
	 */
	private static Item parseItem(File f){
		try(BufferedReader br = new BufferedReader(new FileReader(f))){
			String name = f.getName().substring(f.getName().indexOf("_")+1,f.getName().indexOf("."));
			Item i = new Item(name, br.readLine(),new Animation(ImageManager.get("res/items/"+name+".png")));
			return i;
		}catch(IOException e){
			e.printStackTrace();
			return null;
		}
	}
	public String getName(){
		return name;
	}
	public String getDescription(){
		return description;
	}
	public Animation getAnimation(){
		return animation;
	}
	/**
	 * @param name The name of the Item
	 * @return a new instance of the Item loaded file
	 */
	public static Item get(String name){
		File itemFile = database[Arrays.binarySearch(database, new File("data/items/"+name+".txt"))];
		if(itemFile.getName().contains("EQUIP")){
			return Equipment.parseEquipment(itemFile);
		}else if(itemFile.getName().contains("USE")){
			return Consumable.parseConsumable(itemFile);
		}else
			return parseItem(itemFile);
	}
	public Window createItemDetailWindow(){
		TypeWriter.setSize(.75f);
		int width = animation.getCurrentFrame().getWidth()+8+Window.BORDER_SIZE*2+192;
		int height = animation.getCurrentFrame().getHeight()+32+Window.BORDER_SIZE*2+TypeWriter.calcHeight(description, width);
		itemDetail = new Window(Game.frame.getMousePosition().x,Game.frame.getMousePosition().y, width, height, description ,animation);
		itemDetail.backColor = new Color(0,0,0,192);
		//itemDetail.setLayout(2,1);
		//Window top = new Window(itemDetail,true,name);
		//Window bottom = new Window(itemDetail, true, description, animation);
		//itemDetail.add(0,0,top);
		//itemDetail.add(1, 0, bottom);
		//top.resize(top.width, TypeWriter.calcHeight(name, width));
		//bottom.reposition(bottom.x, top.y + TypeWriter.calcHeight(name, width));
		//bottom.resize(bottom.width,height-top.height);
		itemDetail.visible=true;
		TypeWriter.setSize(1);
		return itemDetail;
	}
}
