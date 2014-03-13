package chess;

import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;

public class Board extends JFrame {
	private ArrayList<ArrayList<JButton>> board = new ArrayList<ArrayList<JButton>>();;
	public static final int NORTH = 90;
	public static final int SOUTH = 180;
	public static final int EAST = 0;
	public static final int WEST = 180;
	/**
	 * Creates a 8x8 JFrame filled with empty JButtons.
	 */
	public Board() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createBlankBoard();
		this.setSize(600, 600);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	/**
	 * Returns the board in as a ArrayList<ArrayList<JButton>> 
	 * @return The list of all JButtons within the board
	 */
	public ArrayList<ArrayList<JButton>> getBoard() {
		return board;
	}
	/**
	 * Checks if the passed Button is occupied by a piece by checking
	 * if the button has an Icon.
	 * @param location JButton being checked
	 * @return True if location has icon.
	 */
	public boolean isOccupied(JButton location){
		if(location.getIcon() != null){
			return true;
		}
		else return false;
	}
	/**
	 * Checks if the peice passed has a move in the requested direction
	 * @param piece JButton that is moving
	 * @param direction Direction move will be in
	 */
	public void hasValidMoveInDirection(JButton piece, int direction){
		//NEED PIECE CLASS MUST CONTAIN .getRow() and .getCol()
		switch(direction){
		case NORTH:
			
		}
	}
	
	private void createBlankBoard() {
		// TODO Auto-generated method stub
		initBtns();
		addBtnsToBoard();
		placeNewSetOfPeices();
	}

	private void placeNewSetOfPeices() {
		// TODO Auto-generated method stub
		
	}

	private void addBtnsToBoard() {
		JPanel mainContent = new JPanel();
		mainContent.setLayout(new GridLayout(8, 8));
		for (ArrayList<JButton> row : board) {
			for (int col = 0; col < row.size(); col++) {
				mainContent.add(row.get(col));
			}
		}
		this.add(mainContent, BorderLayout.CENTER);
	}

	private void initBtns() {
		// TODO Auto-generated method stub
		for (int row = 0; row < 8; row++) {
			board.add(new ArrayList<JButton>());
			for (int col = 0; col < 8; col++) {
				board.get(row).add(new JButton());
			}
		}
	}

}
