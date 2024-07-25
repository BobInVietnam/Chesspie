package com.mygdx.game.chesspieces;

import com.mygdx.game.chessboard.ChessBoard;

import java.util.ArrayList;

public class Rook extends Piece {
    public Rook(int x, int y) {
        super(x, y);
    }
    public Rook(int x, int y, String color) {
        super(x, y, color);
    }

    public Rook(int x, int y, String color, int maxHp, int baseAttack) {
        super(x, y, color, maxHp, baseAttack);
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
    public boolean canUseSkillOn(ChessBoard board, int x, int y) {
        return false;
    }

    @Override
    public boolean inSkillRange(ChessBoard board) {
        return false;
    }

    @Override
    public boolean inSkillRange(ChessBoard board, int x, int y) {
        return false;
    }

    @Override
    public boolean canKillwithSkill(ChessBoard board, int x, int y) {
        return this.inSkillRange(board) && board.getAt(x, y).getHp() < this.getChessSkill().getSkillDmg() + this.getDefendShield();
    }

    @Override
    public void attack(ChessBoard board, Piece piece) {
        if (canKillwithBaseAtk(board, piece)) killPiece(board, piece);
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

    @Override
    public void activateSKill(ArrayList<Piece> pieces, ChessBoard board) {

    }

    public void getSkillAttacked(Piece piece) {
        this.setHp(this.getHp() + this.getDefendShield() - piece.getChessSkill().getSkillDmg());
    }

    @Override
    public void killOtherPiecebySkill(ChessBoard board, int x, int y) {
        if(this.canKillwithSkill(board, x, y)) board.removeAt(x, y);
    }
}
