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
  private Stage stage;
  private SceneGUI rootTable;
  private final Skin skin;
  private final Texture ui;

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

    FreeTypeFontGenerator fontGen = new FreeTypeFontGenerator(Gdx.files.internal("Font/pc-senior.regular.ttf"));
    FreeTypeFontGenerator.FreeTypeFontParameter fontParam = new FreeTypeFontGenerator.FreeTypeFontParameter();
    fontParam.size = 28;
    fontParam.color = Color.DARK_GRAY;
    BitmapFont font = fontGen.generateFont(fontParam);

    NinePatchDrawable button = new NinePatchDrawable(
        new NinePatch(new TextureRegion(ui, 190, 0, 60, 60), 25, 25, 25, 25));
    ImageButton.ImageButtonStyle iconButton = new ImageButton.ImageButtonStyle(button, button, button, null, null, null);
    skin.add("ImageButtonSkin", iconButton);
    TextButton.TextButtonStyle gameButtonStyle = new TextButton.TextButtonStyle(button, button, button, font);
    skin.add("TextButtonSkin", gameButtonStyle);

    Label.LabelStyle labelStyle = new Label.LabelStyle(font, Color.DARK_GRAY);
    skin.add("LabelSkin", labelStyle);

    TextureRegionDrawable skill1Skin = new TextureRegionDrawable(
        new TextureRegion(ui, 0, 248, 8, 8)
    );
    skin.add("SkillSkin", skill1Skin);
    TextureRegionDrawable skill2Skin = new TextureRegionDrawable(
        new TextureRegion(ui, 8, 248, 8, 8)
    );
    skin.add("Skill2Skin", skill2Skin);

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
    gui.setDebug(true);
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
