package com.mygdx.game.chesspieces;

import com.mygdx.game.chessboard.ChessBoard;

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
        int diffValue = Integer.min(Math.abs(this.getPosX() - x), Math.abs(this.getPosY() - y));

        if (x > this.getPosX() && y > this.getPosY()) {
            for (int i = 1; i < diffValue; i++) {
                if (board.getAt(this.getPosX() + i, this.getPosY() + i) != null) {
                    return false;
                }
            }
        }

        if (x < this.getPosX() && y < this.getPosY()) {
            for (int i = 1; i < diffValue; i++) {
                if (board.getAt(this.getPosX() - i, this.getPosY() - i) != null) {
                    return false;
                }
            }
        }

        if (x > this.getPosX() && y < this.getPosY()) {
            for (int i = 1; i < diffValue; i++) {
                if (board.getAt(this.getPosX() + i, this.getPosY() - i) != null) {
                    return false;
                }
            }
        }

        if (x < this.getPosX() && y > this.getPosY()) {
            for (int i = 1; i < diffValue; i++) {
                if (board.getAt(this.getPosX() - i, this.getPosY() + i) != null) {
                    return false;
                }
            }
        }

        if (board.getAt(x, y) != null) {
            return !(this.getColor().equals(board.getAt(x, y).getColor()));
        }

        return board.validate(x, y);
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
