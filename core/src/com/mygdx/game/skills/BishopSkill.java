package com.mygdx.game.skills;

import com.mygdx.game.chessboard.ChessBoard;
import com.mygdx.game.chesspieces.*;

public class BishopSkill extends Skill{
    @Override
    public boolean inSkillRange(ChessBoard board, Piece king) {
        return false;
    }

    @Override
    public void setSkillEffect() {
        super.skillEffect = SkillEffect.AttackSkill;
    }
}
