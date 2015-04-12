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
	public static BufferedImage water;
	public static BufferedImage stone;
	public static BufferedImage grass;
	public static BufferedImage bricks;
	public static BufferedImage wood;
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
		loadTiles();
	}
	public static void printWarning(String s){
		System.out.println("[WARNING] Cannot find file \""+s+"\"");
	}
	private static void loadTiles(){
		try {
			water=ImageIO.read(new File("res/tiles/water.png"));
		} catch (IOException e) {
			printWarning("res/tiles/water.png");
		}
		try {
			stone=ImageIO.read(new File("res/tiles/stone.png"));
		} catch (IOException e) {
			printWarning("res/tiles/stone.png");
		}
		try {
			grass=ImageIO.read(new File("res/tiles/grass.png"));
		} catch (IOException e) {
			printWarning("res/tiles/grass.png");
		}
		try {
			bricks=ImageIO.read(new File("res/tiles/bricks.png"));
		} catch (IOException e) {
			printWarning("res/tiles/bricks.png");
		}
		try {
			wood=ImageIO.read(new File("res/tiles/wood.png"));
		} catch (IOException e) {
			printWarning("res/tiles/wood.png");
		}
	}
}
