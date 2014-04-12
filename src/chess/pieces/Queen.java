package chess.pieces;

import chess.Board;
import chess.Location;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {

   /**
    * Creates a queen piece with the location of ({@code row},{@code col}) on {@code team} team.
    *
    * @param row  the row of this piece on the board
    * @param col  the column of this piece on the board
    * @param team the team this piece belongs to
    */
   public Queen(int row, int col, char team) {
      super(row, col);
      this.team = team;
      switch(team) {
         case 'b':
            icon = new ImageIcon("images/queen_black.png");
            break;
         case 'w':
            icon = new ImageIcon("images/queen_white.png");
            break;
         default:
            throw new IllegalArgumentException("Team must be 'b' for black team or 'w' for white team.");
      }
   }

   /**
    * Returns all possible move locations of this piece.
    *
    * @return all possible move location of this piece
    */
   @Override
   public List<List<Location>> getMoveLocations() {
      int row = getRow();
      int col = getCol();
      List<List<Location>> moveLocs = new ArrayList<List<Location>>();

      // get locations North
      List<Location> north = new ArrayList<>();
      for(int j = row - 1; j >= 0; j--) {
         north.add(new Location(j, col));
      }
      // get locations North East
      List<Location> northEast = new ArrayList<>();
      for(Location loc = location.getAdjacentLocation(Location.NORTH_EAST); Board.isValidLocationOnGird(loc); loc = loc.getAdjacentLocation(Location.NORTH_EAST)) {
         northEast.add(loc);
      }
      // get locations East
      List<Location> east = new ArrayList<>();
      for(int i = col + 1; i < 8; i++) {
         east.add(new Location(row, i));
      }
      // get location South East
      List<Location> southEast = new ArrayList<>();
      for(Location loc = location.getAdjacentLocation(Location.SOUTH_EAST); Board.isValidLocationOnGird(loc); loc = loc.getAdjacentLocation(Location.SOUTH_EAST)) {
         southEast.add(loc);
      }
      // get locations South
      List<Location> south = new ArrayList<>();
      for(int j = row + 1; j < 8; j++) {
         south.add(new Location(j, col));
      }
      // get locations South West
      List<Location> southWest = new ArrayList<>();
      for(Location loc = location.getAdjacentLocation(Location.SOUTH_WEST); Board.isValidLocationOnGird(loc); loc = loc.getAdjacentLocation(Location.SOUTH_WEST)) {
         southWest.add(loc);
      }
      //get locations West
      List<Location> west = new ArrayList<>();
      for(int i = col - 1; i >= 0; i--) {
         west.add(new Location(row, i));
      }
      // get locations North West
      List<Location> northWest = new ArrayList<>();
      for(Location loc = location.getAdjacentLocation(Location.NORTH_WEST); Board.isValidLocationOnGird(loc); loc = loc.getAdjacentLocation(Location.NORTH_WEST)) {
         northWest.add(loc);
      }

      moveLocs.add(north);
      moveLocs.add(northEast);
      moveLocs.add(east);
      moveLocs.add(southEast);
      moveLocs.add(south);
      moveLocs.add(southWest);
      moveLocs.add(west);
      moveLocs.add(northWest);

      return moveLocs;
   }
}
