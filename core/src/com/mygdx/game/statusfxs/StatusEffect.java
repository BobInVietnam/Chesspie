package com.mygdx.game.statusfxs;

import com.mygdx.game.chesspieces.Piece;

public abstract class StatusEffect {
  public int duration;
  public int strength;
  public enum Effect {
    PROTECTION
  }
  public StatusEffect(int duration, int strength) {
    this.duration = duration;
    this.strength = strength;
  }
  public static void apply(Piece piece, Effect effect, int duration, int strength) {
    switch (effect) {
      case PROTECTION:
        piece.applyStatus(new Protection(duration, strength));
    }
  }
  public void activate(Piece piece) {
    duration--;
  }
}
