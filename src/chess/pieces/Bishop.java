package chess.pieces;

import chess.Board;
import chess.Location;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {

   /**
    * Creates a bishop piece at a given row and column.
    *
    * @param row the row of this piece on the board
    * @param col the column of this piece on the board
    */
   public Bishop(int row, int col, char team) {
      super(row, col);
      this.team = team;
      switch(team) {
         case 'b':
            icon = new ImageIcon("images/bishop_black.png");
            break;
         case 'w':
            icon = new ImageIcon("images/bishop_white.png");
            break;
         default:
            throw new IllegalArgumentException("Team must be 'b' for black team or 'w' for white team.");
      }
   }

   @Override
   public List<List<Location>> getMoveLocations() {
      List<List<Location>> moveLocs = new ArrayList<>();

      List<Location> diagonalRight = new ArrayList<>();
      for(Location loc = location.getAdjacentLocation(Location.NORTH_EAST); Board.isValidLocationOnGird(loc); loc = loc.getAdjacentLocation(Location.NORTH_EAST))
         diagonalRight.add(loc);
      moveLocs.add(diagonalRight);
      diagonalRight = new ArrayList<>();
      for(Location loc = location.getAdjacentLocation(Location.SOUTH_EAST); Board.isValidLocationOnGird(loc); loc = loc.getAdjacentLocation(Location.SOUTH_EAST))
         diagonalRight.add(loc);
      moveLocs.add(diagonalRight);

      List<Location> diagonalLeft = new ArrayList<>();
      for(Location loc = location.getAdjacentLocation(Location.NORTH_WEST); Board.isValidLocationOnGird(loc); loc = loc.getAdjacentLocation(Location.NORTH_WEST))
         diagonalLeft.add(loc);
      moveLocs.add(diagonalLeft);
      diagonalLeft = new ArrayList<>();
      for(Location loc = location.getAdjacentLocation(Location.SOUTH_WEST); Board.isValidLocationOnGird(loc); loc = loc.getAdjacentLocation(Location.SOUTH_WEST))
         diagonalLeft.add(loc);
      moveLocs.add(diagonalLeft);

      return moveLocs;
   }
}
