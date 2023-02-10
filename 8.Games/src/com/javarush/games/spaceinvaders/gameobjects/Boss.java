package com.javarush.games.spaceinvaders.gameobjects;

import com.javarush.games.spaceinvaders.Direction;
import com.javarush.games.spaceinvaders.ShapeMatrix;

public class Boss extends EnemyShip {
    private int frameCount = 0;

    public Boss(double x, double y) {
        super(x, y);
        setAnimatedView(true, ShapeMatrix.BOSS_ANIMATION_FIRST,
                        ShapeMatrix.BOSS_ANIMATION_SECOND);
        score = 100;
    }

    @Override
    public void nextFrame() {
        frameCount++;
        if (frameCount % 10 == 0 || !isAlive) {
            super.nextFrame();
        }
    }

    @Override
    public Bullet fire() {
        if (!isAlive) {
            return null;
        }

        double bulletX = (matrix == ShapeMatrix.BOSS_ANIMATION_FIRST) ? x + 6 : x;
        return new Bullet(bulletX, y + height, Direction.DOWN);
    }

    @Override
    public void kill() {
        if (!isAlive) {
            return;
        }

        isAlive = false;
        setAnimatedView(false, ShapeMatrix.KILL_BOSS_ANIMATION_FIRST,
                        ShapeMatrix.KILL_BOSS_ANIMATION_SECOND,
                        ShapeMatrix.KILL_BOSS_ANIMATION_THIRD);
    }
}
