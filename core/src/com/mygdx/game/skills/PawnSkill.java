package com.mygdx.game.skills;

import com.mygdx.game.chessboard.ChessBoard;
import com.mygdx.game.chesspieces.*;

import java.util.ArrayList;

public class PawnSkill extends Skill{
    @Override
    public boolean canUseSkillOn(ChessBoard board, int x, int y, Piece piece) {
        return false;
    }

    @Override
    public boolean inSkillRange(ChessBoard board, Piece piece) {
        return false;
    }

    @Override
    public boolean inSkillRange(ChessBoard board, int x, int y, Piece piece) {
        return false;
    }

    @Override
    public void setSkillEffect() {
        super.skillEffect = SkillEffect.AttackSkill;
    }

    @Override
    public void activateSkill(ChessBoard board, Piece piece) {

    }

}
