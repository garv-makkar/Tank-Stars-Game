package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.MyGame;

public class ExitConfirmationScreen implements Screen {

	private Texture exitConfirmationMenuImage;
	private MyGame game;

	public ExitConfirmationScreen(MyGame game) {
		this.setGame(game);
	}

	@Override
	public void show() {
		setExitConfirmationMenuImage(new Texture(Gdx.files.internal("ExitConfirmationImage.png")));
	}

	@Override
	public void render(float delta) {

		if (Gdx.input.isTouched()) {
			System.out.println("touched at " + Gdx.input.getX() + " " + Gdx.input.getY() + " " + "in tank selection screen 1");
		}

		// EXIT BUTTON
		if (Gdx.input.getX() > 400 && Gdx.input.getX() < 660 && Gdx.input.getY() > 450 && Gdx.input.getY() < 700) {
			if (Gdx.input.justTouched()) {
				dispose();
				Gdx.app.exit();
			}
		}

		// BACK BUTTON
		if (Gdx.input.getX() > 920 && Gdx.input.getX() < 1180 && Gdx.input.getY() > 450 && Gdx.input.getY() < 700) {
			if (Gdx.input.justTouched()) {
				dispose();
				getGame().setScreen(new GameMenuScreen(getGame()));
			}
		}

		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		getGame().batch.begin();
		getGame().batch.draw(getExitConfirmationMenuImage(),0,0,MyGame.WIDTH,MyGame.HEIGHT);
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

	public Texture getExitConfirmationMenuImage() {
		return exitConfirmationMenuImage;
	}

	public void setExitConfirmationMenuImage(Texture exitConfirmationMenuImage) {
		this.exitConfirmationMenuImage = exitConfirmationMenuImage;
	}

	public MyGame getGame() {
		return game;
	}

	public void setGame(MyGame game) {
		this.game = game;
	}
}
