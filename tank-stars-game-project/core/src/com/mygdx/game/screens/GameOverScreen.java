package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.MyGame;

public class GameOverScreen implements Screen {

    private Texture gameOver;
    private MyGame game;

    public GameOverScreen(MyGame game) {
        this.setGame(game);
    }

    @Override
    public void show() {
        gameOver(new Texture(Gdx.files.internal("gameover.png")));
    }

    private void gameOver(Texture texture) {
    }

    @Override
    public void render(float delta) {


        dispose();
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        getGame().batch.begin();
        getGame().batch.draw(getGameOver(),0,0,MyGame.WIDTH,MyGame.HEIGHT);
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

    public Texture getGameOver() {
        return gameOver;
    }

    public void setGameOver(Texture gameOver) {
        this.gameOver = gameOver;
    }

    public MyGame getGame() {
        return game;
    }

    public void setGame(MyGame game) {
        this.game = game;
    }

    @Override
    public void dispose() {

    }
}
