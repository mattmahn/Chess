package chess;

import javax.swing.ImageIcon;

public abstract class Piece {

	private int row,col;
	public Piece(int row, int col){
		this.row = row;
		this.col=col;
	}
	public int getRow(){
		return row;
	}
	public int getCol(){
		return col;
	}
	public abstract ImageIcon getIcon();
}
