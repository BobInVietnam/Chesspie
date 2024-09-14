package com.mygdx.game.skills;

import com.mygdx.game.chessboard.ChessBoard;
import com.mygdx.game.chesspieces.*;

import java.util.ArrayList;

public class BishopSkill extends Skill{
    public BishopSkill(int dmg) {
        skillID = 0;
        skillActivation = SkillActivation.TARGET;
        skillDmg = dmg;
    }
    @Override
    public boolean canUseSkillOn(ChessBoard board, int x, int y, Piece piece) {
        int posX = piece.getPosX();
        int posY = piece.getPosY();
        return !(x > posX + 2 || x < posX - 2 || y > posY + 2 || y < posY - 2
            || (x == posX && y == posY));
    }

    @Override
    public boolean inSkillRange(ChessBoard board, int x, int y, Piece piece) {
        if (board.getAt(x, y) == null) return false;
        return canUseSkillOn(board, x, y, piece)
            && !(board.getAt(x, y).getColor().equals(piece.getColor()));
    }

    @Override
    public void setSkillEffect() {
        super.skillEffect = SkillEffect.AttackSkill;
    }

    @Override
    public void activateTargetedSkill(ChessBoard board, Piece piece, int x, int y) {
        board.getAt(x, y).getSkillAttacked(piece);
    }
}
