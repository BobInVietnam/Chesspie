package com.mygdx.game.sceneguis;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.settings.Settings;

public class SettingWindow extends SceneWindow {
  private Settings settings;
  private TextButton exitButton;
  private Label languageSelectBoxLabel;
  private SelectBox<String> languageSelectBox;
  private Array<String> languages;
  public SettingWindow(Skin skin) {
    this.skin = skin;
    languages = new Array<>(new String[]{"English", "Vietnamese"});
    settings = Settings.getInstance();
    createSettingWindow(skin);
    createLanguageSetting(skin);
    loadComponents();
  }

  private void createSettingWindow(Skin skin) {
    root = SceneGUI.createWindow(skin, false);
    root.setSize(900, 600);
    root.setPosition(
        (float) Gdx.graphics.getWidth() / 2 - 450,
        (float) Gdx.graphics.getHeight() / 2 - 300
    );
    root.setTouchable(Touchable.enabled);
    exitButton = SceneGUI.createTextButton(skin, language.getSettingWindow("exit"), false);
    exitButton.addListener(new InputListener() {
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        updateSettings();
        root.setVisible(false);
        exitButton.setChecked(true);
        return true;
      }
    });
  }

  private void createLanguageSetting(Skin skin) {
    languageSelectBoxLabel  = SceneGUI.createLabel(skin, language.getSettingWindow("language"), 1f, false, false);
    languageSelectBox = new SelectBox<>(skin);
    languageSelectBox.setItems(languages);
    languageSelectBox.addListener(new ChangeListener() {
      @Override
      public void changed(ChangeEvent event, Actor actor) {
        System.out.println(languageSelectBox.getSelected());
        settings.setLanguage(languageSelectBox.getSelected());
        updateSettings();
      }
    });
  }
  private void loadComponents() {
    root.add(languageSelectBoxLabel).expand();
    root.add(languageSelectBox).expand();
    root.row();
    root.add(exitButton).expandX().colspan(2);
    root.setZIndex(10);
    root.setVisible(false);
  }
  public void updateSettings() {
    settings.notifyObservers();
  }

  @Override
  public void update(Settings settings) {
    System.out.println("Setting updated");
    languageSelectBoxLabel.setText(language.getSettingWindow("language"));
    exitButton.setText(language.getSettingWindow("exit"));
  }

  @Override
  public void dispose() {
    Settings settings = Settings.getInstance();
    settings.removeObserver(this);
  }
}
