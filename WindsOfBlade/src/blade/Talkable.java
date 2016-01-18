package blade;

public interface Talkable {
	public Animation getNeutralFace();
	public Animation getHappyFace();
	public Animation getSadFace();
	public Animation getAngryFace();
	
	public void talk();
}
