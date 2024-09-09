package com.mygdx.game.sceneguis;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.settings.Settings;

public abstract class PauseWindow extends SceneWindow {
  private Label pauseLabel;
  private Label randomTips;
  private TextButton resumeButton;
  private TextButton settingButton;
  private TextButton quitButton;
  private SettingWindow settingWindow;

  public PauseWindow(Skin skin) {
    this.skin = skin;
    root = SceneGUI.createWindow(skin, false);
    root.setSize(700, 400);
    root.setPosition(
        (float) Gdx.graphics.getWidth() / 2 - 400,
        (float) Gdx.graphics.getHeight() / 2 - 200
    );
    pauseLabel = SceneGUI.createLabel(skin, "PAUSED", 1.5f, false, false);
    resumeButton = SceneGUI.createTextButton(skin, "Resume Game", false, 1f, 200, 60);
    resumeButton.addListener(new InputListener() {
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        handleResumeButton();
        resumeButton.setChecked(true);
        return true;
      }
    });
    settingButton = SceneGUI.createTextButton(skin, "Settings", false,1f, 200, 60);
    settingButton.addListener(new InputListener() {
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        handleSettingButton();
        settingButton.setChecked(true);
        return true;
      }
    });
    quitButton = SceneGUI.createTextButton(skin, "Quit Game", false, 1f, 200, 60);
    quitButton.addListener(new InputListener() {
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        handleQuitButton();
        return true;
      }
    });
    root.add(pauseLabel).colspan(3).expand();
    root.row();
    root.add(resumeButton).expandX();
    root.add(settingButton).expandX();
    root.add(quitButton).expandX();
    root.setZIndex(9);
    root.setVisible(false);
  }

  public abstract void handleSettingButton();
  public abstract void handleResumeButton();
  public abstract void handleQuitButton();

  @Override
  public void update(Settings settings) {
    System.out.println("Pause window updated");
    pauseLabel.setText(language.getPauseWindow("title"));
    resumeButton.setText(language.getPauseWindow("b1"));
    settingButton.setText(language.getPauseWindow("b2"));
    quitButton.setText(language.getPauseWindow("b3"));
  }
  @Override
  public void dispose() {
    Settings settings = Settings.getInstance();
    settings.removeObserver(this);
  }
}
