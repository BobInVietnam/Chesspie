package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.sceneguis.SceneGUI;

public class GUIRenderer {
  private final Stage stage;
  private SceneGUI rootTable;
  private final Skin skin;
  private final Texture ui;

  public enum BitIcon {
    SKILL1,
    SKILL2,
    ATK,
    DEF
  }

  public GUIRenderer() {
    float ratio = (float) Gdx.graphics.getHeight() / Gdx.graphics.getWidth();
    stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
    rootTable = null;
    skin = new Skin();

    // Load skin data
    ui = new Texture("Images/ui.png");
    NinePatchDrawable window = new NinePatchDrawable(
        new NinePatch(new TextureRegion(ui, 0, 0, 190, 190), 90, 90, 90, 90));
    skin.add("TableSkin", window);
    NinePatchDrawable block = new NinePatchDrawable(
        new NinePatch(new TextureRegion(ui, 190, 60, 23, 23), 10, 10, 10, 10));
    skin.add("BlockSkin", block);
    NinePatchDrawable blockDark = new NinePatchDrawable(
        new NinePatch(new TextureRegion(ui, 213, 60, 23, 23), 8, 8, 8, 8));
    skin.add("BlockDarkSkin", blockDark);

    FreeTypeFontGenerator fontGen = new FreeTypeFontGenerator(Gdx.files.internal("Font/pc-senior.regular.ttf"));
    FreeTypeFontGenerator.FreeTypeFontParameter fontParam = new FreeTypeFontGenerator.FreeTypeFontParameter();
    fontParam.size = 28;
    fontParam.color = Color.DARK_GRAY;
    BitmapFont font = fontGen.generateFont(fontParam);
    FreeTypeFontGenerator font2Gen = new FreeTypeFontGenerator(Gdx.files.internal("Font/cnc-red-alert-lan.ttf"));
    FreeTypeFontGenerator.FreeTypeFontParameter font2Param = new FreeTypeFontGenerator.FreeTypeFontParameter();
    fontParam.size = 28;
    fontParam.color = Color.DARK_GRAY;
    BitmapFont font2 = fontGen.generateFont(fontParam);

    NinePatchDrawable button = new NinePatchDrawable(
        new NinePatch(new TextureRegion(ui, 190, 0, 60, 60), 25, 25, 25, 25));
    ImageButton.ImageButtonStyle iconButton = new ImageButton.ImageButtonStyle(button, button, button, null, null, null);
    skin.add("ImageButtonSkin", iconButton);
    TextButton.TextButtonStyle gameButtonStyle = new TextButton.TextButtonStyle(button, button, button, font);
    skin.add("TextButtonSkin", gameButtonStyle);

    Label.LabelStyle labelStyle = new Label.LabelStyle(font, Color.DARK_GRAY);
    skin.add("LabelSkin", labelStyle);
    Label.LabelStyle label2Style = new Label.LabelStyle(font, Color.WHITE);
    skin.add("LabelDarkSkin", label2Style);
    Label.LabelStyle labelSmallStyle = new Label.LabelStyle(font2, Color.DARK_GRAY);
    skin.add("LabelSmallSkin", labelSmallStyle);

    for (BitIcon bi: BitIcon.values()) {
      TextureRegionDrawable icon = new TextureRegionDrawable(
          new TextureRegion(ui, bi.ordinal() * 8, 248, 8, 8)
      );
      skin.add(bi.name(), icon);
    }
    Gdx.input.setInputProcessor(stage);
  }

    public Skin getSkin() {
    return skin;
  }

  public Stage getStage(){
    return stage;
  }

  public void loadGUI(SceneGUI gui) {
    this.rootTable = gui;
    stage.addActor(rootTable);
  }

  public void render(float delta) {
    stage.act(delta);
    stage.draw();
  }

  public void resize (int width, int height) {
    stage.getViewport().update(width, height, true);
  }

  public void dispose() {
    ui.dispose();
  }
}
