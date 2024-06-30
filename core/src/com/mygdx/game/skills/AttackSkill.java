package com.mygdx.game.skills;

import com.mygdx.game.chesspieces.Piece;

import java.util.ArrayList;

public class AttackSkill extends Skill{

    public Character getSymbol() {
        return 'A';
    }

    @Override
    public boolean inRange(ArrayList<Piece> pieces) {
        return false;
    }
}
