package com.javarush.task.task17.task1714;

/* 
Comparable
*/

public class Beach implements Comparable<Beach> {
    private String name;      //название
    private float distance;   //расстояние
    private int quality;    //качество

    public Beach(String name, float distance, int quality) {
        this.name = name;
        this.distance = distance;
        this.quality = quality;
    }

    @Override
    public synchronized int compareTo(Beach beach) {
        int distanceRating, qualityRating;

        if (this.getDistance() == beach.getDistance()) {
            distanceRating = 0;
        } else if (this.getDistance() < beach.getDistance()) {
            distanceRating = 1;
        } else {
            distanceRating = -1;
        }

        if (this.getQuality() == beach.getQuality()) {
            qualityRating = 0;
        } else if (this.getQuality() > beach.getQuality()) {
            qualityRating = 1;
        } else {
            qualityRating = -1;
        }

        return distanceRating + qualityRating;
    }

    public synchronized String getName() {
        return name;
    }

    public synchronized void setName(String name) {
        this.name = name;
    }

    public synchronized float getDistance() {
        return distance;
    }

    public synchronized void setDistance(float distance) {
        this.distance = distance;
    }

    public synchronized int getQuality() {
        return quality;
    }

    public synchronized void setQuality(int quality) {
        this.quality = quality;
    }

    public static void main(String[] args) {
        Beach beach1 = new Beach("Первый пляж", 30, 5);
        Beach beach2 = new Beach("Второй пляж", 29, 4);
        System.out.println(beach1.compareTo(beach2));
    }
}
