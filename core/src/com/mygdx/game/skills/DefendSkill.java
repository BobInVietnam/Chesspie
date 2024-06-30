package com.mygdx.game.skills;

import com.mygdx.game.chesspieces.Piece;

import java.util.ArrayList;

public class DefendSkill extends Skill{

    public Character getSymbol() {
        return 'D';
    }
    @Override
    public boolean inRange(ArrayList<Piece> pieces) {
        return false;
    }
}
