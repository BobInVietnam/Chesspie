package com.mygdx.game.chesspieces;

import com.badlogic.gdx.utils.Array;
import com.mygdx.game.chessboard.ChessBoard;

import com.mygdx.game.skills.*;
import com.mygdx.game.statusfxs.StatusEffect;

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
    private boolean isAlive; // true means alive, false means dead
    private Array<StatusEffect> status;
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
    public Array<StatusEffect> getStatus() {
        return status;
    }

    public boolean isAlive() {
        return isAlive;
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

    public void setAlive(boolean alive) {
        this.isAlive = alive;
    }
    public void applyStatus(StatusEffect s) {
            status.add(s);
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
        this.status = new Array<>();
    }

    public abstract Character getSymbol();
    public abstract boolean canMove(ChessBoard board, int x, int y);
    public void move(int x, int y) {
        this.setPosX(x);
        this.setPosY(y);
    }
    public abstract boolean inBaseAtkRange(ChessBoard board, int x, int y);
    public boolean canUseSkillOn(ChessBoard board, int x, int y) {
        return chessSkill.canUseSkillOn(board, x, y, this);
    }
    public abstract boolean inSkillRange(ChessBoard board);
    public boolean inSkillRange(ChessBoard board, int x, int y) {
        return chessSkill.inSkillRange(board, x, y, this);
    }
    public boolean canKillwithBaseAtk(ChessBoard board, Piece piece) {
        return (this.attack - piece.defense >= piece.hp);
    }
    public abstract void attack(ChessBoard board, Piece piece); //attack piece
    public void activateSkill(ChessBoard board) {
        this.chessSkill.activateSkill(board, this);
    }
    public void activateTargetedSkill(ChessBoard board, int x, int y) {
        this.chessSkill.activateTargetedSkill(board, this, x, y);
    }
    public void getAttacked(Piece piece) {
        int damage = piece.attack - this.defense;
        this.setHp(this.hp - (Math.max(damage, 0)));
    }
    public void getSkillAttacked(Piece piece) {
        int damage = piece.getChessSkill().getSkillDmg() - this.defense;
        this.setHp(this.hp - (Math.max(damage, 0)));
    }
    public void killPiece(ChessBoard board, Piece piece) {
        piece.setAlive(false);
        board.removeAt(piece.posX, piece.posY);
        this.move(piece.posX, piece.posY);
    }
    public void moveAfterAttack(ChessBoard board, Piece piece) {}
}
