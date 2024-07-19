package com.mygdx.game.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Chesspie;
import com.mygdx.game.GameRenderer;
import com.mygdx.game.chessboard.ChessBoard;
import com.mygdx.game.chesspieces.*;
import com.mygdx.game.sceneguis.GameplayGUI;

import java.util.HashMap;

public class GameplayScreen implements Screen {
  final Chesspie game;
  private OrthographicCamera camera;
  private GameRenderer gameRenderer;
  private InputAdapter inputHandler;
  private ChessBoard board;
  private HashMap<Piece, Rectangle> pieceHitboxes;
  public static float cornerX;
  public static float cornerY;
  private Vector2 mousePos;
  private Vector3 mousePos3;
  private boolean whiteTurn;
  public boolean pieceChosen;
  public boolean skillChosen;
  public Piece chosenPiece;

  private GameplayGUI gui;

  public GameplayScreen(Chesspie game) {
    this.game = game;
    gui = new GameplayGUI(this.game.gui.getSkin()) {
      @Override
      public void buttonClicked() {
        System.out.println("Skill button");
        skillChosen = !skillChosen;
        gameRenderer.skillChosen = !gameRenderer.skillChosen;
      }
    };
    this.game.gui.loadGUI(gui);

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
    piecesList.add(new Rook(1, 1, "white"));
    piecesList.add(new Rook(8, 1, "white"));
    piecesList.add(new Knight(2, 1, "white"));
    piecesList.add(new Knight(7, 1, "white"));
    piecesList.add(new Bishop(3, 1, "white"));
    piecesList.add(new Bishop(6, 1, "white"));
    piecesList.add(new Queen(4, 1, "white"));
    piecesList.add(new King(5, 1, "white"));
    for (int i = 1; i <= 8; i++) {
      piecesList.add(new Pawn(i, 2, "white"));
    }
    piecesList.add(new Rook(1, 8, "black"));
    piecesList.add(new Rook(8, 8, "black"));
    piecesList.add(new Knight(2, 8, "black"));
    piecesList.add(new Knight(7, 8, "black"));
    piecesList.add(new Bishop(3, 8, "black"));
    piecesList.add(new Bishop(6, 8, "black"));
    piecesList.add(new Queen(4, 8, "black"));
    piecesList.add(new King(5, 8, "black"));
    for (int i = 1; i <= 8; i++) {
      piecesList.add(new Pawn(i, 7, "black"));
    }
    board.pieces = piecesList;

    inputHandler = new InputAdapter() {
      @Override
      public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        handleMouseInput();
        return true;
      }
    };

    InputMultiplexer inputMultiplexer = new InputMultiplexer();
    inputMultiplexer.addProcessor(this.game.gui.getStage());
    inputMultiplexer.addProcessor(inputHandler);
    Gdx.input.setInputProcessor(inputMultiplexer);

    pieceHitboxes = new HashMap<>();
    for (Piece piece: board.pieces) {
      pieceHitboxes.put(piece, new Rectangle(cornerX + piece.getPosX(), cornerY + piece.getPosY(), 1, 1));
    }
    whiteTurn = true;
    System.out.println("A");
  }
  @Override
  public void show() {

  }

  private void calculateMousePos() {
    mousePos3.x = Gdx.input.getX();
    mousePos3.y = Gdx.input.getY();
    mousePos3 = camera.unproject(mousePos3);
    mousePos.x = mousePos3.x;
    mousePos.y = mousePos3.y;
  }

  private void setSelectState(boolean pieceChosen, Piece piece) {
    this.pieceChosen = pieceChosen;
    this.chosenPiece = piece;
    this.skillChosen = false;
    gameRenderer.pieceChosen = pieceChosen;
    gameRenderer.chosenPiece = piece;
    gameRenderer.skillChosen = false;
  }

  private void selectPiece() {
    for (Piece piece: pieceHitboxes.keySet()) {
      if (pieceHitboxes.get(piece).contains(mousePos) && ((piece.getColor().equals("white")) == whiteTurn)) {
        setSelectState(true, piece);
        gui.showInfo(piece);
        break;
      }
    }
  }
  private void movePiece(Piece piece, ChessBoard board, int posX, int posY) {
    whiteTurn = !whiteTurn;
    piece.move(posX, posY);
    pieceHitboxes.put(piece, new Rectangle(
        cornerX + piece.getPosX(), cornerY + piece.getPosY(), 1, 1)
    );
  }
  private void attackPiece(Piece piece, ChessBoard board, int posX, int posY) {
    whiteTurn = !whiteTurn;
    // TESTING: integrating normal chess rule
    pieceHitboxes.remove(board.getAt(posX, posY));
    board.removeAt(posX, posY);
    piece.move(posX, posY);
    pieceHitboxes.put(piece, new Rectangle(
    cornerX + piece.getPosX(), cornerY + piece.getPosY(), 1, 1)
    );
  }
  private void useSkill(){};
  private void handleMouseInput() {
    if (Gdx.input.justTouched()) {
      //Select piece. Else deselect piece
      if (!pieceChosen) {
        selectPiece();
      } else {
        int posX = (int) (mousePos.x - cornerX);
        int posY = (int) (mousePos.y - cornerY);
        if (chosenPiece.canMove(board, posX, posY)) {
          movePiece(chosenPiece, board, posX, posY);
        } else if (chosenPiece.inBaseAtkRange(board, posX, posY)) {
          attackPiece(chosenPiece, board, posX, posY);
        }
        setSelectState(false, null);
        gui.hideInfo();
      }
    }
  }

  @Override
  public void render(float delta) {
    calculateMousePos();
    gameRenderer.draw(delta);
    this.game.gui.render(delta);
  }

  @Override
  public void resize(int width, int height) {
    this.game.gui.resize(width, height);
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
