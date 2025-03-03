package com.mygdx.game.chesspieces;

import com.mygdx.game.chessboard.ChessBoard;
import com.mygdx.game.skills.BishopSkill;
import com.mygdx.game.statusfxs.StatusEffect;

public class Bishop extends Piece{
    public Bishop(int x, int y) {
        super(x, y);
    }
    public Bishop(int x, int y, String color) {
        super(x, y, color);
    }
    public Bishop(int x, int y, String color, int maxHp, int baseAttack) {
        super(x, y, color, maxHp, baseAttack);
        chessSkill = new BishopSkill(10);
    }
    public Piece clone() {
        Bishop p = new Bishop(posX, posY, color, maxHp, baseAttack);
        p.hp = hp;
        p.attack = attack;
        p.defense = defense;
        for (StatusEffect s: status) {
            p.status.add(s.clone());
        }
        return p;
    }

    public Character getSymbol() {
        return 'B';
    }

    public boolean canMove(ChessBoard board, int x, int y) {
        if (board.invalidPosition(x, y)) {
            return false;
        }
        int currentX = this.getPosX();
        int currentY = this.getPosY();
        if (Math.abs(x - currentX) != Math.abs(y - currentY)) {
            return false;
        }
        if (x > currentX) {
            for (int i = 1; i < y - currentY; i++) {
                if (board.getAt(currentX + i, currentY + i) != null) {
                    return false;
                }
            }
            for (int i = 1; i < currentY - y; i++) {
                if (board.getAt(currentX + i, currentY - i) != null) {
                    return false;
                }
            }
        }
        if (x < currentX) {
            for (int i = 1; i < y - currentY; i++) {
                if (board.getAt(currentX - i, currentY + i) != null) {
                    return false;
                }
            }
            for (int i = 1; i < currentY - y; i++) {
                if (board.getAt(currentX - i, currentY - i) != null) {
                    return false;
                }
            }
        }
        return (board.getAt(x, y) == null);
    }

    @Override
    public boolean inBaseAtkRange(ChessBoard board, int x, int y) {
        if (board.invalidPosition(x, y)) {
            return false;
        }
        int currentX = this.getPosX();
        int currentY = this.getPosY();
        if (Math.abs(x - currentX) != Math.abs(y - currentY)) {
            return false;
        }
        if (x > currentX) {
            for (int i = 1; i < y - currentY; i++) {
                if (board.getAt(currentX + i, currentY + i) != null) {
                    return false;
                }
            }
            for (int i = 1; i < currentY - y; i++) {
                if (board.getAt(currentX + i, currentY - i) != null) {
                    return false;
                }
            }
        }
        if (x < currentX) {
            for (int i = 1; i < y - currentY; i++) {
                if (board.getAt(currentX - i, currentY + i) != null) {
                    return false;
                }
            }
            for (int i = 1; i < currentY - y; i++) {
                if (board.getAt(currentX - i, currentY - i) != null) {
                    return false;
                }
            }
        }
        return (board.getAt(x, y) != null && !board.getAt(x, y).getColor().equals(this.getColor()));
    }

    @Override
    public void attack(ChessBoard board, Piece piece) {
        super.attack(board, piece);
        if (canKillwithBaseAtk(piece)) killPiece(piece);
        else {
            piece.getAttacked(this);
            moveAfterAttack(board, piece);
        }
    }

    @Override
    public void moveAfterAttack(ChessBoard board, Piece piece) {
        int x = piece.getPosX();
        int y = piece.getPosY();
        if (x > this.getPosX()) {
            if (y > this.getPosY())
                this.move(x - 1, y - 1);
            else this.move(x - 1, y + 1);
        } else {
            if (y > this.getPosY())
                this.move(x + 1, y - 1);
            else this.move(x + 1, y + 1);
        }
    }
}
