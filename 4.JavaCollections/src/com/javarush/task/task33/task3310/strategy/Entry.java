package com.javarush.task.task33.task3310.strategy;


import java.io.Serializable;

public class Entry implements Serializable {
    Long key;
    String value;
    Entry next;
    int hash;

    public Entry(int hash, Long key, String value, Entry next) {
        this.hash = hash;
        this.key = key;
        this.value = value;
        this.next = next;
    }

    public Long getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    @Override
    public int hashCode() {
        int result = (key != null) ? key.hashCode() : 0;
        result = 31 * result + ((value != null) ? value.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }

        Entry e = (Entry) o;

        if ((this.key != null) ? !key.equals(e.key) : e.key != null) return false;
        return (this.value != null) ? this.value.equals(e.value) : e.value == null;
    }

    @Override
    public String toString() {
        return key + "=" + value;
    }
}
