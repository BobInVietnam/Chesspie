package com.mygdx.game.sceneguis;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.mygdx.game.settings.Settings;
import com.mygdx.game.settings.SettingsObserver;

public abstract class TutorialGUI extends SceneGUI{
  private Label title;
  private Label line1;
  private Label line2;
  private Texture tutorialImageTexture;
  private Texture tutorialImageTexture2;
  private Image tutorialImage1;
  private TextButton continueButton;
  private TextButton skipButton;
  private int counter;
  private static final boolean DEBUG_MODE = false;
  public TutorialGUI(Skin skin) {
    this.skin = skin;
    createImage1();
    createLabels(skin);
    createContinueButton(skin);
    createSkipButton(skin);
    counter = 2;
    loadComponents();
  }

  private void createImage1() {
    tutorialImageTexture = new Texture(Gdx.files.internal("Images/tutorial_stats.png"));
    tutorialImageTexture2 = new Texture(Gdx.files.internal("Images/tutorial_skill.png"));
    tutorialImage1 = new Image(tutorialImageTexture);
    tutorialImage1.setScaling(Scaling.fit);
  }

  private void createLabels(Skin skin) {
    title = createLabel(skin, language.getTutorialLines("title"), 1.4f, false, false);
    line1 = createLabel(skin, language.getTutorialLines("l1"), 1f, false, true);
    line1.setWrap(true);
    line2 = createLabel(skin, language.getTutorialLines("l2"), 1f, false, true);
    line2.setWrap(true);
  }

  private void createContinueButton(Skin skin) {
    continueButton = createTextButton(skin, language.getTutorialLines("button"), false);
    continueButton.addListener(new InputListener() {
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        continueButtonClicked();
        continueButton.setChecked(true);
        return true;
      }
    });
  }

  private void createSkipButton(Skin skin) {
    skipButton = createTextButton(skin, language.getTutorialLines("button2"), false);
    skipButton.addListener(new InputListener() {
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        switchScene();
        return true;
      }
    });
  }

  private void loadComponents() {
    root = createWindow(skin, DEBUG_MODE);
    root.add(title).colspan(2).expandX();
    root.row();
    root.add(line1).width(Value.percentWidth(0.4f, root)).expand();
    root.add(tutorialImage1).fill().expand();
    root.row();
    root.add(line2).expandY().colspan(2).width(Value.percentWidth(0.75f, root));
    root.row();
    root.add(continueButton).width(Value.percentWidth(0.2f, root));
    root.add(skipButton).width(Value.percentWidth(0.2f, root));
    root.setFillParent(true);
  }
  public void loadSecondPage() {
    tutorialImage1.setDrawable(new TextureRegionDrawable(tutorialImageTexture2));
    root.clearChildren();
    root.add(title).colspan(2).expandX();
    Table nested = new Table();
    nested.add(line1).width(Value.percentWidth(0.8f, nested))
        .getActor().setText(language.getTutorialLines("l3"));
    nested.row();
    nested.add(line2).width(Value.percentWidth(0.8f, nested))
        .getActor().setText(language.getTutorialLines("l4"));
    root.row();
    root.add(nested).expand().fill();
    root.add(tutorialImage1).expand().fill(1f, 0.8f);
    root.row();
    root.add(continueButton).width(Value.percentWidth(0.2f, root));
    root.add(skipButton).width(Value.percentWidth(0.2f, root));
  }
  public void loadThirdPage() {
    root.clearChildren();
    root.add(title).colspan(2).expandX();
    root.row();
    root.add(line1).width(Value.percentWidth(0.75f, root)).expand().colspan(2)
        .getActor().setText(language.getTutorialLines("l5"));
    root.row();
    root.add(line2).width(Value.percentWidth(0.75f, root)).expand().colspan(2)
        .getActor().setText(language.getTutorialLines("l6"));
    root.row();
    root.add(continueButton).width(Value.percentWidth(0.2f, root));
    root.add(skipButton).width(Value.percentWidth(0.2f, root));
  }
  public abstract void switchScene();
  public void continueButtonClicked() {
    switch (counter) {
      case 2:
        loadSecondPage();
        counter--;
        break;
      case 1:
        loadThirdPage();
        counter--;
        break;
      default:
        switchScene();
    }
  };
  @Override
  public void update(Settings settings) {
  }
  public void dispose() {
    tutorialImageTexture.dispose();
    tutorialImageTexture2.dispose();
    Settings settings = Settings.getInstance();
    settings.removeObserver(this);
  }
}
