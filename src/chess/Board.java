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
	private ArrayList<ArrayList<PieceButton>> buttons = new ArrayList<ArrayList<PieceButton>>();
	/**
	 * The button that was last clicked by the user
	 */
	private PieceButton btnLastClicked = null;
	/**
	 * The chess logic that dictates who's move it is and when the game is over
	 */
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
	 * <p>Creates a board that is ready for game play. 
	 * This board has all pieces set in their appropriate starting positions and
	 * awaits for the user to make the first move.
	 */
	private void createBlankBoard() {
		initBtns();
		addBtnsToBoard();
	}

	/**
	 * Places buttons inside of the board and gives all buttons listeners
	 */
	private void initBtns() {
		ActionListener focusListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				//Determine button clicked
				PieceButton btnClicked = findbtn(event);
				//Check if button was active prior to click
				if (btnClicked.isActive()) {
					if (isOccupied(btnClicked)) {
						//If piece is focused remove focus 
						if (btnClicked.isFocused()) {
							btnClicked.setFocused(false);
							btnLastClicked = null;
							removeFocuses();
						}
						//Button clicked is now in focus for player
						else {
							removeFocuses();
							btnLastClicked = btnClicked;
							btnClicked.setFocused(true);
							getValidLocations(btnClicked.getPiece());
						}
					}
				} else
					removeFocuses();
			}

		};
		ActionListener moveListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//Determine button clicked
				PieceButton btnToMoveTo = findbtn(e);
				//If the button is one of the focused ones and is not the previously clicked button
				if (btnToMoveTo.isFocused() && !(btnToMoveTo.equals(btnLastClicked))) {
					movePieceToBtn(btnToMoveTo);
				}
			}

		};
		//Add new Piece buttons to each position in the board and add listeners
		for (int row = 0; row < 8; row++) {
			buttons.add(new ArrayList<PieceButton>());
			for (int col = 0; col < 8; col++) {
				buttons.get(row).add(new PieceButton());
				buttons.get(row).get(col).addActionListener(focusListener);
				buttons.get(row).get(col).addActionListener(moveListener);
			}
		}
	}

	/**
	 * Adds buttons to the GUI.
	 */
	private void addBtnsToBoard() {
		JPanel mainContent = new JPanel();
		mainContent.setLayout(new GridLayout(8, 8));
		for (ArrayList<PieceButton> row : buttons) {
			for(PieceButton pieceButton : row) {
				mainContent.add(pieceButton);
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
				if (buttons.get(row).get(col).equals(event.getSource()))
					return buttons.get(row).get(col);
			}
		}
		return null;
	}

	/**
	 * Checks if the passed Button is occupied by a piece.
	 *
	 * @param location
	 *            PieceButton being checked
	 * @return True if location has icon.
	 */
	public boolean isOccupied(PieceButton location) {
		return location.getIcon() != null;
	}

	/**
	 * Removes the focuses from all buttons on the board
	 */
	private void removeFocuses() {
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				buttons.get(row).get(col).setFocused(false);
				buttons.get(row).get(col).setBackground(null);
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
			for(Location currentLocation : loc) {
				//Check if the location is valid
				if(isValidLocationOnGird(currentLocation)) {
					//Check for a piece occupying the location
					if(!isOccupied(getButtonAtLocation(currentLocation))) {
						//Do special things if the piece is a pawn
						if(piece instanceof Pawn) {
							Pawn pawn = (Pawn) piece;
							checkPawnLocation(currentLocation, pawn);
						} else
							getButtonAtLocation(currentLocation).setFocused(true);
					} else {
						if(CL.potentialKill(piece, currentLocation))
							getButtonAtLocation(currentLocation).setFocused(true);
						break;
					}
				}
			}
		}

	}
	/**
	 * Helper method for getValidLocations that is called when a pawn is about to move
	 * @param currentLocation Location of the pawn
	 * @param pawn Pawn trying to move
	 */
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

	/**
	 * <p>Moves the piece that was on the last button clicked to the new PieceButton that is
	 * passed to this method. After moving the piece to the new button the button last clicked
	 * no longer contains the piece that has moved.
	 * @param btnToMoveTo PieceButton that will recieve the new piece
	 */
	protected void movePieceToBtn(PieceButton btnToMoveTo) {
		if (btnLastClicked != null) {
			if(btnLastClicked.getPiece() instanceof Pawn){
				Pawn pawn = (Pawn) btnLastClicked.getPiece();
				pawn.hasMoved();
			}
			Piece pieceToMove = btnLastClicked.getPiece();
			btnLastClicked.setPiece(null);
			Piece pieceRemoved = btnToMoveTo.setPiece(pieceToMove);
			CL.removePiece(pieceRemoved);
			Location locOfBtnToMoveTo = findLocationOfButton(btnToMoveTo);
			pieceToMove.setLocation(new Location(locOfBtnToMoveTo.getRow(), locOfBtnToMoveTo
					.getCol()));
			CL.updateLogic();
		}
	}

	/**
	 *<p>Determines if the location is valid based on the board.
	 * A location is valid as long as its Row and Column is within the bounds of
	 * the grid.
	 * @param currentLocation Location to test
	 * @return True if location is valid false otherwise
	 */
	public static boolean isValidLocationOnGird(Location currentLocation) {
		int col = currentLocation.getCol();
		int row = currentLocation.getRow();
		return !(col > 7 || row > 7 || col < 0 || row < 0);
	}

	/**
	 * Gets the button a the given location
	 *
	 * @param loc
	 *            The location to pull the button from
	 * @return PieceButton in the location requested, null if Loc is not valid
	 */
	public PieceButton getButtonAtLocation(Location loc) {
		PieceButton btn = null;
		try {
			btn = buttons.get(loc.getRow()).get(loc.getCol());
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Requested location inside of getButtonAtLocation() was null");
		}
		return btn;
	}

	/**
	 * Returns the location where the button is located at in the grid
	 * @param btn Button to find location of
	 * @return Location of the button
	 */
	private Location findLocationOfButton(PieceButton btn) {
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				PieceButton current = buttons.get(row).get(col);
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
		return buttons;
	}

	/**
	 * Places the piece into a button that corresponds with the pieces location.
	 *
	 * @param piece
	 *            Piece that will be placed on the board.
	 */
	public void placePiece(Piece piece) {
		buttons.get(piece.getRow()).get(piece.getCol()).setPiece(piece);

	}
}
