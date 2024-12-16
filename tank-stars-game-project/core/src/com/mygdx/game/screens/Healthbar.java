package com.mygdx.game.screens;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;

import javax.swing.*;
public class Healthbar extends ProgressBar {
    public double maxHealth=1000;
    private double currentHealth=1000;
    public Healthbar(int width, int height) {
        super(0f, 1f, 0.01f, false, new ProgressBar.ProgressBarStyle());
        getStyle().background = com.mygdx.game.screens.Utils.getColoredDrawable(width, height, Color.BLUE);
        getStyle().knob = com.mygdx.game.screens.Utils.getColoredDrawable(0, height, Color.YELLOW);
        getStyle().knobBefore = Utils.getColoredDrawable(width, height, Color.YELLOW);

        setWidth(width);
        setHeight(height);
        setValue(1f);
        setAnimateDuration(0.25f);
    }

    public double getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(double currentHealth) {
        this.currentHealth = currentHealth;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }

}