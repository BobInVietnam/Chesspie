package com.mygdx.game.sceneguis;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;

public class SettingWindow {
  private Array<String> languages;
  private Skin skin;
  private static SettingWindow settingWindow;
  private Preferences settings;
  private final Table settingBoard;
  private final TextButton exitButton;
  private final Label languageSelectBoxLabel;
  private SelectBox<String> languageSelectBox;
  private SettingWindow(Skin skin) {
    this.skin = skin;
    languages = new Array<>(new String[]{"English", "Vietnamese"});
    settings = Gdx.app.getPreferences("ChesspieSettings");
    settingBoard = SceneGUI.createWindow(skin, false);
    settingBoard.setSize(900, 600);
    settingBoard.setPosition(
        (float) Gdx.graphics.getWidth() / 2 - 450,
        (float) Gdx.graphics.getHeight() / 2 - 300
    );
    exitButton = new TextButton("Save & Exit", skin);
    exitButton.addListener(new InputListener() {
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        settingBoard.setVisible(false);
        exitButton.setChecked(true);
        return true;
      }
    });
    languageSelectBoxLabel  = SceneGUI.createLabel(skin, "Language", 1f, false, false);
    languageSelectBox = new SelectBox<>(skin);
    languageSelectBox.setItems(languages);
    languageSelectBox.addListener(new ChangeListener() {
      @Override
      public void changed(ChangeEvent event, Actor actor) {
        System.out.println(languageSelectBox.getSelected());
      }
    });
    settingBoard.add(languageSelectBoxLabel).expand();
    settingBoard.add(languageSelectBox).expand();
    settingBoard.row();
    settingBoard.add(exitButton).expandX().colspan(2);
    settingBoard.setZIndex(10);
    settingBoard.setVisible(false);
  }
  public Table getSettingBoard() {
    return settingBoard;
  }
  public static SettingWindow getInstance(Skin skin) {
    if (settingWindow == null) {
      settingWindow = new SettingWindow(skin);
    }
    return settingWindow;
  }

}
