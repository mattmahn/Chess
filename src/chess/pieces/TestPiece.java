package chess.pieces;

import chess.Location;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class TestPiece extends Piece {

	private ImageIcon pieceIcon = new ImageIcon("images/knight_black.png");

	public TestPiece(int row, int col) {
		super(row, col);
	}

	public ImageIcon getIcon() {
		return pieceIcon;
	}

	@Override
	public List<List<Location>> getMoveLocations() {
		// TODO Auto-generated method stub
		List<Location> b = new ArrayList<Location>();
		b.add(new Location(0, 0));
		List<List<Location>> a = new ArrayList<List<Location>>();
		a.add(b);
		return a;
	}

	@Override
	public String toString() {
		return "TestPiece [pieceIcon=" + pieceIcon + "]";
	}

}
