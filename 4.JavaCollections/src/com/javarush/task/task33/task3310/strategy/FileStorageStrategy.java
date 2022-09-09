package com.javarush.task.task33.task3310.strategy;

public class FileStorageStrategy implements StorageStrategy {
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private static final long DEFAULT_BUCKET_SIZE_LIMIT = 10000;
    private FileBucket[] table = new FileBucket[DEFAULT_INITIAL_CAPACITY];
    private int size;
    private long bucketSizeLimit = DEFAULT_BUCKET_SIZE_LIMIT;
    private long maxBucketSize;

    public long getBucketSizeLimit() {
        return bucketSizeLimit;
    }

    public void setBucketSizeLimit(long bucketSizeLimit) {
        this.bucketSizeLimit = bucketSizeLimit;
    }

    public int hash(Long k) {
        int h;
        return (k == null) ? 0 : (h = k.hashCode()) ^ (h >>> 16);
    }

    public int indexFor(int hash, int length) {
        return hash & (length - 1);
    }

    public Entry getEntry(Long key) {
        int hash = hash(key);
        int index = indexFor(hash, table.length);

        if (table[index] != null) {
            Entry e = table[index].getEntry();
            while (e != null) {
                if (hash == e.hash && (key == e.key || (key != null && key.equals(e.key)))) {
                    return e;
                }
                e = e.next;
            }
        }
        return null;
    }

    public void resize(int newCapacity) {
        FileBucket[] newTable = new FileBucket[newCapacity];
        transfer(newTable);
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                table[i].remove();
            }
        }
        table = newTable;
    }

    public void transfer(FileBucket[] newTable) {
        int newCapacity = newTable.length;
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                Entry e = table[i].getEntry();
                while (e != null) {
                    Entry next = e.next;
                    int index = indexFor(e.hash, newCapacity);
                    if (newTable[index] == null) {
                        newTable[index] = new FileBucket();
                    }
                    e.next = newTable[index].getEntry();
                    newTable[index].putEntry(e);
                    e = next;
                }
            }
        }
    }

    public void addEntry(int hash, Long key, String value, int bucketIndex) {
        if (table[bucketIndex] == null) {
            table[bucketIndex] = new FileBucket();
        }
        Entry e = table[bucketIndex].getEntry();
        table[bucketIndex].putEntry(new Entry(hash, key, value, e));
        size++;
        if (table[bucketIndex].getFileSize() > bucketSizeLimit) {
            resize(2 * table.length);
        }
    }

    public void createEntry(int hash, Long key, String value, int bucketIndex) {
        FileBucket bucket = (table[bucketIndex] != null) ? table[bucketIndex] : new FileBucket();
        Entry e = table[bucketIndex].getEntry();
        bucket.putEntry(new Entry(hash, key, value, e));
        size++;
    }


    @Override
    public boolean containsKey(Long key) {
        return getEntry(key) != null;
    }

    @Override
    public boolean containsValue(String value) {
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                Entry e = table[i].getEntry();
                while (e != null) {
                    if (e.value.equals(value)) {
                        return true;
                    }
                    e = e.next;
                }
            }
        }
        return false;
    }

    @Override
    public void put(Long key, String value) {
        int hash = hash(key);
        int bucketIndex = indexFor(hash, table.length);

        if (table[bucketIndex] != null) {
            Entry e = table[bucketIndex].getEntry();
            Entry chain = e;
            while (e != null) {
                if (hash == e.hash && (key == e.key) || (key != null && key.equals(e.key))) {
                    e.value = value;
                    table[bucketIndex].putEntry(chain);
                    return;
                }
                e = e.next;
            }
        }
        addEntry(hash, key, value, bucketIndex);
    }

    @Override
    public Long getKey(String value) {
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                Entry e = table[i].getEntry();
                while (e != null) {
                    if (e.value.equals(value)) {
                        return e.key;
                    }
                    e = e.next;
                }
            }
        }
        return null;
    }

    @Override
    public String getValue(Long key) {
        Entry e = getEntry(key);
        return (e != null) ? e.value : null;
    }
}
