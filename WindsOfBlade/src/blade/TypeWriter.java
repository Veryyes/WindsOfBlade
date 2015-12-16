package blade;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class TypeWriter {
	private static BufferedImage[] characters;
	private static BufferedImage newLine;
	private static float size = 1;
	/**
	 * Loads chars from 32 to 126 into an array
	 */
	public static void loadFont(){
		characters = new BufferedImage[95];
		for(char c = ' ';c<='~';c++){
			if(c>='a'&&c<='z')//Lower Case Letter Handling b/c Windows files aren't case-sensitive
				try{
					characters[c-32]=ImageIO.read(new File("font/"+c+"1.png"));
				}catch(IOException e){
					System.out.println("[WARNING] Can not find file \"font/"+c+"1.png\"");
				}
			else{
				try{
					switch(c){
					case '/':
						characters[c-32]=ImageIO.read(new File("font/forwardslash.png"));
						break;
					case '.':
						characters[c-32]=ImageIO.read(new File("font/period.png"));
						break;
					case '?':
						characters[c-32]=ImageIO.read(new File("font/question.png"));
						break;
					case ';':
						characters[c-32]=ImageIO.read(new File("font/semicolon.png"));
						break;
					case '<':
						characters[c-32]=ImageIO.read(new File("font/greaterthan.png"));
						break;
					case '>':
						characters[c-32]=ImageIO.read(new File("font/lessthan.png"));
						break;
					case '\\':
						characters[c-32]=ImageIO.read(new File("font/backslash.png"));
						break;
					case ':':
						characters[c-32]=ImageIO.read(new File("font/colon.png"));
						break;
					case '*':
						characters[c-32]=ImageIO.read(new File("font/star.png"));
						break;
					case '|':
						characters[c-32]=ImageIO.read(new File("font/or.png"));
						break;
					case '\"':
						characters[c-32]=ImageIO.read(new File("font/quote.png"));
						break;
					case ' ':
						characters[c-32]=ImageIO.read(new File("font/space.png"));
						break;
					default:
						characters[c-32]=ImageIO.read(new File("font/"+c+".png"));
					}
				}catch(IOException e){
					System.out.println("[WARNING] Can not find file \"font/"+c+".png\"");
				}
			}
		}
		try{
			newLine = ImageIO.read(new File("font/newLine.png"));
		}catch(IOException e){
			System.out.println("[WARNING] Can not find file \"font/newline.png\"");
		}
	}
	public static void drawString(String text, int x, int y, Graphics g){
		char[] data = text.toCharArray();
		for(int i=0;i<data.length;i++){
			if(data[i]==' ')
				data[i]='_';
			BufferedImage img = getChar(data[i]);
			g.drawImage(img,(int)(x+i*img.getWidth()*size),y,(int)(img.getWidth()*size),(int)(img.getHeight()*size),null);
		}
	}
	/**
	 * Draws a message in a rectangle text wrapping inside it
	 * @param text The message to be drawn
	 * @param x X-coordinate of rectangle
	 * @param y Y-coordinate of rectangle
	 * @param width Width of rectangle
	 * @param height Height of rectangle
	 * @param g Graphics to be drawn on
	 */
	public static void drawString(String text, int x, int y, int width, int height, Graphics g){
		char[] data = text.toCharArray();
		int lineNumber=0;
		int maxChars = (int) (width/(characters[0].getWidth()*size));
		int charNums = 0;
		for(int i=0;i<data.length;i++){
			BufferedImage img = getChar(data[i]);
			if(charNums>maxChars||img==newLine){
				lineNumber++;
				charNums=0;
				if((lineNumber+1)*img.getHeight()>height)
					break;
			}
			g.drawImage(img,(int)(x+((charNums*img.getWidth()*size))),(int)(y+img.getHeight()*size*lineNumber),(int)(img.getWidth()*size),(int)(img.getHeight()*size),null);
			
			charNums++;
		}
	}
	/**
	 * Draws a message in a rectangle text wrapping inside it without a height limit
	 * @param text The message to be drawn
	 * @param x X-coordinate of rectangle
	 * @param y Y-coordinate of rectangle
	 * @param width Width of rectangle
	 * @param g Graphics to be drawn on
	 */
	public static void drawString(String text, int x, int y, int width, Graphics g){
		char[] data = text.toCharArray();
		int lineNumber=0;
		int maxChars = (int) (width/(characters[0].getWidth()*size));
		int charNums = 0;
		for(int i=0;i<data.length;i++){
			BufferedImage img = getChar(data[i]);
			if(charNums>maxChars||img==newLine){
				lineNumber++;
				charNums=0;
			}
			g.drawImage(img,(int)(x+((charNums*img.getWidth()*size))),(int)(y+img.getHeight()*size*lineNumber),(int)(img.getWidth()*size),(int)(img.getHeight()*size),null);
			charNums++;
		}
	}
	/**
	 * Calculates the height in pixels that writing this message takes up
	 * @param text The message
	 * @param width	the width of the rectangle to write in
	 * @return The height of the message if it were to be drawn
	 */
	public static int calcHeight(String text, int width){
		int newLineCount=0;
		for(int i=0;i<text.length();i++)
			if(text.charAt(i)=='\n')
				newLineCount++;
		int totalLength =  (int)((text.length()-newLineCount)*characters[0].getWidth()*size);
		return (int) ((totalLength/(float)width)*characters[0].getHeight()*size +((1+newLineCount)*characters[0].getHeight()*size));
	}
	/**
	 * Returns the BufferedImage representation of the char O(1)
	 * @param c char to get image of
	 * @return a BufferdImage of char c
	 */
	private static BufferedImage getChar(char c){
		if(c=='\n')
			return newLine;
		return characters[c-32];
	}
	public static void setSize(float sizeMultiplyer){
		size=sizeMultiplyer;
	}
	public static int getCharWidth(){
		return characters[0].getWidth();
	}
	public static int getCharHeight(){
		return characters[0].getHeight();
	}
}
