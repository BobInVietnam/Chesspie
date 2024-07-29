package com.mygdx.game.statusfxs;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.chesspieces.Piece;

public class PawnShield extends StatusEffect{
  private boolean moveCheck;
  public PawnShield(int strength, Piece piece) {
    super(1, strength);
    moveCheck = piece.isEvenMove();
  }
  @Override
  public void activate(Piece piece) {
    if (piece.isEvenMove() != moveCheck) {
      duration--;
      return;
    }
    piece.setDefense(piece.getDefense() + strength);
  }
}
