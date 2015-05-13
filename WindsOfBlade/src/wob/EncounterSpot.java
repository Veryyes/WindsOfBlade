package wob;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

public class EncounterSpot extends Entity implements WorldObject{
	float encounterRate;
	public EncounterSpot(int x, int y, int w, int h,float rate) {
		super(x, y);
		hitBox = new Rectangle2D.Double(x,y,w,h);
		encounterRate=rate;
	}
	
	public void update() {
		updateLocation();
		if(Camera.isMoving()&&Math.random()<encounterRate&&Game.player.hitBox.intersects(hitBox)){
			Game.gameStates&=~16;
			Game.gameStates|=32;
			UI.enableSelectionBtns();
			BattleManager.battleState|=1;
			UI.backBtn.enabled=true;
			//Load other battle stuff
		}
	}

	@Override
	public void worldRender(Graphics g) {
		g.drawRect(x, y, (int)hitBox.getWidth(), (int)hitBox.getHeight());
		
	}

}
