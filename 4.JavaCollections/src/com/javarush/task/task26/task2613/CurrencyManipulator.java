package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.*;

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

    public boolean isAmountAvailable(int expectedAmount) {
        return getTotalAmount() >= expectedAmount;
    }

    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException {
        Map<Integer, Integer> result = new HashMap<>();
        List<Integer> nominals = new ArrayList<>(denominations.keySet());
        Collections.sort(nominals, (o1, o2) -> o2 - o1);
        int restAmount = expectedAmount;
        for (Integer nominal : nominals) {
            if (nominal > restAmount) {
                continue;
            }
            int availableCount = denominations.get(nominal);
            int count = 0;
            while (nominal <= restAmount && availableCount > 0 && restAmount > 0) {
                availableCount--;
                count++;
                restAmount -= nominal;
            }
            result.put(nominal, count);
            if (restAmount == 0) {
                deleteNominals(result);
                return result;
            }
        }
        throw new NotEnoughMoneyException();
    }

    private void deleteNominals(Map<Integer, Integer> map) {
        for (Map.Entry<Integer, Integer> pair : map.entrySet()) {
            int newCount = denominations.get(pair.getKey()) - pair.getValue();
            if (newCount > 0) {
                denominations.put(pair.getKey(), newCount);
            } else {
                denominations.remove(pair.getKey());
            }
        }
    }
}
