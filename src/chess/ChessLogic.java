package chess;

import java.util.ArrayList;

import chess.pieces.Piece;
import chess.pieces.Rook;

public class ChessLogic {

	private Board ChessBoard;
	private ArrayList<Piece> BlackPieces;
	private ArrayList<Piece> WhitePieces;
	private boolean isWhitesTurn = true;

	public ChessLogic(Board board) {
		ChessBoard = board;
		placeNewPeices();
	}

	private void placeNewPeices() {
		// TODO Auto-generated method stub
		initPieces();
		for (Piece piece : BlackPieces)
			ChessBoard.placePiece(piece);
		for (Piece piece : WhitePieces)
			ChessBoard.placePiece(piece);
	}

	private void initPieces() {
		BlackPieces = new ArrayList<Piece>();
		WhitePieces = new ArrayList<Piece>();
		// For now only adding rooks
		for (int i = 0; i < 8; i++)
			BlackPieces.add(new Rook(0, i, 'b'));
		for (int i = 0; i < 8; i++)
			WhitePieces.add(new Rook(7, i, 'w'));
		//Disable Blacks Peices because of new game
		enablePeices(BlackPieces,false);
	}

	public void updateLogic() {
		// First change turn to opposite color
		if (isWhitesTurn) {
			enablePeices(WhitePieces,false);
			enablePeices(BlackPieces,true);
			isWhitesTurn = false;
			
		} 
		else {
			enablePeices(BlackPieces,false);
			enablePeices(WhitePieces,true);
			isWhitesTurn = true;
		}
	}

	private void enablePeices(ArrayList<Piece> Pieces,boolean enable) {
		for (Piece piece : Pieces) {
			PieceButton a = ChessBoard.getButtonAtLocation(new Location(piece
					.getRow(), piece.getCol()));
			a.setEnabled(enable);
		}

	}

}
