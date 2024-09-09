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

/**
 * Game loop works as follows:
 * 1. White goes first. Player select the piece they want to control.
 * 2. Player can click on a yellow square on the board to move that piece, or a red square under an enemy piece to attack.
 *    Additionally, one can use that piece's skill. Hover on the skill button to display its range and info board. Click on it to activate skill.
 * 3. Enemy piece may take damage immediately after being attacked.
 * 4. Passive skills of white pieces activate their effects.
 * 5. Status effects on white pieces (if present) take effects.
 * 6. Game switches to black turn. Repeat this from 1.
 */
public class GameplayScreen implements Screen {
  final Chesspie game;
  private final OrthographicCamera camera;
  private final GameRenderer gameRenderer;
  private final InputAdapter inputHandler;
  private final ChessBoard board;
  private HashMap<Piece, Rectangle> pieceHitboxes;
  public static float cornerX;
  public static float cornerY;
  private final Vector2 mousePos;
  private Vector3 mousePos3;

  private boolean gamePaused;
  private boolean whiteTurn;
  public boolean pieceChosen;
  public boolean skillChosen;
  public Piece chosenPiece;

  private final int TIMER = 1800; // Timer before the game automatically closes

  private final GameplayGUI gui;

  public GameplayScreen(Chesspie game) {
    this.game = game;
    gui = new GameplayGUI(this.game.gui.getSkin()) {
      @Override
      public void buttonClicked() {
        System.out.println("Skill button");
        if (chosenPiece.getChessSkill().getSkillActivation() == SkillActivation.TARGET) {
          skillChosen = !skillChosen;
          gameRenderer.skillChosen = !gameRenderer.skillChosen;
          gui.setSkillSelectedMessageDisplay(skillChosen);
        } else if (chosenPiece.getChessSkill().getSkillActivation() == SkillActivation.TRIGGER) {
          useSkill();
        }
        gui.skillButtonCheck(!skillChosen);
      }

      @Override
      public void buttonEnter() {
        gameRenderer.skillRangeDisplay = true;
        if (chosenPiece != null)
          showSkillInfo(chosenPiece);
      }

      @Override
      public void buttonExit() {
        gameRenderer.skillRangeDisplay = false;
        hideSkillInfo();
      }

      @Override
      public void resumeButtonClicked() {
        gamePaused = false;
        gui.hidePauseWindow();
      }

      @Override
      public void quitButtonClicked() {
        game.setScreen(new TitleScreen(game));
      }

      @Override
      public void timeup() {
        System.out.println("Time up!");
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

      @Override
      public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.ESCAPE) {
          gamePaused = !gamePaused;
          if (gamePaused) {
            gui.showPauseWindow();
          } else {
            gui.hidePauseWindow();
          }
        }
        return super.keyDown(keycode);
      }
    };

    InputMultiplexer inputMultiplexer = new InputMultiplexer();
    inputMultiplexer.addProcessor(this.game.gui.getStage());
    inputMultiplexer.addProcessor(inputHandler);
    Gdx.input.setInputProcessor(inputMultiplexer);

    pieceHitboxes = updatePieceHitboxes();
    whiteTurn = true;
    gui.setTimer(TIMER);
  }
  @Override
  public void show() {

  }
  private void beginGame() {
    board.boardState = ChessBoard.State.PLAYING;
    gui.startTimer();
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
  private void activatePassiveSkills(boolean whiteTurn) {
    int l = board.pieces.size;
    for (int i = 0; i < l; i++) {
      Piece p = board.pieces.get(i);
      if (p.getColor().equals("white") == whiteTurn
          && p.getChessSkill().getSkillActivation() == SkillActivation.PASSIVE)
        p.activateSkill(board);
    }
  }
  private void switchSide() {
    activatePassiveSkills(whiteTurn);
    board.refresh(whiteTurn);
    pieceHitboxes = updatePieceHitboxes();
    setSelectState(false, null);
    gui.setSkillSelectedMessageDisplay(skillChosen);
    gui.skillButtonCheck(skillChosen);
    if (isWinState()) return;
    whiteTurn = !whiteTurn;
    gui.setTurnIndicatorText(whiteTurn);
  }

  private boolean isWinState() {
    switch (board.boardState) {
      case WHITE_WON:
        System.out.println("WHITE WON! CONGRATZ!");
        return true;
      case BLACK_WON:
        System.out.println("BLACK WON! CONGRATZ!");
        return true;
      default:
    }
    return false;
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
  }

  private void useTargetedSkill(int posX, int posY) {
    chosenPiece.activateTargetedSkill(board, posX, posY);
    switchSide();
  }
  private void handleMouseInput() {
    if (board.boardState == ChessBoard.State.START) {
      beginGame();
    }
    if (gamePaused) {
      return;
    }
    if (Gdx.input.justTouched()) {
      //Select piece. Else deselect piece
      if (!pieceChosen) {
        selectPiece();
      } else {
        int posX = (int) (mousePos.x - cornerX);
        int posY = (int) (mousePos.y - cornerY);
        if (skillChosen) {
          if (chosenPiece.inSkillRange(board, posX, posY)) {
            useTargetedSkill(posX, posY);
          } else {
            setSelectState(false, null);
            gui.setSkillSelectedMessageDisplay(skillChosen);
            gui.skillButtonCheck(skillChosen);
            gui.hideInfo();
          }
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
          if (pieceChosen) return;
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
    if (gamePaused) {
      return;
    }
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
    gui.dispose();
  }

}
