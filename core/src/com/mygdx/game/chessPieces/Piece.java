package com.mygdx.game.chessPieces;

import com.mygdx.game.chessBoard.ChessBoard;

import java.util.Objects;

public abstract class Piece {
    private int posX;
    private int posY;
    private String color;
    private int hp;
    private int baseAttack;
    private boolean status; // true means alive, false means dead

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public String getColor() {
        return color;
    }

    public int getHp() {return hp;}

    public int getBaseAttack() {return baseAttack;}

    public boolean isStatus() {
        return status;
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

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setBaseAttack(int baseAttack) {
        this.baseAttack = baseAttack;
    }

    public void setStatus(boolean status) {
        this.status = status;
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
//                && (p.getPosY() >= 1 && p.getPosY() <= 8)) {
//            return true;
//        }
//        return false;
//    }

    public abstract String getSymbol();
    public abstract boolean canMove(ChessBoard board, int x, int y);
//    public abstract boolean canKill(ChessBoard board, int x, int y);
    public abstract void attack(Piece piece); //attack piece
    public abstract void getAttacked(Piece piece); //get attacked from piece
}
