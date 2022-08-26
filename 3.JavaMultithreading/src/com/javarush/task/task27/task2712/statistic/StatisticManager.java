package com.javarush.task.task27.task2712.statistic;

import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventType;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.util.*;

public class StatisticManager {
    private static StatisticManager manager;
    private StatisticStorage statisticStorage = new StatisticStorage();

    private StatisticManager() {

    }

    public static StatisticManager getInstance() {
        if (manager == null) {
            manager = new StatisticManager();
        }
        return manager;
    }

    public void register(EventDataRow data) {
        statisticStorage.put(data);
    }

    private Date calcDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public Map<Date, Long> calcAdvertisementProfitPerDays() {
        Map<Date, Long> result = new TreeMap<>(new Comparator<Date>(){
            @Override
            public int compare(Date o1, Date o2) {
                return o2.compareTo(o1);
            }
        });
        List<EventDataRow> events = statisticStorage.get().get(EventType.SELECTED_VIDEOS);
        for (EventDataRow event : events) {
            VideoSelectedEventDataRow videoEvent = (VideoSelectedEventDataRow) event;
            Date date = calcDate(videoEvent.getDate());
            if (!result.containsKey(date)) {
                result.put(date, videoEvent.getAmount());
            } else {
                long amount = result.get(date);
                amount += videoEvent.getAmount();
                result.replace(date, amount);
            }
        }
        return result;
    }

    public Map<Date, Map<String, Integer>> calcCookWorkloading() {
        Map<Date, Map<String, Integer>> result = new TreeMap<>(new Comparator<Date>(){
            @Override
            public int compare(Date o1, Date o2) {
                return o2.compareTo(o1);
            }
        });
        List<EventDataRow> events = statisticStorage.get().get(EventType.COOKED_ORDER);
        for (EventDataRow event : events) {
            CookedOrderEventDataRow cookedEvent = (CookedOrderEventDataRow) event;
            Date date = calcDate(cookedEvent.getDate());
            Map<String, Integer> cookingMap;
            if (!result.containsKey(date)) {
                cookingMap = new TreeMap<>();
                cookingMap.put(cookedEvent.getCookName(), cookedEvent.getTime());
                result.put(date, cookingMap);
            } else {
                cookingMap = result.get(date);
                if (!cookingMap.containsKey(cookedEvent.getCookName())) {
                    cookingMap.put(cookedEvent.getCookName(), cookedEvent.getTime());
                } else {
                    int cookingTime = cookingMap.get(cookedEvent.getCookName());
                    cookingTime += cookedEvent.getTime();
                    cookingMap.replace(cookedEvent.getCookName(), cookingTime);
                }
                result.replace(date, cookingMap);
            }
        }
        return result;
    }

    private class StatisticStorage {
        private Map<EventType, List<EventDataRow>> storage = new HashMap<>();

        private StatisticStorage() {
            for (EventType eventType : EventType.values()) {
                storage.put(eventType, new ArrayList<EventDataRow>());
            }
        }

        private void put(EventDataRow data) {
            List<EventDataRow> events = storage.get(data.getType());
            if (events != null) {
                events.add(data);
            }
        }

        private Map<EventType, List<EventDataRow>> get() {
            return storage;
        }
    }
}
