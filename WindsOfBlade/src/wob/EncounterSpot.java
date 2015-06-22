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
			UI.buttonShift=0;
			UI.enableSelectionBtns();
			BattleManager.battleState|=1;
			UI.backBtn.enabled=false;
			BattleManager.targets.clear();
			for(int i=0;i<BattleManager.selectedItem.size();i++){
				BattleManager.selectedItem.set(i,null);
				BattleManager.selectedTarget.set(i,null);
				BattleManager.selectedTechnique.set(i,null);
			}
			BattleManager.targets.add(Game.player);
			for(byte i = 0;i<Game.player.party.size();i++)
				BattleManager.targets.add(Game.player.party.get(i));
			byte enemyNum = (byte) (Math.random()*3 +1);
			for(byte i = 0;i<enemyNum;i++)
				BattleManager.targets.add(Game.map.enemies.get((int)(Math.random()*Game.map.enemies.size())).clone());
			//Load other battle stuff
		}
	}
	public void worldRender(Graphics g) {
		g.drawRect(x, y, (int)hitBox.getWidth(), (int)hitBox.getHeight());
	}

}
