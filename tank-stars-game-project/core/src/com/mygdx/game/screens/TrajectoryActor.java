package com.mygdx.game.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.screens.TrajectoryEquation;

public class TrajectoryActor extends Actor {
    private TrajectoryEquation equation;
    private Texture texture;
    public float power = 10, angle = 0;

    public int trajectoryPointCount = 15;
    public float timeSeparation = 0.5f;

    public TrajectoryActor(float gravity) {
        texture = new Texture("projectile.jpg");
        this.equation = new TrajectoryEquation(gravity);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        equation.startVelocity.set(power, 0f);
        equation.startVelocity.rotate(angle);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        float t = 0f;
        float width = this.getWidth();
        float height = this.getHeight();

        float timeSeparation = this.timeSeparation;

        for (int i = 0; i<trajectoryPointCount; i++) {
            float x = this.getX() + equation.getX(t);
            float y = this.getY() + equation.getY(t);

            batch.setColor(this.getColor());
            batch.draw(texture, x, y, width, height);

            t += timeSeparation;
        }
    }

    @Override
    public Actor hit(float x, float y, boolean touchable) {
        return null;
    }
}