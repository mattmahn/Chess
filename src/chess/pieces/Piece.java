package chess.pieces;

import chess.Location;

import javax.swing.*;
import java.util.List;

public abstract class Piece {

   /**
    * The current location of this piece.
    */
   protected Location location;
   /**
    * The team this piece belongs to.
    */
   protected char team;
   /**
    * The icon of this piece.
    */
   protected ImageIcon icon;

   /**
    * Creates a generic piece at a given row and column.
    *
    * @param row the row of this piece on the board
    * @param col the column of this piece on the board
    */
   public Piece(int row, int col) {
      this.location = new Location(row, col);
   }

   /**
    * Returns the Location of this piece.
    *
    * @return the Location of this piece
    */
   public Location getLocation() {
      return location;
   }

   /**
    * Returns the row this piece is in.
    *
    * @return the row this piece is in
    */
   public int getRow() {
      return location.getRow();
   }

   /**
    * Returns the column this piece is in.
    *
    * @return the column this piece is in
    */
   public int getCol() {
      return location.getCol();
   }

   /**
    * Updates the location of this piece.
    *
    * @param newLocation the new location of this piece
    */
   public void setLocation(Location newLocation) {
      this.location = newLocation;
   }

   /**
    * Returns the icon of this piece.
    *
    * @return the icon of this piece
    */
   public ImageIcon getIcon() {
      return icon;
   }

   /**
    * Returns all possible move locations of this piece.
    *
    * @return all possible move location of this piece
    */
   public abstract List<List<Location>> getMoveLocations();

   /**
    * Returns the team of this piece.
    *
    * @return the team of this piece
    */
   public char getTeam() {
      return team;
   }

   /**
    * Returns a string representation of this piece in the format:
    * "Rook (rook_white.png) at location (1,2)"
    *
    * @return a string representation of this piece
    */
   @Override
   public String toString() {
      return this.getClass().getSimpleName() + " (" + getIcon().toString() + ") at location " + location.toString();
   }
}
