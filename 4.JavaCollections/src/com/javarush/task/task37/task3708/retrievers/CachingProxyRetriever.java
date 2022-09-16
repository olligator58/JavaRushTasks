package com.javarush.task.task37.task3708.retrievers;

import com.javarush.task.task37.task3708.cache.LRUCache;
import com.javarush.task.task37.task3708.storage.Storage;

public class CachingProxyRetriever implements Retriever {
    private OriginalRetriever retriever;
    private LRUCache<Long, Object> cache = new LRUCache<>(10);

    public CachingProxyRetriever(Storage storage) {
        retriever = new OriginalRetriever(storage);
    }

    @Override
    public Object retrieve(long id) {
        Object result;
        if ((result = cache.find(id)) != null) {
            System.out.println("Getting a value for id #" + id + " from RemoteStorage...");
            return result;
        }
        result = retriever.retrieve(id);
        cache.set(id, result);
        return result;
    }
}
