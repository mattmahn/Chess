package chess.pieces;

import chess.Location;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class King extends Piece {

   /**
    * Creates a king piece at a given row and column.
    *
    * @param row  the row of this piece on the board
    * @param col  the column of this piece on the board
    * @param team the team this piece belongs to
    */
   public King(int row, int col, char team) {
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

      List<Location> north = new ArrayList<Location>();
      north.add(location.getAdjacentLocation(Location.NORTH));
      moveLocs.add(north);

      List<Location> south = new ArrayList<Location>();
      south.add(location.getAdjacentLocation(Location.SOUTH));
      moveLocs.add(south);

      List<Location> east = new ArrayList<Location>();
      east.add(location.getAdjacentLocation(Location.EAST));
      moveLocs.add(east);

      List<Location> west = new ArrayList<Location>();
      west.add(location.getAdjacentLocation(Location.WEST));
      moveLocs.add(west);

      List<Location> southEast = new ArrayList<Location>();
      southEast.add(location.getAdjacentLocation(Location.SOUTH_EAST));
      moveLocs.add(southEast);

      List<Location> northEast = new ArrayList<Location>();
      northEast.add(location.getAdjacentLocation(Location.NORTH_EAST));
      moveLocs.add(northEast);

      List<Location> northWest = new ArrayList<Location>();
      northWest.add(location.getAdjacentLocation(Location.NORTH_WEST));
      moveLocs.add(northWest);

      List<Location> southWest = new ArrayList<Location>();
      northWest.add(location.getAdjacentLocation(Location.SOUTH_WEST));
      moveLocs.add(southWest);

      return moveLocs;
   }

}
