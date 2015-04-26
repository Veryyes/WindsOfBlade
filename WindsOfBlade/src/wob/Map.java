package wob;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;


public class Map {
	Tile[][] background;	//TODO make this int? flags for wall and door and ect?
	LinkedList<Wall> walls;
	LinkedList<Npc> npcs;
	Portal north,south,east,west;
	float animationTimer;
	float animationSpeed;
	public Map(String filename){
		try {
			walls = new LinkedList<Wall>();
			npcs = new LinkedList<Npc>();
			background=mapLoader(filename);
			animationTimer=0;
			animationSpeed=.15f;
		} catch (IOException e) {
			System.out.println("[Warning] Problem reading file \""+filename+"\"");
		}
		npcs.add(new Npc(256,256));
	}
	public void update(){
		for(Wall w:walls)
			w.update();
		for(Npc n:npcs)
			n.update();
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
	}
	public static void loadWorld(){
		
	}
	public Tile[][] mapLoader(String filename) throws IOException{
		//Reading the file;
		BufferedReader br = new BufferedReader(new FileReader(new File(filename)));
		int item;
		String rawInput = "";
		while((item=br.read())!=-1)
			rawInput+=(char)item;
		br.close();
		//Removing ',' and '\n'
		String mapData="";
		int numCols=0;
		int numRows=1;
		rawInput=rawInput.trim();
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
				switch(mapData.charAt(j+i*(numRows+1))){//TODO make place tiles in an array plz :|
				case '1'://TODO make tile class so water can animate //TODO make water animations
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

}
