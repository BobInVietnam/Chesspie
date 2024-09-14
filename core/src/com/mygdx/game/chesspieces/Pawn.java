package com.mygdx.game.chesspieces;

import com.mygdx.game.chessboard.ChessBoard;
import com.mygdx.game.skills.PawnSkill;
import com.mygdx.game.statusfxs.StatusEffect;

public class Pawn extends Piece{
    private boolean firstMove;
    public Pawn(int x, int y) {
        super(x, y);
        firstMove = true;
    }
    public Pawn(int x, int y, String color) {
        super(x, y, color);
        firstMove = true;
    }

    public Pawn(int x, int y, String color, int maxHp, int baseAttack) {
        super(x, y, color, maxHp, baseAttack);
        chessSkill = new PawnSkill(5);
        firstMove = true;
    }

    public Piece clone() {
        Pawn p = new Pawn(posX, posY, color, maxHp, baseAttack);
        p.firstMove = firstMove;
        p.hp = hp;
        p.attack = attack;
        p.defense = defense;
        for (StatusEffect s: status) {
            p.status.add(s.clone());
        }
        return p;
    }

    public Character getSymbol() {
        return 'P';
    }

    @Override
    public boolean canMove(ChessBoard board, int x, int y) {
        if (!board.validate(x, y)) {
            return false;
        }

        if(this.getColor().equals("white")) {
            if (firstMove) {
                return (y == this.getPosY() + 1 || y == this.getPosY() + 2)
                    && x == this.getPosX() && board.getAt(x, y) == null;
            } else {
                return y == this.getPosY() + 1 && x == this.getPosX() && board.getAt(x, y) == null;
            }
        }

        if(this.getColor().equals("black")) {
            if (firstMove) {
                return (y == this.getPosY() - 1 || y == this.getPosY() - 2)
                    && x == this.getPosX() && board.getAt(x, y) == null;
            } else {
                return y == this.getPosY() - 1 && x == this.getPosX() && board.getAt(x, y) == null;
            }
        }

        return false;
    }

    @Override
    public void move(int x, int y) {
        super.move(x, y);
        if (firstMove) {
            firstMove = false;
        }
    }

    @Override
    public boolean inBaseAtkRange(ChessBoard board, int x, int y) {
        if (board.getAt(x, y) == null) return false;
        if(this.getColor().equals("white")){
            return ((x == this.getPosX()+1 || x == this.getPosX()-1) && y == this.getPosY()+1)
                    &&  board.getAt(x, y).getColor().equals("black");
        }

        if(this.getColor().equals("black")){
            return ((x == this.getPosX()+1 || x == this.getPosX()-1) && y == this.getPosY()-1)
                    && board.getAt(x, y).getColor().equals("white");
        }
        return false;
    }

    @Override
    public void attack(ChessBoard board, Piece piece) {
        super.attack(board, piece);
        if (firstMove) {
            firstMove = false;
        }
        if (canKillwithBaseAtk(piece)) killPiece(piece);
        else {
            piece.getAttacked(this);
        }
    }
}

