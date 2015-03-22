package wob;

public class Cell<anyType> {
	int x;
	int y;
	anyType value;
	Cell next;
	public Cell(int myX, int myY, anyType data, Cell myNext){
		x=myX;
		y=myY;
		value=data;
		next=myNext;
	}
	public anyType getValue(){
		return value;
	}
	public Cell getNext(){
		return next;
	}
	public void setValue(anyType myValue){
		value=myValue;
	}
	public void setNext(Cell myNext){
		next=myNext;
	}
}
