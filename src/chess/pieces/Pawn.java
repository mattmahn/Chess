package chess.pieces;

import chess.Location;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

   private boolean isFirstMove;

   /**
    * Creates a pawn piece with the location of ({@code row},{@code col}) on {@code team} team.
    *
    * @param row  the row of this piece on the board
    * @param col  the column of this piece on the board
    * @param team the team this piece belongs to
    */
   public Pawn(int row, int col, char team) {
      super(row, col);
      this.team = team;
      switch(team) {
         case 'b':
            icon = new ImageIcon("images/pawn_black.png");
            break;
         case 'w':
            icon = new ImageIcon("images/pawn_white.png");
            break;
         default:
            throw new IllegalArgumentException("Team must be 'b' for black team or 'w' for white team.");
      }
      this.isFirstMove = true;
   }

   /**
    * Marks the pawn as having already moved, so it cannot move two spaces forward.
    */
   public void hasMoved() {
      isFirstMove = false;
   }

   /**
    * Returns all possible move locations of this piece.
    *
    * @return all possible move location of this piece
    */
   @Override
   public List<List<Location>> getMoveLocations() {
      List<List<Location>> moveLocs = new ArrayList<List<Location>>();

      List<Location> vertical = new ArrayList<>(), diagLeft = new ArrayList<>(), diagRight = new ArrayList<>();
      if(team == 'w') {
         vertical.add(location.getAdjacentLocation(Location.NORTH));
         if(isFirstMove){
            vertical.add(location.getAdjacentLocation(Location.NORTH).getAdjacentLocation(Location.NORTH));
            isFirstMove = false;
         }
         diagLeft.add(location.getAdjacentLocation(Location.NORTH_WEST));
         diagRight.add(location.getAdjacentLocation(Location.NORTH_EAST));
      } else {
         vertical.add(location.getAdjacentLocation(Location.SOUTH));
         if(isFirstMove){
            vertical.add(location.getAdjacentLocation(Location.SOUTH).getAdjacentLocation(Location.SOUTH));
            isFirstMove = false;
         }
         diagLeft.add(location.getAdjacentLocation(Location.SOUTH_WEST));
         diagRight.add(location.getAdjacentLocation(Location.SOUTH_EAST));
      }

      moveLocs.add(vertical);
      moveLocs.add(diagLeft);
      moveLocs.add(diagRight);

      return moveLocs;
   }
}
