package com.mygdx.game.chesspieces;

import com.mygdx.game.chessboard.ChessBoard;

import com.mygdx.game.skills.*;

public abstract class Piece {
    private int posX;
    private int posY;
    private String color;
    private int hp;
    private int maxHp;
    private int baseAttack;
    private int attack;
    private int baseDefense = 0;
    private int defense;
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
    public int getMaxHp() {return maxHp;}

    public int getBaseAttack() {return baseAttack;}
    public int getAttack() {
        return attack;
    }
    public int getBaseDefense() {
        return baseDefense;
    }

    public int getDefense() {
        return defense;
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

    public void setAttack(int attack) {
        this.attack = attack;
    }
    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void setChessSkill(Skill chessSkill) {
        this.chessSkill = chessSkill;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Piece() {}

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

    public Piece(int x, int y, String color, int maxHp, int baseAttack) {
        this(x, y, color);
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.baseAttack = baseAttack;
        this.attack = baseAttack;
        this.baseDefense = 0;
        this.defense = 0;
    }

    public abstract Character getSymbol();
    public abstract boolean canMove(ChessBoard board, int x, int y);
    public void move(int x, int y) {
        this.setPosX(x);
        this.setPosY(y);
    }
    public abstract boolean inBaseAtkRange(ChessBoard board, int x, int y);
    public abstract boolean canUseSkillOn(ChessBoard board, int x, int y);
    public abstract boolean inSkillRange(ChessBoard board);
    public abstract boolean inSkillRange(ChessBoard board, int x, int y);
    public boolean canKillwithBaseAtk(ChessBoard board, Piece piece) {
        return (this.baseAttack - piece.baseDefense >= piece.hp);
    }
    public abstract boolean canKillwithSkill(ChessBoard board, int x, int y);
    public abstract void attack(ChessBoard board, Piece piece); //attack piece
    public void activateSkill(ChessBoard board) {
        this.chessSkill.activateSkill(board, this);
    }
    public void getAttacked(Piece piece) {
        int damage = piece.baseAttack - this.baseDefense;
        this.setHp(this.hp - (Math.max(damage, 0)));
    }
    public abstract void getSkillAttacked(Piece piece);
    public void killPiece(ChessBoard board, Piece piece) {
        piece.setStatus(false);
        board.removeAt(piece.posX, piece.posY);
        this.move(piece.posX, piece.posY);
    }
    public void moveAfterAttack(ChessBoard board, Piece piece) {}
    public abstract void killOtherPiecebySkill(ChessBoard board, int x, int y);
}
