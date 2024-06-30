package com.mygdx.game.chesspieces;

import com.mygdx.game.chessboard.ChessBoard;

public class Bishop extends Piece{
    public Bishop(int x, int y) {
        super(x, y);
    }

    public Bishop(int x, int y, String color) {
        super(x, y, color);
    }

    public Character getSymbol() {
        return 'B';
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
    public boolean inBaseAtkRange(ChessBoard board, int x, int y) {
        return false;
    }

    @Override
    public boolean inSkillRange(ChessBoard board, int x, int y) {
        return false;
    }

    @Override
    public boolean canKillwithBaseAtk(ChessBoard board, int x, int y) {
        return false;
    }


    @Override
    public boolean canKillwithSkill(ChessBoard board, int x, int y) {
        return false;
    }

    @Override
    public void attack(ChessBoard board, Piece piece) {

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

    @Override
    public void killOtherPiecebyBaseAtk(ChessBoard board, int x, int y) {

    }


    @Override
    public void killOtherPiecebySkill(ChessBoard board, int x, int y) {
        if(this.canKillwithSkill(board, x, y)) board.removeAt(x, y);
    }
}
