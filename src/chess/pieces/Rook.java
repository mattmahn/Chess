package chess.pieces;

import chess.Board;
import chess.Location;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {

   /**
    * Creates a rook piece with the location of ({@code row},{@code col}) on {@code team} team.
    *
    * @param row  the row of this piece on the board
    * @param col  the column of this piece on the board
    * @param team the team this piece belongs to
    */
   public Rook(int row, int col, char team) {
      super(row, col);
      this.team = team;
      switch(team) {
         case 'b':
            icon = new ImageIcon("images/rook_black.png");
            break;
         case 'w':
            icon = new ImageIcon("images/rook_white.png");
            break;
         default:
            throw new IllegalArgumentException("Team must be 'b' for black team or 'w' for white team.");
      }
   }

   /**
    * Returns a list of locations where a rook can move (assuming an empty board).
    *
    * @return a list of move locations
    */
   @Override
   public List<List<Location>> getMoveLocations() {
      int row = getRow();
      int col = getCol();
      List<List<Location>> moveLocs = new ArrayList<>();

      // get locations North
      List<Location> north = new ArrayList<Location>();
      for(Location loc = location.getAdjacentLocation(Location.NORTH); Board.isValidLocationOnGird(loc); loc = loc.getAdjacentLocation(Location.NORTH)) {
         north.add(loc);
      }
      // get locations East
      List<Location> east = new ArrayList<Location>();
      for(Location loc = location.getAdjacentLocation(Location.EAST); Board.isValidLocationOnGird(loc); loc = loc.getAdjacentLocation(Location.EAST)) {
         east.add(loc);
      }
      // get locations South
      List<Location> south = new ArrayList<Location>();
      for(Location loc = location.getAdjacentLocation(Location.SOUTH); Board.isValidLocationOnGird(loc); loc = loc.getAdjacentLocation(Location.SOUTH)) {
         south.add(loc);
      }
      //get locations West
      List<Location> west = new ArrayList<Location>();
      for(Location loc = location.getAdjacentLocation(Location.WEST); Board.isValidLocationOnGird(loc); loc = loc.getAdjacentLocation(Location.WEST)) {
         west.add(loc);
      }

      moveLocs.add(north);
      moveLocs.add(east);
      moveLocs.add(south);
      moveLocs.add(west);

      return moveLocs;
   }

}
