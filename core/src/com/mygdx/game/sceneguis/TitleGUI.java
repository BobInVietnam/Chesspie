package com.mygdx.game.sceneguis;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.settings.Settings;

public abstract class TitleGUI extends SceneGUI{
  private Image title;
  private Texture bg;
  private Texture titleTexture;
  private TextButton startButton;
  private TextButton settingButton;
  private TextButton quitButton;
  private SettingWindow settingsBoard;

  private static final boolean DEBUG_MODE = false;

  public TitleGUI(Skin skin) {
    this.skin = skin;
    settingsBoard = new SettingWindow(skin);
    loadTextures();
    createStartButton();
    createSettingButton();
    createQuitButton();
    loadComponents();
  }
  private void loadTextures() {
    titleTexture = new Texture("Images/title.png");
    bg = new Texture("Images/concept_arts.jpg");
    root.setBackground(new TextureRegionDrawable(
        new TextureRegion(bg)
    ));
    title = new Image(titleTexture);
    title.setDebug(DEBUG_MODE);
    title.setScale(1f);
  }
  private void createStartButton() {
    startButton = createTextButton(skin, language.getUITitle("start"), true, 1.2f, 300, 80);
    startButton.addListener(new InputListener() {
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        startButtonClicked();
        return true;
      }
    });
    startButton.setDebug(DEBUG_MODE);
  }
  private void createSettingButton() {
    settingButton = createTextButton(skin, language.getUITitle("settings"), true, 1.2f, 300, 80);
    settingButton.addListener(new InputListener() {
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        settingButtonClicked();
        settingButton.setChecked(true);
        return true;
      }
    });
    settingButton.setDebug(DEBUG_MODE);
  }
  private void createQuitButton() {
    quitButton = createTextButton(skin, language.getUITitle("quit"), true, 1.2f, 300, 80);
    quitButton.addListener(new InputListener() {
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        handleQuitButton();
        return true;
      }
    });
    quitButton.setDebug(DEBUG_MODE);
  }
  private void loadComponents() {
    root.defaults().space(15f);
    root.center().add(title).size(823, 272).padBottom(35f);
    root.row();
    root.center().add(startButton).size(300, 80);
    root.row();
    root.center().add(settingButton).size(300, 80);
    root.row();
    root.center().add(quitButton).size(300, 80);
    root.setDebug(DEBUG_MODE);
    root.setFillParent(true);
    windows.add(settingsBoard);
  }
  public abstract void startButtonClicked();
  public abstract void settingButtonClicked();
  public abstract void handleQuitButton();
  public void showSettingWindow() {
    settingsBoard.getRoot().setVisible(true);
  }
  @Override
  public void update(Settings settings) {
    startButton.setText(language.getUITitle("start"));
    settingButton.setText(language.getUITitle("settings"));
    quitButton.setText(language.getUITitle("quit"));
  }
  public void dispose() {
    bg.dispose();
    titleTexture.dispose();
    Settings settings = Settings.getInstance();
    settings.removeObserver(this);
  }
}
