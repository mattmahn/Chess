package chess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Board extends JFrame {

	private ArrayList<ArrayList<PieceButton>> board = new ArrayList<ArrayList<PieceButton>>();
	public static final int NORTH = 0;
	public static final int SOUTH = 180;
	public static final int EAST = 90;
	public static final int WEST = 270;

	/**
	 * Creates a 8x8 JFrame filled with empty JButtons.
	 */
	public Board() {
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		createBlankBoard();
		this.setSize(600, 600);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	/**
	 * Returns the board in as a ArrayList<ArrayList<JButton>>
	 *
	 * @return The list of all JButtons within the board
	 */
	public ArrayList<ArrayList<PieceButton>> getBoard() {
		return board;
	}

	/**
	 * Checks if the passed Button is occupied by a piece by checking if the
	 * button has an Icon.
	 *
	 * @param location JButton being checked
	 * @return True if location has icon.
	 */
	public boolean isOccupied(JButton location) {
		try {
			if(location.getIcon() != null) {
				return true;
			} else
				return false;
		} catch(ArrayIndexOutOfBoundsException e) {
			return false;
		}
	}
	
	public void getValidLocations(Piece piece) {
		List<Location> locs = piece.getMoveLocations();
		Location pieceLocation = piece.getLocation();
		for(Location loc : locs) {
			System.out.println(loc);
			getButtonAtLocation(loc).setBackground(Color.yellow);
		}

	}

	/**
	 * Checks if the piece passed has a move in the requested direction
	 *
	 * @param piece     JButton that is moving
	 * @param direction Direction move will be in
	 */
	public boolean hasValidMoveInDirection(Piece piece, int direction) {
		int pieceRow = piece.getRow();
		int pieceCol = piece.getCol();
		switch(direction) {
			case NORTH:
				if(isOccupied(getButton(pieceRow - 1, pieceCol)))
					return false;
				break;
			case SOUTH:
				if(isOccupied(getButton(pieceRow + 1, pieceCol)))
					return false;
				break;
			case EAST:
				if(isOccupied(getButton(pieceRow, pieceCol + 1)))
					return false;
				break;
			case WEST:
				if(isOccupied(getButton(pieceRow, pieceCol - 1)))
					return false;
				break;
		}
		return true;
	}

	/**
	 * Sets the icon of the peices location to a button on the board
	 *
	 * @param piece Peice that will be placed on the board.
	 */
	public void placePiece(Piece piece) {
		board.get(piece.getRow()).get(piece.getCol()).setIcon(piece.getIcon());
	}

	private JButton getButton(int Row, int Col) {
		return board.get(Row).get(Col);
	}

	private void createBlankBoard() {
		// TODO Auto-generated method stub
		initBtns();
		addBtnsToBoard();
		placeNewSetOfPeices();
	}

	// TODO create all different types of pieces and set their locations.
	private void placeNewSetOfPeices() {
		//Piece myPiece = new TestPiece(4, 4);
		//board.get(3).get(3).setPiece(new TestPiece(3, 3));
		board.get(5).get(5).setPiece(new Rook(5,5,'b'));
		//board.get(4).get(4).setPiece(myPiece);
		ActionListener testPieceListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				PieceButton btnClicked = findbtn(event);
				if(isOccupied(btnClicked)) {
					if(btnClicked.isFocused())
						btnClicked.setFocused(false);
					else {
						removeFocuses();
						btnClicked.setFocused(true);
						getValidLocations(btnClicked.getPiece());
					}
				}
			}

		};
		
		board.get(5).get(5).addActionListener(testPieceListener);
		board.get(4).get(4).addActionListener(testPieceListener);
	}

	private void removeFocuses() {
		for(int row = 0; row < 8; row++) {
			for(int col = 0; col < 8; col++) {
				board.get(row).get(col).setFocused(false);
			}
		}

	}

	public PieceButton getButtonAtLocation(Location loc) {
		PieceButton btn = null;
		try {
			btn = board.get(loc.getRow()).get(loc.getCol());
		} catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("Requested location inside of getButtonAtLocation() was null");
		}
		return btn;
	}

	protected PieceButton findbtn(ActionEvent event) {
		PieceButton empty = null;
		for(int row = 0; row < 8; row++) {
			for(int col = 0; col < 8; col++) {
				if(board.get(row).get(col).equals(event.getSource()))
					return board.get(row).get(col);
			}
		}
		return empty;
	}

	private void addBtnsToBoard() {
		JPanel mainContent = new JPanel();
		mainContent.setLayout(new GridLayout(8, 8));
		for(ArrayList<PieceButton> row : board) {
			for(int col = 0; col < row.size(); col++) {
				mainContent.add(row.get(col));
			}
		}
		this.add(mainContent, BorderLayout.CENTER);
	}

	private void initBtns() {
		// TODO Auto-generated method stub
		for(int row = 0; row < 8; row++) {
			board.add(new ArrayList<PieceButton>());
			for(int col = 0; col < 8; col++) {
				board.get(row).add(new PieceButton());
			}
		}
	}

}
