package com.mygdx.game.skills;

import com.mygdx.game.chessboard.ChessBoard;
import com.mygdx.game.chesspieces.*;

import java.util.ArrayList;

public class RookSkill extends Skill{
    @Override
    public boolean inSkillRange(ChessBoard board, Piece piece) {
        return false;
    }

    @Override
    public void setSkillEffect() {
        super.skillEffect = SkillEffect.DefendSkill;
    }

    @Override
    public void activateSkill(ArrayList<Piece> pieces) {

    }
}
