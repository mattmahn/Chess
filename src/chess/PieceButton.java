package chess;

import javax.swing.JButton;

public class PieceButton extends JButton {
	Piece housedPiece;
	public PieceButton(){
		super();
	}
	public void setPiece(Piece p){
		housedPiece = p;
		this.setIcon(p.getIcon());
		this.setDisabledIcon(p.getIcon());
	}
	public Piece getPiece(){
		return housedPiece;
	}
}
