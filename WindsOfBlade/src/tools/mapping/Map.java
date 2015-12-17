package tools.mapping;

public class Map {
	private int[][] data;
	public int rowSize;
	public int colSize;
	String name;
	public Map(int row, int col) {
		data= new int[row][col];
		rowSize=row;
		colSize=col;
	}
	public int get(int r, int c){
		return data[r][c];
	}
	public void set(int r, int c, int v){
		data[r][c]=v;
	}
	public void clear(){
		data=new int[rowSize][colSize];
	}
}
