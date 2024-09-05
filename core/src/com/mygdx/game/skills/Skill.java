package com.mygdx.game.skills;

import com.mygdx.game.LanguageLoader;
import com.mygdx.game.chessboard.ChessBoard;
import com.mygdx.game.chesspieces.*;

public abstract class Skill {
  int skillID;
  int skillDmg;
  protected LanguageLoader languageLoader;
  protected String skillName;
  protected String skillDescription;
  protected SkillEffect skillEffect;
  protected SkillActivation skillActivation;

  public Skill() {
    languageLoader = LanguageLoader.getInstance();
  }

  public int getSkillID() {return skillID;}
  public int getSkillDmg() {
      return skillDmg;
  }
  public SkillActivation getSkillActivation() { return skillActivation; }
  public String getSkillName() {
    return skillName;
  }
  public String getSkillDescription() {
    return skillDescription;
  }
  public void setSkillDetails(int skillID) {
    skillName = languageLoader.getSkillName(skillID);
    skillDescription = languageLoader.getSkillDescription(skillID);
  }
  public void setSkillDmg(int skillDmg) {
      this.skillDmg = skillDmg;
  }

  public abstract boolean canUseSkillOn(ChessBoard board, int x, int y, Piece piece);
  public abstract boolean inSkillRange(ChessBoard board, int x, int y, Piece piece);
  public abstract void setSkillEffect();
  public void activateSkill(ChessBoard board, Piece piece) {}
  public void activateTargetedSkill(ChessBoard board, Piece piece, int x, int y) {}
}
