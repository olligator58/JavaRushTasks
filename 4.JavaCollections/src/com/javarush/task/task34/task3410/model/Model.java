package com.javarush.task.task34.task3410.model;

import com.javarush.task.task34.task3410.controller.EventListener;

import java.nio.file.Paths;

public class Model {
    public static final int FIELD_CELL_SIZE = 20;
    private EventListener eventListener;
    private GameObjects gameObjects;
    private int currentLevel = 1;
    private LevelLoader levelLoader = new LevelLoader(Paths.get("./4.JavaCollections/src/" +
            Model.class.getPackage().getName()
                    .replace(".", "/")
                    .replace("model", "res") +
                    "/levels.txt"));

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public GameObjects getGameObjects() {
        return gameObjects;
    }

    public void restartLevel(int level) {
        gameObjects = levelLoader.getLevel(level);
    }

    public void restart() {
        restartLevel(currentLevel);
    }

    public void startNextLevel() {
        currentLevel++;
        restart();
    }

    public void move(Direction direction) {
        Player player = gameObjects.getPlayer();

        if (checkWallCollision(player, direction)) {
            return;
        }

        if (checkBoxCollisionAndMoveIfAvailable(direction)) {
            return;
        }

        player.move(getDx(direction), getDy(direction));
        checkCompletion();
    }

    public boolean checkWallCollision(CollisionObject gameObject, Direction direction) {
        for (Wall wall : gameObjects.getWalls()) {
            if (gameObject.isCollision(wall, direction)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkBoxCollisionAndMoveIfAvailable(Direction direction) {
        Player player = gameObjects.getPlayer();
        for (Box box : gameObjects.getBoxes()) {
            if (player.isCollision(box, direction)) {
                // если за ящиком стена
                if (checkWallCollision(box, direction)) {
                    return true;
                }
                for (Box anotherBox : gameObjects.getBoxes()) {
                    //если за ящиком другой ящик
                    if (box.isCollision(anotherBox, direction)) {
                        return true;
                    }
                }
                box.move(getDx(direction), getDy(direction));
            }
        }
        return false;
    }

    public void checkCompletion() {
        boolean levelCompleted = true;
        for (Home home : gameObjects.getHomes()) {
            boolean boxFound = false;
            for (Box box : gameObjects.getBoxes()) {
                if (home.x == box.x && home.y == box.y) {
                    boxFound = true;
                    break;
                }
            }
            if (!boxFound) {
                levelCompleted = false;
                break;
            }
        }
        if (levelCompleted) {
            eventListener.levelCompleted(currentLevel);
        }
    }

    private int getDx(Direction direction) {
        switch (direction) {
            case LEFT:
                return  -FIELD_CELL_SIZE;
            case RIGHT:
                return FIELD_CELL_SIZE;
            default:
                return 0;
        }
    }

    private int getDy(Direction direction) {
        switch (direction) {
            case UP:
                return -FIELD_CELL_SIZE;
            case DOWN:
                return FIELD_CELL_SIZE;
            default:
                return 0;
        }
    }
}
