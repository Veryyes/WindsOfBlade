package wob;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageManager {
	/*
	 *  Loads in all Images on initialization
	 */
	public static BufferedImage wbSepia;
	public static BufferedImage loading;
	public static BufferedImage tileSet;
	public static void LoadImages() {
		try {
			wbSepia=ImageIO.read(new File("res/menu/Winged Blade Sepia.png"));
		} catch (IOException e) {
			printWarning("res/menu/Winged Blade Sepia.png");
		}
		try {
			loading=ImageIO.read(new File("res/menu/Loading.png"));
		} catch (IOException e) {
			printWarning("res/menu/Loading.png");
		}
		try {
			tileSet=ImageIO.read(new File("res/tiles/tileset.png"));
		} catch (IOException e) {
			printWarning("res/tiles/tileset.png");
		}
	}
	public static void printWarning(String s){
		System.out.println("[WARNING] Cannot find file \""+s+"\"");
	}
}
