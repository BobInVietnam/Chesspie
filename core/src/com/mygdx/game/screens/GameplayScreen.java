package com.mygdx.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.mygdx.game.Chesspie;
import com.mygdx.game.GameRenderer;
import com.mygdx.game.chessboard.ChessBoard;

public class GameplayScreen implements Screen {
  final Chesspie game;
  private GameRenderer gameRenderer;
  private ChessBoard board;
  public GameplayScreen(Chesspie game) {
    this.game = game;
    gameRenderer = new GameRenderer(game.batch, board);
  }
  @Override
  public void show() {

  }

  @Override
  public void render(float delta) {
    gameRenderer.draw(delta);
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
    gameRenderer.dispose();
  }
}
