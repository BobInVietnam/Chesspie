package com.mygdx.game.chesspieces;

import com.mygdx.game.chessboard.ChessBoard;

import java.util.ArrayList;

public class King extends Piece{
    public King() {}

    public King(int x, int y) {
        super(x, y);
    }

    public King(int x, int y, String color) {
        super(x, y, color);
    }

    public Character getSymbol() {
        return 'K';
    }

    @Override
    public boolean canMove(ChessBoard board, int x, int y) {
        if (!board.validate(x, y)) {
            return false;
        }
        int x_pos = this.getPosX();
        int y_pos = this.getPosY();
        if (x == x_pos-1 && y== y_pos && board.getAt(x, y) == null) return true;
        else if (x == x_pos+1 && y== y_pos && board.getAt(x, y) == null) return true;
        else if (x == x_pos && y== y_pos-1 && board.getAt(x, y) == null) return true;
        else if (x == x_pos && y== y_pos+1 && board.getAt(x, y) == null) return true;
        else if (x == x_pos-1 && y== y_pos-1 && board.getAt(x, y) == null) return true;
        else if (x == x_pos+1 && y== y_pos+1 && board.getAt(x, y) == null) return true;
        else if (x == x_pos+1 && y== y_pos-1 && board.getAt(x, y) == null) return true;
        else return x == x_pos-1 && y == y_pos+1 && board.getAt(x, y) == null;
    }

    @Override
    public boolean inBaseAtkRange(ChessBoard board, int x, int y) {
        if (!board.validate(x, y)) {
            return false;
        }
        for(int i=-1;i<=1;i++) {
            for(int j=-1;j<1;j++) {
                if(i != 0 || j != 0) {
                    int newRow = this.getPosX() + i;
                    int newCol = this.getPosY() + j;
                    if(newRow == x && newCol == y && board.getAt(x, y) != null
                            && !(board.getAt(x, y).getColor().equals(this.getColor()))) return true;
                }
            }
        }

        return false;
    }

    @Override
    public boolean inSkillRange(ChessBoard board) {
        return this.getChessSkill().inSkillRange(board, this);
    }

    @Override
    public boolean canKillwithBaseAtk(ChessBoard board, int x, int y) {
        return this.inBaseAtkRange(board, x, y) && board.getAt(x, y).getHp() < this.getBaseAttack() + this.getDefendShield();
    }

    @Override
    public boolean canKillwithSkill(ChessBoard board, int x, int y) {
        return this.inSkillRange(board) && board.getAt(x, y).getHp() < this.getChessSkill().getSkillDmg() + this.getDefendShield();
    }

    public void attack(ChessBoard board, Piece piece) {
        if(this.inBaseAtkRange(board, piece.getPosX(), piece.getPosY())) {
            piece.getAttacked(this);
        }
    }

    @Override
    public void activateSKill(ArrayList<Piece> pieces, ChessBoard board) {
//        if(this.inSkillRange(board)) {
//            this.getChessSkill().activateSkill(pieces,  );
//        }
    }

    public void getAttacked(Piece piece) {
        this.setHp(this.getHp() + this.getDefendShield() - piece.getBaseAttack());
    }

    public void getSkillAttacked(Piece piece) {
        this.setHp(this.getHp() + this.getDefendShield() - piece.getChessSkill().getSkillDmg());
    }

    public void killOtherPiecebyBaseAtk(ChessBoard board, int x, int y) {
        if(this.canKillwithBaseAtk(board, x, y)) board.removeAt(x, y);
    }

    @Override
    public void killOtherPiecebySkill(ChessBoard board, int x, int y) {
        if(this.canKillwithSkill(board, x, y)) board.removeAt(x, y);
    }
}
