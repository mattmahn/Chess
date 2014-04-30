package chess;

import chess.pieces.Pawn;
import chess.pieces.Piece;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Creates a board that allows the user to play a game of chess
 *
 * @author Jordan Longato,Matthew Mahnke
 */
public class Board extends JFrame {
	/**
	 * The PieceButtons that are placed onto the board.
	 */
	private ArrayList<ArrayList<PieceButton>> board = new ArrayList<ArrayList<PieceButton>>();
	private PieceButton btnToMovePieceFrom = null;
	private ChessLogic CL;

	/**
	 * Creates a 8x8 JFrame filled with empty PieceButtons.
	 */
	public Board() {
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		createBlankBoard();
		CL = new ChessLogic(this);
		this.setSize(700, 700);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	/**
	 * Creates a board that is ready for gameplay. <br>
	 * This board has all pieces set in their appropriate starting positions and
	 * awaits for the user to make the first move.
	 */
	private void createBlankBoard() {
		initBtns();
		addBtnsToBoard();
	}

	/**
	 * Creates a new board
	 */
	private void initBtns() {
		ActionListener testPieceListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				PieceButton btnClicked = findbtn(event);
				if (btnClicked.isActive()) {
					if (isOccupied(btnClicked)) {
						if (btnClicked.isFocused()) {
							btnClicked.setFocused(false);
							btnToMovePieceFrom = null;
							removeFocuses();
						} else {
							removeFocuses();
							btnToMovePieceFrom = btnClicked;
							btnClicked.setFocused(true);
							getValidLocations(btnClicked.getPiece());
						}
					}
				} else
					removeFocuses();
			}

		};
		ActionListener testMoveListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				PieceButton btnToMoveTo = findbtn(e);
				if (btnToMoveTo.isFocused() && !(btnToMoveTo.equals(btnToMovePieceFrom))) {
					movePieceToBtn(btnToMoveTo);
				}
			}

		};
		for (int row = 0; row < 8; row++) {
			board.add(new ArrayList<PieceButton>());
			for (int col = 0; col < 8; col++) {
				board.get(row).add(new PieceButton());
				board.get(row).get(col).addActionListener(testPieceListener);
				board.get(row).get(col).addActionListener(testMoveListener);
			}
		}
	}

	/**
	 * Adds empty PieceButtons to the board. <br>
	 */
	private void addBtnsToBoard() {
		JPanel mainContent = new JPanel();
		mainContent.setLayout(new GridLayout(8, 8));
		for (ArrayList<PieceButton> row : board) {
			for (int col = 0; col < row.size(); col++) {
				mainContent.add(row.get(col));
			}
		}
		this.add(mainContent, BorderLayout.CENTER);
	}

	/**
	 * Finds the button based on the event passed in
	 *
	 * @param event
	 *            ActionEvent that was triggered by a user clicking on a button
	 * @return PieceButton that caused the event.
	 */
	protected PieceButton findbtn(ActionEvent event) {
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				if (board.get(row).get(col).equals(event.getSource()))
					return board.get(row).get(col);
			}
		}
		return null;
	}

	/**
	 * Checks if the passed Button is occupied by a piece by checking if the
	 * button has an Icon.
	 *
	 * @param location
	 *            JButton being checked
	 * @return True if location has icon.
	 */
	public boolean isOccupied(PieceButton location) {
		return location.getIcon() != null;
	}

	/**
	 * Removes the highlighting and the focus attribute of all buttons are set
	 * to false
	 */
	private void removeFocuses() {
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				board.get(row).get(col).setFocused(false);
				board.get(row).get(col).setBackground(null);
			}
		}

	}

	/**
	 * Highlights the available locations for the piece to move to
	 *
	 * @param piece
	 *            the piece to get possible valid locations to move to
	 */
	public void getValidLocations(Piece piece) {
		// get move all possible move locations for this piece
		List<List<Location>> locs = piece.getMoveLocations();

		// go through all locations
		for (List<Location> loc : locs) {
			for (int i = 0; i < loc.size(); i++) {
				Location currentLocation = loc.get(i);
				//Check if the location is valid
				if (isValidLocationOnGird(currentLocation)) {
					//Check for a piece occupying the location
					if (!isOccupied(getButtonAtLocation(currentLocation))) {
						//Do special things if the piece is a pawn
						if (piece instanceof Pawn) {
							Pawn pawn = (Pawn) piece;
							checkPawnLocation(currentLocation, pawn);
						} else
							getButtonAtLocation(currentLocation).setFocused(true);
					} else {
						if (CL.potentialKill(piece, currentLocation))
							getButtonAtLocation(currentLocation).setFocused(true);
						break;
					}
				}
			}
		}

	}

	private void checkPawnLocation(Location currentLocation, Pawn pawn) {
		Location pawnLoc = pawn.getLocation();
		boolean isNorth = pawnLoc.getAdjacentLocation(Location.NORTH).equals(currentLocation);
		boolean isSouth = pawnLoc.getAdjacentLocation(Location.SOUTH).equals(currentLocation);
		if (isNorth || isSouth)
			getButtonAtLocation(currentLocation).setFocused(true);
		else if(pawn.getIsFirstMove()){
			Location north = pawnLoc.getAdjacentLocation(Location.NORTH).getAdjacentLocation(Location.NORTH);
			boolean isNorthTwice = north.equals(currentLocation);
			Location south = pawnLoc.getAdjacentLocation(Location.SOUTH).getAdjacentLocation(Location.SOUTH);
			boolean isSouthTwice = south.equals(currentLocation);
			if(isNorthTwice || isSouthTwice)
				getButtonAtLocation(currentLocation).setFocused(true);
		}
	}

	protected void movePieceToBtn(PieceButton btnToMoveTo) {
		if (btnToMovePieceFrom != null) {
			if(btnToMovePieceFrom.getPiece() instanceof Pawn){
				Pawn pawn = (Pawn) btnToMovePieceFrom.getPiece();
				pawn.hasMoved();
			}
			Piece pieceToMove = btnToMovePieceFrom.getPiece();
			btnToMovePieceFrom.setPiece(null);
			Piece pieceRemoved = btnToMoveTo.setPiece(pieceToMove);
			CL.removePiece(pieceRemoved);
			Location locOfBtnToMoveTo = findLocationOfButton(btnToMoveTo);
			pieceToMove.setLocation(new Location(locOfBtnToMoveTo.getRow(), locOfBtnToMoveTo
					.getCol()));
			CL.updateLogic();
		}
	}

	public static boolean isValidLocationOnGird(Location currentLocation) {
		int col = currentLocation.getCol();
		int row = currentLocation.getRow();
		if (col > 7 || row > 7 || col < 0 || row < 0)
			return false;
		return true;
	}

	/**
	 * Gets the button a the given location
	 *
	 * @param loc
	 *            The location to pull the button from
	 * @return PieceButton in the location requested
	 */
	public PieceButton getButtonAtLocation(Location loc) {
		PieceButton btn = null;
		try {
			btn = board.get(loc.getRow()).get(loc.getCol());
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Requested location inside of getButtonAtLocation() was null");
		}
		return btn;
	}

	private Location findLocationOfButton(PieceButton btn) {
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				PieceButton current = board.get(row).get(col);
				if (current.equals(btn))
					return new Location(row, col);
			}
		}
		return null;
	}

	/**
	 * Returns the current state of the Board
	 *
	 * @return The list of all PieceButtons within the board
	 */
	public ArrayList<ArrayList<PieceButton>> getBoard() {
		return board;
	}

	/**
	 * Places the piece into a button that corresponds with the pieces location.
	 *
	 * @param piece
	 *            Piece that will be placed on the board.
	 */
	public void placePiece(Piece piece) {
		board.get(piece.getRow()).get(piece.getCol()).setPiece(piece);

	}


}
