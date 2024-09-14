package com.mygdx.game.chessboard;

import com.badlogic.gdx.utils.Array;
import com.mygdx.game.chesspieces.King;
import com.mygdx.game.chesspieces.Piece;
import com.mygdx.game.statusfxs.StatusEffect;

public class ChessBoard {
    public enum State {
        START,
        PLAYING,
        BLACK_WON,
        WHITE_WON
    }
    public static final int WIDTH = 8;
    public static final int HEIGHT = 8;
    public Array<Piece> pieces;
    public State boardState;
    private int boardID;

    public ChessBoard(int id){
        boardState = State.START;
        boardID = id;
        pieces = new Array<>();
    }

    public ChessBoard clone() {
        ChessBoard c = new ChessBoard(boardID);
        for (Piece p: pieces) {
            c.pieces.add(p.clone());
        }
        c.boardState = boardState;
        c.boardID = boardID + 1;
        return c;
    }

    public int getBoardID() {
        return boardID;
    }

    public boolean validate(int x, int y) {
      return (x >= 1 && x <= 8) && (y >= 1 && y <= 8);
    }

    public Piece getAt(int x, int y) {
        Piece rand = null;
        int l = pieces.size;
        for (int i = 0; i < l; i++) {
            Piece piece = pieces.get(i);
            if (piece.getPosX() == x && piece.getPosY() == y) {
                rand = piece;
            }
        }
        return rand;
    }

    public void refresh(boolean whiteTurn) {
        for (Piece p: pieces) {
            checkDead(p);
            if (p.getColor().equals("white") == whiteTurn)
             activateStatusEffects(p);
            checkDead(p);
        }
    }
    private void checkDead(Piece p) {
        if (p.getHp() <= 0) {
            if (p.getClass().equals(King.class)) {
                if (p.getColor().equals("white")){
                    boardState = State.BLACK_WON;
                } else {
                    boardState = State.WHITE_WON;
                }
            }
            pieces.removeValue(p, false);
        }
    }
    private void activateStatusEffects(Piece p) {
        p.setAttack(p.getBaseAttack());
        p.setDefense(p.getBaseDefense());
        for (StatusEffect s: p.getStatus()) {
            s.activate(p);
            if (s.getDuration() == 0) {
                p.getStatus().removeValue(s, false);
            }
        }
    }
}
