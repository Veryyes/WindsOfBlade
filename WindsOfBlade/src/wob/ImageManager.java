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
	public static BufferedImage topUIBorder;
	public static BufferedImage botUIBorder;
	public static BufferedImage leftUIBorder;
	public static BufferedImage rightUIBorder;
	public static BufferedImage topRightUIBorder;
	public static BufferedImage topLeftUIBorder;
	public static BufferedImage botRightUIBorder;
	public static BufferedImage botLeftUIBorder;
	public static BufferedImage defaultBackdrop;
	public static BufferedImage tempNPC;
	public static void LoadImages() throws IOException{
		wbSepia=loadImage("res/menu/Winged Blade Sepia.png");
		loading=loadImage("res/menu/Loading.png");
		tileSet=loadImage("res/tiles/tileset.png");
		player=loadImage("res/sprites/player/tempPlayer.png");
		loadTiles();
		loadUI();
		loadBackdrops();
		tempNPC=loadImage("res/npc/npc.png");
	}
	private static BufferedImage loadImage(String filepath) throws IOException{
		return ImageIO.read(new File(filepath));
	}
	private static void loadBackdrops() throws IOException{
		defaultBackdrop=loadImage("res/backdrop/backdrop.png");
	}
	private static void loadUI() throws IOException{
		topUIBorder=loadImage("res/ui/topBorder.png");
		botUIBorder=loadImage("res/ui/botBorder.png");
		leftUIBorder=loadImage("res/ui/leftBorder.png");
		rightUIBorder=loadImage("res/ui/rightBorder.png");
		topLeftUIBorder=loadImage("res/ui/topLeftBorder.png");
		topRightUIBorder=loadImage("res/ui/topRightBorder.png");
		botLeftUIBorder=loadImage("res/ui/botLeftBorder.png");
		botRightUIBorder=loadImage("res/ui/botRightBorder.png");
	}
	private static void loadTiles() throws IOException{
		water=loadImage("res/tiles/water.png");
		stone=loadImage("res/tiles/stone.png");
		grass=loadImage("res/tiles/grass.png");
		bricks=loadImage("res/tiles/bricks.png");
		wood=loadImage("res/tiles/wood.png");
	}
}
