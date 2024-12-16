package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.MyGame;

public class PauseMenuScreen implements Screen {
    private Texture pauseImage;
    private MyGame game;

    public PauseMenuScreen(MyGame game) {
        this.setGame(game);
    }

    @Override
    public void show() {
        setPauseImage(new Texture(Gdx.files.internal("pause_menu.png")));
    }

    @Override
    public void render(float delta) {
        if(Gdx.input.justTouched()) {
            System.out.println("touched at " + Gdx.input.getX() + " " + Gdx.input.getY() + " " + "in tank selection screen 1");
        }

        if (Gdx.input.getX() > 1060 && Gdx.input.getX() < 1520 && Gdx.input.getY() > 94 && Gdx.input.getY() < 270) {
            if (Gdx.input.justTouched()) {
                System.out.println("touched at " + Gdx.input.getX() + " " + Gdx.input.getY() + " "+ "in menu screen");
                dispose();
                getGame().setScreen(new TankSelectionScreen1(getGame()));
            }
        }


        if (Gdx.input.getX() > 1060 && Gdx.input.getX() < 1520 && Gdx.input.getY() > 350 && Gdx.input.getY() < 530) {
            if (Gdx.input.justTouched()) {
                System.out.println("touched at " + Gdx.input.getX() + " " + Gdx.input.getY() + " "+ "in menu screen");
                dispose();
                getGame().setScreen(new SaveGameScreen(getGame()));
            }
        }

        if (Gdx.input.getX() > 1060 && Gdx.input.getX() < 1520 && Gdx.input.getY() > 610 && Gdx.input.getY() < 790) {
            if (Gdx.input.justTouched()) {
                System.out.println("touched at " + Gdx.input.getX() + " " + Gdx.input.getY() + " "+ "in menu screen");
                dispose();
                getGame().setScreen(new ExitConfirmationScreen(getGame()));
            }
        }

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        getGame().batch.begin();
        getGame().batch.draw(getPauseImage(),0,0,MyGame.WIDTH,MyGame.HEIGHT);
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

    public Texture getPauseImage() {
        return pauseImage;
    }

    public void setPauseImage(Texture pauseImage) {
        this.pauseImage = pauseImage;
    }

    public MyGame getGame() {
        return game;
    }

    public void setGame(MyGame game) {
        this.game = game;
    }
}
