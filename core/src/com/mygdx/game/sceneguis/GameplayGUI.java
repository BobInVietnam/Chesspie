package com.mygdx.game.sceneguis;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.GUIRenderer;
import com.mygdx.game.chesspieces.Piece;

import java.text.DecimalFormat;

public abstract class GameplayGUI extends SceneGUI {
  private Table pieceInfoBoard;
  private Image pieceAvatar;
  private Label pieceHealth;
  private ImageButton skill1;
  private Table enemyPieceInfoBoard;
  private Image enemyPieceAvatar;
  private Label enemyPieceHealth;
  private Label attack;
  private Label defense;
  private Label enemyAttack;
  private Label enemyDefense;
  private float timer;
  private Label timerDisplay;
  private Table timerBoard;
  private boolean timeRunning;
  private Table turnIndicator;
  private Label turnIndicatorText;
  private static final boolean DEBUG_MODE = true;
  private final Skin skin;

  public GameplayGUI(Skin skin) {
    // Piece info table
    this.skin = skin;

    createSkillButton();
    initializeStats();
    initializePlayerBoardComponents();
    createPlayerBoard();

    initializeEnemyBoardComponents();
    createEnemyBoard();
    createTimer();
    createTurnIndicator();
    // Adding components to root table
    loadComponents();

    hideInfo();
    hideEnemyInfo();
  }
  private Image loadImage(GUIRenderer.BitIcon icon) {
    return new Image(skin.get(icon.name(),TextureRegionDrawable.class));
  }
  private void initializeStats() {
    attack = new Label("0", skin, "LabelSkin");
    attack.setFontScale(0.5f);
    enemyAttack = new Label("0", skin, "LabelSkin");
    enemyAttack.setFontScale(0.5f);
    defense = new Label("0", skin, "LabelSkin");
    defense.setFontScale(0.5f);
    enemyDefense = new Label("0", skin, "LabelSkin");
    enemyDefense.setFontScale(0.5f);
  }
  private void createSkillButton() {
    skill1 = new ImageButton(skin.get("ImageButtonSkin", ImageButton.ImageButtonStyle.class));
    skill1.addListener( new InputListener() {
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        buttonClicked();
        return true;
      }
      @Override
      public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
        buttonEnter();
      }
      @Override
      public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
        buttonExit();
      }
    });
    skill1.getImageCell().expand().fill();
    skill1.getStyle().imageUp = skin.get("SKILL1", TextureRegionDrawable.class);
  }
  private void initializePlayerBoardComponents() {
    pieceInfoBoard = new Table();
    pieceInfoBoard.setBackground(skin.get("BlockSkin", NinePatchDrawable.class));
    pieceInfoBoard.setDebug(DEBUG_MODE);
    pieceAvatar = loadImage(GUIRenderer.BitIcon.SKILL1);// Will be replaced with actual piece avatar assets
    pieceHealth = new Label("0/0", skin, "LabelSkin");
  }
  private void createPlayerBoard() {
    pieceInfoBoard.pad(15, 15, 15, 15);
    pieceInfoBoard.left();
    Table info = new Table();
    info.setDebug(DEBUG_MODE);
    info.add(pieceHealth).center().expand().colspan(4);
    info.row();
    info.add(loadImage(GUIRenderer.BitIcon.ATK)).size(25).expandX();
    info.add(attack).expandX();
    info.add(loadImage(GUIRenderer.BitIcon.DEF)).size(25).expandX();
    info.add(defense).expandX();
    pieceInfoBoard.add(pieceAvatar).size(60).expandY();
    pieceInfoBoard.add(info).fill().expand();
  }
  private void initializeEnemyBoardComponents() {
    enemyPieceInfoBoard = new Table();
    enemyPieceInfoBoard.setBackground(skin.get("BlockSkin", NinePatchDrawable.class));
    enemyPieceInfoBoard.setDebug(DEBUG_MODE);
    enemyPieceAvatar = loadImage(GUIRenderer.BitIcon.SKILL2);// Will be replaced with actual piece avatar assets
    enemyPieceHealth = new Label("0/0", skin, "LabelSkin");
  }
  private void createEnemyBoard() {
    enemyPieceInfoBoard.pad(15, 15, 15, 15);
    enemyPieceInfoBoard.right();
    Table info2 = new Table();
    info2.setDebug(DEBUG_MODE);
    info2.add(enemyPieceHealth).center().expand().colspan(4);
    info2.row();
    info2.add(loadImage(GUIRenderer.BitIcon.ATK)).size(25).expandX();
    info2.add(enemyAttack).expandX();
    info2.add(loadImage(GUIRenderer.BitIcon.DEF)).size(25).expandX();
    info2.add(enemyDefense).expandX();
    enemyPieceInfoBoard.add(info2).fill().expand();
    enemyPieceInfoBoard.add(enemyPieceAvatar).size(60).expandY();
  }
  private void createTimer() {
    timerBoard = new Table();
    timerBoard.setBackground(skin.get("BlockSkin", NinePatchDrawable.class));
    timerDisplay = new Label("Timer: 00:00", skin, "LabelSkin");
    timerDisplay.setFontScale(0.7f);
    timerBoard.padLeft(15);
    timerBoard.add(timerDisplay).left();
  }
  private void createTurnIndicator() {
    turnIndicator = new Table();
    turnIndicator.setBackground(skin.get("BlockSkin", NinePatchDrawable.class));
    turnIndicatorText = new Label("W", skin, "LabelSkin");
    turnIndicator.add(turnIndicatorText);
  }
  private void loadComponents() {
    this.setDebug(DEBUG_MODE);
    this.left().top().add(pieceInfoBoard).size(300f, 80f).expandX().left();
    this.add(enemyPieceInfoBoard).size(300f, 80f).expandX().right();
    this.row().left();
    this.add(skill1).size(100).expand();
    this.row().left();
    this.add(timerBoard).size(300, 80).left();
    this.add(turnIndicator).size(80, 80).right();
    this.setFillParent(true);
  }

  public abstract void buttonClicked();
  public abstract void buttonEnter();
  public abstract void buttonExit();
  public void hideInfo() {
    pieceInfoBoard.setVisible(false);
    skill1.setVisible(false);
  }

  public void showInfo(Piece piece) {
    pieceInfoBoard.setVisible(true);
    pieceHealth.setText(piece.getHp() + "/" + piece.getMaxHp());
    attack.setText(piece.getAttack());
    defense.setText(piece.getDefense());
    skill1.setVisible(true);
  }
  public void hideEnemyInfo() {
    enemyPieceInfoBoard.setVisible(false);
  }
  public void showEnemyInfo(Piece piece) {
    enemyPieceInfoBoard.setVisible(true);
    enemyPieceHealth.setText(piece.getHp() + "/" + piece.getMaxHp());
    enemyAttack.setText(piece.getAttack());
    enemyDefense.setText(piece.getDefense());
  }
  public void setPieceAvatar(Drawable image) {
    pieceAvatar.setDrawable(image);
  }
  public void setTimer(float s) {
    timer = s;
  }
  public void startTimer() {
    timeRunning = true;
  }
  public void countdown() {
    if (!timeRunning) return;
    if (timer < 0) {
      timerDisplay.setText("Timer: 0:00.00");
      timeup();
      timeRunning = false;
      return;
    }
    timer -= Gdx.graphics.getDeltaTime();
    int min = (int) (timer / 60);
    float sec = timer%60;
    DecimalFormat df = new DecimalFormat("00.00");
    timerDisplay.setText("Timer: " + min + ':' + df.format(sec));
  }
  public abstract void timeup();
  public void setTurnIndicatorText(boolean whiteTurn) {
    if (whiteTurn) {
      turnIndicatorText.setText("W");
    } else {
      turnIndicatorText.setText("B");
    }
  }
}
