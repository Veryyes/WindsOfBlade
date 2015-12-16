package blade;

public class Animation{
	/**
	 * A array of Images to iterated over;
	 * @author Brandon Wong
	 */
	private Image[] images;
	private float timer=0;
	private float speed=.15f;
	private boolean repeat=true;
	public Animation(Image[] imageSet){
		images = imageSet;
	}
	public Animation(Image[] imageSet, boolean repeat){
		this(imageSet);
		this.repeat=repeat;
	}
	public Animation(Image singleImage){
		images = new Image[1];
		images[0] = singleImage;
		repeat=false;
	}
	/**
	 * Returns a image at the given index
	 * @param i index
	 * @return image at the index
	 */
	public Image getFrame(int i){
		return images[i];
	}
	/**
	 * Used to get the current image this animation on
	 * @return the current image this animation should be on
	 */
	public Image getCurrentFrame(){
		return images[(int)(timer)];
	}
	/**
	 * Updates the animation to iterate through at it's current speed
	 */
	public void update(){
		if(repeat&&timer%images.length==images.length-1)
			timer-=speed;
		timer+=speed;
		timer%=images.length;
	}
	public void setSpeed(float s){
		speed=s;
	}
	public float getSpeed(){
		return speed;
	}
	public void setRepeat(boolean b){
		repeat=b;
	}
	public boolean isRepeat(){
		return repeat;
	}
	public boolean onLastFrame(){
		return (int)timer==images.length-1;
	}
	public int getWidth(){
		return getCurrentFrame().getWidth();
	}
	public int getHeight(){
		return getCurrentFrame().getHeight();
	}
}
