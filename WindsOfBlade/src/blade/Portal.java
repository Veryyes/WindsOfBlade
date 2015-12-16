package blade;

import java.awt.Graphics;

public class Portal extends Entity{
	public Portal(int x, int y){
		super(x,y, new Animation(ImageManager.getList("res/sprites/portal/portal", 20)));
	}

	@Override
	public void update(Graphics g) {
		super.update(g);
	}

}
