package wob;

import java.awt.image.BufferedImage;
/*
 *  Animation Set
 *  Meant for things that animate,
 *  and will animate independent of other objects
 */
public class Animation extends Tile{
	private float timer;
	private float speed;
	public boolean repeat;
	public Animation(BufferedImage[] animationSet) {
		super(animationSet);
		timer=0;
		speed=.15f;
		repeat=true;
	}
	public Animation(BufferedImage[] animationSet, boolean repeat) {
		super(animationSet);
		timer=0;
		speed=.15f;
		this.repeat=repeat;
	}
	public Animation(BufferedImage stillImage){
		super(stillImage);
		timer=0;
		speed=.15f;
		repeat=false;
	}
	public BufferedImage getFrame(int i){
		return images[i];
	}
	public BufferedImage getFrame(){
		return images[(int)(timer%images.length)];
	}
	public void update(){
		if(repeat&&timer%images.length==images.length-1)
			timer-=speed;
		timer+=speed;
		timer%=images.length;
		
	}
}
