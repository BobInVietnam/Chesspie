package com.mygdx.game.chesspieces;

import com.mygdx.game.chessboard.ChessBoard;
import com.mygdx.game.skills.RookSkill;
import com.mygdx.game.statusfxs.StatusEffect;

public class Rook extends Piece {
    public Rook(int x, int y) {
        super(x, y);
    }
    public Rook(int x, int y, String color) {
        super(x, y, color);
    }

    public Rook(int x, int y, String color, int maxHp, int baseAttack) {
        super(x, y, color, maxHp, baseAttack);
        chessSkill = new RookSkill(8);
    }
    public Piece clone() {
        Rook p = new Rook(posX, posY, color, maxHp, baseAttack);
        p.hp = hp;
        p.attack = attack;
        p.defense = defense;
        for (StatusEffect s: status) {
            p.status.add(s.clone());
        }
        return p;
    }

    public Character getSymbol() {
        return 'R';
    }
    public boolean canMove(ChessBoard board, int x, int y) {
        if (!board.validate(x, y)) {
            return false;
        }
        if (x != this.getPosX() && y != this.getPosY()) return false;

        if (x > this.getPosX()) {
            for (int i = this.getPosX() + 1; i < x; i++) {
                if (board.getAt(i, y) != null) {
                    return false;
                }
            }
        }

        if (y > this.getPosY()) {
            for (int i = this.getPosY() + 1; i < y; i++) {
                if (board.getAt(x, i) != null) {
                    return false;
                }
            }
        }

        if (x < this.getPosX()) {
            for (int i = this.getPosX() - 1; i > x; i--) {
                if (board.getAt(i, y) != null) {
                    return false;
                }
            }
        }

        if (y < this.getPosY()) {
            for (int i = this.getPosY() - 1; i > y; i--) {
                if (board.getAt(x, i) != null) {
                    return false;
                }
            }
        }

        return (board.getAt(x, y) == null);
    }

    @Override
    public boolean inBaseAtkRange(ChessBoard board, int x, int y) {
        if (!board.validate(x, y)) {
            return false;
        }
        if (x != this.getPosX() && y != this.getPosY()) return false;

        if (x > this.getPosX()) {
            for (int i = this.getPosX() + 1; i < x; i++) {
                if (board.getAt(i, y) != null) {
                    return false;
                }
            }
        }
        if (y > this.getPosY()) {
            for (int i = this.getPosY() + 1; i < y; i++) {
                if (board.getAt(x, i) != null) {
                    return false;
                }
            }
        }
        if (x < this.getPosX()) {
            for (int i = this.getPosX() - 1; i > x; i--) {
                if (board.getAt(i, y) != null) {
                    return false;
                }
            }
        }
        if (y < this.getPosY()) {
            for (int i = this.getPosY() - 1; i > y; i--) {
                if (board.getAt(x, i) != null) {
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
        if (x == this.getPosX()) {
            if (y > this.getPosY())
                this.move(x, y - 1);
            else this.move(x, y + 1);
        } else {
            if (x > this.getPosX())
                this.move(x - 1, y);
            else this.move(x + 1, y);
        }
    }
}
