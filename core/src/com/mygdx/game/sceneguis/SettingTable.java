package com.mygdx.game.sceneguis;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class SettingTable {
  enum Language {
    ENGLISH,
    VIETNAMESE
  }
  private Skin skin;
  private static SettingTable settingTable;
  private Preferences settings;
  private final Table settingBoard;
  private SelectBox<Language> languageSelectBox;
  private SettingTable(Skin skin) {
    this.skin = skin;
    settings = Gdx.app.getPreferences("ChesspieSettings");
    settingBoard = SceneGUI.createBlock(skin, false, false);
    settingBoard.setSize(720, 500);
    settingBoard.setPosition(
        (float) Gdx.graphics.getWidth() / 2 + 360,
        (float) Gdx.graphics.getHeight() / 2 + 250
    );
  }
  public Table getSettingBoard() {
    return settingBoard;
  }
  public static SettingTable getInstance(Skin skin) {
    if (settingTable == null) {
      settingTable = new SettingTable(skin);
    }
    return settingTable;
  }

}
