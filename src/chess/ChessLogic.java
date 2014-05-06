package chess;

import java.util.ArrayList;

import chess.pieces.*;
import chess.pieces.Rook;
/**
 * <p>This class monitors the state of the game by organizing al lthe pieces into
 * a list. It also dictates who's turn it is and when the player may interact with
 * pieces and what pieces they may interact with.
 * @author Jordan Longato,Matthew Mahnke
 *
 */
public class ChessLogic {
	/**
	 * Board used for game play.
	 */
	private Board ChessBoard;
	/**
	 * A list of the pieces that the Black player has left
	 */
	private ArrayList<Piece> BlackPieces;
	/**
	 * A list of the pieces that the White player has left
	 */
	private ArrayList<Piece> WhitePieces;
	/**
	 * Variable that determines whose turn it is.
	 */
	private boolean isWhitesTurn = true;
	/**
	 * <p>Produces a Chess Logic that will maintain the gameplay
	 * of Chess so long as after each action done to the board 
	 * is followed by a call to this class's appropriate method for
	 * what action happened on the board.
	 * <p>For example if a piece over takes a piece on the board then
	 * a call must be made to the removePiece method and then to the updateLogic
	 * method.
	 * @param board Board to maintain
	 */
	public ChessLogic(Board board) {
		ChessBoard = board;
		placeNewPeices();
	}
	/**
	 * Places a new set of white and black Chess pieces onto the board
	 * and adds them to the board.
	 */
	private void placeNewPeices() {
		// TODO Auto-generated method stub
		initPieces();
		for (Piece piece : BlackPieces)
			ChessBoard.placePiece(piece);
		for (Piece piece : WhitePieces)
			ChessBoard.placePiece(piece);
	}
	/**
	 * Initializes a new set of pieces for white and black
	 */
	private void initPieces() {
		BlackPieces = new ArrayList<Piece>();
		WhitePieces = new ArrayList<Piece>();
		// Add pawns
		for (int i = 0; i < 8; i++) {
			BlackPieces.add(new Pawn(1, i, 'b'));
			WhitePieces.add(new Pawn(6, i, 'w'));
		}
		// Add rooks
		BlackPieces.add(new Rook(0, 0, 'b'));
		BlackPieces.add(new Rook(0, 7, 'b'));
		WhitePieces.add(new Rook(7, 0, 'w'));
		WhitePieces.add(new Rook(7, 7, 'w'));
		// Add Knights
		BlackPieces.add(new Knight(0, 1, 'b'));
		BlackPieces.add(new Knight(0, 6, 'b'));
		WhitePieces.add(new Knight(7, 1, 'w'));
		WhitePieces.add(new Knight(7, 6, 'w'));
		// Add bishop
		BlackPieces.add(new Bishop(0, 2, 'b'));
		BlackPieces.add(new Bishop(0, 5, 'b'));
		WhitePieces.add(new Bishop(7, 2, 'w'));
		WhitePieces.add(new Bishop(7, 5, 'w'));
		// Add Queen
		BlackPieces.add(new Queen(0, 3, 'b'));
		WhitePieces.add(new Queen(7, 3, 'w'));
		// Add kings
		BlackPieces.add(new King(0, 4, 'b'));
		WhitePieces.add(new King(7, 4, 'w'));
		// Disable Blacks Pieces because of new game
		enablePieces(BlackPieces, false);
		// Enable White Pieces because of new game
		enablePieces(WhitePieces, true);
	}
	/**
	 * Updates the logic of this class by checking if there 
	 * a player has won the game. If a player has not won
	 * the game then this class will make the opposite players 
	 * pieces become available for their turn.
	 */
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
	/**
	 * Checks if white has won by checking to see if
	 * there is still a king in the list of pieces.
	 * @return True if no king was found in pieces false otherwise
	 */
	private boolean checkWhiteWin() {
		for (Piece piece : BlackPieces) {
			if (piece instanceof King)
				return false;
		}
		return true;
	}
	/**
	 * Checks if black has won by checking to see if 
	 * there is still a king in the list of pieces.
	 * @return True if no king was found in pieces false otherwise. 
	 */
	private boolean checkBlackWin() {
		for (Piece piece : WhitePieces) {
			if (piece instanceof King)
				return false;
		}
		return true;
	}
	/**
	 * Enables the list of pieces based on enable
	 * @param Pieces Pieces to enable
	 * @param enable 
	 */
	private void enablePieces(ArrayList<Piece> Pieces, boolean enable) {
		for (Piece piece : Pieces) {
			PieceButton a = ChessBoard.getButtonAtLocation(new Location(piece.getRow(), piece
					.getCol()));
			a.setActive(enable);
		}

	}
	/**
	 * Checks to see if there a chance that a piece will over take another piece
	 * by moving onto a spot. 
	 * @param pieceMoving Piece trying to move
	 * @param currentLocation Location that piece wants to move to but 
	 * there is a piece in the way
	 * @return true if pieceMoving can overtake the piece false otherwise
	 */
	public boolean potentialKill(Piece pieceMoving, Location currentLocation) {
		PieceButton btnKillLocation = ChessBoard.getButtonAtLocation(currentLocation);
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
	/**
	 * Removes a piece from the appropriate list of pieces
	 * @param pieceRemoved Piece to be removed
	 */
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
