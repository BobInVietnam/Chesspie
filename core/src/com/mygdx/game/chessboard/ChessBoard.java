package com.mygdx.game.chessboard;

import com.badlogic.gdx.utils.Array;
import com.mygdx.game.chesspieces.Piece;
import com.mygdx.game.statusfxs.StatusEffect;

public class ChessBoard {
    public static final int WIDTH = 8;
    public static final int HEIGHT = 8;
    public Array<Piece> pieces = new Array<>();

    private int boardID;

    public ChessBoard(){}

    public int getBoardID() {
        return boardID;
    }

    public void setBoardID(int boardID) {
        this.boardID = boardID;
    }

    public boolean validate(int x, int y) {
        if ((x >= 1 && x <= 8) && (y >= 1 && y <= 8)) {
            return true;
        }
        return false;
    }

    public Piece getAt(int x, int y) {
        Piece rand = null;
        for (Piece piece : pieces) {
            if (piece.getPosX() == x && piece.getPosY() == y) {
                rand = piece;
            }
        }
        return rand;
    }

    public void removeAt(int x, int y) {
        for (int i = 0; i < pieces.size; i++) {
            if (pieces.get(i).getPosX() == x && pieces.get(i).getPosY() == y) {
                pieces.removeIndex(i);
                i--;
            }
        }
    }
    public void refresh(boolean whiteTurn) {
        for (Piece p: pieces) {
            if (p.getHp() <= 0) {
                pieces.removeValue(p, false);
            }
             if (p.getColor().equals("white") == whiteTurn)
                 activateStatusEffects(p);
        }
    }
    private void activateStatusEffects(Piece p) {
        p.setAttack(p.getBaseAttack());
        p.setDefense(p.getBaseDefense());
        for (StatusEffect s: p.getStatus()) {
            s.activate(p);
            if (s.duration == 0) {
                p.getStatus().removeValue(s, false);
            }
        }
    }
}
