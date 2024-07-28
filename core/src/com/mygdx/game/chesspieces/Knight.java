package com.mygdx.game.chesspieces;

import com.mygdx.game.chessboard.ChessBoard;
import com.mygdx.game.skills.KnightSkill;

public class Knight extends Piece {
    public Knight(int x, int y) {
        super(x, y);
    }
    public Knight(int x, int y, String color) {
        super(x, y, color);
    }
    public Knight(int x, int y, String color, int maxHp, int baseAttack) {
        super(x, y, color, maxHp, baseAttack);
        chessSkill = new KnightSkill(7);
    }

    public Character getSymbol() {
        return 'N';
    }

    public boolean canMove(ChessBoard board, int x, int y) {
        if (!board.validate(x, y)) {
            return false;
        }

        int x_pos = this.getPosX();
        int y_pos = this.getPosY();
        if (x == x_pos-1 && y== y_pos-2 && board.getAt(x, y) == null) return true;
        else if (x == x_pos+1 && y== y_pos-2 && board.getAt(x, y) == null) return true;
        else if (x == x_pos-1 && y== y_pos+2 && board.getAt(x, y) == null) return true;
        else if (x == x_pos+1 && y== y_pos+2 && board.getAt(x, y) == null) return true;
        else if (x == x_pos-2 && y== y_pos-1 && board.getAt(x, y) == null) return true;
        else if (x == x_pos-2 && y== y_pos+1 && board.getAt(x, y) == null) return true;
        else if (x == x_pos+2 && y== y_pos-1 && board.getAt(x, y) == null) return true;
        else return x == x_pos + 2 && y == y_pos + 1 && board.getAt(x, y) == null;
    }

    //Need to re-check this
    @Override
    public boolean inBaseAtkRange(ChessBoard board, int x, int y) {
        int x_pos = this.getPosX();
        int y_pos = this.getPosY();
        Piece enemy = board.getAt(x, y);
        if(enemy == null)  return false;

        if (!board.validate(x, y)) {
            return false;
        }

        if (x == x_pos-1 && y == y_pos-2 && !(enemy.getColor().equals(this.getColor()))) return true;
        else if (x == x_pos+1 && y== y_pos-2 && !(enemy.getColor().equals(this.getColor()))) return true;
        else if (x == x_pos-1 && y== y_pos+2 && !(enemy.getColor().equals(this.getColor()))) return true;
        else if (x == x_pos+1 && y== y_pos+2 && !(enemy.getColor().equals(this.getColor()))) return true;
        else if (x == x_pos-2 && y== y_pos-1 && !(enemy.getColor().equals(this.getColor()))) return true;
        else if (x == x_pos-2 && y== y_pos+1 && !(enemy.getColor().equals(this.getColor()))) return true;
        else if (x == x_pos+2 && y== y_pos-1 && !(enemy.getColor().equals(this.getColor()))) return true;
        else return x == x_pos + 2 && y == y_pos + 1 && !(enemy.getColor().equals(this.getColor()));
    }

    @Override
    public boolean inSkillRange(ChessBoard board) {
        return false;
    }

    public void attack(ChessBoard board, Piece piece) {
        if (canKillwithBaseAtk(board, piece)) killPiece(board, piece);
        else {
            piece.getAttacked(this);
        }
    }
}
