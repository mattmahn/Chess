package chess.pieces;

import chess.Location;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {

	private ImageIcon icon;
	private char team;

	/**
	 * Creates a rook piece with the location of ({@code row},{@code col}) on {@code team} team.
	 *
	 * @param row  the row of this piece on the board
	 * @param col  the column of this piece on the board
	 * @param team the team this piece belongs to
	 */
	public Rook(int row, int col, char team) {
		super(row, col);
		this.team = team;
		switch(team) {
			case 'b':
				icon = new ImageIcon("images/rook_black.png");
				break;
			case 'w':
				icon = new ImageIcon("images/rook_white.png");
				break;
			default:
				throw new IllegalArgumentException("Team must be 'b' for black team or 'w' for white team.");
		}
	}

	/**
	 * Returns the team of the piece.
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
	public List<List<Location>> getMoveLocations() {
		int row = getRow();
		int col = getCol();
		List<List<Location>> moveLocs = new ArrayList<>();

		// get locations North
		List<Location> north = new ArrayList<Location>();
		for(int j = row - 1; j >= 0; j--) {
			north.add(new Location(j, col));
		}
		// get locations East
		List<Location> east = new ArrayList<Location>();
		for(int i = col + 1; i < 8; i++) {
			east.add(new Location(row, i));
		}
		// get locations South
		List<Location> south = new ArrayList<Location>();
		for(int j = row + 1; j < 8; j++) {
			south.add(new Location(j, col));
		}
		//get locations West
		List<Location> west = new ArrayList<Location>();
		for(int i = col - 1; i >= 0; i--) {
			west.add(new Location(row, i));
		}

		moveLocs.add(north);
		moveLocs.add(east);
		moveLocs.add(south);
		moveLocs.add(west);

		return moveLocs;
	}

}
