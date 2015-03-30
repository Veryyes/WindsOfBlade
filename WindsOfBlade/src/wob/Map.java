package wob;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class Map {
	BufferedImage[] background;	//TODO make this int? flags for wall and door and ect?
	Portal north,south,east,west;
	public Map() {
		
	}
	public String[][] mapLoader(String filename) throws IOException{//Going to make this return a SparseMatrix when we go to states
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
		char[] data3 = data2.toCharArray();
		String data4 = "";
		for(char c:data3){
			if(c!='\n')
				data4+=c;
		}
		//Shoving it in a 2D array && loading in entities
		char[] data5=data4.toCharArray();
		String[][] map = new String[64][64];
		for(int i = 0;i<map.length;i++){
			for(int j=0;j<map[0].length;j++){
				map[i][j]=""+data5[j+i*65];
				if(map[i][j].equals("2")){
					//Global.walls.add(new Wall(j*128,i*128));
				}else if(map[i][j].equals("3")){
					//Global.portal=(new Portal(j*128,i*128));
				}else if(map[i][j].equals("4")){
					//x=-((j*128)-(4*128));
					//y=-(i*128-(2*128));
				}
			}
		}
		return map;
	}

}
