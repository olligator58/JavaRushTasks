package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.ad.Advertisement;
import com.javarush.task.task27.task2712.ad.StatisticAdvertisementManager;
import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.*;

public class DirectorTablet {
    private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

    public void printAdvertisementProfit() {
        Map<Date, Long> profits = StatisticManager.getInstance().calcAdvertisementProfitPerDays();
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (Map.Entry<Date, Long> profit : profits.entrySet()) {
            BigDecimal amount = BigDecimal.valueOf(profit.getValue() / 100D).setScale(2, RoundingMode.HALF_UP);
            totalAmount = totalAmount.add(amount);
            ConsoleHelper.writeMessage(String.format("%s - %s", FORMATTER.format(profit.getKey()), amount.toString()));
        }
        ConsoleHelper.writeMessage(String.format("Total - %s", totalAmount.setScale(2, RoundingMode.HALF_UP).toString()));
    }

    public void printCookWorkloading() {
        Map<Date, Map<String, Integer>> loadings = StatisticManager.getInstance().calcCookWorkloading();
        for (Map.Entry<Date, Map<String, Integer>> loading : loadings.entrySet()) {
            ConsoleHelper.writeMessage(FORMATTER.format(loading.getKey()));
            for (Map.Entry<String, Integer> cooking : loading.getValue().entrySet()) {
                BigDecimal time = BigDecimal.valueOf(cooking.getValue() / 60).setScale(0, RoundingMode.HALF_UP);
                ConsoleHelper.writeMessage(String.format("%s - %s min", cooking.getKey(), time));
            }
            ConsoleHelper.writeMessage("");
        }
    }

    public void printActiveVideoSet() {
        List<Advertisement> videoSet = StatisticAdvertisementManager.getInstance().getActiveVideoSet();
        Collections.sort(videoSet, new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                return o1.getName().toLowerCase().compareTo(o2.getName().toLowerCase());
            }
        });

        for (Advertisement advertisement : videoSet) {
            System.out.println(advertisement.getName() + " - " + advertisement.getHits());
        }
    }

    public void printArchivedVideoSet() {
        List<Advertisement> archivedVideos = StatisticAdvertisementManager.getInstance().getArchivedVideoSet();
        Collections.sort(archivedVideos, new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });
        for (Advertisement video : archivedVideos) {
            ConsoleHelper.writeMessage(video.getName());
        }
    }
}
