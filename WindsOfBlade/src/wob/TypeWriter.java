package wob;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;

public class TypeWriter {
	public static char[] chars = {'/','.','?',';','_',',','!','$','0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
	public static BufferedImage[] fonts;
	//28x40
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
	public static void drawString(String text, int x,int y,Graphics g){
		text=text.toLowerCase();
		char[] data = text.toCharArray();
		for(int i=0;i<data.length;i++){
			if(data[i]==' ')
				data[i]='_';
			g.drawImage(getImage(data[i]),x+i*28,y,null);
		}
	}
	private static BufferedImage getImage(char c){
		return fonts[Arrays.binarySearch(chars,c)];
	}
}