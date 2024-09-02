package com.mygdx.game.sceneguis;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.GUIRenderer;

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
    table.setBackground(new NinePatchDrawable(skin.getPatch("ui_bigFrame")));
    table.setDebug(debugMode);
    return table;
  }
  public static Table createBlock(Skin skin, boolean isDarkMode, boolean debugMode) {
    Table table = new Table();
    if (isDarkMode) {
      table.setBackground(new NinePatchDrawable(skin.getPatch("ui_smallFrame_dark")));
    } else {
      table.setBackground(new NinePatchDrawable(skin.getPatch("ui_smallFrame")));
    }
    table.setDebug(debugMode);
    return table;
  }

  public static Label createLabel(Skin skin, String defaultText, float fontScale, boolean isDarkMode, boolean isSmall) {
    Label label;
    if (isSmall) {
      if (isDarkMode) {
        label = new Label(defaultText, skin, "default_dark");
      } else {
        label = new Label(defaultText, skin, "default");
      }
    } else {
      if (isDarkMode) {
        label = new Label(defaultText, skin, "title_dark");
      } else {
        label = new Label(defaultText, skin, "title");
      }
    }
    label.setFontScale(fontScale);
    return label;
  }

  public static TextButton createTextButton(Skin skin, String defaultText, boolean isTitle) {
    TextButton button;
    if (isTitle) {
      button = new TextButton(defaultText, skin, "title");
    } else {
      button = new TextButton(defaultText, skin);
    }
    return button;
  }
  public static TextButton createTextButton(Skin skin, String defaultText, boolean isTitle, float fontScale, int w, int h) {
    TextButton button = createTextButton(skin, defaultText, isTitle);
    button.setSize(w, h);
    button.getLabel().setScale(fontScale);
    return button;
  }
  public static Image createIcon(Skin skin, String iconName) {
    return new Image(new TextureRegionDrawable(skin.getRegion(iconName)));
  }
}
