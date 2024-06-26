package com.mygdx.game.Skills;

import com.mygdx.game.chessPieces.Piece;

import java.util.ArrayList;

public class AttackSkill extends Skill{

    @Override
    public boolean inRange(ArrayList<Piece> pieces) {
        return false;
    }
}
