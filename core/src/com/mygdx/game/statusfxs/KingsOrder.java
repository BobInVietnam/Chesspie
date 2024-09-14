package com.mygdx.game.statusfxs;

import com.mygdx.game.chesspieces.Piece;

public class KingsOrder extends StatusEffect{

  @Override
  public StatusEffect clone() {
    return new KingsOrder(duration, strength);
  }

  public KingsOrder(int duration, int strength) {
    super(duration, strength);
  }

  @Override
  public void activate(Piece piece) {
    super.activate(piece);
    piece.setDefense(piece.getDefense() + strength);
    piece.setAttack(piece.getAttack() + strength);
  }
}
