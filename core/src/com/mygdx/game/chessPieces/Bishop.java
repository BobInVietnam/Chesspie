package com.mygdx.game.chessPieces;

import com.mygdx.game.chessBoard.ChessBoard;

public class Bishop extends  Piece{
    public Bishop(int x, int y) {
        super(x, y);
    }

    public Bishop(int x, int y, String color) {
        super(x, y, color);
    }

    public String getSymbol() {
        return "B";
    }

    public boolean canMove(ChessBoard board, int x, int y) {
        return false;
    }
}
