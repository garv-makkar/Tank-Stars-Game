package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.MyGame;

public class SaveGameScreen implements Screen {

	private Texture saveGameMenuImage;
	private MyGame game;

	public SaveGameScreen(MyGame game) {
		this.setGame(game);
	}

	@Override
	public void show() {
			setSaveGameMenuImage(new Texture(Gdx.files.internal("SavedGamesImage.png")));
	}

	@Override
	public void render(float delta) {

		// BACK BUTTON
		if (Gdx.input.getX() > 50 && Gdx.input.getX() < 130 && Gdx.input.getY() > 50 && Gdx.input.getY() < 130) {
			if (Gdx.input.justTouched()) {
				dispose();
				getGame().setScreen(new GameMenuScreen(getGame()));
			}
		}


		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		getGame().batch.begin();
		getGame().batch.draw(getSaveGameMenuImage(),0,0,MyGame.WIDTH,MyGame.HEIGHT);
		getGame().batch.end();

	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose() {

	}

	public Texture getSaveGameMenuImage() {
		return saveGameMenuImage;
	}

	public void setSaveGameMenuImage(Texture saveGameMenuImage) {
		this.saveGameMenuImage = saveGameMenuImage;
	}

	public MyGame getGame() {
		return game;
	}

	public void setGame(MyGame game) {
		this.game = game;
	}
}
