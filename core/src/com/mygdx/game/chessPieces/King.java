package com.mygdx.game.chessPieces;

import com.mygdx.game.chessBoard.ChessBoard;

public class King extends Piece{
    public King(int x, int y) {
        super(x, y);
    }

    public King(int x, int y, String color) {
        super(x, y, color);
    }

    public String getSymbol() {
        return "K";
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
    public boolean canKill(ChessBoard board, int x, int y) {
        return false;
    }

    public void attack(Piece piece) {

    }

    public void getAttacked(Piece piece) {
        this.setHp(this.getHp() - piece.getBaseAttack());
    }

}
