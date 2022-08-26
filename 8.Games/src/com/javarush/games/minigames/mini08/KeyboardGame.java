package com.javarush.games.minigames.mini08;

import com.javarush.engine.cell.*;

/* 
Работа с клавиатурой
*/

public class KeyboardGame extends Game {

    @Override
    public void initialize() {
        setScreenSize(3, 3);

        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                setCellColor(x, y, Color.WHITE);
            }
        }
    }

    @Override
    public void onKeyPress(Key key) {
        switch (key) {
            case LEFT:
                setCellColor(0, 0, Color.GREEN);
                setCellColor(0, 1, Color.GREEN);
                setCellColor(0, 2, Color.GREEN);
                break;
            case RIGHT:
                setCellColor(2, 0, Color.GREEN);
                setCellColor(2, 1, Color.GREEN);
                setCellColor(2, 2, Color.GREEN);
                break;
            case UP:
                setCellColor(0, 0, Color.GREEN);
                setCellColor(1, 0, Color.GREEN);
                setCellColor(2, 0, Color.GREEN);
                break;
            case DOWN:
                setCellColor(0, 2, Color.GREEN);
                setCellColor(1, 2, Color.GREEN);
                setCellColor(2, 2, Color.GREEN);
                break;
        }
    }

    @Override
    public void onKeyReleased(Key key) {
        switch (key) {
            case LEFT:
                setCellColor(0, 0, Color.WHITE);
                setCellColor(0, 1, Color.WHITE);
                setCellColor(0, 2, Color.WHITE);
                break;
            case RIGHT:
                setCellColor(2, 0, Color.WHITE);
                setCellColor(2, 1, Color.WHITE);
                setCellColor(2, 2, Color.WHITE);
                break;
            case UP:
                setCellColor(0, 0, Color.WHITE);
                setCellColor(1, 0, Color.WHITE);
                setCellColor(2, 0, Color.WHITE);
                break;
            case DOWN:
                setCellColor(0, 2, Color.WHITE);
                setCellColor(1, 2, Color.WHITE);
                setCellColor(2, 2, Color.WHITE);
                break;
        }
    }
}
