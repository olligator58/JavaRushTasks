package com.javarush.task.task27.task2712.ad;

import java.util.ArrayList;
import java.util.List;

public class StatisticAdvertisementManager {
    private static StatisticAdvertisementManager instance = new StatisticAdvertisementManager();
    private AdvertisementStorage advertisementStorage = AdvertisementStorage.getInstance();

    private StatisticAdvertisementManager() {
    }

    public static StatisticAdvertisementManager getInstance() {
        return instance;
    }

    public List<Advertisement> getActiveVideoSet() {
        List<Advertisement> result = new ArrayList<>();
        for (Advertisement video : advertisementStorage.list()) {
            if (video.isActive()) {
                result.add(video);
            }
        }
        return result;
    }

    public List<Advertisement> getArchivedVideoSet() {
        List<Advertisement> result = new ArrayList<>();
        for (Advertisement video : advertisementStorage.list()) {
            if (video.getHits() <= 0) {
                result.add(video);
            }
        }
        return result;
    }
}
