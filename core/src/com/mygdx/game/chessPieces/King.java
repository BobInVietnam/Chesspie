package com.mygdx.game.chessPieces;

import com.mygdx.game.chessBoard.ChessBoard;

public class King extends Piece{
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
    public boolean canKillwithBaseAtl(ChessBoard board, int x, int y) {
        for(int i=-1;i<=1;i++) {
            for(int j=-1;j<1;j++) {
                if(i != 0 || j != 0) {
                    int newRow = this.getPosX() + i;
                    int newCol = this.getPosY() + j;
                    if(newRow == x && newCol == y && board.getAt(x, y).getHp() < this.getHp()) return true;
                }
            }
        }

        return false;
    }

    @Override
    public boolean canKillwithSkill(ChessBoard board, int x, int y) {
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

    public void killOtherPiecebyAlt(ChessBoard board, int x, int y) {
        if(this.canKillwithBaseAtl(board, x, y)) board.removeAt(x, y);
    }

    @Override
    public void killOtherPiecebySkill(ChessBoard board, int x, int y) {
        if(this.canKillwithSkill(board, x, y)) board.removeAt(x, y);
    }
}
