package com.mygdx.game.chessPieces;

import com.mygdx.game.chessBoard.ChessBoard;

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

    public abstract boolean canMove(ChessBoard board, int x, int y);
}
