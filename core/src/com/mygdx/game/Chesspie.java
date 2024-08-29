package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.screens.GameplayScreen;
import com.mygdx.game.screens.TitleScreen;
//import com.mygdx.game.screens.TitleScreen;

public class Chesspie extends Game {
	public SpriteBatch batch;
	public GUIRenderer gui;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		gui = new GUIRenderer();
		this.setScreen(new TitleScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		gui.dispose();
	}
}
