package com.mygdx.game.skills;

import com.mygdx.game.chessboard.ChessBoard;
import com.mygdx.game.chesspieces.*;

import java.util.ArrayList;

public class KingSkill extends Skill{
    public KingSkill() {}

    @Override
    public boolean canUseSkillOn(ChessBoard board, int x, int y, Piece piece) {
        int posX = piece.getPosX();
        int posY = piece.getPosY();
        return !(x > posX + 1 || x < posX - 1 || y > posY + 1 || y < posY - 1
            || (x != posX && y!= posY));
    }

    ;
    @Override
    public boolean inSkillRange(ChessBoard board, Piece piece) {
        int cnt = 0;
        for(int j=-1;j<=1;j++) {
            for(int k=-1;k<=1;k++) {
                if(k != 0 || j != 0) {
                    int newX = piece.getPosX() + j;
                    int newY = piece.getPosY() + k;
                    if(board.getAt(newX, newY) != null
                            && board.getAt(newX, newY).getColor().equals(piece.getColor())) {
                        cnt++;
                    }
                }
            }
        }

        return cnt > 0;
    }

    @Override
    public boolean inSkillRange(ChessBoard board, int x, int y, Piece piece) {
        return canUseSkillOn(board, x, y, piece)
            && (board.getAt(x, y).getColor().equals(piece.getColor()));
    }

    @Override
    public void setSkillEffect() {
        super.skillEffect = SkillEffect.BuffSkill;
    }

    @Override
    public void activateSkill(ArrayList<Piece> enemies, Piece piece) {
//        for (Piece piece : enemies) {
//            piece.setDefendShield(5);
//        }
    }
}
