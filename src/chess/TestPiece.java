package chess;

import javax.swing.ImageIcon;

public class TestPiece extends Piece {

	private ImageIcon pieceIcon = new ImageIcon("knight.png");
	public TestPiece(int row, int col) {
		super(row, col);
	}
	public ImageIcon getIcon(){
		return pieceIcon;
	}

}
