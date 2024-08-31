package com.mygdx.game.sceneguis;

import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Array;

public abstract class SceneGUI{
  protected Array<Table> windows;
  protected Table root;
  protected Skin skin;

  public SceneGUI() {
    root = new Table();
    windows = new Array<>();
  }
  public Array<Table> getWindows() {
    return windows;
  }
  public Table getRoot() {
    return root;
  }
  public static Table createWindow(Skin skin, boolean debugMode) {
    Table table = new Table();
    table.setBackground(skin.get("TableSkin", NinePatchDrawable.class));
    table.setDebug(debugMode);
    return table;
  }
  public static Table createBlock(Skin skin, boolean isDarkMode, boolean debugMode) {
    Table table = new Table();
    if (isDarkMode) {
      table.setBackground(skin.get("BlockDarkSkin", NinePatchDrawable.class));
    } else {
      table.setBackground(skin.get("BlockSkin", NinePatchDrawable.class));
    }
    table.setDebug(debugMode);
    return table;
  }

  public static Label createLabel(Skin skin, String defaultText, float fontScale, boolean isDarkMode, boolean isSmall) {
    Label label;
    if (isSmall) {
      if (isDarkMode) {
        label = new Label(defaultText, skin, "LabelSmallDarkSkin");
      } else {
        label = new Label(defaultText, skin, "LabelSmallSkin");
      }
    } else {
      if (isDarkMode) {
        label = new Label(defaultText, skin, "LabelDarkSkin");
      } else {
        label = new Label(defaultText, skin, "LabelSkin");
      }
    }
    label.setFontScale(fontScale);
    return label;
  }

  public static TextButton createTextButton(Skin skin, String defaultText, float fontScale, int w, int h) {
    TextButton button;
    button = new TextButton(defaultText, skin, "TextButtonSkin");
    button.setSize(w, h);
    button.getLabel().setScale(fontScale);
    return button;
  }
}
