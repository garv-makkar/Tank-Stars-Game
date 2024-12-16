package com.mygdx.game.screens;

import com.badlogic.gdx.math.Vector2;

public class TrajectoryEquation {
        public float gravity;
        public TrajectoryEquation(float gravity) {
            this.gravity = gravity;
        }
        public Vector2 startVelocity = new Vector2();
        public Vector2 startPoint = new Vector2();

        public float getX(float t) {
            return startVelocity.x * t + startPoint.x;
        }

        public float getY(float t) {
            return ((1/2) * gravity * t * t) + (startVelocity.y * t) + startPoint.y;
        }
}
