package com.mygdx.game.skills;

import com.mygdx.game.chesspieces.*;

import java.util.ArrayList;

public class RookSkill extends Skill{
    @Override
    public boolean inSkillRange(ArrayList<Piece> pieces, Piece king) {
        return false;
    }

    @Override
    public void setSkillEffect() {
        super.skillEffect = SkillEffect.DefendSkill;
    }
}
