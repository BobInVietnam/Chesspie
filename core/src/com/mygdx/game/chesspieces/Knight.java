package com.mygdx.game.chesspieces;

import com.mygdx.game.chessboard.ChessBoard;

import java.util.ArrayList;

public class Knight extends Piece {
    public Knight(int x, int y) {
        super(x, y);
    }
    public Knight(int x, int y, String color) {
        super(x, y, color);
    }
    public Knight(int x, int y, String color, int maxHp, int baseAttack) {
        super(x, y, color, maxHp, baseAttack);
    }

    public Character getSymbol() {
        return 'N';
    }

    public boolean canMove(ChessBoard board, int x, int y) {
        if (!board.validate(x, y)) {
            return false;
        }

        int x_pos = this.getPosX();
        int y_pos = this.getPosY();
        if (x == x_pos-1 && y== y_pos-2 && board.getAt(x, y) == null) return true;
        else if (x == x_pos+1 && y== y_pos-2 && board.getAt(x, y) == null) return true;
        else if (x == x_pos-1 && y== y_pos+2 && board.getAt(x, y) == null) return true;
        else if (x == x_pos+1 && y== y_pos+2 && board.getAt(x, y) == null) return true;
        else if (x == x_pos-2 && y== y_pos-1 && board.getAt(x, y) == null) return true;
        else if (x == x_pos-2 && y== y_pos+1 && board.getAt(x, y) == null) return true;
        else if (x == x_pos+2 && y== y_pos-1 && board.getAt(x, y) == null) return true;
        else return x == x_pos + 2 && y == y_pos + 1 && board.getAt(x, y) == null;
    }

    //Need to re-check this
    @Override
    public boolean inBaseAtkRange(ChessBoard board, int x, int y) {
        int x_pos = this.getPosX();
        int y_pos = this.getPosY();
        Piece enemy = board.getAt(x, y);
        if(enemy == null)  return false;

        if (!board.validate(x, y)) {
            return false;
        }

        if (x == x_pos-1 && y == y_pos-2 && !(enemy.getColor().equals(this.getColor()))) return true;
        else if (x == x_pos+1 && y== y_pos-2 && !(enemy.getColor().equals(this.getColor()))) return true;
        else if (x == x_pos-1 && y== y_pos+2 && !(enemy.getColor().equals(this.getColor()))) return true;
        else if (x == x_pos+1 && y== y_pos+2 && !(enemy.getColor().equals(this.getColor()))) return true;
        else if (x == x_pos-2 && y== y_pos-1 && !(enemy.getColor().equals(this.getColor()))) return true;
        else if (x == x_pos-2 && y== y_pos+1 && !(enemy.getColor().equals(this.getColor()))) return true;
        else if (x == x_pos+2 && y== y_pos-1 && !(enemy.getColor().equals(this.getColor()))) return true;
        else return x == x_pos + 2 && y == y_pos + 1 && !(enemy.getColor().equals(this.getColor()));
    }

    @Override
    public boolean canUseSkillOn(ChessBoard board, int x, int y) {
        return false;
    }

    @Override
    public boolean inSkillRange(ChessBoard board) {
        return false;
    }

    @Override
    public boolean inSkillRange(ChessBoard board, int x, int y) {
        return false;
    }

    @Override
    public boolean canKillwithSkill(ChessBoard board, int x, int y) {
        return this.inSkillRange(board) && board.getAt(x, y).getHp() < this.getChessSkill().getSkillDmg() + this.getDefendShield();
    }


    public void attack(ChessBoard board, Piece piece) {
        if (canKillwithBaseAtk(board, piece)) killPiece(board, piece);
        else {
            piece.getAttacked(this);
        }
    }

    @Override
    public void activateSKill(ArrayList<Piece> pieces, ChessBoard board) {
    }

    @Override
    public void getSkillAttacked(Piece piece) {
        this.setHp(this.getHp() + this.getDefendShield() - piece.getChessSkill().getSkillDmg());
    }

    @Override
    public void killOtherPiecebySkill(ChessBoard board, int x, int y) {
        if(this.canKillwithSkill(board, x, y)) board.removeAt(x, y);
    }
}
