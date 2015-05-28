package wob;

public abstract class Actor extends Entity {
	Animation animation;
	String name;
	public Actor(int x, int y) {
		super(x,y);
		name="NO NAME";
	}

}
