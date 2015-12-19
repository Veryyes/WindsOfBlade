package tools.mapping;

public class Map {
	private int[][] data;
	public int rowSize;
	public int colSize;
	private int layer;
	public Map(int row, int col, int layer) {
		data= new int[row][col];
		rowSize=row;
		colSize=col;
                this.layer=layer;
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
        public String toString(){
            return "Layer "+layer;
        }
}
