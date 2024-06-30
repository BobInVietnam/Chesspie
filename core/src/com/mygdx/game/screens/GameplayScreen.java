package com.mygdx.game.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Chesspie;
import com.mygdx.game.GameRenderer;
import com.mygdx.game.chessboard.ChessBoard;
import com.mygdx.game.chesspieces.King;
import com.mygdx.game.chesspieces.Piece;

import java.util.HashMap;

public class GameplayScreen implements Screen {
  final Chesspie game;
  private OrthographicCamera camera;
  private GameRenderer gameRenderer;
  private ChessBoard board;
  private HashMap<Piece, Rectangle> pieceHitboxes;
  public static float cornerX;
  public static float cornerY;
  private Vector2 mousePos;
  private Vector3 mousePos3;

  public boolean pieceChosen;
  public Piece chosenPiece;
  public GameplayScreen(Chesspie game) {
    this.game = game;

    board = new ChessBoard();
    camera = new OrthographicCamera();
    float ratio = (float) Gdx.graphics.getHeight() / Gdx.graphics.getWidth();
    camera.setToOrtho(false, 12 / ratio, 12);

    cornerX = camera.viewportWidth/2 - 5;
    cornerY = 1;

    mousePos = new Vector2();
    mousePos3 = new Vector3();
    gameRenderer = new GameRenderer(game.batch, board, camera);
    chosenPiece = null;
    pieceChosen = false;

    // TESTING: Add pieces to board
    Array<Piece> piecesList = new Array<>();
    piecesList.add(new King(2, 3, "white"));
    piecesList.add(new King(6, 6, "black"));
    board.pieces = piecesList;

    pieceHitboxes = new HashMap<>();
    for (Piece piece: board.pieces) {
      pieceHitboxes.put(piece, new Rectangle(cornerX + piece.getPosX(), cornerY + piece.getPosY(), 1, 1));
    }
  }
  @Override
  public void show() {

  }

  @Override
  public void render(float delta) {
    //Calculate mouse position
    mousePos3.x = Gdx.input.getX();
    mousePos3.y = Gdx.input.getY();
    mousePos3 = camera.unproject(mousePos3);
    mousePos.x = mousePos3.x;
    mousePos.y = mousePos3.y;

    //Handle mouse click
    if (Gdx.input.justTouched()) {
      //Select piece. Else deselect piece
      if (!pieceChosen) {
        for (Piece piece: pieceHitboxes.keySet()) {
          if (pieceHitboxes.get(piece).contains(mousePos)) {
            setSelectState(true, piece);
            break;
          }
        }
      } else {
        setSelectState(false, null);
      }
    }
    gameRenderer.draw(delta);
    System.out.println(delta);
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

  private void setSelectState(boolean pieceChosen, Piece piece) {
    this.pieceChosen = pieceChosen;
    this.chosenPiece = piece;
    gameRenderer.pieceChosen = pieceChosen;
    gameRenderer.chosenPiece = piece;
  }
}
