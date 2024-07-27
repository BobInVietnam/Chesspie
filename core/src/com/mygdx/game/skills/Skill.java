package com.mygdx.game.skills;

import com.mygdx.game.chessboard.ChessBoard;
import com.mygdx.game.chesspieces.*;

public abstract class Skill {
    int skillID;

    int skillDmg;

    protected SkillEffect skillEffect;
    protected SkillActivation skillActivation;

    public Skill() {};

    public int getSkillID() {return skillID;}

    public int getSkillDmg() {
        return skillDmg;
    }
    public SkillActivation getSkillActivation() { return skillActivation; }

    public void setSkillDmg(int skillDmg) {
        this.skillDmg = skillDmg;
    }

    public abstract boolean canUseSkillOn(ChessBoard board, int x, int y, Piece piece);
    public abstract boolean inSkillRange(ChessBoard board, Piece piece);
    public abstract boolean inSkillRange(ChessBoard board, int x, int y, Piece piece);
    public abstract void setSkillEffect();
    public abstract void activateSkill(ChessBoard board, Piece piece);
}
