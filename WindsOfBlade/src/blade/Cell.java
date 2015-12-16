package blade;

public class Cell<anyType> {
	private int x;
	private int y;
	public anyType value;
	private Cell<anyType> next;
	public Cell(int myX, int myY, anyType data, Cell<anyType> myNext){
		x=myX;
		y=myY;
		value=data;
		next=myNext;
	}
	public anyType getValue(){
		return value;
	}
	public Cell<anyType> getNext(){
		return next;
	}
	public void setValue(anyType myValue){
		value=myValue;
	}
	public void setNext(Cell<anyType> myNext){
		next=myNext;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
}
