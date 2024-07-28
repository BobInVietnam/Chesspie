package com.mygdx.game.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Chesspie;
import com.mygdx.game.GameRenderer;
import com.mygdx.game.chessboard.ChessBoard;
import com.mygdx.game.chesspieces.*;
import com.mygdx.game.sceneguis.GameplayGUI;
import com.mygdx.game.skills.SkillActivation;

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

  private final int TIMER = 120; // Timer before the game automatically closes

  private GameplayGUI gui;

  public GameplayScreen(Chesspie game) {
    this.game = game;
    gui = new GameplayGUI(this.game.gui.getSkin()) {
      @Override
      public void buttonClicked() {
        System.out.println("Skill button");
        if (chosenPiece.getChessSkill().getSkillActivation() == SkillActivation.TARGET) {
          skillChosen = !skillChosen;
          gameRenderer.skillChosen = !gameRenderer.skillChosen;
        } else if (chosenPiece.getChessSkill().getSkillActivation() == SkillActivation.TRIGGER) {
          useSkill();
        }
      }

      @Override
      public void buttonEnter() {
        gameRenderer.skillRangeDisplay = true;
      }

      @Override
      public void buttonExit() {
        gameRenderer.skillRangeDisplay = false;
      }

      @Override
      public void timeup() { // Change this if you don't want the game to close on your face
        System.out.println("Time up!");
        game.dispose();
        Gdx.app.exit();
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
    board.pieces = PieceListGenerator.generatePieces();

    inputHandler = new InputAdapter() {
      @Override
      public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        handleMouseInput();
        return true;
      }

      @Override
      public boolean mouseMoved(int screenX, int screenY) {
        handleMouseOver();
        return true;
      }
    };

    InputMultiplexer inputMultiplexer = new InputMultiplexer();
    inputMultiplexer.addProcessor(this.game.gui.getStage());
    inputMultiplexer.addProcessor(inputHandler);
    Gdx.input.setInputProcessor(inputMultiplexer);

    pieceHitboxes = updatePieceHitboxes();
    whiteTurn = true;
    gui.setTimer(TIMER);
    gui.startTimer();
  }
  @Override
  public void show() {

  }

  private HashMap<Piece, Rectangle> updatePieceHitboxes() {
    pieceHitboxes = new HashMap<>();
    for (Piece piece: board.pieces) {
      pieceHitboxes.put(piece, new Rectangle(cornerX + piece.getPosX(), cornerY + piece.getPosY(), 1, 1));
    }
    return pieceHitboxes;
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
    gameRenderer.skillRangeDisplay = false;
  }
  private void switchSide() {
    board.refresh(whiteTurn);
    pieceHitboxes = updatePieceHitboxes();
    setSelectState(false, null);
    whiteTurn = !whiteTurn;
    gui.setTurnIndicatorText(whiteTurn);
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
    piece.move(posX, posY);
    switchSide();
  }
  private void attackPiece(Piece piece, ChessBoard board, int posX, int posY) {
    piece.attack(board, board.getAt(posX, posY));
    switchSide();
  }
  private void useSkill(){
    chosenPiece.activateSkill(board);
    switchSide();
  };
  private void useTargetedSkill(int posX, int posY) {
    chosenPiece.activateTargetedSkill(board, posX, posY);
    switchSide();
  }
  private void handleMouseInput() {
    if (Gdx.input.justTouched()) {
      //Select piece. Else deselect piece
      if (!pieceChosen) {
        selectPiece();
      } else {
        int posX = (int) (mousePos.x - cornerX);
        int posY = (int) (mousePos.y - cornerY);
        if (skillChosen && chosenPiece.inSkillRange(board, posX, posY)) {
          useTargetedSkill(posX, posY);
        } else if (chosenPiece.canMove(board, posX, posY)) {
          movePiece(chosenPiece, board, posX, posY);
        } else if (chosenPiece.inBaseAtkRange(board, posX, posY)) {
          attackPiece(chosenPiece, board, posX, posY);
        } else {
          setSelectState(false, null);
          gui.hideInfo();
        }
      }
    }
  }
  private void showPieceInfo() {
    for (Piece piece: pieceHitboxes.keySet()) {
      if (pieceHitboxes.get(piece).contains(mousePos)) {
        if ((piece.getColor().equals("white")) == whiteTurn) {
          gui.showInfo(piece);
          return;
        } else {
          gui.showEnemyInfo(piece);
          return;
        }
      }
    }
    if (!pieceChosen) {
      gui.hideInfo();
    }
    gui.hideEnemyInfo();
  }
  private void handleMouseOver() {
    showPieceInfo();
  }

  @Override
  public void render(float delta) {
    calculateMousePos();
    gui.countdown();
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
