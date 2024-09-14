package com.mygdx.game.moves;

import com.badlogic.gdx.utils.Array;
import com.mygdx.game.chessboard.ChessBoard;

public class History {
  private Array<Move> history;
  public History() {
    history = new Array<>();
  }
  public Move getMoveAt(int index) {
    return history.get(index);
  }
  public void addMove(Move move) {
    history.add(move);
  }
}
