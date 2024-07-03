package com.mygdx.game.skills;

import com.mygdx.game.chessboard.ChessBoard;
import com.mygdx.game.chesspieces.*;

import java.util.ArrayList;

public class KingSkill extends Skill{
    @Override
    public boolean inSkillRange(ArrayList<Piece> pieces, Piece king) {
        int cnt = 0;
        for(int i=0;i<=pieces.size();i++) {
            for(int j=-1;j<=1;j++) {
                for(int k=-1;k<=1;k++) {
                    if(k != 0 || j != 0) {
                        int newX = king.getPosX() + j;
                        int newY = king.getPosY() + k;
                        if(pieces.get(i).getPosX() == newX && pieces.get(i).getPosY() == newY
                                && pieces.get(i).getColor().equals(king.getColor())) {
                            cnt++;
                        }
                    }
                }
            }
        }

        return cnt == pieces.size();
    }

    @Override
    public void setSkillEffect() {
        super.skillEffect = SkillEffect.BuffSkill;
    }
}
