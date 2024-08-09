package com.mygdx.game.moves;

import com.mygdx.game.chesspieces.Piece;

public class PieceHistory {
  public enum PieceState {
    MOVE,
    ATTACK,
    SKILL,
    ATTACKED,
    BUFFED,
    KILLED,
  }
  public Piece piece;
  public PieceState state;

  /**
   * Piece history couples piece with its "state" - whether it has been moved, attacked, or getting attacked, etc.
   * @param piece
   * @param state
   */
  public PieceHistory(Piece piece, PieceState state) {
    this.piece = piece;
    this.state = state;
  }
}
