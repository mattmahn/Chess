package chess;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {

	private ImageIcon icon;
	private char team;

	/**
	 * Creates a rook piece with the location of ({@code row},{@code col}) on {@code team} team.
	 *
	 * @param row
	 * @param col
	 * @param team
	 */
	public Rook(int row, int col, char team) {
		super(row, col);
		this.team = team;
		switch(team) {
			case 'b':
				icon = new ImageIcon("rook_black.png");
				break;
			case 'w':
				icon = new ImageIcon("rook_white.png");
				break;
			default:
				throw new IllegalArgumentException("Team must be 'b' for black team or 'w' for white team.");
		}
	}

	/**
	 * Returns the team of the piece
	 *
	 * @return the team of the piece
	 */
	public char getTeam() {
		return team;
	}


	@Override
	public ImageIcon getIcon() {
		return icon;
	}

	/**
	 * Returns a list of locations where a rook can move (assuming an empty board).
	 *
	 * @return a list of move locations
	 */
	@Override
	public List<Location> getMoveLocations() {
		int row = location.getRow();
		int col = location.getCol();
		List<Location> moveLocs = new ArrayList<Location>();

		// get locations North
		for(int j = row - 1; j >= 0; j--) {
			moveLocs.add(new Location(j, col));
		}
		// get locations East
		for(int i = col + 1; i < 8; i++) {
			moveLocs.add(new Location(row, i));
		}
		// get locations South
		for(int j = row + 1; j < 8; j++) {
			moveLocs.add(new Location(j, col));
		}
		//get locations West
		for(int i = col - 1; i >= 0; i++) {
			moveLocs.add(new Location(row, i));
		}

		return moveLocs;
	}

}
