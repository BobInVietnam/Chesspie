package com.mygdx.game.statusfxs;

import com.mygdx.game.chesspieces.Piece;

public class Protection extends StatusEffect {
  public Protection(int duration, int strength) {
    super(duration, strength);
  }
  @Override
  public void activate(Piece piece) {
    super.activate(piece);
    piece.setDefense(piece.getDefense() + strength);
  }
}
