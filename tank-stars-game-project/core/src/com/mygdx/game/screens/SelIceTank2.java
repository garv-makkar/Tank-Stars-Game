package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.MyGame;

public class SelIceTank2 implements Screen {
    private Texture iceTankWhite;
    private MyGame game;

    public SelIceTank2(MyGame game) {
        this.setGame(game);
    }

    @Override

    public void show() {
        setIceTankWhite(new Texture(Gdx.files.internal("TankSelectionPics/p21.png")));
    }


    @Override
    public void render(float delta) {

        if (Gdx.input.isTouched()) {
            System.out.println("touched at " + Gdx.input.getX() + " " + Gdx.input.getY() + " " + "in tank selection screen 1");
        }

        //NEXT BUTTON
        if (Gdx.input.getX() > 1290 && Gdx.input.getX() < 1560 && Gdx.input.getY() > 45 && Gdx.input.getY() < 170) {
            if (Gdx.input.justTouched()) {
                System.out.println("touched at " + Gdx.input.getX() + " " + Gdx.input.getY() + " "+ "in tank selection screen 1");
                dispose();
                getGame().setScreen(new TankSelectionScreen2(getGame()));
            }
        }

        // BACK BUTTON
        if (Gdx.input.getX() > 50 && Gdx.input.getX() < 130 && Gdx.input.getY() > 50 && Gdx.input.getY() < 130) {
            if (Gdx.input.justTouched()) {
                System.out.println("touched at " + Gdx.input.getX() + " " + Gdx.input.getY() + " "+ "in tank selection screen 1");
                dispose();
                getGame().setScreen(new TankSelectionScreen1(getGame()));
            }
        }

        //SELECTING ICE TANK
//        if (Gdx.input.getX() > 200 && Gdx.input.getX() < 480 && Gdx.input.getY() > 400 && Gdx.input.getY() < 670) {
//            if (Gdx.input.justTouched()) {
//                dispose();
//                game.setScreen(new SelIceTank2(game ));      // HERE ICE TANK SELECTION CLASS CALLED
//            }
//        }

        //SELECTING ARMY TANK
        if (Gdx.input.getX() > 600 && Gdx.input.getX() < 880 && Gdx.input.getY() > 400 && Gdx.input.getY() < 670) {
            if (Gdx.input.justTouched()) {
                dispose();
                getGame().setScreen(new SelArmyTank2(getGame()));      // HERE ARMY TANK SELECTION CLASS CALLED
            }
        }

        //SELECTING FIRE TANK
        if (Gdx.input.getX() > 1000 && Gdx.input.getX() < 1280 && Gdx.input.getY() > 400 && Gdx.input.getY() < 670) {
            if (Gdx.input.justTouched()) {
                dispose();
                getGame().setScreen(new SelFireTank2(getGame()));      // HERE FIRE TANK SELECTION CLASS CALLED
            }
        }


        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        getGame().batch.begin();
        getGame().batch.draw(getIceTankWhite(),0,0,MyGame.WIDTH,MyGame.HEIGHT);
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

    public Texture getIceTankWhite() {
        return iceTankWhite;
    }

    public void setIceTankWhite(Texture iceTankWhite) {
        this.iceTankWhite = iceTankWhite;
    }

    public MyGame getGame() {
        return game;
    }

    public void setGame(MyGame game) {
        this.game = game;
    }
}

