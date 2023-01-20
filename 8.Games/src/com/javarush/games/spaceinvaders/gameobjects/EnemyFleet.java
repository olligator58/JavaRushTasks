package com.javarush.games.spaceinvaders.gameobjects;

import com.javarush.engine.cell.Game;
import com.javarush.games.spaceinvaders.Direction;
import com.javarush.games.spaceinvaders.ShapeMatrix;
import com.javarush.games.spaceinvaders.SpaceInvadersGame;

import java.util.ArrayList;
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
