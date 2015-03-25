package wob;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageManager {
	/*
	 *  Loads in all Images on initialization
	 */
	private static String warning = "[WARNING] Cannot find file ";
	public static BufferedImage wbSepia;
	public static void LoadImages(){
		try {
			wbSepia=ImageIO.read(new File("res/menu/Winged Blade Sepia.png"));
		} catch (IOException e) {
			System.out.println(warning+"\"res/menu/Winged Blade Sepia.png\"");
		}
	}

}
