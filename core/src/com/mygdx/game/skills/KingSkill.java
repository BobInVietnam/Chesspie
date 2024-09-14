package com.mygdx.game.skills;

import com.mygdx.game.chessboard.ChessBoard;
import com.mygdx.game.chesspieces.*;
import com.mygdx.game.statusfxs.StatusEffect;

public class KingSkill extends Skill{
    public KingSkill(int buff) {
        skillID = 1;
        skillActivation = SkillActivation.PASSIVE;
        skillDmg = buff;
    }

    @Override
    public boolean canUseSkillOn(ChessBoard board, int x, int y, Piece piece) {
        int posX = piece.getPosX();
        int posY = piece.getPosY();
        return !(x > posX + 1 || x < posX - 1 || y > posY + 1 || y < posY - 1
            || (x == posX && y == posY));
    }

    @Override
    public boolean inSkillRange(ChessBoard board, int x, int y, Piece piece) {
        if (board.getAt(x, y) == null) return false;
        return canUseSkillOn(board, x, y, piece)
            && (board.getAt(x, y).getColor().equals(piece.getColor()));
    }

    @Override
    public void setSkillEffect() {
        super.skillEffect = SkillEffect.BuffSkill;
    }

    @Override
    public void activateSkill(ChessBoard board, Piece piece) {
        int posX = piece.getPosX();
        int posY = piece.getPosY();
        for (int i = posX - 1; i <= posX + 1; i++) {
            for (int j = posY - 1; j <= posY + 1; j++) {
                if (inSkillRange(board, i, j, piece)) {
                    StatusEffect.apply(board.getAt(i, j), StatusEffect.Effect.KINGS_ORDER, 1, 5);
                }
            }
        }
    }

}
