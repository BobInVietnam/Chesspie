package com.mygdx.game.skills;

import com.mygdx.game.chessboard.ChessBoard;
import com.mygdx.game.chesspieces.*;
import com.mygdx.game.statusfxs.Protection;
import com.mygdx.game.statusfxs.StatusEffect;

import java.util.ArrayList;

public class RookSkill extends Skill{
    public RookSkill(int shield) {
        skillID = 5;
        skillActivation = SkillActivation.TRIGGER;
        skillDmg = shield;
        setSkillDetails(skillID);
    }
    @Override
    public boolean canUseSkillOn(ChessBoard board, int x, int y, Piece piece) {
        return false;
    }

    @Override
    public boolean inSkillRange(ChessBoard board, int x, int y, Piece piece) {
        return false;
    }

    @Override
    public void setSkillEffect() {
        super.skillEffect = SkillEffect.DefendSkill;
    }

    @Override
    public void activateSkill(ChessBoard board, Piece piece) {
        StatusEffect.apply(piece, StatusEffect.Effect.PROTECTION, 3, skillDmg);
    }

}
