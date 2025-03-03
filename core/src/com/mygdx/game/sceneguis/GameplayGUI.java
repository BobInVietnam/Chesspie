package com.mygdx.game.sceneguis;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.chesspieces.Piece;
import com.mygdx.game.settings.Settings;

import java.text.DecimalFormat;

public abstract class GameplayGUI extends SceneGUI {
  private Table pieceInfoBoard;
  private Image pieceAvatar;
  private Label pieceHealth;
  private PauseWindow pauseWindow;
  private SettingWindow settingWindow;

  private ImageButton skill1;
  private Table skillDescriptionPane;
  private Label skillName;
  private Label skillDescription;
  private Table skillSelectedMessagePane;
  private Label skillSelectedMessage;

  private Table enemyPieceInfoBoard;
  private Image enemyPieceAvatar;
  private Label enemyPieceHealth;

  private Label attack;
  private Label defense;
  private Label enemyAttack;
  private Label enemyDefense;

  private float timer;
  private Label timerDisplay;
  private Table timerBoard;
  private boolean timeRunning;

  private TextButton toggleHistoryButton;
  private ScrollPane historyBoard;
  private VerticalGroup blackHistory;
  private VerticalGroup whiteHistory;

  private Table turnIndicator;
  private Label turnIndicatorText;
  private static final boolean DEBUG_MODE = true;

  private int screenWidth;
  private int screenHeight;

  private SceneWindow winBoard;
  private Label winLabel;

  public GameplayGUI(Skin skin, int x, int y) {
    // Piece info table
    this.screenHeight = y;
    this.screenWidth = x;
    this.skin = skin;
    pauseWindow = new PauseWindow(skin) {
      @Override
      public void handleSettingButton() {
        settingWindow.getRoot().setVisible(true);
      }

      @Override
      public void handleResumeButton() {
        resumeButtonClicked();
      }

      @Override
      public void handleQuitButton() {
        quitButtonClicked();
      }
    };
    settingWindow = new SettingWindow(skin);
    windows.add(pauseWindow, settingWindow);

    createSkillButton();
    createSkillDescriptionPane();
    createSkillSelectedMessage();
    initializeStats();
    initializePlayerBoardComponents();
    createPlayerBoard();

    initializeEnemyBoardComponents();
    createEnemyBoard();
    createTimer();
    createTurnIndicator();
    createHistoryPane();
    createWinWindow();
    // Adding components to root table
    loadComponents();

    hideInfo();
    hideEnemyInfo();
    hideSkillInfo();
  }

