package wob;

public class SparseMatrix<anyType> implements Matrixable<anyType> {
	Cell<anyType> head;
	private int rowMax;
	private int colMax;
	public SparseMatrix(int row, int col) {
		rowMax=row;
		colMax=col;
		head=null;
	}
	//post - returns object at given coordinates
	//		 returns null when the matrix is empty
	//		 returns null when there is no item at the given coordinates
	//		 returns null when row or columns given are out of bounds
	public anyType get(int row, int col) {
		if(outOfBounds(row,col))
			return null;
		Cell<anyType> current = head;
		if(current==null)
			return null;
		while(current.getNext()!=null){
			if(getIndex(current)==row*rowMax+col)
				return current.getValue();
			else if(getIndex(current)>row*rowMax+col)
				return null;
			current=current.getNext();
		}
		if(getIndex(current)==row*rowMax+col)
			return current.getValue();
		else if(getIndex(current)>row*rowMax+col)
			return null;
		return null;
	}
	//post - sets the item at the given coordinates to the new item
	//		 returns the given item
	//		 returns null when the matrix is empty
	//		 returns null when there is no item at the given coordinates
	//	 	 returns null when row or columns given are out of bounds
	public anyType set(int row, int col, anyType x) {
		if(outOfBounds(row,col))
			return null;
		Cell<anyType> current = head;
		if(current==null)
			return null;
		while(current.getNext()!=null){
			if(getIndex(current)==row*rowMax+col){
				current.setValue(x);
				return x;
			}
			else if(getIndex(current)>row*rowMax+col)
				return null;
			current=current.getNext();
		}
		if(getIndex(current)==row*rowMax+col){
			current.setValue(x);
			return x;
		}
		else if(getIndex(current)>row*rowMax+col)
			return null;
		return null;
	}
	//post - adds the given cell in sorted order into the matrix
	//		 returns true if successful
	//		 returns false if there is an item already at the given coordinates
	//		 returns false when row or columns given are out of bounds
	public boolean add(int row, int col, anyType x) {
		if(outOfBounds(row,col))
			return false;
		Cell<anyType> current = head;
		if(current==null){
			head=new Cell<anyType>(col,row,x,null);
			return true;
		}
		if(getIndex(row,col)<getIndex(head)){
			head=new Cell<anyType>(col,row,x,current);
			return true;
		}
		while(current.getNext()!=null&&getIndex(current.getNext())<getIndex(row,col)){
			 current = current.getNext();
		}
		if(current.getNext()==null){
			if(getIndex(current)==getIndex(row,col))
				return false;
			if(getIndex(current)<getIndex(row,col)){
				current.setNext(new Cell<anyType>(col,row,x,current.getNext()));
				return true;
			}
			if(getIndex(current)>getIndex(row,col)){
			//	head=new Cell(col,row,x,current);
			}
		}
		if(getIndex(current.getNext())==getIndex(row,col))
			return false;
		
		current.setNext(new Cell<anyType>(col,row,x,current.getNext()));
		return true;
	}
	//post - removes item at given coordinates
	//		 returns null if there is not a item at the given coordinates
	//		 returns null if row or columns given are out of bounds
	//		 returns null if matrix is empty
	public anyType remove(int row, int col) {
		if(outOfBounds(row,col))
			return null;
		Cell<anyType> current = head;
		if(current==null)
			return null;
		if(head.getNext()!=null&&getIndex(head)==getIndex(row,col)){
			head=head.getNext();
			return current.getValue();
		}
		while(current.getNext()!=null&&getIndex(current.getNext())<getIndex(row,col)){
			 current = current.getNext();
		} 
		if(current.getNext()==null&&(getIndex(current)==getIndex(row,col))){
			head=null;
			return(current.getValue());
		}
		if(getIndex(current.getNext())==getIndex(row,col)){
			Cell<anyType> temp = current;
			current.setNext(current.getNext().getNext());
			return temp.getValue();
		}
		return null;
	}
	//post - returns the current size of matrix (not max possible size);
	public int size() {
		int count = 0;
		Cell<anyType> current = head;
		while(current!=null){
			count++;
			current = current.getNext();
		}
		return count;
	}
	//post - returns max number of row
	public int numRows() {
		return rowMax;
	}
	//post - returns max numbers of column
	public int numColumns() {
		return colMax;
	}
	//post - returns true if matrix contains given item
	//		 returns false if matrix does not contain given item
	public boolean contains(anyType x) {
		Cell<anyType> current = head;
		if(current==null)
			return false;
		if(current.getValue().equals(x))
			return true;
		while(current.getNext()!=null){
			if(current.getValue().equals(x))
				return true;
			current = current.getNext();
		}
		
		return false;
	}
	//post - returns the location of the first appearance of the given item
	//		 returns null if matrix is empty
	//		 returns null if matrix does not contain given item
	public int[] getLocation(anyType x) {
		Cell<anyType> current = head;
		int[] location = new int[2];
		if(current==null)
			return null;
		if(current.getValue().equals(x)){
			location[0]=current.y;
			location[1]=current.x;
			return location;
		}
		while(current.getNext()!=null){
			if(current.getValue().equals(x)){
				location[0]=current.y;
				location[1]=current.x;
				return location;
			}
			current = current.getNext();
		}
		
		return null;
	}
	//post - returns the 2D array equivalent of this matrix
	public Object[][] toArray() {
		Object[][] array = new Object[rowMax][colMax];
		for(int i=0;i<rowMax;i++){
			for(int j=0;j<colMax;j++){
				array[i][j]=get(i,j);
			}
		}
		return array;
	}
	//post - returns true if matrix is empty
	//		 returns false if matrix is not empty
	public boolean isEmpty() {
		if(head==null)
			return true;
		return false;
	}
	//post - removes all items from the matrix
	public void clear() {
		head=null;
	}
	
	private int getIndex(Cell<anyType> c){
		return (c.y*rowMax + c.x);
	}
	private int getIndex(int row, int col){
		return (row*rowMax + col);
	}
	private boolean outOfBounds(int row, int col){
		if(row>rowMax||col>colMax)
			return true;
		return false;
	}

}
