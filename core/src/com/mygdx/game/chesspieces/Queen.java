package com.mygdx.game.chesspieces;

import com.mygdx.game.chessboard.ChessBoard;

public class Queen extends Piece{
    public Queen(int x, int y) {
        super(x, y);
    }

    public Queen(int x, int y, String color) {
        super(x, y, color);
    }

    public String getSymbol() {
        return "Q";
    }

    @Override
    public boolean canMove(ChessBoard board, int x, int y) {
        return false;
    }

    @Override
    public boolean canKill(ChessBoard board, int x, int y) {
        return false;
    }

    public void attack(Piece piece) {

    }

    public void getAttacked(Piece piece) {
        this.setHp(this.getHp() - piece.getBaseAttack());
    }
}
