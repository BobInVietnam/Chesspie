package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.XmlReader;
import com.mygdx.game.settings.Settings;
import com.mygdx.game.settings.SettingsObserver;

public class LanguageLoader implements SettingsObserver {
  private static final FileHandle ENGLISH = Gdx.files.internal("Language/English/language.xml");
  private static final FileHandle VIETNAMESE = Gdx.files.internal("Language/Vietnamese/language.xml");

  private static LanguageLoader languageLoader;
  private FileHandle currentLang;
  private final XmlReader xmlReader;
  private XmlReader.Element uiElements;
  private XmlReader.Element skillsInfo;
  private LanguageLoader() {
    xmlReader = new XmlReader();
    // Default language is English
    currentLang = ENGLISH;
    updateLanguage();
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
  public String getPauseWindow(String name) {
    return uiElements.getChildByName("pauseWindow").get(name);
  }
  public String getSettingWindow(String name) {
    return uiElements.getChildByName("settingWindow").get(name);
  }
  @Override
  public void update(Settings settings) {
    switch (settings.getLanguage()) {
      case "English":
        currentLang = ENGLISH;
        updateLanguage();
        break;
      case "Vietnamese":
        currentLang = VIETNAMESE;
        updateLanguage();
        break;
      default:
        currentLang = ENGLISH;
        updateLanguage();
    }
  }
  public void updateLanguage() {
    uiElements = xmlReader.parse(currentLang).getChildByName("ui");
    skillsInfo = xmlReader.parse(currentLang).getChildByName("skills");
  }
}
