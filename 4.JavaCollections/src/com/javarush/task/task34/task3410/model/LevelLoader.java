package com.javarush.task.task34.task3410.model;

import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

public class LevelLoader {
    private Path levels;

    public LevelLoader(Path levels) {
        this.levels = levels;
    }

    public GameObjects getLevel(int level) {
        Set<Wall> walls = new HashSet<>();
        walls.add(new Wall(210, 210));
        walls.add(new Wall(210, 230));
        walls.add(new Wall(210, 250));
        Set<Box> boxes = new HashSet<>();
        boxes.add(new Box(110, 230));
        Set<Home> homes = new HashSet<>();
        homes.add(new Home(270, 230));
        return new GameObjects(walls, boxes, homes, new Player(110, 250));
    }
}
