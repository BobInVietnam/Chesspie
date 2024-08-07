package com.mygdx.game.skills;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.XmlReader;

public class SkillInfoLoader {
  private static SkillInfoLoader skillInfoLoader;
  private final XmlReader xmlReader;
  private final XmlReader.Element skillsInfo;
  private SkillInfoLoader() {
    xmlReader = new XmlReader();
    skillsInfo = xmlReader.parse(Gdx.files.internal("skills.xml"));
  }
  public static SkillInfoLoader getInstance() {
    if (skillInfoLoader == null) {
      skillInfoLoader = new SkillInfoLoader();
    }
    return skillInfoLoader;
  }
  public String getSkillName(int id) {
    return skillsInfo.getChild(id).get("name");
  }
  public String getSkillDescription(int id) {
    return skillsInfo.getChild(id).get("description");
  }
}
