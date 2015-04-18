package wob;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;


public class Map {
	BufferedImage[][] background;	//TODO make this int? flags for wall and door and ect?
	LinkedList<Wall> walls;
	Portal north,south,east,west;
	public Map(String filename){
		try {
			walls = new LinkedList<Wall>();
			background=mapLoader(filename);
		} catch (IOException e) {
			System.out.println("[Warning] Problem reading file \""+filename+"\"");
		}
	}
	public void render(Graphics g){//TODO fix this
		for(int i=0;i<background.length;i++){
			for(int j=0;j<background[0].length;j++){
				if(i*64+Camera.yShift>-64&&i*64+Camera.yShift<Game.frameHeight&&j*64+Camera.xShift>-64&&j*64+Camera.xShift<Game.frameWidth)
					g.drawImage(background[i][j],j*64+Camera.xShift,i*64+Camera.yShift,null);
			}
		}
	}
	public static void loadWorld(){
		
	}
	public BufferedImage[][] mapLoader(String filename) throws IOException{//Going to make this return a SparseMatrix when we go to states
		//Reading the File
		BufferedReader br = new BufferedReader(new FileReader(new File(filename)));
		int item;
		String data = "";
		while((item=br.read())!=-1){
			data+=(char)item;
		}
		//Removing ","
		data=data.trim();
		String[] data1 = data.split(",");
		String data2="";
		for(String s : data1){
			data2+=s;
		}
		//Removing "\n"
		int numCols=0;
		int numRows=1;
		char[] data3 = data2.toCharArray();
		String data4 = "";
		for(int i = 0;i<data3.length;i++){
			if(data3[i]!='\n'){
				data4+=data3[i];
				numCols++;
			}
			else
				numRows++;
		}
		numCols/=numRows;
		//Shoving it in a 2D array && loading in entities
		char[] data5=data4.toCharArray();
		BufferedImage[][] map = new BufferedImage[numRows][numCols];
		SparseMatrix<Wall> wally = new SparseMatrix<Wall>(map[0].length,map.length);
		for(int i = 0;i<map.length;i++){
			for(int j=0;j<map[0].length;j++){
				//map[i][j]=ImageManager.tileSet.getSubimage(((j+i*(numRows+1))%8)*64,(int) ((Math.floor((int)((j+i*(numRows+1))/8)))*64), 64, 64);
				switch(data5[j+i*(numRows+1)]){//TODO make place tiles in an array plz :|
				case '1'://TODO make tile class so water can animate //TODO make water animations
					map[i][j]=ImageManager.water;
					wally.add(i, j, new Wall(j*64,i*64,64,64));
					break;
				case '2':
					map[i][j]=ImageManager.stone;
					break;
				case '3':
					map[i][j]=ImageManager.grass;
					break;
				case '4':
					map[i][j]=ImageManager.bricks;
					break;
				case '5':
					map[i][j]=ImageManager.wood;
					break;
				}
			}
		}
		//TODO optimize walls
		//walls = optimizeWalls(wally);
		for(Cell current = wally.head;current!=null;current=current.getNext()){
			walls.add((Wall) current.getValue());
		}
		return map;
	}
	private LinkedList<Wall> optimizeWalls(SparseMatrix<Wall> parentList){ //Borken plz fix pl0x;
		SparseMatrix<Wall> temp = new SparseMatrix<Wall>(parentList.numRows(),parentList.numColumns());
		for(Cell current=parentList.head;current.getNext().getNext()!=null;current=current.getNext()){
			if(current.x+(((Wall)(current.getValue())).hitBox.getWidth()/64)==current.getNext().x){
				Wall big = new Wall(1,1,1,1);
				Rectangle2D.Double.union(((Wall)current.getValue()).hitBox,((Wall)current.getNext().getValue()).hitBox,big.hitBox);
				temp.add(current.x,current.y,big);
			}
		}
		LinkedList<Wall> list = new LinkedList<Wall>();
		for(Cell current = temp.head;current.getNext()!=null;current=current.getNext()){
			list.add((Wall) current.getValue());
		}
		return list;
	}

}
