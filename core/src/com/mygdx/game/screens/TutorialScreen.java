package com.mygdx.game.screens;

import com.badlogic.gdx.Screen;
import com.mygdx.game.Chesspie;
import com.mygdx.game.sceneguis.TutorialGUI;

public class TutorialScreen implements Screen {
  final Chesspie game;
  private final TutorialGUI gui;
  public TutorialScreen(Chesspie game) {
    this.game = game;
    gui = new TutorialGUI(game.gui.getSkin()) {
      @Override
      public void switchScene() {
        game.setScreen(new GameplayScreen(game));
      }
    };
    game.gui.loadGUI(gui);
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
