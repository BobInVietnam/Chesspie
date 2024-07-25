package com.mygdx.game.chesspieces;

import com.mygdx.game.chessboard.ChessBoard;
import com.mygdx.game.skills.KingSkill;

import java.util.ArrayList;

public class King extends Piece{
    public King() {}
    public King(int x, int y) {
        super(x, y);
    }
    public King(int x, int y, String color) {
        super(x, y, color);
    }
    public King(int x, int y, String color, int maxHp, int baseAttack) {
        super(x, y, color, maxHp, baseAttack);
        this.chessSkill = new KingSkill();
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
        if (x < x_pos - 1 || x > x_pos + 1 || y < y_pos - 1 || y > y_pos + 1
            || (x == x_pos && y == y_pos)) {
            return false;
        } else {
            return (board.getAt(x, y) == null);
        }
    }

    @Override
    public boolean inBaseAtkRange(ChessBoard board, int x, int y) {
        if (!board.validate(x, y)) {
            return false;
        }
        int x_pos = this.getPosX();
        int y_pos = this.getPosY();
        if (x < x_pos - 1 || x > x_pos + 1 || y < y_pos - 1 || y > y_pos + 1
            || (x == x_pos && y == y_pos)) {
            return false;
        } else {
            return (board.getAt(x, y) != null && !board.getAt(x, y).getColor().equals(this.getColor()));
        }
    }

    @Override
    public boolean canUseSkillOn(ChessBoard board, int x, int y) {
        return chessSkill.canUseSkillOn(board, x, y, this);
    }

    @Override
    public boolean inSkillRange(ChessBoard board) {
        return this.getChessSkill().inSkillRange(board, this);
    }

    @Override
    public boolean inSkillRange(ChessBoard board, int x, int y) {
        return chessSkill.inSkillRange(board, x, y, this);
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
//        if(this.inSkillRange(board)) {
//            this.getChessSkill().activateSkill(pieces,  );
//        }
    }

    public void getSkillAttacked(Piece piece) {
        this.setHp(this.getHp() + this.getDefendShield() - piece.getChessSkill().getSkillDmg());
    }

    @Override
    public void killOtherPiecebySkill(ChessBoard board, int x, int y) {
        if(this.canKillwithSkill(board, x, y)) board.removeAt(x, y);
    }
}
