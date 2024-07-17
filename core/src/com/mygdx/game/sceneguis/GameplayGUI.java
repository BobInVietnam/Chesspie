package com.mygdx.game.sceneguis;

import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;

public class GameplayGUI extends SceneGUI {
  private Table pieceInfoBoard;
  private Image pieceAvatar;
  private Label pieceHealth;
  private ImageButton skill1;
  private Table enemyPieceInfoBoard;
  private Image enemyPieceAvatar;
  private Label enemyPieceHealth;

  public GameplayGUI(Skin skin) {
    pieceInfoBoard = new Table();
    pieceInfoBoard.setBackground(skin.get("BlockSkin", NinePatchDrawable.class));
    pieceAvatar = new Image();
    pieceHealth = new Label("placeholder", skin, "LabelSkin");
    skill1 = new ImageButton(skin.get("ImageButtonSkin", ImageButton.ImageButtonStyle.class));
    enemyPieceInfoBoard = new Table();
    enemyPieceInfoBoard.setBackground(skin.get("BlockSkin", NinePatchDrawable.class));
    enemyPieceAvatar = new Image();
    enemyPieceHealth = new Label("placeholder", skin, "LabelSkin");

    pieceInfoBoard.pad(5, 5, 5, 5);
    pieceInfoBoard.left();
    pieceInfoBoard.add(pieceAvatar).size(50, 50f);
    pieceInfoBoard.add(pieceHealth);

    this.left().top().add(pieceInfoBoard).size(200f, 60f);
    this.setFillParent(true);
  }
  public void buttonClicked() {

  }
  public void setPieceAvatar(Drawable image) {
    pieceAvatar.setDrawable(image);
  }
  @Override
  public void setSkin(Skin skin) {

  }
}
