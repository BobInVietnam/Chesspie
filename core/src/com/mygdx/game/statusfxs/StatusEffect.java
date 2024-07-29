package com.mygdx.game.statusfxs;

import com.mygdx.game.chesspieces.Piece;

public abstract class StatusEffect {
  protected int duration;
  protected int strength;
  public enum Effect {
    PROTECTION,
    KINGS_ORDER,
    PAWN_SHIELD
  }
  public StatusEffect(int duration, int strength) {
    this.duration = duration;
    this.strength = strength;
  }

  public int getDuration() {
    return duration;
  }

  public void setDuration(int duration) {
    this.duration = duration;
  }

  public int getStrength() {
    return strength;
  }

  public void setStrength(int strength) {
    this.strength = strength;
  }

  public static void apply(Piece piece, Effect effect, int duration, int strength) {
    switch (effect) {
      case PROTECTION:
        piece.applyStatus(new Protection(duration, strength));
        break;
      case KINGS_ORDER:
        piece.applyStatus(new KingsOrder(duration, strength));
        break;
      case PAWN_SHIELD:
        piece.applyStatus(new PawnShield(strength, piece));
    }
  }
  public void activate(Piece piece) {
    duration--;
  }
}
