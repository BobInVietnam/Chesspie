package com.mygdx.game;

import com.mygdx.game.chessPieces.Piece;

public class Move {
    private int startX;
    private int endX;
    private int startY;
    private int endY;
    private Piece movedPiece;
    private Piece killedPiece;
    private Piece attackingPiece;
    private Piece attackedPiece;


    public Move(int startX, int endX, int startY, int endY, Piece movedPiece) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.movedPiece = movedPiece;
    }

    public Move(int startX, int endX, int startY, int endY,
                Piece movedPiece, Piece killedPiece) {
        this(startX, endX, startY, endY, movedPiece);
        this.killedPiece = killedPiece;
    }

    public Move(int startX, int endX, int startY, int endY,
                Piece movedPiece, Piece killedPiece, Piece attackingPiece, Piece attackedPiece) {
        this(startX, endX, startY, endY, movedPiece, killedPiece);
        this.attackingPiece = attackingPiece;
        this.attackedPiece = attackedPiece;
    }

    public int getStartX() {
        return this.startX;
    }

    public int getEndX() {
        return this.endX;
    }

    public int getStartY() {
        return this.startY;
    }

    public int getEndY() {
        return this.endY;
    }

    public void setStartX(int x) {
        this.startX = x;
    }

    public void setEndX(int x) {
        this.endX = x;
    }

    public void setStartY(int y) {
        this.startY = y;
    }

    public void setEndY(int y) {
        this.endY = y;
    }

    public Piece getMovedPiece() {
        return this.movedPiece;
    }

    public Piece getKilledPiece() {
        return this.killedPiece;
    }

    public void setMovedPiece(Piece piece) {
        this.movedPiece = piece;
    }

    public void setKilledPiece(Piece piece) {
        this.killedPiece = piece;
    }

    public Piece getAttackingPiece() {
        return this.attackingPiece;
    }

    public Piece getAttackedPiece() {
        return this.attackedPiece;
    }

    public void setAttackingPiece(Piece attackingPiece) {
        this.attackingPiece = attackingPiece;
    }

    public void setAttackedPiece(Piece attackedPiece) {
        this.attackedPiece = attackedPiece;
    }
}
