package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.mygdx.game.Chesspie;
import com.mygdx.game.GameRenderer;
import com.mygdx.game.chessBoard.ChessBoard;

public class GameplayScreen implements Screen {
  final Chesspie game;
  private OrthographicCamera camera;
  private GameRenderer gameRenderer;
  private ChessBoard board;
  public static float cornerX;
  public static float cornerY;
  public GameplayScreen(Chesspie game) {
    this.game = game;

    camera = new OrthographicCamera();
    float ratio = (float) Gdx.graphics.getHeight() / Gdx.graphics.getWidth();
    camera.setToOrtho(false, 12 / ratio, 12);
    cornerX = camera.viewportWidth/2 - 5;
    cornerY = 1;

    gameRenderer = new GameRenderer(game.batch, board, camera);
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
