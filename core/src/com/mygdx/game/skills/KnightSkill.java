package com.mygdx.game.skills;

import com.mygdx.game.chesspieces.*;

import java.util.ArrayList;

public class KnightSkill extends Skill{
    @Override
    public boolean inSkillRange(ArrayList<Piece> pieces, Piece king) {
        return false;
    }

    @Override
    public void setSkillEffect() {
        super.skillEffect = SkillEffect.AttackSkill;
    }
}
