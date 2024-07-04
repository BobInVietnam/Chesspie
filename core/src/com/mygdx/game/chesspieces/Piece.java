package com.mygdx.game.chesspieces;

import com.mygdx.game.chessboard.ChessBoard;

import com.mygdx.game.skills.*;

import java.util.ArrayList;

public abstract class Piece {
    private int posX;
    private int posY;
    private String color;
    private int hp;
    private int baseAttack;
    private int defendShield = 0;
    private boolean status; // true means alive, false means dead
    Skill chessSkill;

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

    public int getDefendShield() {
        return defendShield;
    }

    public boolean isStatus() {
        return status;
    }

    public Skill getChessSkill() {
        return chessSkill;
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

    public void setDefendShield(int defendShield) {
        this.defendShield = defendShield;
    }

    public void setChessSkill(Skill chessSkill) {
        this.chessSkill = chessSkill;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Piece() {};

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

    public boolean checkPosition(Piece p) {
        if ((p.getPosX() >= 1 && p.getPosX() <= 8)
                && (p.getPosY() >= 1 && p.getPosY() <= 8)) {
            return true;
        }
        return false;
    }

    public abstract Character getSymbol();
    public abstract boolean canMove(ChessBoard board, int x, int y);
    public void move(int x, int y) {
        this.setPosX(x);
        this.setPosY(y);
    }
    public abstract boolean inBaseAtkRange(ChessBoard board, int x, int y);
    public abstract boolean inSkillRange(ChessBoard board);
    public abstract boolean canKillwithBaseAtk(ChessBoard board, int x, int y);
    public abstract boolean canKillwithSkill(ChessBoard board, int x, int y);
    public abstract void attack(ChessBoard board, Piece piece); //attack piece
    public abstract void activateSKill(ArrayList<Piece> pieces, ChessBoard board);
    public abstract void getAttacked(Piece piece); //get attacked from piece
    public abstract void getSkillAttacked(Piece piece);
    public abstract void killOtherPiecebyBaseAtk(ChessBoard board, int x, int y);
    public abstract void killOtherPiecebySkill(ChessBoard board, int x, int y);
}
