package com.mygdx.game.sceneguis;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public abstract class TitleGUI extends SceneGUI{
  private Image title;
  private Texture bg;
  private Texture titleTexture;
  private TextButton startButton;
  private TextButton settingButton;
  private TextButton quitButton;
  private SettingTable settingsBoard;

  private static final boolean DEBUG_MODE = true;

  public TitleGUI(Skin skin) {
    this.skin = skin;
    settingsBoard = SettingTable.getInstance(skin);
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
    startButton = createTextButton(skin, "START", 1.2f, 300, 80);
    startButton.addListener(new InputListener() {
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        handleStartButton();
        return true;
      }
    });
    startButton.setDebug(DEBUG_MODE);
  }
  private void createSettingButton() {
    settingButton = createTextButton(skin, "SETTINGS", 1.2f, 300, 80);
    settingButton.addListener(new InputListener() {
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        handleSettingButton();
        return true;
      }
    });
    settingButton.setDebug(DEBUG_MODE);
  }
  private void createQuitButton() {
    quitButton = createTextButton(skin, "QUIT", 1.2f, 300, 80);
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
  }
  public abstract void handleStartButton();
  public abstract void handleSettingButton();
  public abstract void handleQuitButton();
  public void dispose() {
    bg.dispose();
    titleTexture.dispose();
  }
}
