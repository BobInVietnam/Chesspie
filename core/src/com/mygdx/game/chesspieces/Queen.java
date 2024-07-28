package com.mygdx.game.chesspieces;

import com.mygdx.game.chessboard.ChessBoard;
import com.mygdx.game.skills.QueenSkill;

public class Queen extends Piece{
    public Queen(int x, int y) {
        super(x, y);
    }
    public Queen(int x, int y, String color) {
        super(x, y, color);
    }
    public Queen(int x, int y, String color, int maxHp, int baseAttack) {
        super(x, y, color, maxHp, baseAttack);
        chessSkill = new QueenSkill(8);
    }

    public Character getSymbol() {
        return 'Q';
    }

    @Override
    public boolean canMove(ChessBoard board, int x, int y) {
        if (!board.validate(x, y)) {
            return false;
        }
        int currentX = this.getPosX();
        int currentY = this.getPosY();
        if (Math.abs(x - currentX) != Math.abs(y - currentY) && x != currentX && y != currentY) {
            return false;
        }
        if (x > currentX) {
            if(y > currentY) {
                for (int i = 1; i < y - currentY; i++) {
                    if (board.getAt(currentX + i, currentY + i) != null) {
                        return false;
                    }
                }
            }
            else if(y < currentY) {
                for (int i = 1; i < currentY - y; i++) {
                    if (board.getAt(currentX + i, currentY - i) != null) {
                        return false;
                    }
                }
            }
            else {
                for (int i = this.getPosX() + 1; i < x; i++) {
                    if (board.getAt(i, y) != null) {
                        return false;
                    }
                }
            }
        }
        else if (x < currentX) {
            if (y > currentY) {
                for (int i = 1; i < y - currentY; i++) {
                    if (board.getAt(currentX - i, currentY + i) != null) {
                        return false;
                    }
                }
            }
            else if(y < currentY) {
                for (int i = 1; i < currentY - y; i++) {
                    if (board.getAt(currentX - i, currentY - i) != null) {
                        return false;
                    }
                }
            }
            else {
                for (int i = this.getPosX() - 1; i > x; i--) {
                    if (board.getAt(i, y) != null) {
                        return false;
                    }
                }
            }
        }
        else {
            if (y > this.getPosY()) {
                for (int i = this.getPosY() + 1; i < y; i++) {
                    if (board.getAt(x, i) != null) {
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
        }

        return (board.getAt(x, y) == null);
    }

    @Override
    public boolean inBaseAtkRange(ChessBoard board, int x, int y) {
        if (!board.validate(x, y)) {
            return false;
        }
        int currentX = this.getPosX();
        int currentY = this.getPosY();
        if (Math.abs(x - currentX) != Math.abs(y - currentY) && x != currentX && y != currentY) {
            return false;
        }
        if (x > currentX) {
            if(y > currentY) {
                for (int i = 1; i < y - currentY; i++) {
                    if (board.getAt(currentX + i, currentY + i) != null) {
                        return false;
                    }
                }
            }
            else if(y < currentY) {
                for (int i = 1; i < currentY - y; i++) {
                    if (board.getAt(currentX + i, currentY - i) != null) {
                        return false;
                    }
                }
            }
            else {
                for (int i = this.getPosX() + 1; i < x; i++) {
                    if (board.getAt(i, y) != null) {
                        return false;
                    }
                }
            }

        }
        else if (x < currentX) {
            if (y > currentY) {
                for (int i = 1; i < y - currentY; i++) {
                    if (board.getAt(currentX - i, currentY + i) != null) {
                        return false;
                    }
                }
            }
            else if(y < currentY) {
                for (int i = 1; i < currentY - y; i++) {
                    if (board.getAt(currentX - i, currentY - i) != null) {
                        return false;
                    }
                }
            }
            else {
                for (int i = this.getPosX() - 1; i > x; i--) {
                    if (board.getAt(i, y) != null) {
                        return false;
                    }
                }
            }
        }
        else {
            if (y > this.getPosY()) {
                for (int i = this.getPosY() + 1; i < y; i++) {
                    if (board.getAt(x, i) != null) {
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
        }
        return (board.getAt(x, y) != null && !board.getAt(x, y).getColor().equals(this.getColor()));
    }

    @Override
    public boolean inSkillRange(ChessBoard board) {
        return false;
    }

    @Override
    public void attack(ChessBoard board, Piece piece) {
        if (canKillwithBaseAtk(board, piece)) killPiece(board, piece);
        else {
            piece.getAttacked(this);
            moveAfterAttack(board, piece);
        }
    }
    public void moveAfterAttack(ChessBoard board, Piece piece) {
        int x = piece.getPosX();
        int y = piece.getPosY();
        if (x == this.getPosX()) {
            if (y > this.getPosY())
                this.move(x, y - 1);
            else this.move(x, y + 1);
        } else {
            if (y == this.getPosY()) {
                if (x > this.getPosX())
                    this.move(x - 1, y);
                else this.move(x + 1, y);
            } else {
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
    }
}
