package com.mygdx.game.chessPieces;

import com.mygdx.game.chessBoard.ChessBoard;

import java.util.Objects;

public abstract class Piece {
    private int posX;
    private int posY;
    private String color;

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public String getGetColor() {
        return color;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Piece(int x, int y) {
        this.posX = x;
        this.posY = y;
    }

    public Piece(int x, int y, String color) {
        this(x, y);
        if (color.equals("white") || color.equals("black")) {
            this.color = color;
        }
    }

//    public boolean checkPosition(Piece p) {
//        if ((p.getPosX() >= 1 && p.getPosX() <= 8)
//                && (p.getPosY() >= 1 && p.getPosY <= 8)) {
//            return true;
//        }
//        return false;
//    }

    public abstract String getSymbol();
    public abstract boolean canMove(ChessBoard board, int x, int y);
}
