package chess;

import javax.swing.*;
import java.util.List;

public abstract class Piece {

	protected Location location;

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
	public abstract List<Location> getMoveLocations();
}
