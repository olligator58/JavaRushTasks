package com.javarush.task.task35.task3513;

import java.util.*;

public class Model {
    private static final int FIELD_WIDTH = 4;
    private Tile[][] gameTiles;
    int score;
    int maxTile;
    private Stack<Tile[][]> previousStates = new Stack<>();
    private Stack<Integer> previousScores = new Stack<>();
    private boolean isSaveNeeded = true;

    public Model() {
        resetGameTiles();
    }

    public Tile[][] getGameTiles() {
        return gameTiles;
    }

    void resetGameTiles() {
        gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < gameTiles.length; i++) {
            for (int j = 0; j < gameTiles[i].length; j++) {
                gameTiles[i][j] = new Tile();
            }
        }
        addTile();
        addTile();
    }

    private void addTile() {
        List<Tile> emptyTiles = getEmptyTiles();
        if (emptyTiles.size() > 0) {
            int randomIndex = (int) (Math.random() * emptyTiles.size());
            int randomValue = (Math.random() < 0.9) ? 2 : 4;
            emptyTiles.get(randomIndex).value = randomValue;
        }
    }

    private List<Tile> getEmptyTiles() {
        List<Tile> result = new ArrayList<>();
        for (int i = 0; i < gameTiles.length; i++) {
            for (int j = 0; j < gameTiles[i].length; j++) {
                if (gameTiles[i][j].isEmpty()) {
                    result.add(gameTiles[i][j]);
                }
            }
        }
        return result;
    }

    private boolean compressTiles(Tile[] tiles) {
        boolean result = false;
        int counter = 0;
        for (int i = 0; i < tiles.length; i++) {
            if (!tiles[i].isEmpty()) {
                if (i != counter) {
                    tiles[counter] = tiles[i];
                    result = true;
                }
                counter++;
            }
        }
        for (int i = counter; i < tiles.length; i++) {
            tiles[i] = new Tile();
        }
        return result;
    }

    private boolean mergeTiles(Tile[] tiles) {
        boolean result = false;
        for (int i = 0; i < tiles.length - 1; i++) {
            if (!tiles[i].isEmpty() && tiles[i].value == tiles[i + 1].value) {
                int newValue = tiles[i].value * 2;
                tiles[i].value = newValue;
                tiles[i + 1].value = 0;
                compressTiles(tiles);
                if (newValue > maxTile) {
                    maxTile = newValue;
                }
                score += newValue;
                result = true;
            }
        }
        return result;
    }

    private void rotateMatrixOn90Degrees(Tile[][] tiles) {
        int size = tiles.length;
        for (int i = 0; i < size / 2; i++) {
            for (int j = i; j < size - i - 1; j++) {
                Tile temp = tiles[i][j];
                tiles[i][j] = tiles[size - 1 - j][i];
                tiles[size - 1 - j][i] = tiles[size - 1 - i][size - 1 - j];
                tiles[size - 1 - i][size - 1 - j] = tiles[j][size - 1 - i];
                tiles[j][size - 1 - i] = temp;
            }
        }
    }

    public void left() {
        if (isSaveNeeded) {
            saveState(gameTiles);
        }
        boolean fieldChanged = false;
        for (int i = 0; i < gameTiles.length; i++) {
            if (compressTiles(gameTiles[i]) | mergeTiles(gameTiles[i])) {
                fieldChanged = true;
            }
        }
        if (fieldChanged) {
            addTile();
        }
        isSaveNeeded = true;
    }

    public void right() {
        saveState(gameTiles);
        rotateMatrixOn90Degrees(gameTiles);
        rotateMatrixOn90Degrees(gameTiles);
        left();
        rotateMatrixOn90Degrees(gameTiles);
        rotateMatrixOn90Degrees(gameTiles);
    }

    public void down() {
        saveState(gameTiles);
        rotateMatrixOn90Degrees(gameTiles);
        left();
        rotateMatrixOn90Degrees(gameTiles);
        rotateMatrixOn90Degrees(gameTiles);
        rotateMatrixOn90Degrees(gameTiles);
    }

    public void up() {
        saveState(gameTiles);
        rotateMatrixOn90Degrees(gameTiles);
        rotateMatrixOn90Degrees(gameTiles);
        rotateMatrixOn90Degrees(gameTiles);
        left();
        rotateMatrixOn90Degrees(gameTiles);
    }

    public boolean canMove() {
        if (!getEmptyTiles().isEmpty()) {
            return true;
        }
        for (int y = 0; y < gameTiles.length; y++) {
            for (int x = 0; x < gameTiles[y].length; x++) {
                if (x < gameTiles[y].length - 1 && gameTiles[y][x].value == gameTiles[y][x + 1].value ||
                    y < gameTiles.length - 1 && gameTiles[y][x].value == gameTiles[y + 1][x].value ) {
                    return true;
                }
            }
        }
        return false;
    }

    private Tile[][] getCopyOfMatrix(Tile[][] tiles) {
        Tile[][] result = new Tile[tiles.length][tiles[0].length];
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                result[i][j] = new Tile(tiles[i][j].value);
            }
        }
        return result;
    }

    private void saveState (Tile[][] tiles) {
        previousStates.push(getCopyOfMatrix(tiles));
        previousScores.push(score);
        isSaveNeeded = false;
    }

    public void rollback() {
        if (!previousStates.empty() && !previousScores.empty()) {
            gameTiles = previousStates.pop();
            score = previousScores.pop();
        }
    }

    public void randomMove() {
        int n = ((int) (Math.random() * 100)) % 4;
        switch (n) {
            case 0:
                left();
                break;
            case 1:
                right();
                break;
            case 2:
                up();
                break;
            case 3:
                down();
                break;
        }
    }

    public boolean hasBoardChanged() {
        for (int i = 0; i < gameTiles.length; i++) {
            for (int j = 0; j < gameTiles[i].length; j++) {
                if (gameTiles[i][j].value != previousStates.peek()[i][j].value) {
                    return true;
                }
            }
        }
        return false;
    }

    public MoveEfficiency getMoveEfficiency(Move move) {
        MoveEfficiency result;
        move.move();
        if (!hasBoardChanged()) {
            result = new MoveEfficiency(-1, 0, move);
        } else {
            result = new MoveEfficiency(getEmptyTiles().size(), score, move);
        }
        rollback();
        return result;
    }

    public void autoMove() {
        PriorityQueue<MoveEfficiency> queue = new PriorityQueue<>(4, Collections.reverseOrder());
        queue.offer(getMoveEfficiency(this::left));
        queue.offer(getMoveEfficiency(this::right));
        queue.offer(getMoveEfficiency(this::up));
        queue.offer(getMoveEfficiency(this::down));
        queue.peek().getMove().move();
    }
}
