package com.javarush.games.racer;

import com.javarush.engine.cell.*;
import com.javarush.games.racer.road.RoadManager;

public class RacerGame extends Game {
    public static final int WIDTH = 64;
    public static final int HEIGHT = 64;
    public static final int CENTER_X = WIDTH / 2;
    public static final int ROADSIDE_WIDTH = 14;
    private RoadMarking roadMarking;
    private PlayerCar player;
    private RoadManager roadManager;
    private boolean isGameStopped;

    @Override
    public void initialize() {
        showGrid(false);
        setScreenSize(WIDTH, HEIGHT);
        createGame();
    }

    private void createGame() {
        roadMarking = new RoadMarking();
        player = new PlayerCar();
        roadManager = new RoadManager();
        setTurnTimer(40);
        isGameStopped = false;
        drawScene();
    }

    private void drawScene() {
        drawField();
        roadMarking.draw(this);
        player.draw(this);
        roadManager.draw(this);
    }

    private void drawField() {
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                Color color;
                // разделительная полоса
                if (x == CENTER_X) {
                    color = Color.WHITE;
                // дорога
                } else if (x >= ROADSIDE_WIDTH && x < WIDTH - ROADSIDE_WIDTH) {
                    color = Color.DIMGREY;
                // обочина
                } else {
                    color = Color.GREEN;
                }
                setCellColor(x, y, color);
            }
        }
    }

    @Override
    public void setCellColor(int x, int y, Color color) {
        if (x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT) {
            return;
        }
        super.setCellColor(x, y, color);
    }

    @Override
    public void onTurn(int step) {
        if (roadManager.checkCrush(player)) {
            gameOver();
            drawScene();
            return;
        }
        moveAll();
        roadManager.generateNewRoadObjects(this);
        drawScene();
    }

    @Override
    public void onKeyPress(Key key) {
        switch (key) {
            case LEFT:
                player.setDirection(Direction.LEFT);
                break;
            case RIGHT:
                player.setDirection(Direction.RIGHT);
                break;
            case SPACE:
                if (isGameStopped) {
                    createGame();
                }
                break;
            case UP:
                player.speed = 2;
                break;
            default:
        }
    }

    @Override
    public void onKeyReleased(Key key) {
        if (key == Key.RIGHT && player.getDirection() == Direction.RIGHT ||
            key == Key.LEFT && player.getDirection() == Direction.LEFT) {
            player.setDirection(Direction.NONE);
        }
        if (key == Key.UP) {
            player.speed = 1;
        }
    }

    private void moveAll() {
        roadMarking.move(player.speed);
        player.move();
        roadManager.move(player.speed);
    }

    private void gameOver() {
        isGameStopped = true;
        showMessageDialog(Color.NONE, "GAME OVER", Color.RED, 50);
        stopTurnTimer();
        player.stop();
    }
}
