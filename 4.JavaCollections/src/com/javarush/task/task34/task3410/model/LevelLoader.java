package com.javarush.task.task34.task3410.model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

public class LevelLoader {
    private Path levels;
    private static final int NUM_OF_LEVELS = 60;

    public LevelLoader(Path levels) {
        this.levels = levels;
    }

    public GameObjects getLevel(int level) {
        Set<Wall> walls = new HashSet<>();
        Set<Box> boxes = new HashSet<>();
        Set<Home> homes = new HashSet<>();

        level = (level % NUM_OF_LEVELS != 0) ? level % NUM_OF_LEVELS : NUM_OF_LEVELS;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(levels.toFile())))) {
            // доходим ло строки с нужным уровнем
            while (!(reader.readLine()).equals(String.format("Maze: %d", level))) {
            }
            //пропускаем ненужную строку
            reader.readLine();

            int sizeX = Integer.parseInt(reader.readLine().trim().substring(8));
            int sizeY = Integer.parseInt(reader.readLine().trim().substring(8));

            //пропускаем ненужные строки
            for (int i = 0; i < 3; i++) {
                reader.readLine();
            }

            Player player = null;
            for (int i = 0; i < sizeY; i++) {
                String line = reader.readLine();
                for (int j = 0; j < sizeX; j++) {
                    int x = Model.FIELD_CELL_SIZE / 2 + j * Model.FIELD_CELL_SIZE;
                    int y = Model.FIELD_CELL_SIZE / 2 + i * Model.FIELD_CELL_SIZE;

                    char c = line.charAt(j);
                    switch (c) {
                        case 'X':
                            walls.add(new Wall(x, y));
                            break;
                        case '*':
                            boxes.add(new Box(x, y));
                            break;
                        case '.':
                            homes.add(new Home(x, y));
                            break;
                        case '&':
                            boxes.add(new Box(x, y));
                            homes.add(new Home(x, y));
                            break;
                        case '@':
                            player = new Player(x, y);
                            break;
                    }
                }
            }
            return new GameObjects(walls, boxes, homes, player);
        } catch (IOException ignored) {
        }
        return null;
    }
}
