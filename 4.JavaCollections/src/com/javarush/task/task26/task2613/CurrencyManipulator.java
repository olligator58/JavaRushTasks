package com.javarush.task.task26.task2613;

import java.util.HashMap;
import java.util.Map;

public class CurrencyManipulator {
    private String currencyCode;
    private Map<Integer, Integer> denominations = new HashMap<>();

    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void addAmount(int denomination, int count) {
        if (denominations.containsKey(denomination)) {
            count += denominations.get(denomination);
        }
        denominations.put(denomination, count);
    }

    public int getTotalAmount() {
        int totalAmount = 0;
        for (Map.Entry<Integer, Integer> pair : denominations.entrySet()) {
            totalAmount += pair.getKey() * pair.getValue();
        }
        return totalAmount;
    }

    public boolean hasMoney() {
        return getTotalAmount() > 0;
    }
}
