package com.mygdx.game.skills;

import com.mygdx.game.chessPieces.*;

import java.util.ArrayList;

public class BuffSkill extends Skill{

    public Character getSymbol() {
        return 'U';
    }

    @Override
    public boolean inRange(ArrayList<Piece> pieces) {
        return false;
    }

    public void buffBaseAttach(ArrayList<Piece> pieces) {

    }

    public void buffDefend(ArrayList<Piece> pieces) {

    }
}
