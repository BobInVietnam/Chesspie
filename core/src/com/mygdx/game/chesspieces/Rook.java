package com.mygdx.game.chesspieces;

import com.mygdx.game.chessboard.ChessBoard;

public class Rook extends Piece {
    public Rook(int x, int y) {
        super(x, y);
    }

    public Rook(int x, int y, String color) {
        super(x, y, color);
    }

    public Character getSymbol() {
        return 'R';
    }

    public boolean canMove(ChessBoard board, int x, int y) {
        if (x != this.getPosX() && y != this.getPosY()) {
            return false;
        }

        if (x > this.getPosX()) {
            for (int i = this.getPosX() + 1; i < x; i++) {
                if (board.getAt(i, y) != null) {
                    return false;
                }
            }
        }

        if (y > this.getPosY()) {
            for (int i = this.getPosY() + 1; i < y; i++) {
                if (board.getAt(x, i) != null) {
                    return false;
                }
            }
        }

        if (x < this.getPosX()) {
            for (int i = this.getPosX() - 1; i > x; i--) {
                if (board.getAt(i, y) != null) {
                    return false;
                }
            }
        }

        if (y < this.getPosY()) {
            for (int i = this.getPosY() - 1; i > y; i--) {
                if (board.getAt(x, i) != null) {
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

    @Override
    public void activateSKill() {

    }

    public void getAttacked(Piece piece) {
        this.setHp(this.getHp() - piece.getBaseAttack());
    }

    public void getSkillAttacked(Piece piece) {
        this.setHp(this.getHp() - piece.getChessSkill().getSkillDmg());
    }
}
