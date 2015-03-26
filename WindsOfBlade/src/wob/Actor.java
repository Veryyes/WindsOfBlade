package wob;

import java.awt.image.BufferedImage;

public abstract class Actor extends Entity {
	BufferedImage image;
	public Actor(int x, int y) {
		super(x,y);
	}

}
