package wob;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

import javax.imageio.ImageIO;

public class ImageManager {
	/*
	 *  Loads in all Images on initialization
	 */
	public static String[] imageNames;
	public static BufferedImage[] images;
	private static LinkedList<String> tempNames = new LinkedList<String>();
	public static void LoadImages(String root) throws IOException{
		loadImageNames(root);
		imageNames = new String[tempNames.size()];
		images = new BufferedImage[imageNames.length];
		for(int i=0;i<images.length;i++)
			imageNames[i]=tempNames.get(i);
		Arrays.sort(imageNames);
		for(int i=0;i<images.length;i++){
			images[i]=loadImage(imageNames[i]);
		}
		tempNames.clear();
	}
	public static BufferedImage getImage(String filepath){
		int index = Arrays.binarySearch(imageNames, filepath);
		if(index<0){
			System.out.println("[WARNING] Missing or Invalid Image \""+filepath+"\"");
			return null;
		}
		return images[index];
	}
	public static BufferedImage[] getImageList(String filepath, int imageListLength){
		BufferedImage[] list = new BufferedImage[imageListLength];
		for(int i=0;i<imageListLength;i++)
			list[i]=getImage(filepath.split("\\.")[0]+(i+1)+"."+filepath.split("\\.")[1]);
		return list;
	}
	public static BufferedImage[] getImageList(String ... filepaths){
		BufferedImage[] list = new BufferedImage[filepaths.length];
		for(int i=0;i<list.length;i++)
			list[i]=getImage(filepaths[i]);
		return list;
	}
	private static void loadImageNames(String root){
		File imageResources = new File(root);
		for(String s: imageResources.list()){
			if(!s.contains("."))
				loadImageNames(root+"/"+s);
			else
				tempNames.add(root+"/"+s);
		}
	}
	private static BufferedImage loadImage(String filepath) throws IOException{
		return ImageIO.read(new File(filepath));
	}
	//http://stackoverflow.com/questions/4248104/applying-a-tint-to-an-image-in-java
	/*public BufferedImage colorImage(BufferedImage loadImg, int red, int green, int blue) {
	    BufferedImage img = new BufferedImage(loadImg.getWidth(), loadImg.getHeight(),
	        BufferedImage.TRANSLUCENT);
	    Graphics2D graphics = img.createGraphics(); 
	    Color newColor = new Color(red, green, blue, 0 );
	    graphics.setXORMode(newColor);
	    graphics.drawImage(loadImg, null, 0, 0);
	    graphics.dispose();
	    return img;
	}
	public static BufferedImage colorImage(BufferedImage loadImg, Color c) {
	    BufferedImage img = new BufferedImage(loadImg.getWidth(), loadImg.getHeight(),
	        BufferedImage.TRANSLUCENT);
	    Graphics2D graphics = img.createGraphics(); 
	    Color newColor = new Color(c.getRed(), c.getGreen(), c.getBlue(), 0 );
	    graphics.setXORMode(newColor);
	    graphics.drawImage(loadImg, null, 0, 0);
	    graphics.dispose();
	    return img;
	}*/
	
}
