package com.mygdx.game.chesspieces;

import com.mygdx.game.chessboard.ChessBoard;

import java.util.ArrayList;

public class Pawn extends Piece{
    private boolean firstMove;
    public Pawn(int x, int y) {
        super(x, y);
        firstMove = true;
    }
    public Pawn(int x, int y, String color) {
        super(x, y, color);
        firstMove = true;
    }

    public Pawn(int x, int y, String color, int maxHp, int baseAttack) {
        super(x, y, color, maxHp, baseAttack);
        firstMove = true;
    }

    public Character getSymbol() {
        return 'P';
    }

    @Override
    public boolean canMove(ChessBoard board, int x, int y) {
        if (!board.validate(x, y)) {
            return false;
        }

        if(this.getColor().equals("white")) {
            if (firstMove) {
                return (y == this.getPosY() + 1 || y == this.getPosY() + 2)
                    && x == this.getPosX() && board.getAt(x, y) == null;
            } else {
                return y == this.getPosY() + 1 && x == this.getPosX() && board.getAt(x, y) == null;
            }
        }

        if(this.getColor().equals("black")) {
            if (firstMove) {
                return (y == this.getPosY() - 1 || y == this.getPosY() - 2)
                    && x == this.getPosX() && board.getAt(x, y) == null;
            } else {
                return y == this.getPosY() - 1 && x == this.getPosX() && board.getAt(x, y) == null;
            }
        }

        return false;
    }

    @Override
    public void move(int x, int y) {
        super.move(x, y);
        if (firstMove) {
            firstMove = false;
        }
    }

    @Override
    public boolean inBaseAtkRange(ChessBoard board, int x, int y) {
        if (board.getAt(x, y) == null) return false;
        if(this.getColor().equals("white")){
            return ((x == this.getPosX()+1 || x == this.getPosX()-1) && y == this.getPosY()+1)
                    &&  board.getAt(x, y).getColor().equals("black");
        }

        if(this.getColor().equals("black")){
            return ((x == this.getPosX()+1 || x == this.getPosX()-1) && y == this.getPosY()-1)
                    && board.getAt(x, y).getColor().equals("white");
        }
        return false;
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

    @Override
    public void attack(ChessBoard board, Piece piece) {
        if (canKillwithBaseAtk(board, piece)) killPiece(board, piece);
        else {
            piece.getAttacked(this);
        }
    }

    @Override
    public void activateSKill(ArrayList<Piece> pieces, ChessBoard board) {

    }

    public void getSkillAttacked(Piece piece) {
        this.setHp(this.getHp() - piece.getChessSkill().getSkillDmg());
    }

    @Override
    public void killOtherPiecebySkill(ChessBoard board, int x, int y) {
        if(this.canKillwithSkill(board, x, y)) board.removeAt(x, y);
    }
}

