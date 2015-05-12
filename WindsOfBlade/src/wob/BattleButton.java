package wob;

public class BattleButton extends Button {
	boolean selected;
	public BattleButton(int x, int y, int width, int height) {
		super(x, y, width, height);
		selected=false;
	}
}
