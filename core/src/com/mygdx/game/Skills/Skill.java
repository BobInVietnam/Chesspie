package com.mygdx.game.Skills;

import com.mygdx.game.chessPieces.*;

import java.util.ArrayList;

public abstract class Skill {
    int skillID;



    public int getSkillID() {return skillID;}

    public void setSkillID(int skillID) {
        this.skillID = skillID;
    }

    public abstract boolean inRange(ArrayList<Piece> pieces);
    //    public abstract void activateSkillEffect();
}
