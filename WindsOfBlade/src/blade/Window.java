package blade;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
/**
 * UI Windows
 * @author Brandon Wong
 * 
 */
public class Window {
	public boolean visible;
	protected Window parent;
	//TODO public String title="";
	public String text="";
	public Animation animation;
	public boolean imageVisible;
	public boolean drawFrame=true;
	public int x;
	public int y;
	public int width;
	public int height;
	public Color backColor = Color.black;
	public int hgap;	//horizontal gap between Children
	public int vgap; 	//Vertical gap between children
	protected SparseMatrix<Window> subWindows;	//contains this's children
	public static final byte BORDER_SIZE = 7;
	//Stand Alone Window with only text
	public Window(int x, int y, int width, int height, String text){
		this(null, text);
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
	}
	//Stand Alone Window With only image
	public Window(int x, int y, int width, int height, Animation animation){
		this(null, animation);
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
	}
	//Window that is a child of a parent with only text
	public Window(Window parent, String text){
		setParent(parent, true);
		this.text=text;
		visible=false;
		imageVisible=false;
	}
	//Window that is a child of a parent with only image
	public Window(Window parent, Animation animation){
		setParent(parent, true);
		visible=false;
		this.animation=animation;
		imageVisible=true;
	}
	public Window(int x, int y, int width, int height, String text, Animation animation){
		this(null, text, animation);
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
	}
	public Window(Window parent, String text, Animation animation){
		setParent(parent, true);
		this.text=text;
		visible=false;
		this.animation=animation;
		imageVisible=true;
	}
	/**
	 * Sets this Windows parent and can resize the window to fit in the parent window's grid
	 * @param parent The parent window
	 * @param resize Whether or not to resize the window
	 */
	public void setParent(Window parent, boolean resize){
		this.parent=parent;
		if(parent!=null&&resize){
			width = (parent.width - 2*BORDER_SIZE - (parent.subWindows.numColumns()+1)*parent.hgap)/parent.subWindows.numColumns();
			height = (parent.height - 2*BORDER_SIZE -(parent.subWindows.numRows()+1)*parent.vgap)/parent.subWindows.numRows();
		}
	}
	/**
	 * Scales this window to new dimensions and scales all its subWindows proportionally
	 * @param w the new Width
	 * @param h the new Height
	 */
	public void resize(int w, int h){
		if(subWindows!=null)
			for(Cell<Window> c = subWindows.getHead();c!=null;c=c.getNext())
				c.getValue().resize((int)(c.getValue().width*((float)w/width)),(int)(c.getValue().height+((float)h/height)));
		width=w;
		height=h;
	}
	/**
	 * Translates this window and all its subwindow to these coodinates
	 * @param x the new X-coordinate position
	 * @param y the new Y-coordinate position
	 */
	public void reposition(int x, int y){
		if(subWindows!=null)
			for(Cell<Window> c = subWindows.getHead();c!=null;c=c.getNext())
				c.getValue().reposition(c.getValue().x+(x-this.x), c.getValue().y+(y-this.y));
		this.x=x;
		this.y=y;
	}
	public void update(Graphics g) {
		if(visible){
			drawBackground(g, backColor);
			//If only img
			if(animation!=null&&imageVisible&&text.length()==0)
				animation.getCurrentFrame().draw(x+BORDER_SIZE, y+BORDER_SIZE, width-2*BORDER_SIZE, height-2*BORDER_SIZE, g);
			//If only string
			else if(animation==null&&text.length()>0)
				drawText(g);
			//Both
			else if(animation!=null&&imageVisible&&text.length()>0){
				//animation.getCurrentFrame().draw(x+BORDER_SIZE, y+BORDER_SIZE, width-2*BORDER_SIZE, height-2*BORDER_SIZE, g);
				animation.getCurrentFrame().draw(x+BORDER_SIZE+8, y+BORDER_SIZE+8, g);
				TypeWriter.drawString(text, x+BORDER_SIZE+8, y+BORDER_SIZE+animation.getCurrentFrame().getHeight(), width-2*BORDER_SIZE-32, height-2*BORDER_SIZE-animation.getCurrentFrame().getHeight(), g);
			}
			if(drawFrame)
				drawFrame(g);
		}
		updateSubWindows(g);
	}
	protected void drawFrame(Graphics g){
		Image t=ImageManager.get("res/ui/topBorder.png");
		Image b=ImageManager.get("res/ui/botBorder.png");
		Image l=ImageManager.get("res/ui/leftBorder.png");
		Image r=ImageManager.get("res/ui/rightBorder.png");
		ImageManager.get("res/ui/topLeftBorder.png").draw(x, y, g);
		ImageManager.get("res/ui/botLeftBorder.png").draw(x,y+height-15,g);
		ImageManager.get("res/ui/topRightBorder.png").draw(x+width-15, y, g);	
		ImageManager.get("res/ui/botRightBorder.png").draw(x+width-15, y+height-15,g);
		for(int j = x+15;j<x+width-15;j++){
			t.draw(j, y, g);
			b.draw(j, y+height-BORDER_SIZE, g);
		}
		for(int i = y+15; i<y+height-15;i++){
			l.draw(x, i, g);
			r.draw(x+width-BORDER_SIZE,i,g);
		}
	}
	/**
	 * Draws a string starting at the top left corner of the Window
	 * @param g The Graphics context that is being drawn on
	 */
	protected void drawText(Graphics g){
		TypeWriter.drawString(text, x+BORDER_SIZE+8, y+BORDER_SIZE, width-2*BORDER_SIZE-32, height-2*BORDER_SIZE, g);
	}
	/**
	 * Draws a solid color background to fit correctly with the default boarders
	 * @param g
	 * @param c
	 */
	protected void drawBackground(Graphics g, Color c){
		g.setColor(c);
		g.fillRect(x+BORDER_SIZE,y+BORDER_SIZE,width-2*BORDER_SIZE,height-2*BORDER_SIZE);
	}
	public boolean contains(Point point){
		return (point!=null&&point.x-2>x&&point.x-2<x+width&&point.y-24>y&&point.y-24<y+height);
	}
	public void setLayout(int numRows, int numCols){
		subWindows = new SparseMatrix<Window>(numRows, numCols);
	}
	/**
	 * Mutator for all the subWindow's visibility variables
	 * @param visiblilty
	 */
	public void setVisibleAllSubWindows(boolean visiblilty){
		if(subWindows!=null){
			for(Cell<Window> c = subWindows.getHead();c!=null;c=c.getNext()){
				c.getValue().visible=visiblilty;
			}
		}
	}
	/**
	 * Updates all subWindows
	 * @param g The graphics object that everything is drawn on to
	 */
	public void updateSubWindows(Graphics g) {
		if(subWindows!=null){
			for(Cell<Window> c = subWindows.getHead();c!=null;c=c.getNext()){
				c.getValue().update(g);
			}
		}
	}
	public Window get(int row, int col){
		return subWindows.get(row, col);
	}
	public Window set(int row, int col, Window c){ 
		return subWindows.set(row, col, c);
	}
	/**
	 * Adds a component into this component's interior and sets its x & y coordinates according to its position inside this
	 * @param row The row to add c in
	 * @param col The column to add c in
	 * @param c The component to be added
	 * @return true if successful
	 * @
	 */
	public boolean add(int row, int col, Window c) {
		c.x=x+BORDER_SIZE+(col+1)*hgap+col*c.width;
		c.y=y+BORDER_SIZE+(row+1)*vgap+row*c.height;
		return subWindows.add(row, col, c);
	}
	public boolean add(Window ...c){
		boolean success=true;
		for(int i=0;i<c.length;i++)
			success &= add(i/c.length,i%c.length,c[i]);
		return success;
	}
	/**
	 * removes the component at the row X column location specified
	 * @param row
	 * @param col
	 * @return the Window removed; null if no component is at the location given
	 * @
	 */
	public Window remove(int row, int col) {
		return subWindows.remove(row, col);
	}
	/**
	 * @return the number of subWindows in this window
	 */
	public int size() {
		return subWindows.size();
	}
	/**
	 * @return the max number of rows in this window
	 */
	public int numRows() {
		return subWindows.numRows();
	}
	/**
	 * @return the max number of columns in this window
	 */
	public int numColumns() {
		return subWindows.numColumns();
	}
	/**
	 * @param w reference to a possible subWindow
	 * @return true if w is a subWindow of this
	 */
	public boolean contains(Window w) {
		return subWindows.contains(w);
	}
	/**
	 * @return true if this Window does not have any subWindows
	 */
	public boolean isEmpty() {
		return subWindows.isEmpty();
	}
	/**
	 * remove all subWindows from this window
	 */
	public void clear() {
		subWindows.clear();
	}
}
