package blade;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Image implements Comparable<Image>{
	private BufferedImage img;
	private File file;
	public Image(File file) throws IOException{
		this.file = file;
		img = ImageIO.read(file);
	}
	Image(BufferedImage img, File file){
		this.file=file;
		this.img=img;
	}
	public void draw(int x, int y, Graphics g){
		g.drawImage(img,x,y,null);
	}
	public void draw(int x, int y, int w, int h, Graphics g){
		g.drawImage(img,x,y,w,h,null);
	}
	@Override
	public int compareTo(Image other) {
		return file.compareTo(other.file);
	}
	/**
	 * Same as BufferedImage.getSubimage()
	 * @see java#awt#image#BufferedImage#getSubimage();
	 */
	public Image getSubimage(int x, int y, int w, int h){
		return new Image(img.getSubimage(x, y, w, h),file);
	}
	public void setImg(BufferedImage img){
		this.img=img;
	}
	public void setFile(File file){
		this.file=file;
	}
	public BufferedImage getImg(){
		return img;
	}
	public File getFile(){
		return file;
	}
	public int getWidth(){
		return img.getWidth();
	}
	public int getHeight(){
		return img.getHeight();
	}
//	@Override
	//public String toString(){
	///	return file.getName();
	//}
	
	public Image rotate(float radians){
		AffineTransform af = AffineTransform.getRotateInstance(radians, img.getWidth()/2f, img.getHeight()/2f);
		AffineTransformOp afop = new AffineTransformOp(af, AffineTransformOp.TYPE_BILINEAR);
		return new Image(afop.filter(img, null),null);
	}
	//TODO Image Effects, Rotations, etc
	//http://stackoverflow.com/questions/4248104/applying-a-tint-to-an-image-in-java
			/*public BufferedImage colorImage(BufferedImage loadImg, int red, int green, int blue) {
			    BufferedImage img = new BufferedImage(loadImg.getWidth(), loadImg.getHeight(),
			        BufferedImage.TRANSLUCENT);
			    Graphics2D graphics = img.createGraphics(); 
			    Color newColor = new Color(red, green, blue, 0 );
			    graphics.setXORMode(newColor);
			    graphics.drawImage(loadImg, null, 0, 0);
			    graphics.dispose();
			    return img;
			}
			public static BufferedImage colorImage(BufferedImage loadImg, Color c) {
			    BufferedImage img = new BufferedImage(loadImg.getWidth(), loadImg.getHeight(),
			        BufferedImage.TRANSLUCENT);
			    Graphics2D graphics = img.createGraphics(); 
			    Color newColor = new Color(c.getRed(), c.getGreen(), c.getBlue(), 0 );
			    graphics.setXORMode(newColor);
			    graphics.drawImage(loadImg, null, 0, 0);
			    graphics.dispose();
			    return img;
			}*/

}
