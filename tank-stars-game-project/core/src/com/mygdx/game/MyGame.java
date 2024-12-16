package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.screens.GameMenuScreen;

public class MyGame extends Game {
	public static final int WIDTH = 1600;
	public static final int HEIGHT = 900;
	public SpriteBatch batch;

	public static AssetManager manager;


	@Override
	public void create() {
		batch = new SpriteBatch();
		manager=new AssetManager();
		manager.load("AudioFiles/gameMusics/HEROICCC.mp3", Music.class);
//		manager.load("AudioFiles/gamesounds/fire.mp3", Sound.class);
		manager.load("AudioFiles/gameSounds/tankrunning.mp3", Sound.class);
//		manager.load("AudioFiles/gamesounds/damage.mp3", Sound.class);
//		manager.load("AudioFiles/gamesounds/fight.mp3",Sound.class);
//		manager.load("AudioFiles/gamesounds/gameover.mp3",Sound.class);
//		manager.load("AudioFiles/gamesounds/.mp3",Sound.class);
		manager.finishLoading();
		this.setScreen(new GameMenuScreen(this));

	}


	@Override
	public void render() {
		super.render();
	}
	@Override
	public void dispose() {
		batch.dispose();
		super.dispose();
		manager.dispose();
	}

}
