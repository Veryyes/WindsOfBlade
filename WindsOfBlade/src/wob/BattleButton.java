package wob;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class BattleButton extends Button {
	boolean selected;
	int index;
	public BattleButton(int x, int y, int width, int height, int index) {
		super(x, y, width, height);
		selected=false;
		this.index=index;
	}
	public void highlight(Graphics g){
		if((BattleManager.battleState&4)>0){
			for(int i=1+Game.player.party.size();i<BattleManager.targets.size();i++){
				if(index==i){
					BufferedImage img = ImageManager.getImage("res/enemy/"+BattleManager.targets.get(i).name+".png");
					UI.drawRectUI(256*(i-Game.player.party.size())-128, 128, img.getWidth(), img.getHeight(), false, g);
				}
			}
			try{
				@SuppressWarnings("unused")
				Fighter f = BattleManager.targets.get(this.index+BattleManager.buttonShift*3);
				super.highlight(g);
			}catch(java.lang.IndexOutOfBoundsException e){//So we don't highlight boxes w/o text
				//Do Nothing
			}
		}else if(((BattleManager.battleState&8)>0)||((BattleManager.battleState&16)>0))
			super.highlight(g);
	}
}
