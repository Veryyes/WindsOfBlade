package wob;

import java.awt.Graphics;

public interface BattleObject {
	public void battleRender(Graphics g);
	public void takeDamage(int value); //Negatives will heal
	public void useMove(Move attack, int index); //use a move, and on which enemy?
	
}
