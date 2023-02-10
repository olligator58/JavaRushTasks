package com.javarush.games.spaceinvaders;

import com.javarush.engine.cell.*;
import com.javarush.games.spaceinvaders.gameobjects.Bullet;
import com.javarush.games.spaceinvaders.gameobjects.EnemyFleet;
import com.javarush.games.spaceinvaders.gameobjects.PlayerShip;
import com.javarush.games.spaceinvaders.gameobjects.Star;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SpaceInvadersGame extends Game {
    public static final int WIDTH = 64;
    public static final int HEIGHT = 64;
    public static final int COMPLEXITY = 5;
    private static final int PLAYER_BULLETS_MAX = 1;
    private List<Star> stars;
    private EnemyFleet enemyFleet;
    private List<Bullet> enemyBullets;
    private List<Bullet> playerBullets;
    private PlayerShip playerShip;
    private boolean isGameStopped;
    private int animationsCount;
    private int score;

    @Override
    public void initialize() {
        setScreenSize(WIDTH, HEIGHT);
        createGame();
    }

    @Override
    public void onTurn(int step) {
        moveSpaceObjects();
        check();
        Bullet bullet = enemyFleet.fire(this);
        if (bullet != null) {
            enemyBullets.add(bullet);
        }
        setScore(score);
        drawScene();
    }

    @Override
    public void onKeyPress(Key key) {
        switch (key) {
            case SPACE:
                if (isGameStopped) {
                    createGame();
                } else {
                    Bullet bullet = playerShip.fire();
                    if (bullet != null && playerBullets.size() < PLAYER_BULLETS_MAX) {
                        playerBullets.add(bullet);
                    }
                }
                break;
            case LEFT:
                playerShip.setDirection(Direction.LEFT);
                break;
            case RIGHT:
                playerShip.setDirection(Direction.RIGHT);
                break;
            default:
        }
    }

    @Override
    public void onKeyReleased(Key key) {
        if (key == Key.LEFT && playerShip.getDirection() == Direction.LEFT ||
            key == Key.RIGHT && playerShip.getDirection() == Direction.RIGHT) {
            playerShip.setDirection(Direction.UP);
        }
    }

    @Override
    public void setCellValueEx(int x, int y, Color cellColor, String value) {
        if (x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT) {
            return;
        }
        super.setCellValueEx(x, y, cellColor, value);
    }

    private void createGame() {
        setTurnTimer(40);
        createStars();
        enemyFleet = new EnemyFleet();
        enemyBullets = new ArrayList<>();
        playerBullets = new ArrayList<>();
        playerShip = new PlayerShip();
        isGameStopped = false;
        animationsCount = 0;
        score = 0;
        drawScene();
    }

    private void drawScene() {
        drawField();
        enemyFleet.draw(this);
        for (Bullet bullet : enemyBullets) {
            bullet.draw(this);
        }
        playerShip.draw(this);
        for (Bullet bullet : playerBullets) {
            bullet.draw(this);
        }
    }

    private void drawField() {
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                setCellValueEx(x, y, Color.BLACK, "");
            }
        }

        for (Star star : stars) {
            star.draw(this);
        }
    }

    private void createStars() {
        stars = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            stars.add(new Star(getRandomNumber(WIDTH), getRandomNumber(HEIGHT)));
        }
    }

    private void moveSpaceObjects() {
        enemyFleet.move();
        playerShip.move();
        for (Bullet bullet : enemyBullets) {
            bullet.move();
        }
        for (Bullet bullet : playerBullets) {
            bullet.move();
        }
    }

    private void removeDeadBullets() {
        Iterator<Bullet> it1 = enemyBullets.iterator();
        while (it1.hasNext()) {
            Bullet bullet = it1.next();
            if (!bullet.isAlive || bullet.y >= HEIGHT - 1) {
                it1.remove();
            }
        }

        Iterator<Bullet> it2 = playerBullets.iterator();
        while (it2.hasNext()) {
            Bullet bullet = it2.next();
            if (!bullet.isAlive || bullet.y + bullet.height < 0) {
                it2.remove();
            }
        }
    }

    private void check() {
        playerShip.verifyHit(enemyBullets);
        score += enemyFleet.verifyHit(playerBullets);
        enemyFleet.deleteHiddenShips();
        removeDeadBullets();
        if (!playerShip.isAlive) {
            stopGameWithDelay();
        }

        if (enemyFleet.getBottomBorder() >= playerShip.y) {
            playerShip.kill();
        }

        if (enemyFleet.getShipsCount() == 0) {
            playerShip.win();
            stopGameWithDelay();
        }
    }

    private void stopGame(boolean isWin) {
        isGameStopped = true;
        stopTurnTimer();
        Color color = (isWin) ? Color.GREEN : Color.RED;
        String message = (isWin) ? "You won !!!" : "You lost !!!";
        showMessageDialog(Color.NONE, message, color, 50);
    }

    private void stopGameWithDelay() {
        animationsCount++;
        if (animationsCount >= 10) {
            stopGame(playerShip.isAlive);
        }
    }
}
