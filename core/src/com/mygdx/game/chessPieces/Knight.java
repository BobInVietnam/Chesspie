package com.mygdx.game.chessPieces;

import com.mygdx.game.chessBoard.ChessBoard;

public class Knight extends  Piece {
    public Knight(int x, int y) {
        super(x, y);
    }

    public Knight(int x, int y, String color) {
        super(x, y, color);
    }

    public String getSymbol() {
        return "K";
    }

    public boolean canMove(ChessBoard board, int x, int y) {
        return false;
    }
}
