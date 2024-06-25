package com.mygdx.game.chessPieces;

import com.mygdx.game.chessBoard.ChessBoard;

public class Rook extends Piece{
    public Rook(int x, int y) {
        super(x, y);
    }

    public Rook(int x, int y, String color) {
        super(x, y, color);
    }

    public String getSymbol() {
        return "R";
    }

    public boolean canMove(ChessBoard board, int x, int y) {
        return false;
    }
}
