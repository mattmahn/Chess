package chess;

import javax.swing.*;

public class PieceButton extends JButton {

	private Piece housedPiece;
	private boolean focused;

	public PieceButton() {
		super();
	}

	public void setPiece(Piece p) {
		housedPiece = p;
		this.setIcon(p.getIcon());
		this.setDisabledIcon(p.getIcon());
	}

	public Piece getPiece() {
		return housedPiece;
	}

	public void setFocused(boolean b) {
		this.focused = b;
	}

	public boolean isFocused() {
		return focused;
	}
}
