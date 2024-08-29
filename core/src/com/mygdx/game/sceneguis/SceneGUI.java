package com.mygdx.game.sceneguis;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public abstract class SceneGUI extends Table {
  protected Skin skin;
  protected Label createLabel(String defaultText, float fontScale, boolean isDarkMode, boolean isSmall) {
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
  protected TextButton createTextButton(String defaultText, float fontScale, int w, int h) {
    TextButton button;
    button = new TextButton(defaultText, skin, "TextButtonSkin");
    button.setSize(w, h);
    button.getLabel().setScale(fontScale);
    return button;
  }
}
