package wob;

import java.awt.image.BufferedImage;
/*
 *  Just a way to manage background tiles
 *  Some animate and some dont, plus number of frame 
 *  in an animation may vary depending on the tile 
 */
public class Tile {
	BufferedImage[] animation;
	public Tile(BufferedImage[] animationSet) {
		animation=animationSet;
	}
	public Tile(BufferedImage stillImage){
		animation = new BufferedImage[1];
		animation[0]=stillImage;
	}
	public BufferedImage getFrame(int i){
		return animation[i%animation.length];
		
	}

}
