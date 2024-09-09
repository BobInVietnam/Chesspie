package com.mygdx.game.settings;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.utils.Array;

public class Settings {
  private static Settings settings;
  private Array<SettingsObserver> observers;
  private Preferences prefs;
  private Settings() {
    observers = new Array<>();
    prefs = Gdx.app.getPreferences("ChesspieSettings");
  }
  public static Settings getInstance() {
    if (settings == null) {
      settings = new Settings();
    }
    return settings;
  }
  public void addObserver(SettingsObserver settingsObserver) {
    observers.add(settingsObserver);
  }
  public void removeObserver(SettingsObserver settingsObserver) {
    observers.removeValue(settingsObserver, false);
  }
  public void notifyObservers() {
    prefs.flush();
    for (SettingsObserver s: observers) {
      s.update(this);
    }
  }
  public String getLanguage() {
    return prefs.getString("language", "English");
  }
  public void setLanguage(String language) {
    prefs.putString("language", language);
  }
}
