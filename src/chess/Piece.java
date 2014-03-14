package chess;

import javax.swing.*;

public abstract class Piece {

	private Location location;

	public Piece(int row, int col) {
		this.location = new Location(row, col);
	}

	public Location getLocation() {
		return location;
	}

	public int getRow() {
		return location.getRow();
	}

	public int getCol() {
		return location.getCol();
	}

	public abstract ImageIcon getIcon();
}
