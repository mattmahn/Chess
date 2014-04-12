package chess;

import java.util.ArrayList;

import chess.pieces.*;
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
		// Add pawns
		for (int i = 0; i < 8; i++){
			BlackPieces.add(new Pawn(1, i, 'b'));
			WhitePieces.add(new Pawn(6, i, 'w'));
		}
		//Add rooks
		BlackPieces.add(new Rook(0,0,'b'));
		BlackPieces.add(new Rook(0,7,'b'));
		WhitePieces.add(new Rook(7,0,'w'));
		WhitePieces.add(new Rook(7,7,'w'));
		//Add Knights
		BlackPieces.add(new Knight(0,1,'b'));
		BlackPieces.add(new Knight(0,6,'b'));
		WhitePieces.add(new Knight(7,1,'w'));
		WhitePieces.add(new Knight(7,6,'w'));
		//Add bishop
		BlackPieces.add(new Bishop(0,2,'b'));
		BlackPieces.add(new Bishop(0,5,'b'));
		WhitePieces.add(new Bishop(7,2,'w'));
		WhitePieces.add(new Bishop(7,5,'w'));
		//Add Queen
		BlackPieces.add(new Queen(0,3,'b'));
		WhitePieces.add(new Queen(7,3,'w'));
		//Add kings
		BlackPieces.add(new King(0, 4, 'b'));
		WhitePieces.add(new King(7, 4, 'w'));
		// Disable Blacks Pieces because of new game
		enablePieces(BlackPieces, false);
		//Enable White Pieces because of new game
		enablePieces(WhitePieces,true);
	}

	public void updateLogic() {
		// Check for win
		if (checkBlackWin()) {
			System.out.println("Black Has won!");
		}
		if (checkWhiteWin()) {
			System.out.println("White Has won!");
		}
		// Change turn to opposite color
		if (isWhitesTurn) {
			enablePieces(WhitePieces, false);
			enablePieces(BlackPieces, true);
			isWhitesTurn = false;

		} else {
			enablePieces(BlackPieces, false);
			enablePieces(WhitePieces, true);
			isWhitesTurn = true;
		}

	}

	private boolean checkWhiteWin() {
		for (Piece piece : BlackPieces) {
			if (piece instanceof King)
				return false;
		}
		return true;
	}

	private boolean checkBlackWin() {
		for (Piece piece : WhitePieces) {
			if (piece instanceof King)
				return false;
		}
		return true;
	}

	private void enablePieces(ArrayList<Piece> Pieces, boolean enable) {
		for (Piece piece : Pieces) {
			PieceButton a = ChessBoard.getButtonAtLocation(new Location(piece
					.getRow(), piece.getCol()));
			a.setActive(enable);
		}

	}

	public boolean potentialKill(Piece pieceMoving, Location currentLocation) {
		PieceButton btnKillLocation = ChessBoard
				.getButtonAtLocation(currentLocation);
		char pieceTeam = pieceMoving.getTeam();
		// Check what team the piece attempting to move is on
		if (pieceTeam == 'w') {
			// Check if location is okay to move to for a kill
			if (btnKillLocation.getPiece().getTeam() == 'b')
				return true;
			else
				return false;
		} else {
			if (btnKillLocation.getPiece().getTeam() == 'w')
				return true;
			else
				return false;
		}
	}

	public void removePiece(Piece pieceRemoved) {
		if (pieceRemoved != null) {
			for (int i = 0; i < BlackPieces.size(); i++) {
				Piece piece = BlackPieces.get(i);
				if (pieceRemoved.equals(piece))
					BlackPieces.remove(piece);
			}
			for (int i = 0; i < WhitePieces.size(); i++) {
				Piece piece = WhitePieces.get(i);
				if (pieceRemoved.equals(piece))
					WhitePieces.remove(piece);
			}

		}
	}

}
