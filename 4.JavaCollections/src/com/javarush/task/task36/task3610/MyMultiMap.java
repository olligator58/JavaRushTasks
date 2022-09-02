package com.javarush.task.task36.task3610;

import java.io.Serializable;
import java.util.*;

public class MyMultiMap<K, V> extends HashMap<K, V> implements Cloneable, Serializable {
    static final long serialVersionUID = 123456789L;
    private HashMap<K, List<V>> map;
    private int repeatCount;

    public MyMultiMap(int repeatCount) {
        this.repeatCount = repeatCount;
        map = new HashMap<>();
    }

    @Override
    public int size() {
        int counter = 0;
        for (List<V> values : map.values()) {
            counter += values.size();
        }
        return counter;
    }

    @Override
    public V put(K key, V value) {
        V result = null;
        List<V> values;

        if (!map.containsKey(key)) {
            values = new ArrayList<>();
            values.add(value);
            map.put(key, values);
        } else {
            values = map.get(key);
            result = values.get(values.size() - 1);
            if (values.size() == repeatCount) {
                values.remove(0);
            }
            values.add(value);
        }
        return result;
    }

    @Override
    public V remove(Object key) {
        V result = null;
        if (map.containsKey(key)) {
            List<V> values = map.get(key);
            result = values.remove(0);
            if (values.isEmpty()) {
                map.remove(key);
            }
        }
        return result;
    }



    @Override
    public Set<K> keySet() {
        return map.keySet();
    }

    @Override
    public Collection<V> values() {
        List<V> result = new ArrayList<>();
        for (List<V> values : map.values()) {
            result.addAll(values);
        }
        return result;
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        for (List<V> values : map.values()) {
            if (values.contains(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (Map.Entry<K, List<V>> entry : map.entrySet()) {
            sb.append(entry.getKey());
            sb.append("=");
            for (V v : entry.getValue()) {
                sb.append(v);
                sb.append(", ");
            }
        }
        String substring = sb.substring(0, sb.length() - 2);
        return substring + "}";
    }
}