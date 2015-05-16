package wob;

import java.awt.image.BufferedImage;
/*
 *  Animation Set
 *  used to be Tile.java, but it was 
 *  pretty much just set up only for animations so why not?
 *  
 *  kinda like a container class for BufferedImage[]
 *  w/ just a few utilities in it
 */
public class Animation {
	private BufferedImage[] animation;
	public Animation(BufferedImage[] animationSet) {
		animation=animationSet;
	}
	public Animation(BufferedImage stillImage){
		animation = new BufferedImage[1];
		animation[0]=stillImage;
	}
	public BufferedImage getFrame(int i){
		return animation[i%animation.length];
	}

}
