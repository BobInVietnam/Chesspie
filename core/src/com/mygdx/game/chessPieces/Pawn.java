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
            return y == this.getPosY() && x == this.getPosX()+1 && board.getAt(x, y) == null;
        }

        if(this.getColor().equals("black")) {
            return y == this.getPosY() && x == this.getPosX() - 1 && board.getAt(x, y) == null;
        }

        return false;
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
