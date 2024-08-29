package com.mygdx.game.sceneguis;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
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
  private Table skillDescriptionPane;
  private Label skillName;
  private Label skillDescription;
  private Table skillSelectedMessagePane;
  private Label skillSelectedMessage;

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

  public GameplayGUI(Skin skin) {
    // Piece info table
    this.skin = skin;

    createSkillButton();
    createSkillDescriptionPane();
    createSkillSelectedMessage();
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
    hideSkillInfo();
  }
  private Image loadImage(GUIRenderer.BitIcon icon) {
    return new Image(skin.get(icon.name(),TextureRegionDrawable.class));
  }
  private Table createTable(boolean isDarkMode) {
    Table table = new Table();
    if (isDarkMode) {
      table.setBackground(skin.get("BlockDarkSkin", NinePatchDrawable.class));
    } else {
      table.setBackground(skin.get("BlockSkin", NinePatchDrawable.class));
    }
    table.setDebug(DEBUG_MODE);
    return table;
  }
  private void initializeStats() {
    attack = createLabel("0", 0.6f, false, false);
    enemyAttack = createLabel("0", 0.6f, false, false);
    defense = createLabel("0", 0.6f, false, false);
    enemyDefense = createLabel("0", 0.6f, false, false);
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
    skill1.getStyle().imageChecked = skin.get("SKILL2", TextureRegionDrawable.class);
  }
  private void createSkillDescriptionPane() {
    skillDescriptionPane = createTable(true);
    skillName = createLabel("Skill", 1, true, false);
    skillName.setWrap(true);
    skillDescription = createLabel(
        "deals damage, buffs allies or self and many other effects can happen if you push this button, deals damage, buffs allies or self and many other effects can happen if you push this button",
        1f, true, true);
    skillDescription.setWrap(true);
    skillDescriptionPane.add(skillName).center().fillX().expand().pad(10, 10, 0, 10);
    skillDescriptionPane.row();
    skillDescriptionPane.add(skillDescription).fill().expand().pad(10, 10, 10, 10);
  }
  private void createSkillSelectedMessage() {
    skillSelectedMessagePane = createTable(false);
    skillSelectedMessage = createLabel("Select skill target", 1, false, true);
    skillSelectedMessagePane.add(skillSelectedMessage);
    skillSelectedMessagePane.setVisible(false);
  }
  private void initializePlayerBoardComponents() {
    pieceInfoBoard = createTable(false);
    pieceAvatar = loadImage(GUIRenderer.BitIcon.SKILL1);// Will be replaced with actual piece avatar assets
    pieceHealth = createLabel("0", 1f, false, false);
  }

  private void createPlayerBoard() {
    pieceInfoBoard.pad(15, 15, 15, 15);
    pieceInfoBoard.left();
    Table info = new Table();
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
    enemyPieceInfoBoard = createTable(false);
    enemyPieceAvatar = loadImage(GUIRenderer.BitIcon.SKILL2);// Will be replaced with actual piece avatar assets
    enemyPieceHealth = createLabel("0", 1f, false, false);
  }
  private void createEnemyBoard() {
    enemyPieceInfoBoard.pad(15, 15, 15, 15);
    enemyPieceInfoBoard.right();
    Table info2 = new Table();
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
    timerBoard = createTable(false);
    timerDisplay = createLabel("Timer: 0:00.00", 0.8f, false, false);
    timerBoard.padLeft(15);
    timerBoard.add(timerDisplay).left();
  }
  private void createTurnIndicator() {
    turnIndicator = createTable(false);
    turnIndicatorText = createLabel("W", 1.2f, false, false);
    turnIndicator.add(turnIndicatorText);
  }
  private void loadComponents() {
    this.setDebug(DEBUG_MODE);
    this.left().top().add(pieceInfoBoard).size(300f, 80f).expandX().left();
    this.add().expandX();
    this.add(enemyPieceInfoBoard).size(300f, 80f).expandX().right();
    this.row().left();
    Table t = new Table();
    t.setDebug(DEBUG_MODE);
    t.left().add(skill1).size(100).expand();
    t.add(skillDescriptionPane).prefWidth(300);
    this.add(t).expand();
    this.row().left();
    this.add(timerBoard).size(320, 80).left();
    this.add(skillSelectedMessagePane).size(200, 50);
    this.add(turnIndicator).size(80, 80).right();
    this.setFillParent(true);
  }

  public abstract void buttonClicked();
  public abstract void buttonEnter();
  public abstract void buttonExit();
  public void skillButtonCheck(boolean checked) {
    skill1.setChecked(checked);
  }
  public void setSkillSelectedMessageDisplay(boolean checked) {
    skillSelectedMessagePane.setVisible(checked);
  }
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
  public void hideSkillInfo() {
    skillDescriptionPane.setVisible(false);
  }
  public void showSkillInfo(Piece piece) {
    skillDescriptionPane.setVisible(true);
    skillName.setText(piece.getChessSkill().getSkillName());
    skillDescription.setText(piece.getChessSkill().getSkillDescription());
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
