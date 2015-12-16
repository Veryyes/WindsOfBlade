package blade;

import java.awt.Color;
import java.awt.Graphics;

public class Wall extends Entity{
	static boolean drawWalls = false;
	public Wall(int x, int y) {
		super(x, y, Game.GRIDSIZE, Game.GRIDSIZE);
	}
	@Override
	public void update(Graphics g) {
		super.update(g);
		if(drawWalls){
			g.setColor(Color.WHITE);
			g.drawRect(box.x, box.y, box.width, box.height);
		}
	}
}
