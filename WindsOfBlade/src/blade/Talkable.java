package blade;
/**
 * Talkable things talk() and show an appropriate face to
 * @author Brandon
 * 
 */
public interface Talkable {
	public Animation getNeutralFace();
	public Animation getHappyFace();
	public Animation getSadFace();
	public Animation getAngryFace();
	/**
	 * Proceeds to the next piece of dialogue when called
	 */
	public void talk();
}
