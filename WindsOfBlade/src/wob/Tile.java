package wob;

import java.awt.image.BufferedImage;
/*
 *  Tile Set
 *  Container Class for BufferedImage[]
 *  For iterating though a set of images
 *  or just a single image
 */
public class Tile {
	protected BufferedImage[] images;
	public Tile(BufferedImage[] tileSet) {
		images=tileSet;
	}
	public Tile(BufferedImage stillImage){
		images = new BufferedImage[1];
		images[0]=stillImage;
	}
	public BufferedImage getFrame(int i){
		return images[i%images.length];
	}
}
