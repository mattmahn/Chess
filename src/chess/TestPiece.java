package chess;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class TestPiece extends Piece {

	private ImageIcon pieceIcon = new ImageIcon("knight.png");
	
	public TestPiece(int row, int col) {
		super(row, col);
	}
	public ImageIcon getIcon(){
		return pieceIcon;
	}
	@Override
	public List<Location> getMoveLocations() {
		// TODO Auto-generated method stub
		List<Location> b = new ArrayList<Location>();
		b.add(new Location(0,0));
		return b;
	}

}
