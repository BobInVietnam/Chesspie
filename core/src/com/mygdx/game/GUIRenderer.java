package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.sceneguis.SceneGUI;
import com.mygdx.game.sceneguis.SceneWindow;
import com.mygdx.game.settings.Settings;
import com.mygdx.game.settings.SettingsObserver;

public class GUIRenderer implements SettingsObserver {
  private static GUIRenderer guiRenderer;
  private final Stage stage;
  private SceneGUI sceneGUI;
  private Skin skin;
  private TextureAtlas atlas;

  private GUIRenderer() {
    float ratio = (float) Gdx.graphics.getHeight() / Gdx.graphics.getWidth();
    stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
    sceneGUI = null;
    skin = new Skin(Gdx.files.internal("Images/normal_skin.json")) {
      //Override json loader to process FreeType fonts from skin JSON
      @Override
      protected Json getJsonLoader(final FileHandle skinFile) {
        Json json = super.getJsonLoader(skinFile);
        final Skin skin = this;

        json.setSerializer(FreeTypeFontGenerator.class, new Json.ReadOnlySerializer<FreeTypeFontGenerator>() {
          @Override
          public FreeTypeFontGenerator read(Json json,
                                            JsonValue jsonData, Class type) {
            String path = json.readValue("font", String.class, jsonData);
            jsonData.remove("font");

            FreeTypeFontGenerator.Hinting hinting = FreeTypeFontGenerator.Hinting.valueOf(json.readValue("hinting",
                String.class, "AutoMedium", jsonData));
            jsonData.remove("hinting");

            Texture.TextureFilter minFilter = Texture.TextureFilter.valueOf(
                json.readValue("minFilter", String.class, "Nearest", jsonData));
            jsonData.remove("minFilter");

            Texture.TextureFilter magFilter = Texture.TextureFilter.valueOf(
                json.readValue("magFilter", String.class, "Nearest", jsonData));
            jsonData.remove("magFilter");

            FreeTypeFontGenerator.FreeTypeFontParameter parameter = json.readValue(FreeTypeFontGenerator.FreeTypeFontParameter.class, jsonData);
            parameter.hinting = hinting;
            parameter.minFilter = minFilter;
            parameter.magFilter = magFilter;
            FreeTypeFontGenerator generator = new FreeTypeFontGenerator(skinFile.parent().child(path));
            BitmapFont font = generator.generateFont(parameter);
            skin.add(jsonData.name, font);
            if (parameter.incremental) {
              generator.dispose();
              return null;
            } else {
              return generator;
            }
          }
        });
        return json;
      }
    };
    atlas = new TextureAtlas(Gdx.files.internal("Images/normal_skin.atlas"));
    skin.addRegions(atlas);
    Gdx.input.setInputProcessor(stage);
  }

  public static GUIRenderer getInstance() {
    if (guiRenderer == null) {
      guiRenderer = new GUIRenderer();
    }
    return guiRenderer;
  }
  public Skin getSkin() {
    return skin;
  }

  public Stage getStage(){
    return stage;
  }

  public void loadGUI(SceneGUI gui) {
    Settings settings = Settings.getInstance();
    stage.clear();
    this.sceneGUI = gui;
    settings.addObserver(gui);
    stage.addActor(sceneGUI.getRoot());
    for (SceneWindow t: sceneGUI.getWindows()) {
      stage.addActor(t.getRoot());
      settings.addObserver(t);
    }
  }

  public void render(float delta) {
    stage.act(delta);
    stage.draw();
  }

  public void resize (int width, int height) {
    stage.getViewport().update(width, height, true);
  }

  public void dispose() {
    atlas.dispose();
  }

  @Override
  public void update(Settings settings) {

  }
}
