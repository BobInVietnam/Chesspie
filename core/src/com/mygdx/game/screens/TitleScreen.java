package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.mygdx.game.Chesspie;
import com.mygdx.game.sceneguis.TitleGUI;

public class TitleScreen implements Screen {
  final Chesspie game;
  private final TitleGUI gui;

  public TitleScreen(Chesspie game) {
    this.game = game;
    this.gui = new TitleGUI(this.game.gui.getSkin()) {
      @Override
      public void startButtonClicked() {
        game.setScreen(new TutorialScreen(game));
      }

      @Override
      public void settingButtonClicked() {
        gui.showSettingWindow();
      }

      @Override
      public void handleQuitButton() {
        dispose();
        Gdx.app.exit();
      }
    };
    this.game.gui.loadGUI(gui);
  }
  @Override
  public void show() {

  }

  @Override
  public void render(float delta) {
    this.game.gui.render(delta);
  }

  @Override
  public void resize(int width, int height) {

  }

  @Override
  public void pause() {

  }

  @Override
  public void resume() {

  }

  @Override
  public void hide() {

  }

  @Override
  public void dispose() {
    gui.dispose();
  }
}