  private void initializeStats() {
    attack = createLabel(skin, "0", 0.6f, false, false);
    enemyAttack = createLabel(skin, "0", 0.6f, false, false);
    defense = createLabel(skin, "0", 0.6f, false, false);
    enemyDefense = createLabel(skin, "0", 0.6f, false, false);
  }
  private void createSkillButton() {
    skill1 = new ImageButton(skin);
    skill1.addListener( new InputListener() {
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        buttonClicked();
        return true;
      }
      @Override
      public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
        buttonEnter();
      }
      @Override
      public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
        buttonExit();
      }
    });
    skill1.getImageCell().expand().fill();
    skill1.getStyle().imageUp = new TextureRegionDrawable(skin.getRegion("icon_skill1"));
    skill1.getStyle().imageChecked = new TextureRegionDrawable(skin.getRegion("icon_skill2"));
  }
  private void createSkillDescriptionPane() {
    skillDescriptionPane = createBlock(skin, true, DEBUG_MODE);
    skillName = createLabel(skin, "Skill", 1, true, false);
    skillName.setWrap(true);
    skillDescription = createLabel(
        skin, "deals damage, buffs allies or self and many other effects can happen if you push this button, deals damage, buffs allies or self and many other effects can happen if you push this button",
        1f, true, true);
    skillDescription.setWrap(true);
    skillDescriptionPane.add(skillName).center().fillX().expand().pad(10, 10, 0, 10);
    skillDescriptionPane.row();
    skillDescriptionPane.add(skillDescription).fill().expand().pad(10, 10, 10, 10);
  }
  private void createSkillSelectedMessage() {
    skillSelectedMessagePane = createBlock(skin, false, DEBUG_MODE);
    skillSelectedMessage = createLabel(skin, language.getUIGameplay("skillSelect"), 1, false, true);
    skillSelectedMessagePane.add(skillSelectedMessage);
    skillSelectedMessagePane.setVisible(false);
  }
  private void initializePlayerBoardComponents() {
    pieceInfoBoard = createBlock(skin, false, DEBUG_MODE);
    pieceAvatar = createIcon(skin, "icon_skill1");// Will be replaced with actual piece avatar assets
    pieceHealth = createLabel(skin, "0", 1f, false, false);
  }

  private void createPlayerBoard() {
    pieceInfoBoard.pad(15, 15, 15, 15);
    pieceInfoBoard.left();
    Table info = new Table();
    info.add(pieceHealth).center().expand().colspan(4);
    info.row();
    info.add(createIcon(skin, "icon_attack")).size(25).expandX();
    info.add(attack).expandX();
    info.add(createIcon(skin, "icon_defense")).size(25).expandX();
    info.add(defense).expandX();
    pieceInfoBoard.add(pieceAvatar).size(60).expandY();
    pieceInfoBoard.add(info).fill().expand();
  }
  private void initializeEnemyBoardComponents() {
    enemyPieceInfoBoard = createBlock(skin, false, DEBUG_MODE);
    enemyPieceAvatar = createIcon(skin, "icon_skill2");// Will be replaced with actual piece avatar assets
    enemyPieceHealth = createLabel(skin, "0", 1f, false, false);
  }
  private void createEnemyBoard() {
    enemyPieceInfoBoard.pad(15, 15, 15, 15);
    enemyPieceInfoBoard.right();
    Table info2 = new Table();
    info2.add(enemyPieceHealth).center().expand().colspan(4);
    info2.row();
    info2.add(createIcon(skin, "icon_attack")).size(25).expandX();
    info2.add(enemyAttack).expandX();
    info2.add(createIcon(skin, "icon_defense")).size(25).expandX();
    info2.add(enemyDefense).expandX();
    enemyPieceInfoBoard.add(info2).fill().expand();
    enemyPieceInfoBoard.add(enemyPieceAvatar).size(60).expandY();
  }
  private void createTimer() {
    timerBoard = createBlock(skin, false, DEBUG_MODE);
    String timerDisplayString = language.getUIGameplay("timer") + "0:00,00";
    timerDisplay = createLabel(skin, timerDisplayString, 0.8f, false, false);
    timerBoard.padLeft(15);
    timerBoard.add(timerDisplay).left();
  }
  private void createTurnIndicator() {
    turnIndicator = createBlock(skin, false, DEBUG_MODE);
    turnIndicatorText = createLabel(skin, "1.W", 0.8f, false, false);
    turnIndicator.add(turnIndicatorText);
  }
  private void createHistoryPane() {
    Table main = SceneGUI.createBlock(skin, false, DEBUG_MODE);
    blackHistory = new VerticalGroup();
    whiteHistory = new VerticalGroup();
    main.add(SceneGUI.createLabel(skin, "White", 0.6f, false, false)).expandX();
    main.add(SceneGUI.createLabel(skin, "Black", 0.6f, false, false)).expandX();
    main.row();
    main.add(whiteHistory).fill().expand();
    main.add(blackHistory).fill().expand();
    historyBoard = new ScrollPane(main, skin);
  }

  private void createWinWindow() {
    winBoard = new SceneWindow() {
      @Override
      public void dispose() {
        Settings settings = Settings.getInstance();
        settings.removeObserver(this);
      }

      @Override
      public void update(Settings settings) {
      }
    };
    winBoard.setRoot(SceneGUI.createWindow(skin, DEBUG_MODE));
    winLabel = SceneGUI.createLabel(skin, "Somet won! Press this to restart.", 1, false, false);
    winBoard.getRoot().setWidth(800);
    winBoard.getRoot().setHeight(200);
    winBoard.getRoot().add(winLabel);
    winBoard.getRoot().setVisible(false);
    winBoard.getRoot().addListener(new InputListener() {
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        winBoard.getRoot().setVisible(false);
        restartGame();
        return super.touchDown(event, x, y, pointer, button);
      }
    });
    windows.add(winBoard);
  }

  private void loadComponents() {
    root.setDebug(DEBUG_MODE);
    root.left().top().add(pieceInfoBoard).size(300f, 80f).expandX().left();
    root.add().expandX();
    root.add(enemyPieceInfoBoard).size(300f, 80f).expandX().right();
    root.row().left();
    Table t = new Table();
    t.setDebug(DEBUG_MODE);
    t.left().add(skill1).size(100).expand();
    t.add(skillDescriptionPane).prefWidth(300);
    root.add(t).expand();
    root.add(toggleHistoryButton).right();
    root.add(historyBoard).right().size(250, 400).colspan(2);
    root.row().left();
    root.add(timerBoard).size(320, 80).left();
    root.add(skillSelectedMessagePane).size(200, 50);
    root.add(turnIndicator).size(80, 80).right();
    root.setFillParent(true);
  }

  public abstract void buttonClicked();
  public abstract void buttonEnter();
  public abstract void buttonExit();
  public abstract void resumeButtonClicked();
  public abstract void quitButtonClicked();
  public void skillButtonCheck(boolean checked) {
    skill1.setChecked(checked);
  }
  public void setSkillSelectedMessageDisplay(boolean checked) {
    skillSelectedMessagePane.setVisible(checked);
  }
  public void hideInfo() {
    pieceInfoBoard.setVisible(false);
    skill1.setVisible(false);
  }

  public void showInfo(Piece piece) {
    pieceInfoBoard.setVisible(true);
    pieceHealth.setText(piece.getHp() + "/" + piece.getMaxHp());
    attack.setText(piece.getAttack());
    defense.setText(piece.getDefense());
    skill1.setVisible(true);
  }
  public void hideEnemyInfo() {
    enemyPieceInfoBoard.setVisible(false);
  }
  public void showEnemyInfo(Piece piece) {
    enemyPieceInfoBoard.setVisible(true);
    enemyPieceHealth.setText(piece.getHp() + "/" + piece.getMaxHp());
    enemyAttack.setText(piece.getAttack());
    enemyDefense.setText(piece.getDefense());
  }
  public void hideSkillInfo() {
    skillDescriptionPane.setVisible(false);
  }
  public void showSkillInfo(Piece piece) {
    skillDescriptionPane.setVisible(true);
    int skillId = piece.getChessSkill().getSkillID();
    skillName.setText(language.getSkillName(skillId));
    skillDescription.setText(language.getSkillDescription(skillId));
  }
  public void showPauseWindow() {
    pauseWindow.getRoot().setVisible(true);
    getRoot().setTouchable(Touchable.disabled);
    timeRunning = false;
  }
  public void hidePauseWindow() {
    pauseWindow.getRoot().setVisible(false);
    getRoot().setTouchable(Touchable.enabled);
    timeRunning = true;
  }
  public void setPieceAvatar(Drawable image) {
    pieceAvatar.setDrawable(image);
  }
  public void setTimer(float s) {
    timer = s;
    int min = (int) (timer / 60);
    float sec = timer%60;
    DecimalFormat df = new DecimalFormat("00.00");
    timerDisplay.setText(language.getUIGameplay("timer") + min + ":" + df.format(sec));
  }
  public void startTimer() {
    timeRunning = true;
  }
  public void stopTimer() {
    timeRunning = false;
  }
  public void countdown() {
    if (!timeRunning) return;
    if (timer < 0) {
      String timerDisplayString = language.getUIGameplay("timer") + "0:00.00";
      timerDisplay.setText(timerDisplayString);
      timeup();
      timeRunning = false;
      return;
    }
    timer -= Gdx.graphics.getDeltaTime();
    setTimer(timer);
  }
  public abstract void timeup();
  public void setTurnIndicatorText(boolean whiteTurn, int number) {
    if (whiteTurn) {
      turnIndicatorText.setText(number + ".W");
    } else {
      turnIndicatorText.setText(number + ".B");
    }
  }
  public void addHistory(Label move, boolean whiteTurn) {
    if (whiteTurn) {
      whiteHistory.addActor(move);
    } else {
      blackHistory.addActor(move);
    }
  }
  public void clearHistory() {
    whiteHistory.clear();
    blackHistory.clear();
  }
  public void displayWin(boolean isWhite) {
    doWinAction();
    if (isWhite) {
      winLabel.setText("White won! Press this to restart.");
    } else {
      winLabel.setText("Black won! Press this to restart.");
    }
    winBoard.getRoot().setVisible(true);
    winBoard.getRoot().setWidth(winLabel.getWidth() + 80);
    winBoard.getRoot().setPosition(
        (float) Gdx.graphics.getWidth() / 2 - winBoard.getRoot().getWidth() / 2,
        (float) Gdx.graphics.getHeight() / 2 - winBoard.getRoot().getHeight() / 2
    );
  }

  public abstract void doWinAction();
  public abstract void restartGame();
  @Override
  public void update(Settings settings) {
    System.out.println("Gameplay updated");
    setTimer(timer);
  }
  public void dispose() {
    Settings settings = Settings.getInstance();
    pauseWindow.dispose();
    settingWindow.dispose();
    settings.removeObserver(this);
  }
}
