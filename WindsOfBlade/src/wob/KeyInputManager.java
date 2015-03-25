package wob;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInputManager implements KeyListener {
	/*
	 *  Deals with Keyboard input
	 *  i take the ascii table from 32 to 96 and subtract 32 from 
	 *  the entire interval so now its 0 to 64.
	 *  every item on the ascii table from 32 to 96 is now represented by a bit on my pressedKeys
	 */
	static long pressedKeys=0;
	public void keyPressed(KeyEvent e) {
		pressedKeys|=(1<<e.getKeyCode()-32);
	}

	public void keyReleased(KeyEvent e) {
		pressedKeys&=~(1<<e.getKeyCode()-32);
	}
	public static boolean isPressed(char key){
		return((pressedKeys&(1<<(int)key))!=0);
	}
	public void keyTyped(KeyEvent e) {
		//Not going to use this
	}

}
