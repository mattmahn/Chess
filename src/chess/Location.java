package chess;

public class Location {

   public static final int NORTH = 0;
   public static final int NORTH_EAST = 45;
   public static final int EAST = 90;
   public static final int SOUTH_EAST = 135;
   public static final int SOUTH = 180;
   public static final int SOUTH_WEST = 225;
   public static final int WEST = 270;
   public static final int NORTH_WEST = 315;
   public static final int AHEAD = 0;
   public static final int BEHIND = 180;

   private int row;
   private int col;

   /**
    * Constructs a Location object with the desired row and column.
    *
    * @param row desired row
    * @param col desired column
    */
   public Location(int row, int col) {
      this.row = row;
      this.col = col;
   }

   /**
    * Returns the row of this Location.
    *
    * @return the row of this location
    */
   public int getRow() {
      return row;
   }

   /**
    * Returns the column of this location.
    *
    * @return the column of this location
    */
   public int getCol() {
      return col;
   }

   /**
    * Returns the adjacent location towards {@code direction}. If an invalid direction was passed, null is returned.
    * This method does not guaranty the returned Location is bounded in a standard chess board.
    *
    * @param direction the direction to look towards
    * @return the adjacent location towards {@code direction}; null if invalid direction was passed
    */
   public Location getAdjacentLocation(int direction) {
      Location adjacentLoc = null;
      switch(direction) {
         case NORTH:
            adjacentLoc = new Location(row - 1, col);
            break;
         case NORTH_EAST:
            adjacentLoc = new Location(row - 1, col + 1);
            break;
         case EAST:
            adjacentLoc = new Location(row, col + 1);
            break;
         case SOUTH_EAST:
            adjacentLoc = new Location(row + 1, col + 1);
            break;
         case SOUTH:
            adjacentLoc = new Location(row + 1, col);
            break;
         case SOUTH_WEST:
            adjacentLoc = new Location(row + 1, col - 1);
            break;
         case WEST:
            adjacentLoc = new Location(row, col - 1);
            break;
         case NORTH_WEST:
            adjacentLoc = new Location(row - 1, col - 1);
            break;
      }

      return adjacentLoc;
   }

   /**
    * Returns whether or not the target represents the same row and column as this Location.
    *
    * @param target the target Location
    * @return whether or not the target represents the same row and column as this Location
    */
   public boolean equals(Object target) {
      boolean isEqual = false;
      if(target instanceof Location) {
         Location targetLoc = (Location) target;
         isEqual = (row == targetLoc.getRow()) && (col == targetLoc.getCol());
      }

      return isEqual;
   }

   /**
    * Returns a String representation of this location in the format "(row,col)".
    *
    * @return a String representation of this location in the format "(row,col)".
    */
   @Override
   public String toString() {
      return "(" + row + "," + col + ")";
   }
}
