package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.MyGame;

public class TankSelectionScreen1 implements Screen {
    private Texture p1Tank;
    private MyGame game;
    public static int tankType;

    public TankSelectionScreen1(MyGame game) {
        this.setGame(game);
    }

    @Override

        public void show() {
            setP1Tank(new Texture(Gdx.files.internal("TankSelectionPics/player1TankSelection.png")));
        }


    @Override
    public void render(float delta) {

        if (Gdx.input.isTouched()) {
            System.out.println("touched at " + Gdx.input.getX() + " " + Gdx.input.getY() + " " + "in tank selection screen 1");
        }

        //NEXT BUTTON
        if (Gdx.input.getX() > 1290 && Gdx.input.getX() < 1560 && Gdx.input.getY() > 45 && Gdx.input.getY() < 170) {
            if (Gdx.input.justTouched()) {
                dispose();
                getGame().setScreen(new TankSelectionScreen2(getGame()));
            }
        }

        // BACK BUTTON
        if (Gdx.input.getX() > 50 && Gdx.input.getX() < 130 && Gdx.input.getY() > 50 && Gdx.input.getY() < 130) {
            if (Gdx.input.justTouched()) {
                dispose();
                getGame().setScreen(new GameMenuScreen(getGame()));
            }
        }

        //SELECTING ICE TANK
        if (Gdx.input.getX() > 200 && Gdx.input.getX() < 480 && Gdx.input.getY() > 390 && Gdx.input.getY() < 690) {
            if (Gdx.input.justTouched()) {
                dispose();
                getGame().setScreen(new SelIceTank1(getGame()));   // HERE ICE TANK SELECTION CLASS CALLED
            }
            tankType = 1;
        }

        //SELECTING ARMY TANK
        if (Gdx.input.getX() > 650 && Gdx.input.getX() < 960 && Gdx.input.getY() > 390 && Gdx.input.getY() < 690) {
            if (Gdx.input.justTouched()) {
                dispose();
                getGame().setScreen(new SelArmyTank1(getGame()));      // HERE ARMY TANK SELECTION CLASS CALLED
            }
            tankType = 2;
        }

        //SELECTING FIRE TANK
        if (Gdx.input.getX() > 1140 && Gdx.input.getX() < 1450 && Gdx.input.getY() > 390 && Gdx.input.getY() < 690) {
            if (Gdx.input.justTouched()) {
                dispose();
                getGame().setScreen(new SelFireTank1(getGame()));      // HERE FIRE TANK SELECTION CLASS CALLED
            }
            tankType = 3;
        }

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        getGame().batch.begin();
        getGame().batch.draw(getP1Tank(),0,0,MyGame.WIDTH,MyGame.HEIGHT);
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

    public Texture getP1Tank() {
        return p1Tank;
    }

    public void setP1Tank(Texture p1Tank) {
        this.p1Tank = p1Tank;
    }

    public int getTankType() {
        return tankType;
    }

    public void setTankType(int tankType) {
        this.tankType = tankType;
    }

    public MyGame getGame() {
        return game;
    }

    public void setGame(MyGame game) {
        this.game = game;
    }
}
