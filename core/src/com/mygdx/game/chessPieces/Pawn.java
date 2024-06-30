package com.mygdx.game.chessPieces;

import com.mygdx.game.chessBoard.ChessBoard;

public class Pawn extends Piece{
    public Pawn(int x, int y) {
        super(x, y);
    }

    public Pawn(int x, int y, String color) {
        super(x, y, color);
    }

    public Character getSymbol() {
        return 'P';
    }

    @Override
    public boolean canMove(ChessBoard board, int x, int y) {
        if(this.getColor().equals("white")) {
            return y == this.getPosY() && x == this.getPosX() + 1 && board.getAt(x, y) == null;
        }

        if(this.getColor().equals("black")) {
            return y == this.getPosY() && x == this.getPosX() - 1 && board.getAt(x, y) == null;
        }

        return false;
    }

    @Override
    public boolean inBaseAtkRange(ChessBoard board, int x, int y) {
        if(this.getColor().equals("white")){
            if((x == this.getPosX()+1) || (x==this.getPosX()-1)
                    && y == this.getPosY() + 1 && board.getAt(x, y).getColor().equals("black")) {
                return true;
            }
        }

        if(this.getColor().equals("black")){
            return (x == this.getPosX() + 1) || (x == this.getPosX() - 1)
                    && y == this.getPosY() - 1 && board.getAt(x, y).getColor().equals("white");
        }
        return false;
    }

    @Override
    public boolean inSkillRange(ChessBoard board, int x, int y) {
        return false;
    }

    @Override
    public boolean canKillwithBaseAtk(ChessBoard board, int x, int y) {
        return this.inBaseAtkRange(board, x, y) && board.getAt(x, y).getHp() < this.getBaseAttack();
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
    public void activateSKill() {

    }

    public void getAttacked(Piece piece) {
        this.setHp(this.getHp() - piece.getBaseAttack());
    }

    public void getSkillAttacked(Piece piece) {
        this.setHp(this.getHp() - piece.getChessSkill().getSkillDmg());
    }

    public void killOtherPiecebyBaseAtk(ChessBoard board, int x, int y) {
        if(this.canKillwithBaseAtk(board, x, y)) board.removeAt(x, y);
    }

    @Override
    public void killOtherPiecebySkill(ChessBoard board, int x, int y) {
        if(this.canKillwithSkill(board, x, y)) board.removeAt(x, y);
    }
}

