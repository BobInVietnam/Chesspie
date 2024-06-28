package com.mygdx.game.skills;

import com.mygdx.game.chesspieces.Piece;

import java.util.ArrayList;

public class DefendSkill extends Skill{
    @Override
    public boolean inRange(ArrayList<Piece> pieces) {
        return false;
    }
}
