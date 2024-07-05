package com.mygdx.game.chesspieces;

import com.mygdx.game.chessboard.ChessBoard;

import java.util.ArrayList;

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
        int currentX = this.getPosX();
        int currentY = this.getPosY();
        if (Math.abs(x - currentX) != Math.abs(y - currentY)) {
            return false;
        }
        if (x > currentX) {
            for (int i = 1; i < y - currentY; i++) {
                if (board.getAt(currentX + i, currentY + i) != null) {
                    return false;
                }
            }
            for (int i = 1; i < currentY - y; i++) {
                if (board.getAt(currentX + i, currentY - i) != null) {
                    return false;
                }
            }
        }
        if (x < currentX) {
            for (int i = 1; i < y - currentY; i++) {
                if (board.getAt(currentX - i, currentY + i) != null) {
                    return false;
                }
            }
            for (int i = 1; i < currentY - y; i++) {
                if (board.getAt(currentX - i, currentY - i) != null) {
                    return false;
                }
            }
        }

        if (board.getAt(x, y) != null) {
            return !board.getAt(x, y).getColor().equals(this.getColor());
        }

        return board.validate(x, y);
    }

    @Override
    public boolean inBaseAtkRange(ChessBoard board, int x, int y) {
        return false;
    }

    @Override
    public boolean inSkillRange(ChessBoard board) {
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
        if(this.inBaseAtkRange(board, piece.getPosX(), piece.getPosY())) {
            piece.getAttacked(this);
        }
    }

    @Override
    public void activateSKill(ArrayList<Piece> pieces, ChessBoard board) {

    }

    public void getAttacked(Piece piece) {
        this.setHp(this.getHp() + this.getDefendShield() - piece.getBaseAttack());
    }

    public void getSkillAttacked(Piece piece) {
        this.setHp(this.getHp() + this.getDefendShield() - piece.getChessSkill().getSkillDmg());
    }

    @Override
    public void killOtherPiecebyBaseAtk(ChessBoard board, int x, int y) {
        if(this.canKillwithBaseAtk(board, x, y)) board.removeAt(x, y);
    }


    @Override
    public void killOtherPiecebySkill(ChessBoard board, int x, int y) {
        if(this.canKillwithSkill(board, x, y)) board.removeAt(x, y);
    }
}
