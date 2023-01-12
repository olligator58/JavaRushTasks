package com.javarush.games.snake;

import com.javarush.engine.cell.*;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    public boolean isAlive = true;
    private List<GameObject> snakeParts = new ArrayList<>();
    private static final String HEAD_SIGN = "\uD83D\uDC7E";
    private static final String BODY_SIGN = "\u26AB";
    private Direction direction = Direction.LEFT;

    public Snake(int x, int y) {
        snakeParts.add(new GameObject(x, y));
        snakeParts.add(new GameObject(x + 1, y));
        snakeParts.add(new GameObject(x + 2, y));
    }

    public void setDirection(Direction direction) {
        if ((this.direction == Direction.LEFT || this.direction == Direction.RIGHT) &&
             snakeParts.get(0).x == snakeParts.get(1).x) {
            return;
        }

        if ((this.direction == Direction.DOWN || this.direction == Direction.UP) &&
            snakeParts.get(0).y == snakeParts.get(1).y) {
            return;
        }

        if (direction == Direction.LEFT && this.direction != Direction.RIGHT ||
                direction == Direction.RIGHT && this.direction != Direction.LEFT ||
                direction == Direction.UP && this.direction != Direction.DOWN ||
                direction == Direction.DOWN && this.direction != Direction.UP) {
            this.direction = direction;
        }
    }

    public void draw(Game game) {
        Color snakeColor = (isAlive) ? Color.BLACK : Color.RED;
        game.setCellValueEx(snakeParts.get(0).x, snakeParts.get(0).y, Color.NONE, HEAD_SIGN, snakeColor, 75);
        for (int i = 1; i < snakeParts.size(); i++) {
            game.setCellValueEx(snakeParts.get(i).x, snakeParts.get(i).y, Color.NONE, BODY_SIGN, snakeColor, 75);
        }
    }

    public void move(Apple apple) {
        GameObject newHead = createNewHead();
        if (checkCollision(newHead)) {
            isAlive = false;
            return;
        }

        if (newHead.x < 0 || newHead.x >= SnakeGame.WIDTH || newHead.y < 0 || newHead.y >= SnakeGame.HEIGHT) {
            isAlive = false;
            return;
        }

        snakeParts.add(0, newHead);
        if (newHead.x == apple.x && newHead.y == apple.y) {
            apple.isAlive = false;
        } else {
            removeTail();
        }
    }

    public GameObject createNewHead() {
        int headX = snakeParts.get(0).x;
        int headY = snakeParts.get(0).y;

        switch (direction) {
            case LEFT:
                return new GameObject(headX - 1, headY);
            case RIGHT:
                return new GameObject(headX + 1, headY);
            case UP:
                return new GameObject(headX, headY - 1);
            case DOWN:
                return new GameObject(headX, headY + 1);
            default:
                return null;
        }
    }

    public void removeTail() {
        snakeParts.remove(snakeParts.size() - 1);
    }

    public boolean checkCollision(GameObject gameObject) {
        for (GameObject snakePart : snakeParts) {
            if (snakePart.x == gameObject.x && snakePart.y == gameObject.y) {
                return true;
            }
        }
        return false;
    }

    public int getLength() {
        return snakeParts.size();
    }
}
