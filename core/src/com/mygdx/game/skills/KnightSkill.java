package com.mygdx.game.skills;

import com.badlogic.gdx.utils.Array;
import com.mygdx.game.chessboard.ChessBoard;
import com.mygdx.game.chesspieces.*;

import java.util.ArrayList;

public class KnightSkill extends Skill{
    public KnightSkill(int dmg) {
        skillID = 2;
        skillActivation = SkillActivation.TRIGGER;
        skillDmg = dmg;
        setSkillDetails(skillID);
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
            && !(board.getAt(x, y).getColor().equals(piece.getColor()));
    }

    @Override
    public void setSkillEffect() {
        super.skillEffect = SkillEffect.AttackSkill;
    }

    @Override
    public void activateSkill(ChessBoard board, Piece piece) {
        Array<Piece> targets = new Array<>();
        for (int i = piece.getPosX() - 1; i <= piece.getPosX() + 1; i++) {
            for (int j = piece.getPosY() - 1; j <= piece.getPosY() + 1; j++) {
                if (findTargetAt(board, piece, i, j)) {
                    targets.add(board.getAt(i, j));
                }
            }
        }
        for (Piece p: targets) {
            p.getSkillAttacked(piece);
        }
    }

    private boolean findTargetAt(ChessBoard board, Piece piece, int x, int y) {
        return board.getAt(x, y) != null && !board.getAt(x, y).getColor().equals(piece.getColor());
    }
}
