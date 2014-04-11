package chess.pieces;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import chess.Location;

public class King extends Piece {

	private ImageIcon icon;
	private char team;

	public King(int row, int col, char team) {
		super(row, col);
		this.team = team;
		switch (team) {
		case 'b':
			icon = new ImageIcon("images/king_black.png");
			break;
		case 'w':
			icon = new ImageIcon("images/king_white.png");
			break;
		default:
			throw new IllegalArgumentException(
					"Team must be 'b' for black team or 'w' for white team.");
		}
	}

	@Override
	public ImageIcon getIcon() {
		// TODO Auto-generated method stub
		return icon;
	}

	@Override
	public List<List<Location>> getMoveLocations() {
		List<List<Location>> moveLocs = new ArrayList<>();
		System.out.println(this.getRow() + ""+ this.getCol());
		List<Location> north = new ArrayList<Location>();
		
		north.add(new Location(this.getRow()-1,this.getCol()));
		moveLocs.add(north);
		
		List<Location> south = new ArrayList<Location>();
		south.add(new Location(this.getRow()+1,this.getCol()));
		moveLocs.add(south);
		
		List<Location> east = new ArrayList<Location>();
		east.add(new Location(this.getRow(),this.getCol()+1));
		moveLocs.add(east);
			
		List<Location> west = new ArrayList<Location>();
		west.add(new Location(this.getRow(),this.getCol()-1));
		moveLocs.add(west);
		
		List<Location> southEast = new ArrayList<Location>();
		southEast.add(new Location(this.getRow()+1,this.getCol()+1));
		moveLocs.add(southEast);
		
		List<Location> northEast = new ArrayList<Location>();
		northEast.add(new Location(this.getRow()-1,this.getCol()+1));
		moveLocs.add(northEast);
		
		List<Location> northWest = new ArrayList<Location>();
		northWest.add(new Location(this.getRow()-1,this.getCol()-1));
		moveLocs.add(northWest);
		
		List<Location> southWest = new ArrayList<Location>();
		northWest.add(new Location(this.getRow()+1,this.getCol()-1));
		moveLocs.add(southWest);
		return moveLocs;
	}

	@Override
	public char getTeam() {
		// TODO Auto-generated method stub
		return team;
	}

}
