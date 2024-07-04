package com.mygdx.game.skills;

import com.mygdx.game.chessboard.ChessBoard;
import com.mygdx.game.chesspieces.*;

import java.util.ArrayList;

public class KingSkill extends Skill{
    public KingSkill() {};
    @Override
    public boolean inSkillRange(ChessBoard board, Piece king) {
        int cnt = 0;
        for(int j=-1;j<=1;j++) {
            for(int k=-1;k<=1;k++) {
                if(k != 0 || j != 0) {
                    int newX = king.getPosX() + j;
                    int newY = king.getPosY() + k;
                    if(board.getAt(newX, newY) != null
                            && board.getAt(newX, newY).getColor().equals(king.getColor())) {
                        cnt++;
                    }
                }
            }
        }

        return cnt > 0;
    }

    @Override
    public void setSkillEffect() {
        super.skillEffect = SkillEffect.BuffSkill;
    }

    @Override
    public void activateSkill(ArrayList<Piece> pieces) {
        for (Piece piece : pieces) {
            piece.setDefendShield(5);
        }
    }
}
