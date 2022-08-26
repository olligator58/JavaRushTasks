package com.javarush.task.task27.task2712.ad;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.EventDataRow;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*public class AdvertisementManager {
    private final AdvertisementStorage storage = AdvertisementStorage.getInstance();
    private int timeSeconds;

    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    public void processVideos() {
        this.totalTimeSecondsLeft = Integer.MAX_VALUE;
        obtainOptimalVideoSet(new ArrayList<Advertisement>(), timeSeconds, 0l);

        VideoSelectedEventDataRow row = new VideoSelectedEventDataRow(optimalVideoSet, maxAmount, timeSeconds - totalTimeSecondsLeft);
        StatisticManager.getInstance().register(row);

        displayAdvertisement();
    }

    //recursy
    private long maxAmount;
    private List<Advertisement> optimalVideoSet;
    private int totalTimeSecondsLeft;

    private void obtainOptimalVideoSet(List<Advertisement> totalList, int currentTimeSecondsLeft, long currentAmount) {
        if (currentTimeSecondsLeft < 0) {
            return;
        } else if (currentAmount > maxAmount
                || currentAmount == maxAmount && (totalTimeSecondsLeft > currentTimeSecondsLeft
                || totalTimeSecondsLeft == currentTimeSecondsLeft && totalList.size() < optimalVideoSet.size())) {
            this.totalTimeSecondsLeft = currentTimeSecondsLeft;
            this.optimalVideoSet = totalList;
            this.maxAmount = currentAmount;
            if (currentTimeSecondsLeft == 0) {
                return;
            }
        }

        ArrayList<Advertisement> tmp = getActualAdvertisements();
        tmp.removeAll(totalList);
        for (Advertisement ad : tmp) {
            if (!ad.isActive()) continue;
            ArrayList<Advertisement> currentList = new ArrayList<>(totalList);
            currentList.add(ad);
            obtainOptimalVideoSet(currentList, currentTimeSecondsLeft - ad.getDuration(), currentAmount + ad.getAmountPerOneDisplaying());
        }
    }

    private ArrayList<Advertisement> getActualAdvertisements() {
        ArrayList<Advertisement> advertisements = new ArrayList<>();
        for (Advertisement ad : storage.list()) {
            if (ad.isActive()) {
                advertisements.add(ad);
            }
        }
        return advertisements;
    }

    private void displayAdvertisement() {
        //TODO displaying
        if (optimalVideoSet == null || optimalVideoSet.isEmpty()) {
            throw new NoVideoAvailableException();
        }

        Collections.sort(optimalVideoSet, new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                long l = o2.getAmountPerOneDisplaying() - o1.getAmountPerOneDisplaying();
                return (int) (l != 0 ? l : o2.getDuration() - o1.getDuration());
            }
        });

        for (Advertisement ad : optimalVideoSet) {
            displayInPlayer(ad);
            ad.revalidate();
        }
    }

    private void displayInPlayer(Advertisement advertisement) {
        //TODO get Player instance and display content
        System.out.println(advertisement.getName() + " is displaying... " + advertisement.getAmountPerOneDisplaying() +
                ", " + (1000 * advertisement.getAmountPerOneDisplaying() / advertisement.getDuration()));
    }
}*/

public class AdvertisementManager {
    private final AdvertisementStorage storage = AdvertisementStorage.getInstance();
    private int timeSeconds;
    private List<Advertisement> bestVideos = new ArrayList<>();

    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    private int calcDuration(List<Advertisement> videos) {
        int result = 0;
        for (Advertisement video : videos) {
            result += video.getDuration();
        }
        return result;
    }

    private long calcAmount(List<Advertisement> videos) {
        long result = 0;
        for (Advertisement video : videos) {
            result += video.getAmountPerOneDisplaying();
        }
        return result;
    }

    private int calcHits(List<Advertisement> videos) {
        int result = 0;
        for (Advertisement video : videos) {
            result += video.getHits();
        }
        return result;
    }

    private boolean isBetterVideos(List<Advertisement> videos) {
        long priceDifference = calcAmount(videos) - calcAmount(bestVideos);
        if (priceDifference != 0) {
            return (priceDifference > 0);
        }
        int durationDifference = calcDuration(videos) - calcDuration(bestVideos);
        if (durationDifference != 0) {
            return (durationDifference > 0);
        }
        int hitsDifference = calcHits(videos) - calcHits(bestVideos);
        return (hitsDifference < 0);
    }

    private boolean checkList(List<Advertisement> videos) {
        if (calcDuration(videos) <= timeSeconds) {
            if (bestVideos.isEmpty()) {
                bestVideos = videos;
                return true;
            } else if (isBetterVideos(videos)) {
                bestVideos = videos;
                return true;
            }
        }
        return false;
    }

    private void makeVideosList(List<Advertisement> videos) {
        boolean isBetter = false;
        if (videos.size() > 0) {
            isBetter = checkList(videos);
        }

        if (!isBetter) {
            for (int i = 0; i < videos.size(); i++) {
                List<Advertisement> newList = new ArrayList<>(videos);
                newList.remove(i);
                makeVideosList(newList);
            }
        }
    }

    public void processVideos() {
        //в первоначальный список включаем видео с ненулевым количеством оставшихся просмотров и длительностью, не превышающее время заказа
        List<Advertisement> initialVideosList = new ArrayList<>();
        for (Advertisement advertisement : storage.list()) {
            if (advertisement.getHits() > 0 && advertisement.getDuration() <= timeSeconds) {
                initialVideosList.add(advertisement);
            }
        }

        makeVideosList(initialVideosList);

        if (bestVideos.isEmpty()) {
            throw new NoVideoAvailableException();
        }
        Collections.sort(bestVideos, new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                int result = (int) (o2.getAmountPerOneDisplaying() - o1.getAmountPerOneDisplaying());
                if (result != 0) {
                    return result;
                }
                return o1.getWeight() - o2.getWeight();
            }
        });

        EventDataRow event = new VideoSelectedEventDataRow(bestVideos, calcAmount(bestVideos), calcDuration(bestVideos));
        StatisticManager.getInstance().register(event);

        for (Advertisement video : bestVideos) {
            ConsoleHelper.writeMessage(String.format("%s is displaying... %d, %d", video.getName(), video.getAmountPerOneDisplaying(), video.getWeight()));
            video.revalidate();
        }

    }
}
