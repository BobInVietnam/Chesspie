package com.mygdx.game.skills;

import com.mygdx.game.chesspieces.*;

import java.util.ArrayList;

public abstract class Skill {
    int skillID;

    int skillDmg;

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

    public abstract boolean inRange(ArrayList<Piece> pieces);
    //    public abstract void activateSkillEffect();
}
