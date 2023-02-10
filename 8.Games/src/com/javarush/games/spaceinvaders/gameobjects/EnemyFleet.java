package com.javarush.games.spaceinvaders.gameobjects;

import com.javarush.engine.cell.Game;
import com.javarush.games.spaceinvaders.Direction;
import com.javarush.games.spaceinvaders.ShapeMatrix;
import com.javarush.games.spaceinvaders.SpaceInvadersGame;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EnemyFleet {
    private static final int ROWS_COUNT = 3;
    private static final int COLUMNS_COUNT = 10;
    private static final int STEP = ShapeMatrix.ENEMY.length + 1;
    private List<EnemyShip> ships;
    private Direction direction = Direction.RIGHT;

    public EnemyFleet() {
        createShips();
    }

    private void createShips() {
        ships = new ArrayList<>();
        for (int y = 0; y < ROWS_COUNT; y++) {
            for (int x = 0; x < COLUMNS_COUNT; x++) {
                ships.add(new EnemyShip(x * STEP, y * STEP + 12));
            }
        }
        ships.add(new Boss(STEP * COLUMNS_COUNT / 2.0 - ShapeMatrix.BOSS_ANIMATION_FIRST.length / 2.0 - 1 , 5));
    }

    public void draw(Game game) {
        for (EnemyShip ship : ships) {
            ship.draw(game);
        }
    }

    public void move() {
        if (ships.isEmpty()) {
            return;
        }

        boolean needToMoveDown = false;
        if (direction == Direction.LEFT && getLeftBorder() < 0) {
            direction = Direction.RIGHT;
            needToMoveDown = true;
        }

        if (direction == Direction.RIGHT && getRightBorder() > SpaceInvadersGame.WIDTH) {
            direction = Direction.LEFT;
            needToMoveDown = true;
        }

        for (EnemyShip ship : ships) {
            ship.move(needToMoveDown ? Direction.DOWN : direction, getSpeed());
        }
    }

    public Bullet fire(Game game) {
        if (ships.isEmpty()) {
            return null;
        }

        if (game.getRandomNumber(100 / SpaceInvadersGame.COMPLEXITY) > 0) {
            return null;
        }

        return ships.get(game.getRandomNumber(ships.size())).fire();
    }

    public int verifyHit(List<Bullet> bullets) {
        if (bullets.isEmpty()) {
            return 0;
        }

        int totalScore = 0;
        for (EnemyShip ship : ships) {
            for (Bullet bullet : bullets) {
                if (ship.isCollision(bullet) && ship.isAlive && bullet.isAlive) {
                    ship.kill();
                    bullet.kill();
                    totalScore += ship.score;
                    break;
                }
            }
        }
        return totalScore;
    }

    public void deleteHiddenShips() {
        Iterator<EnemyShip> it = ships.iterator();
        while (it.hasNext()) {
            EnemyShip enemyShip = it.next();
            if (!enemyShip.isVisible()) {
                it.remove();
            }
        }
    }

    public double getBottomBorder() {
        return ships.stream()
                .map(s -> s.y + s.height)
                .max(Double::compare)
                .orElse(0.0);
    }

    public int getShipsCount() {
        return ships.size();
    }

    private double getLeftBorder() {
        return ships.stream()
                .map(s -> s.x)
                .min(Double::compare)
                .orElse(0.0);
    }

    private double getRightBorder() {
        return ships.stream()
                .map(s -> s.x + s.width)
                .max(Double::compare)
                .orElse(0.0);
    }

    private double getSpeed() {
        return Math.min(2.0, 3.0 / ships.size());
    }
}
