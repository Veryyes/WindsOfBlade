package wob;

public class Cell<anyType> {
	int x;
	int y;
	anyType value;
	Cell<anyType> next;
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
}
