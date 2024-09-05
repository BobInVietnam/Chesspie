package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.XmlReader;

public class LanguageLoader {
  private static LanguageLoader languageLoader;
  private final XmlReader xmlReader;
  private final XmlReader.Element uiElements;
  private final XmlReader.Element skillsInfo;
  private LanguageLoader() {
    xmlReader = new XmlReader();
    uiElements = xmlReader.parse(Gdx.files.internal("Language/English/language.xml")).getChildByName("ui");
    skillsInfo = xmlReader.parse(Gdx.files.internal("Language/English/language.xml")).getChildByName("skills");
  }
  public static LanguageLoader getInstance() {
    if (languageLoader == null) {
      languageLoader = new LanguageLoader();
    }
    return languageLoader;
  }
  public String getSkillName(int id) {
    return skillsInfo.getChild(id).get("name");
  }
  public String getSkillDescription(int id) {
    return skillsInfo.getChild(id).get("description");
  }
  public String getUITitle(String name) {
    return uiElements.getChildByName("titleScreen").get(name);
  }
  public String getUIGameplay(String name) {
    return uiElements.getChildByName("gameplayScreen").get(name);
  }
  public String getTutorialLines(String line) {
    return uiElements.getChildByName("tutorial").get(line);
  }
}
