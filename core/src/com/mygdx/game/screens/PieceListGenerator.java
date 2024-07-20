package com.mygdx.game.screens;

import com.badlogic.gdx.utils.Array;
import com.mygdx.game.chesspieces.*;

public class PieceListGenerator {
  public static Array<Piece> generatePieces() {
    Array<Piece> piecesList = new Array<>();
    piecesList.add(new Rook(1, 1, "white", 30, 15));
    piecesList.add(new Rook(8, 1, "white", 30, 15));
    piecesList.add(new Knight(2, 1, "white", 25, 20));
    piecesList.add(new Knight(7, 1, "white", 25, 20));
    piecesList.add(new Bishop(3, 1, "white", 25, 20));
    piecesList.add(new Bishop(6, 1, "white", 25, 20));
    piecesList.add(new Queen(4, 1, "white", 30, 25));
    piecesList.add(new King(5, 1, "white", 50, 100));
    for (int i = 1; i <= 8; i++) {
      piecesList.add(new Pawn(i, 2, "white", 15, 10));
    }
    piecesList.add(new Rook(1, 8, "black", 30, 15));
    piecesList.add(new Rook(8, 8, "black", 30, 15));
    piecesList.add(new Knight(2, 8, "black", 25, 20));
    piecesList.add(new Knight(7, 8, "black", 25, 20));
    piecesList.add(new Bishop(3, 8, "black", 25, 20));
    piecesList.add(new Bishop(6, 8, "black", 25, 20));
    piecesList.add(new Queen(4, 8, "black",30, 25));
    piecesList.add(new King(5, 8, "black", 50, 100));
    for (int i = 1; i <= 8; i++) {
      piecesList.add(new Pawn(i, 7, "black", 15, 10));
    }
    return piecesList;
  }
}
