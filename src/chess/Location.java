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

	private int row;
	private int col;

	public Location(int row, int col) {
		this.row = row;
		this.col = col;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	/**
	 * Returns the adjacent location towards {@code direction}. If an invalid direction was passed, null is returned.
	 *
	 * @param direction the direction to look towards
	 * @return the adjacent location towards {@code direction}
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

	public boolean equals(Object target) {
		boolean isEqual = false;
		if(target instanceof Location) {
			Location targetLoc = (Location) target;
			isEqual = (row == targetLoc.getRow()) && (col == targetLoc.getCol());
		}

		return isEqual;
	}
}
