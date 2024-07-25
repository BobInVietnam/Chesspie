package com.mygdx.game.skills;

import com.mygdx.game.chessboard.ChessBoard;
import com.mygdx.game.chesspieces.*;

import java.util.ArrayList;

public abstract class Skill {
    int skillID;

    int skillDmg;

    SkillEffect skillEffect;
    public boolean isAura = false;

    public Skill() {};

    public int getSkillID() {return skillID;}

    public int getSkillDmg() {
        return skillDmg;
    }

    public void setSkillID(int skillID) {
        this.skillID = skillID;
    }

    public void setSkillDmg(int skillDmg) {
        this.skillDmg = skillDmg;
    }

    public abstract boolean canUseSkillOn(ChessBoard board, int x, int y, Piece piece);
    public abstract boolean inSkillRange(ChessBoard board, Piece piece);
    public abstract boolean inSkillRange(ChessBoard board, int x, int y, Piece piece);
    public abstract void setSkillEffect();
    public abstract void activateSkill(ArrayList<Piece> enemies, Piece piece);
}
