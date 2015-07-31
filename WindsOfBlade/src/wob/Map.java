package wob;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;


public class Map {
	Tile[][] background;	
	LinkedList<Wall> walls;
	LinkedList<Npc> npcs;
	LinkedList<EncounterSpot> encounterSpots;
	LinkedList<Enemy> enemies;
	LinkedList<Portal> portals;
	float animationTimer;
	float animationSpeed;
	String filename;
	
	public Map(String filename){//Obj Shift
		try{
			UI.shopBtn.clear();
			walls = new LinkedList<Wall>();
			npcs = new LinkedList<Npc>();
			encounterSpots = new LinkedList<EncounterSpot>();
			enemies = new LinkedList<Enemy>();
			portals = new LinkedList<Portal>();
			background=mapLoader(filename);
			animationTimer=0;
			animationSpeed=.15f;
			this.filename=filename;
		} catch (IOException e) {
			System.out.println("[SEVERE] Problem reading map file \""+filename+"\"");
			System.exit(1);
		}
	}
	public void update(){
		for(Wall w:walls)
			w.update();
		for(Npc n:npcs)
			n.update();
		for(EncounterSpot es:encounterSpots)
			es.update();
		for(Portal p:portals){
			p.update();
		}
	}
	public void render(Graphics g){
		animationTimer+=animationSpeed;
		animationTimer%=Float.MAX_VALUE;
		for(int i=0;i<background.length;i++){
			for(int j=0;j<background[0].length;j++){
				if(i*64+Camera.yShift>-64&&i*64+Camera.yShift<Game.frameHeight&&j*64+Camera.xShift>-64&&j*64+Camera.xShift<Game.frameWidth)
					g.drawImage(background[i][j].getFrame((int)animationTimer),j*64+Camera.xShift,i*64+Camera.yShift,null);
			}
		}
		
		for(Portal p:portals)
			p.worldRender(g);
		for(Npc n:npcs)
			n.worldRender(g);
		//for(EncounterSpot es:encounterSpots)
		//	es.worldRender(g);
		//for(Wall w:walls)
			//w.worldRender(g);
	}
	/*
	 * Parses through a map file and loads it as a map object
	 */
	private Tile[][] mapLoader(String filename) throws IOException{
		//Reading the file;
		BufferedReader br = new BufferedReader(new FileReader(new File(filename)));
		int item;
		String rawInput = "";
		while((item=br.read())!=-1)
			rawInput+=(char)item;
		br.close();
		rawInput=rawInput.trim();
		//Grabbing object data
		String[] objectData = rawInput.split("Object");
		try{
			rawInput = (objectData[0].substring(0,objectData[0].indexOf("["))).trim();
		}catch(Exception e){
			System.out.println("[WARNING] this map: \""+filename+"\" has no objects in it");
		}
		//Parsing Through object data
		for(int i=1;i<objectData.length;i++){
			String[] property = objectData[i].split("\n"); 
			if(property[1].contains("type=npc"))
				npcs.add(parseNPC(property));
			else if(property[1].contains("type=battle"))
				encounterSpots.add(parseEncounterSpot(property));
			else if(property[1].contains("type=enemy"))
				for(int j=3;j<property.length;j++)
					enemies.add(new Enemy(property[j].substring(0,property[j].length()-1)));
			else if(property[1].contains("type=portal"))
				portals.add(parsePortal(property));
			else if(property[1].contains("inn"))
				npcs.add(parseInnKeeper(property));
			else if(property[1].contains("shop"))
				npcs.add(parseShopKeeper(property));
		}
		//Parsing Through Map Data
		//Removing ',' and '\n'
		String mapData="";
		int numCols=0;
		int numRows=1;
		for(int i = 0;i<rawInput.length();i++){
			if(rawInput.charAt(i)!=','){
				if(rawInput.charAt(i)=='\n'){
					numRows++;
				}else{
					numCols++;
					mapData+=rawInput.charAt(i);
				}
			}
		}
		numCols/=numRows;
		//Placing data into a 2d array;
		Tile[][] map = new Tile[numRows][numCols];
		SparseMatrix<Wall> wally = new SparseMatrix<Wall>(map[0].length,map.length);
		for(int i = 0;i<map.length;i++){
			for(int j=0;j<map[0].length;j++){
				switch(Integer.parseInt(""+mapData.charAt(j+i*(numRows+1)))){
				case 1:
					map[i][j]= new Tile(ImageManager.getImageList("res/tiles/water/water.png",10));
					wally.add(i, j, new Wall(j*64,i*64,64,64));
					break;
				case 2:
					map[i][j]=new Tile(ImageManager.getImage("res/tiles/stone.png"));
					break;
				case 3:
					map[i][j]=new Tile(ImageManager.getImage("res/tiles/grass.png"));
					break;
				case 4:
					map[i][j]=new Tile(ImageManager.getImage("res/tiles/bricks.png"));
					break;
				case 5:
					map[i][j]=new Tile(ImageManager.getImage("res/tiles/wood.png"));
					break;
				case 9:
					break;
				case 10:
					break;
				case 11:
					break;
				case 17:
					break;
				case 19:
					break;
				case 25:
					break;
				case 26:
					break;
				case 27:
					break;
				}
			}
		}
		walls = optimizeWalls(wally);
		return map;
	}
	private ShopKeeper parseShopKeeper(String[] line) {
		Item[] items;
		int[] prices;
		String[] itemStr = line[3].split("=")[1].split(",");
		String[]pricesStr = line[5].split("=")[1].split(",");
		items = new Item[itemStr.length];
		prices = new int[itemStr.length];
		for(int i=0;i<itemStr.length;i++){
			items[i]=Item.database[Arrays.binarySearch(Item.database, new Item(itemStr[i].trim(),null))];
			prices[i] = Integer.parseInt(pricesStr[i].trim());
		}
		return new ShopKeeper(64*Integer.parseInt(line[2].split(",")[0].split("=")[1]),	//X
				64*Integer.parseInt(line[2].split(",")[1]),								//Y
				line[4].split("=")[1], 													//Name
				items, prices);
	}
	private InnKeeper parseInnKeeper(String[] line) {
		return (new InnKeeper(64*Integer.parseInt(line[2].split(",")[0].split("=")[1]), //X
				64*Integer.parseInt(line[2].split(",")[1]),								//Y
				line[3].split("=")[1],													//Name
				Integer.parseInt(line[4].split("=")[1].trim())));								//Inn price
	}
	/*
	 * Removes Wall objects that have walls touching all their adjacent sides
	 */
	private LinkedList<Wall> optimizeWalls(SparseMatrix<Wall> parentList){//TODO optimize walls more
		LinkedList<Point> flagsForRemoval = new LinkedList<Point>();
		for(Cell<Wall> current = parentList.head;current!=null;current=current.getNext()){
			if((parentList.get(current.y+1, current.x)!=null)&&(parentList.get(current.y-1, current.x)!=null)&&
			(parentList.get(current.y, current.x+1)!=null)&&(parentList.get(current.y, current.x-1)!=null)){
				flagsForRemoval.add(new Point(current.x,current.y));
			}
		}
		for(int i = 0;i<flagsForRemoval.size();i++){
			parentList.remove(flagsForRemoval.get(i).y, flagsForRemoval.get(i).x);
		}
		LinkedList<Wall> list = new LinkedList<Wall>();
		for(Cell<Wall> current = parentList.head;current!=null;current=current.getNext()){
			list.add((Wall) current.getValue());
		}
		return list;
	}
	private Npc parseNPC(String[] line){
		return (new Npc(64*Integer.parseInt(line[2].split(",")[0].split("=")[1]),   //X
				64*Integer.parseInt(line[2].split(",")[1]),							//Y
				line[3].split("=")[1].trim()));										//Name
	}
	private EncounterSpot parseEncounterSpot(String[] line){
		String[] components = line[2].split(",");
		return (new EncounterSpot(64*Integer.parseInt(components[0].split("=")[1]),
				64*Integer.parseInt(components[1]),64*Integer.parseInt(components[2]),
				64*Integer.parseInt(components[3].trim()),
				Float.parseFloat(line[4].split("=")[1].trim())));
	}
	private Portal parsePortal(String[] line){
		return (new Portal(64*Integer.parseInt(line[2].split("=")[1].split(",")[0].trim()),
				64*Integer.parseInt(line[2].split(",")[1]),
				line[4].split("=")[1].trim(),
				new Point(64*Integer.parseInt(line[3].split(",")[0].split("=")[1]),64*Integer.parseInt(line[3].split(",")[1].trim()))));
	}
	public void shiftObjects(int xShift, int yShift){
		for(Wall w:walls){
			w.x+=xShift;
			w.y+=yShift;
		}
		for(Npc n:npcs){
			n.x+=xShift;
			n.y+=yShift;
		}
		for(EncounterSpot es:encounterSpots){
			es.x+=xShift;
			es.y+=yShift;
		}
		for(Portal p:portals){
			p.x+=xShift;
			p.y+=yShift;
		}
	}
}
