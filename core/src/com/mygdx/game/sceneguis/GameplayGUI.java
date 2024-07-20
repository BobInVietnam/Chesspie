package com.mygdx.game.sceneguis;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Timer;
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
  private float timer;
  private Label timerDisplay;
  private Table timerBoard;
  private boolean timeRunning;

  public GameplayGUI(Skin skin) {
    // Piece info table
    pieceInfoBoard = new Table();
    pieceInfoBoard.setBackground(skin.get("BlockSkin", NinePatchDrawable.class));
    pieceInfoBoard.setDebug(true);
    // Piece avatar
    pieceAvatar = new Image();
    pieceAvatar.setDrawable(skin.get("SkillSkin", TextureRegionDrawable.class)); // Will be replaced with actual piece avatar assets
    // Piece health
    pieceHealth = new Label("0/0", skin, "LabelSkin");
    // Piece skill button
    skill1 = new ImageButton(skin.get("ImageButtonSkin", ImageButton.ImageButtonStyle.class));
    skill1.addListener( new InputListener() {
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        buttonClicked();
        return true;
      }

      @Override
      public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
        System.out.println("ENTER CHADLIPS");
      }

      @Override
      public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
        System.out.println("We're so over");
      }
    });
    skill1.getImageCell().expand().fill();
    skill1.getStyle().imageUp = skin.get("SkillSkin", TextureRegionDrawable.class);

    pieceInfoBoard.pad(15, 15, 15, 15);
    pieceInfoBoard.left();
    pieceInfoBoard.add(pieceAvatar).size(70).expandY();
    pieceInfoBoard.add(pieceHealth).center().expand();

    // Enemy piece info board
    enemyPieceInfoBoard = new Table();
    enemyPieceInfoBoard.setBackground(skin.get("BlockSkin", NinePatchDrawable.class));
    enemyPieceInfoBoard.setDebug(true);
    enemyPieceAvatar = new Image();
    enemyPieceAvatar.setDrawable(skin.get("Skill2Skin", TextureRegionDrawable.class)); // Will be replaced with actual piece avatar assets
    enemyPieceHealth = new Label("0/0", skin, "LabelSkin");

    enemyPieceInfoBoard.pad(15, 15, 15, 15);
    enemyPieceInfoBoard.right();
    enemyPieceInfoBoard.add(enemyPieceHealth).center().expand();
    enemyPieceInfoBoard.add(enemyPieceAvatar).size(70).expandY();

    timerBoard = new Table();
    timerBoard.setBackground(skin.get("BlockSkin", NinePatchDrawable.class));
    timerDisplay = new Label("Timer: 00:00", skin, "LabelSkin");
    timerDisplay.setFontScale(0.7f);
    timerBoard.padLeft(15);
    timerBoard.add(timerDisplay).left();

    // Adding components to root table
    this.left().top().add(pieceInfoBoard).size(300f, 80f).expandX().left();
    this.add(enemyPieceInfoBoard).size(300f, 80f).expandX().right();
    this.row().left();
    this.add(skill1).size(100).expand();
    this.row().left();
    this.add(timerBoard).size(300, 80).left();
    this.setFillParent(true);

    hideInfo();
    hideEnemyInfo();
  }

  public abstract void buttonClicked();
  public void hideInfo() {
    pieceInfoBoard.setVisible(false);
    skill1.setVisible(false);
  }

  public void showInfo(Piece piece) {
    pieceInfoBoard.setVisible(true);
    pieceHealth.setText(piece.getHp() + "/" + piece.getMaxHp());
    skill1.setVisible(true);
  }
  public void hideEnemyInfo() {
    enemyPieceInfoBoard.setVisible(false);
  }
  public void showEnemyInfo(Piece piece) {
    enemyPieceInfoBoard.setVisible(true);
    enemyPieceHealth.setText(piece.getHp() + "/" + piece.getMaxHp());
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
}
