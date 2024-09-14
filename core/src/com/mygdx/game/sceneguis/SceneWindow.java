package com.mygdx.game.sceneguis;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.game.LanguageLoader;
import com.mygdx.game.settings.Settings;
import com.mygdx.game.settings.SettingsObserver;

public abstract class SceneWindow implements SettingsObserver {
  protected Skin skin;
  protected Table root;
  protected LanguageLoader language;
  public SceneWindow() {
    root = new Table();
    language = LanguageLoader.getInstance();
  }
  public Table getRoot() {
    return root;
  }
  public abstract void dispose();
}
