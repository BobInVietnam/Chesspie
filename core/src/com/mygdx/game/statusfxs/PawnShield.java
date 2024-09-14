package com.mygdx.game.statusfxs;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.chesspieces.Pawn;
import com.mygdx.game.chesspieces.Piece;

public class PawnShield extends StatusEffect{

  @Override
  public StatusEffect clone() {
    return new PawnShield(strength, moveCheck);
  }

  private boolean moveCheck;
  public PawnShield(int strength, boolean moveCheck) {
    super(1, strength);
    this.moveCheck = moveCheck;
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
