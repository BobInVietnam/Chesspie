package com.mygdx.game.chesspieces;

import com.mygdx.game.chessboard.ChessBoard;
import com.mygdx.game.skills.KingSkill;
import com.mygdx.game.statusfxs.StatusEffect;

public class King extends Piece{
    public King() {}
    public King(int x, int y) {
        super(x, y);
    }
    public King(int x, int y, String color) {
        super(x, y, color);
    }
    public King(int x, int y, String color, int maxHp, int baseAttack) {
        super(x, y, color, maxHp, baseAttack);
        this.chessSkill = new KingSkill(5);
    }
    public Piece clone() {
        King p = new King(posX, posY, color, maxHp, baseAttack);
        p.hp = hp;
        p.attack = attack;
        p.defense = defense;
        for (StatusEffect s: status) {
            p.status.add(s.clone());
        }
        return p;
    }

    public Character getSymbol() {
        return 'K';
    }

    @Override
    public boolean canMove(ChessBoard board, int x, int y) {
        if (board.invalidPosition(x, y)) {
            return false;
        }
        int x_pos = this.getPosX();
        int y_pos = this.getPosY();
        if (x < x_pos - 1 || x > x_pos + 1 || y < y_pos - 1 || y > y_pos + 1
            || (x == x_pos && y == y_pos)) {
            return false;
        } else {
            return (board.getAt(x, y) == null);
        }
    }

    @Override
    public boolean inBaseAtkRange(ChessBoard board, int x, int y) {
        if (board.invalidPosition(x, y)) {
            return false;
        }
        int x_pos = this.getPosX();
        int y_pos = this.getPosY();
        if (x < x_pos - 1 || x > x_pos + 1 || y < y_pos - 1 || y > y_pos + 1
            || (x == x_pos && y == y_pos)) {
            return false;
        } else {
            return (board.getAt(x, y) != null && !board.getAt(x, y).getColor().equals(this.getColor()));
        }
    }

    public void attack(ChessBoard board, Piece piece) {
        super.attack(board, piece);
        if (canKillwithBaseAtk(piece)) killPiece(piece);
        else {
            piece.getAttacked(this);
        }
    }
}
