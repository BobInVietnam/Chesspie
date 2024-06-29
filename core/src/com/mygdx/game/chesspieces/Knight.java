package com.mygdx.game.chesspieces;

import com.mygdx.game.chessboard.ChessBoard;

public class Knight extends Piece {
    public Knight(int x, int y) {
        super(x, y);
    }

    public Knight(int x, int y, String color) {
        super(x, y, color);
    }

    public Character getSymbol() {
        return 'N';
    }

    public boolean canMove(ChessBoard board, int x, int y) {
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

    @Override
    public boolean canKill(ChessBoard board, int x, int y) {
        int x_pos = this.getPosX();
        int y_pos = this.getPosY();
        Piece enemy = board.getAt(x, y);
        if(enemy == null)  return false;

        if(!enemy.getColor().equals(this.getColor()) && enemy.getHp() < this.getBaseAttack()) {
            if (x == x_pos-1 && y == y_pos-2) return true;
            else if (x == x_pos+1 && y== y_pos-2) return true;
            else if (x == x_pos-1 && y== y_pos+2) return true;
            else if (x == x_pos+1 && y== y_pos+2) return true;
            else if (x == x_pos-2 && y== y_pos-1) return true;
            else if (x == x_pos-2 && y== y_pos+1) return true;
            else if (x == x_pos+2 && y== y_pos-1) return true;
            else return x == x_pos + 2 && y == y_pos + 1;
        }

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

    @Override
    public void getSkillAttacked(Piece piece) {

    }
}
