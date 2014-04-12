package chess;


import chess.pieces.Piece;

import javax.swing.*;
import java.awt.*;

public class PieceButton extends JButton {

   private Piece housedPiece;
   private boolean focused, active;

   public PieceButton() {
      super();
   }

   public Piece setPiece(Piece p) {
      Piece oldPiece = housedPiece;
      housedPiece = p;
      if(p == null) {
         this.setIcon(null);
         this.setDisabledIcon(null);
         return null;
      } else {
         this.setIcon(p.getIcon());
         this.setDisabledIcon(p.getIcon());
         return oldPiece;
      }
   }

   public boolean isActive() {
      return active;
   }

   public void setActive(boolean b) {
      this.active = b;
   }

   public Piece getPiece() {
      return housedPiece;
   }

   public boolean isFocused() {
      return focused;
   }

   public void setFocused(boolean b) {
      this.focused = b;
      if(b)
         this.setBackground(Color.yellow);
      else
         this.setBackground(null);

   }
}
