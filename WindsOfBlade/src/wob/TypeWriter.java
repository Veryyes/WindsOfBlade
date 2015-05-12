package wob;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

import javax.imageio.ImageIO;

public class TypeWriter {
	public static char[] chars = {'/','.','?',';','_',',','!','$','0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
	public static BufferedImage[] fonts;
	//28x40
	/*
	 * Loads all the fonts & the characters in them
	 */
	public static void LoadFont(){
		Arrays.sort(chars);
		fonts = new BufferedImage[chars.length];
		for(int i=0;i<fonts.length;i++){
			try {
				switch(chars[i]){
				case '/':
					fonts[i]=ImageIO.read(new File("res/font/forwardslash.png"));
					break;
				case '.':
					fonts[i]=ImageIO.read(new File("res/font/period.png"));
					break;
				case '?':
					fonts[i]=ImageIO.read(new File("res/font/question.png"));
					break;
				case ';':
					fonts[i]=ImageIO.read(new File("res/font/semicolon.png"));
					break;
				default:
					fonts[i]=ImageIO.read(new File("res/font/"+chars[i]+".png"));
					break;
				}
			} catch (IOException e) {
				System.out.println("[WARNING] Can not find file \"res/font/"+chars[i]+".png\"");
			}
		}
	}
	/*
	 * Draws a string on the screen
	 */
	public static void drawString(String text, int x,int y,Graphics g){
		text=text.toLowerCase();
		char[] data = text.toCharArray();
		for(int i=0;i<data.length;i++){
			if(data[i]==' ')
				data[i]='_';
			g.drawImage(getImage(data[i]),x+i*28,y,null);
		}
	}
	/*
	 * Draws a string; meant for the bottom UI dialoge box
	 */
	public static void drawMessage(String text ,Graphics g){
		//35 Chars, max width;
		//4 chars, max height;
		//140 chars max area;
		int widthCounter=0;
		int heightCounter=0;
		text=text.toLowerCase().trim();
		char[] data = text.toCharArray();
		for(int i=0;i<data.length;i++){
			if(data[i]==' ')
				data[i]='_';
			g.drawImage(getImage(data[i]),32+widthCounter*28,448+(40*heightCounter),null);
			widthCounter++;
			if(widthCounter>34){ 
				widthCounter=0;
				heightCounter++;
			}
		}
	}
	/*
	 * Used to overcome the IndexOutofBoundsException thrown when LinkedList.get(index),
	 * where the index is out of range (index < 0 || index >= size())
	 */
	public static void drawMoveName(LinkedList<Move> m, int index,int x, int y, Graphics g){
		try{
			drawString(m.get(index+BattleManager.buttonShift*3).name,x,y,g);
		}catch(java.lang.IndexOutOfBoundsException e){
			drawString("",x,y,g);
		}
	}
	/*
	 * Same as above, but barely changed for Item objects
	 */
	public static void drawItemName(LinkedList<Item> m, int index,int x, int y, Graphics g){
		try{
			drawString(m.get(index+BattleManager.buttonShift*3).name,x,y,g);
		}catch(java.lang.IndexOutOfBoundsException e){
			drawString("",x,y,g);
		}
	}
	/*
	 * binary Search for a specific char & grabs the corresponding Image in a parallel array
	 */
	private static BufferedImage getImage(char c){
		try{
			return fonts[Arrays.binarySearch(chars,c)];
		}catch(Exception e){
			System.out.println(c);//TODO error with '\n'
			return fonts[Arrays.binarySearch(chars,'_')];
		}
	}
}
