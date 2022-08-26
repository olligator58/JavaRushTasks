package com.javarush.task.task34.task3408;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.WeakHashMap;

public class Cache<K, V> {
    private Map<K, V> cache = new WeakHashMap<>();

    public V getByKey(K key, Class<V> clazz) throws Exception {
        V value = cache.get(key);
        if (value != null) {
            return value;
        }

        Constructor<V> constructor = clazz.getConstructor(key.getClass());
        value = constructor.newInstance(key);
        cache.put(key, value);
        return value;
    }

    public boolean put(V obj) {
        Method method = null;
        for (Method declaredMethod : obj.getClass().getDeclaredMethods()) {
            if (declaredMethod.getName().equals("getKey")) {
                method = declaredMethod;
            }
        }
        if (method != null) {
            try {
                method.setAccessible(true);
                K key = (K) method.invoke(obj);
                cache.put(key, obj);
                return true;
            } catch (IllegalAccessException | InvocationTargetException ignore) {
            }
        }
        return false;
    }

    public int size() {
        return cache.size();
    }
}
