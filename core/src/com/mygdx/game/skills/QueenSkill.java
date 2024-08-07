package com.mygdx.game.skills;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.chessboard.ChessBoard;
import com.mygdx.game.chesspieces.*;

import java.util.ArrayList;

public class QueenSkill extends Skill{
    public QueenSkill(int dmg) {
        skillID = 4;
        skillActivation = SkillActivation.TARGET;
        skillDmg = dmg;
        setSkillDetails(skillID);
    }
    @Override
    public boolean canUseSkillOn(ChessBoard board, int x, int y, Piece piece) {
        int posX = piece.getPosX();
        int posY = piece.getPosY();
        return ((x <= posX + 3 && x >= posX - 3) && (y <= posY + 3 && y >= posY - 3)
        && (x == posX || y == posY || Math.abs(x - posX) == Math.abs(y - posY)));
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
        Vector2 v = new Vector2(x - piece.getPosX(), y - piece.getPosY());
        v.nor();
        int dx = Math.round(v.x);
        int dy = Math.round(v.y);
        int posX;
        int posY;
        for (int i = 1; i <= 3; i++) {
            posX = piece.getPosX() + dx * i;
            posY = piece.getPosY() + dy * i;
            if (inSkillRange(board, posX, posY, piece)) {
                board.getAt(posX, posY).getSkillAttacked(piece);
            }
        }
    }

}
