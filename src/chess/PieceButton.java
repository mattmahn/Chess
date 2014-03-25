package chess;

import java.awt.Color;

import javax.swing.*;

public class PieceButton extends JButton {

	private Piece housedPiece;
	private boolean focused;

	public PieceButton() {
		super();
	}

	public void setPiece(Piece p) {
		housedPiece = p;
		if (p == null) {
			this.setIcon(null);
			this.setDisabledIcon(null);
		} else {
			this.setIcon(p.getIcon());
			this.setDisabledIcon(p.getIcon());
		}
	}

	public Piece getPiece() {
		return housedPiece;
	}

	public void setFocused(boolean b) {
		this.focused = b;
		if (b)
			this.setBackground(Color.yellow);
		else
			this.setBackground(null);

	}

	public boolean isFocused() {
		return focused;
	}
}
