package wob;

public class BattleButton extends Button {
	boolean selected;
	int index;
	public BattleButton(int x, int y, int width, int height, int index) {
		super(x, y, width, height);
		selected=false;
		this.index=index;
	}
}
