package wob;

   public interface Matrixable<anyType>
   {
      public anyType get(int row, int col);
      public anyType set(int row, int col, anyType x);
      public boolean add(int row, int col, anyType x);
      public anyType remove(int row, int col);
      public int size();
      public int numRows();
      public int numColumns();
      public boolean contains(anyType x);
      public int[] getLocation(anyType x);
      public Object[][] toArray();
      public boolean isEmpty();
      public void clear();
   }
  