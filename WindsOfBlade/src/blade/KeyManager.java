package blade;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {
	/*
	 *  Deals with Keyboard input
	 *  i take the ascii table from 32 to 96 and subtract 32 from 
	 *  the entire interval so now its 0 to 64.
	 *  every item on the ascii table from 32 to 96 is now represented by a bit on my pressedKeys
	 */
	/**
	 * Deals with keyboard input
	 * <p>
	 * Stores all the keyboard button states on a long datatype
	 */
	public static final char ESC = (char)27;
	static long pressedKeys=0L;
	public void keyPressed(KeyEvent e) {
		pressedKeys|=(1L<<(e.getKeyCode()-32));
	}
	public void keyReleased(KeyEvent e) {
		pressedKeys&=~(1L<<(e.getKeyCode()-32));
	}
	public static boolean isPressed(char key){
		return((pressedKeys&(1L<<(key-32)))!=0);
	}
	public void keyTyped(KeyEvent e) {}
}
