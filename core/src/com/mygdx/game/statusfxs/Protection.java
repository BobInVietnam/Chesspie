package com.mygdx.game.statusfxs;

import com.mygdx.game.chesspieces.Piece;

public class Protection extends StatusEffect {
  @Override
  public StatusEffect clone() {
    return new Protection(duration, strength);
  }

  public Protection(int duration, int strength) {
    super(duration, strength);
  }
  @Override
  public void activate(Piece piece) {
    super.activate(piece);
    piece.setDefense(piece.getDefense() + strength);
  }
}
