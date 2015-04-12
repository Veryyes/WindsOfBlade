package wob;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class Map {
	BufferedImage[][] background;	//TODO make this int? flags for wall and door and ect?
	Portal north,south,east,west;
	public Map(String filename){
		try {
			background=mapLoader(filename);
		} catch (IOException e) {
			System.out.println("[Warning] Problem reading file \""+filename+"\"");
		}
	}
	public void render(Graphics g){
		for(int i=0;i<background.length;i++){
			for(int j=0;j<background[0].length;j++){
				g.drawImage(background[i][j],j*64,i*64,null);
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
		//rows = num of \n + 1?
		//Shoving it in a 2D array && loading in entities
		char[] data5=data4.toCharArray();
		BufferedImage[][] map = new BufferedImage[numRows][numCols];
		for(int i = 0;i<map.length;i++){
			for(int j=0;j<map[0].length;j++){
				//map[i][j]=ImageManager.tileSet.getSubimage(((j+i*(numRows+1))%8)*64,(int) ((Math.floor((int)((j+i*(numRows+1))/8)))*64), 64, 64);
				switch(data5[j+i*(numRows+1)]){//TODO make place tiles in an array plz :|
				case '1':
					map[i][j]=ImageManager.water;
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
		return map;
	}

}
