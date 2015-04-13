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
	public static BufferedImage player;
	public static void LoadImages() throws IOException{
		wbSepia=ImageIO.read(new File("res/menu/Winged Blade Sepia.png"));
		loading=ImageIO.read(new File("res/menu/Loading.png"));
		tileSet=ImageIO.read(new File("res/tiles/tileset.png"));
		player=ImageIO.read(new File("res/sprites/player/tempPlayer.png"));
		loadTiles();
	}
	public static void printWarning(String s){
		System.out.println("[WARNING] Cannot find file \""+s+"\"");
	}
	private static void loadTiles() throws IOException{
		water=ImageIO.read(new File("res/tiles/water.png"));
		stone=ImageIO.read(new File("res/tiles/stone.png"));
		grass=ImageIO.read(new File("res/tiles/grass.png"));
		bricks=ImageIO.read(new File("res/tiles/bricks.png"));
		wood=ImageIO.read(new File("res/tiles/wood.png"));
	}
}
