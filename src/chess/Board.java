package chess;

import chess.pieces.Piece;
import chess.pieces.Rook;

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
 * 
 */
public class Board extends JFrame {
	/**
	 * The PieceButtons that are placed onto the board.
	 */
	private ArrayList<ArrayList<PieceButton>> board = new ArrayList<ArrayList<PieceButton>>();
	private PieceButton btnToMovePieceFrom = null;

	/**
	 * Creates a 8x8 JFrame filled with empty PieceButtons.
	 */
	public Board() {
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		createBlankBoard();
		this.setSize(700, 700);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	/**
	 * Returns the board in as a ArrayList<ArrayList<PieceButton>>
	 * 
	 * @return The list of all PieceButtons within the board
	 */
	public ArrayList<ArrayList<PieceButton>> getBoard() {
		return board;
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
		try {
			if (location.getIcon() != null) {
				return true;
			} else
				return false;
		} catch (ArrayIndexOutOfBoundsException e) {
			return false;
		}
	}

	/**
	 * Highlights the avaliable locations for the peice to move to
	 * 
	 * @param piece
	 */
	public void getValidLocations(Piece piece) {
		List<List<Location>> locs = piece.getMoveLocations();
		Location pieceLocation = piece.getLocation();
		for (List<Location> loc : locs) {
			for(int i = 0; i <loc.size();i++){
				Location currentLocation = loc.get(i);
				if(!isOccupied(getButtonAtLocation(currentLocation)))
					getButtonAtLocation(currentLocation).setFocused(true);
				else
					break;
			}
		}
		

	}

	/**
	 * Places the piece into a button that corresponds with the pieces location.
	 * 
	 * @param piece
	 *            Piece that will be placed on the board.
	 */
	public void placePiece(Piece piece) {
		board.get(piece.getRow()).get(piece.getCol()).setIcon(piece.getIcon());
	}

	/**
	 * Creates a board that is ready for gameplay. <br>
	 * This board has all pieces set in their appropriate starting positions and
	 * awaits for the user to make the first move.
	 */
	private void createBlankBoard() {
		// TODO Auto-generated method stub
		initBtns();
		addBtnsToBoard();
		placeNewSetOfPeices();
	}

	// TODO create all different types of pieces and set their locations.
	private void placeNewSetOfPeices() {
		// Piece myPiece = new TestPiece(4, 4);
		// board.get(3).get(3).setPiece(new TestPiece(3, 3));
		board.get(5).get(5).setPiece(new Rook(5, 5, 'b'));
		board.get(3).get(5).setPiece(new Rook(3, 5, 'b'));
		board.get(4).get(3).setPiece(new Rook(4, 3, 'w'));
//		ActionListener testPieceListener = new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent event) {
//				PieceButton btnClicked = findbtn(event);
//				if (isOccupied(btnClicked)) {
//					if (btnClicked.isFocused()) {
//						btnClicked.setFocused(false);
//						removeFocuses();
//					} else {
//						removeFocuses();
//						btnClicked.setFocused(true);
//						getValidLocations(btnClicked.getPiece());
//					}
//				}
//			}
//
//		};
//		ActionListener testMoveListener = new ActionListener(){
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				PieceButton btnToMoveTo = findbtn(e);
//				if(btnToMoveTo.isFocused()){
//					btnToMoveTo.setBackground(Color.red);
//					removeFocuses();
//				}
//			}
//			
//		};
//		board.get(3).get(5).addActionListener(testPieceListener);
//		board.get(5).get(5).addActionListener(testPieceListener);
//		board.get(4).get(3).addActionListener(testPieceListener);
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
			System.out
					.println("Requested location inside of getButtonAtLocation() was null");
		}
		return btn;
	}

	/**
	 * Finds the button based on the event passed in
	 * 
	 * @param event
	 *            ActionEvent that was triggered by a user clicking on a button
	 * @return PieceButton that caused the event.
	 */
	protected PieceButton findbtn(ActionEvent event) {
		PieceButton empty = null;
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				if (board.get(row).get(col).equals(event.getSource()))
					return board.get(row).get(col);
			}
		}
		return empty;
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
	 * Creates a new board
	 */
	private void initBtns() {
		ActionListener testPieceListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				PieceButton btnClicked = findbtn(event);
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
			}

		};
		ActionListener testMoveListener = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				PieceButton btnToMoveTo = findbtn(e);
				if(btnToMoveTo.isFocused() && !isOccupied(btnToMoveTo)){
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

	private Location findLocationOfButton(PieceButton btn){
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				PieceButton current = board.get(row).get(col);
				if(current.equals(btn))
					return new Location(row,col);
			}
		}
		return null;
	}
	protected void movePieceToBtn(PieceButton btnToMoveTo) {
		if(btnToMovePieceFrom != null){
			Piece pieceToMove = btnToMovePieceFrom.getPiece();
			btnToMovePieceFrom.setPiece(null);
			btnToMoveTo.setPiece(pieceToMove);
			Location locOfBtnToMoveTo = findLocationOfButton(btnToMoveTo);
			pieceToMove.setLocation(new Location(locOfBtnToMoveTo.getRow(),locOfBtnToMoveTo.getCol()));
		}
	}

}
