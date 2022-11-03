package com.javarush.task.task34.task3410.model;

public abstract class CollisionObject extends GameObject {

    public CollisionObject(int x, int y) {
        super(x, y);
    }

    public boolean isCollision(GameObject gameObject, Direction direction) {
        int newX = x;
        int newY = y;

        switch (direction) {
            case LEFT:
                newX -= Model.FIELD_CELL_SIZE;
                break;
            case RIGHT:
                newX += Model.FIELD_CELL_SIZE;
                break;
            case UP:
                newY -= Model.FIELD_CELL_SIZE;
                break;
            case DOWN:
                newY += Model.FIELD_CELL_SIZE;
                break;
        }

        return newX == gameObject.x && newY == gameObject.y;
    }
}
