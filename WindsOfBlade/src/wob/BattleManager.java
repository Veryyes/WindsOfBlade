package wob;

import java.awt.Graphics;

public class BattleManager {
	public BattleManager() {
		
	}
	
	public static void render(Graphics g){
		if(atMenu1()){
			TypeWriter.drawString("attack", 32, 448, g);
			TypeWriter.drawString("technique", 32, 496, g);
			TypeWriter.drawString("item", 32, 542, g);
			TypeWriter.drawString("run", 176, 542, g);
		}
	}
	private static boolean atMenu1(){
		return (UI.attackBtn.enabled&&UI.techniqueBtn.enabled&&UI.itemBtn.enabled&&UI.runBtn.enabled);
	}
}
