package chess.pieces;

import chess.Location;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {

   /**
    * Creates a Knight piece at a given row and column.
    *
    * @param row the row of this piece on the board
    * @param col the column of this piece on the board
    */
   public Knight(int row, int col, char team) {
      super(row, col);
      this.team = team;
      switch(team) {
         case 'b':
            icon = new ImageIcon("images/king_black.png");
            break;
         case 'w':
            icon = new ImageIcon("images/king_white.png");
            break;
         default:
            throw new IllegalArgumentException("Team must be 'b' for black team or 'w' for white team.");
      }
   }

   @Override
   public List<List<Location>> getMoveLocations() {
      List<List<Location>> moveLocs = new ArrayList<>();

      List<Location> foo = new ArrayList<>();
      foo.add(new Location(location.getRow() + 1, location.getCol() + 2));
      moveLocs.add(foo);
      foo = new ArrayList<>();
      foo.add(new Location(location.getRow() + 2, location.getCol() + 1));
      moveLocs.add(foo);
      foo = new ArrayList<>();
      foo.add(new Location(location.getRow() - 1, location.getCol() - 2));
      moveLocs.add(foo);
      foo = new ArrayList<>();
      foo.add(new Location(location.getRow() - 2, location.getCol() - 1));
      moveLocs.add(foo);
      foo = new ArrayList<>();

      return moveLocs;
   }
}
