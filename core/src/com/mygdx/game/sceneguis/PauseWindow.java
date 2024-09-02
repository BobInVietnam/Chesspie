package com.mygdx.game.sceneguis;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import sun.tools.jconsole.Tab;

public abstract class PauseWindow {
  private Skin skin;
  private static PauseWindow pauseWindow;

  private Table pauseBoard;
  private Label pauseLabel;
  private Label randomTips;
  private TextButton resumeButton;
  private TextButton settingButton;
  private TextButton quitButton;
  private SettingWindow settingWindow;

  public PauseWindow(Skin skin) {
    this.skin = skin;
    pauseBoard = SceneGUI.createWindow(skin, false);
    pauseBoard.setSize(700, 400);
    pauseBoard.setPosition(
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
    pauseBoard.add(pauseLabel).colspan(3).expand();
    pauseBoard.row();
    pauseBoard.add(resumeButton).expandX();
    pauseBoard.add(settingButton).expandX();
    pauseBoard.add(quitButton).expandX();
    pauseBoard.setZIndex(9);
    pauseBoard.setVisible(false);
  }
  public Table getPauseBoard(){
    return pauseBoard;
  }

  public abstract void handleSettingButton();
  public abstract void handleResumeButton();
  public abstract void handleQuitButton();
}
