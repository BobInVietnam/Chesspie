package com.mygdx.game.skills;

import com.mygdx.game.chesspieces.Piece;

import java.util.ArrayList;

public class AttackSkill extends Skill{

    @Override
    public boolean inRange(ArrayList<Piece> pieces) {
        return false;
    }
}
