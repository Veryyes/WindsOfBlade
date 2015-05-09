package wob;

import java.awt.Graphics;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;


public class Map {
	Tile[][] background;	
	LinkedList<Wall> walls;
	LinkedList<Npc> npcs;
	LinkedList<EncounterSpot> encounterSpots;
	LinkedList<String> enemyList;
	Portal north,south,east,west;
	float animationTimer;
	float animationSpeed;
	public Map(String filename){
		try {
			walls = new LinkedList<Wall>();
			npcs = new LinkedList<Npc>();
			encounterSpots = new LinkedList<EncounterSpot>();
			enemyList = new LinkedList<String>();
			background=mapLoader(filename);
			animationTimer=0;
			animationSpeed=.15f;
		} catch (IOException e) {
			System.out.println("[Warning] Problem reading file \""+filename+"\"");
		}
	}
	public void update(){
		for(Wall w:walls)
			w.update();
		for(Npc n:npcs)
			n.update();
		for(EncounterSpot es:encounterSpots)
			es.update();
	}
	public void render(Graphics g){//TODO fix this
		animationTimer+=animationSpeed;
		animationTimer%=Float.MAX_VALUE;
		for(int i=0;i<background.length;i++){
			for(int j=0;j<background[0].length;j++){
				if(i*64+Camera.yShift>-64&&i*64+Camera.yShift<Game.frameHeight&&j*64+Camera.xShift>-64&&j*64+Camera.xShift<Game.frameWidth)
					g.drawImage(background[i][j].getFrame((int)animationTimer),j*64+Camera.xShift,i*64+Camera.yShift,null);
			}
		}
		for(Npc n:npcs)
			n.worldRender(g);
		for(EncounterSpot es:encounterSpots)
			es.worldRender(g);
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
			//NPC
			if(property[1].contains("type=npc")){	
				npcs.add(parseNPC(property));
			}
			//EncounterSpot
			if(property[1].contains("type=battle")){
				encounterSpots.add(parseEncounterSpot(property));
			}
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
				switch(mapData.charAt(j+i*(numRows+1))){
				case '1':
					map[i][j]= new Tile(ImageManager.water);
					wally.add(i, j, new Wall(j*64,i*64,64,64));
					break;
				case '2':
					map[i][j]=new Tile(ImageManager.stone);
					break;
				case '3':
					map[i][j]=new Tile(ImageManager.grass);
					break;
				case '4':
					map[i][j]=new Tile(ImageManager.bricks);
					break;
				case '5':
					map[i][j]=new Tile(ImageManager.wood);
					break;
				}
			}
		}
		walls = optimizeWalls(wally);
		return map;
	}
	/*
	 * Removes Wall objects that have walls touching all their adjecent sides
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
	private static Npc parseNPC(String[] strArray){
		return (new Npc(64*Integer.parseInt(strArray[2].split(",")[0].split("=")[1]),
				64*Integer.parseInt(strArray[2].split(",")[1]),strArray[3].split("=")[1].trim()));
	}
	private static EncounterSpot parseEncounterSpot(String[] strArray){
		String[] components = strArray[2].split(",");
		return (new EncounterSpot(64*Integer.parseInt(components[0].split("=")[1]),
				64*Integer.parseInt(components[1]),64*Integer.parseInt(components[2]),
				64*Integer.parseInt(components[3].trim()),
				Float.parseFloat(strArray[4].split("=")[1].trim())));
	}
}
