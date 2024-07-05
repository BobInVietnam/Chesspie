package com.mygdx.game.skills;

import com.mygdx.game.chessboard.ChessBoard;
import com.mygdx.game.chesspieces.*;

import java.util.ArrayList;

public class KnightSkill extends Skill{
    @Override
    public boolean inSkillRange(ChessBoard board, Piece piece) {
        int cnt = 0;
        for(int i=-1;i<=1;i++) {
            for(int j=-1;j<1;j++) {
                if(i != 0 || j != 0) {
                    int newX = piece.getPosX() + i;
                    int newY = piece.getPosY() + j;
                    if(board.getAt(newX, newY) != null
                            && !(board.getAt(newX, newY).getColor().equals(piece.getColor()))) {
                        cnt++;
                    }
                }
            }
        }

        return cnt > 0;
    }

    @Override
    public void setSkillEffect() {
        super.skillEffect = SkillEffect.AttackSkill;
    }

    @Override
    public void activateSkill(ArrayList<Piece> pieces) {
//        for (Piece piece : pieces) {
//            piece.getAttacked();
//        }
    }
}
