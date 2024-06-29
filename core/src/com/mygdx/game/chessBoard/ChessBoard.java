package com.mygdx.game.chessBoard;

import com.mygdx.game.chessPieces.Piece;

import java.util.ArrayList;

public class ChessBoard {
    public static final int WIDTH = 8;
    public static final int HEIGHT = 8;
    private ArrayList<Piece> pieces = new ArrayList<>();

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
        for (int i = 0; i < pieces.size(); i++) {
            if (pieces.get(i).getPosX() == x && pieces.get(i).getPosY() == y) {
                pieces.remove(i);
                i--;
            }
        }
    }
}
