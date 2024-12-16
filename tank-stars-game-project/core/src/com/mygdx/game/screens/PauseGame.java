package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.MyGame;

public class PauseGame implements Screen {
    private Texture pause;
    private MyGame game;

    public PauseGame(MyGame game) {
        this.setGame(game);
    }

    public Texture getPause() {
        return pause;
    }

    public void setPause(Texture pause) {
        this.pause = pause;
    }

    public MyGame getGame() {
        return game;
    }

    public void setGame(MyGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        setPause(new Texture(Gdx.files.internal("pause_menu.png")));
    }

    @Override
    public void render(float delta) {
        if (Gdx.input.getX() > 200 && Gdx.input.getX() < 480 && Gdx.input.getY() > 400 && Gdx.input.getY() < 670) {
            if (Gdx.input.justTouched()) {
                dispose();
                getGame().setScreen(new SelIceTank1(getGame()));      // HERE LOAD GAME SCREEN AGAIN
            }
        }

        if (Gdx.input.getX() > 1000 && Gdx.input.getX() < 1280 && Gdx.input.getY() > 400 && Gdx.input.getY() < 670) {
            if (Gdx.input.justTouched()) {
                dispose();
                getGame().setScreen(new SelFireTank1(getGame()));      // HERE RESTART GAME
            }
        }

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
}
