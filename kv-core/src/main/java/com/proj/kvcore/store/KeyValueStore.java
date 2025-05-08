package com.proj.kvcore.store;

import java.util.concurrent.ConcurrentHashMap;

public class KeyValueStore {
    private final ConcurrentHashMap<String, String> store = new ConcurrentHashMap<>();

    public String get(String key) {
        return store.get(key);
    }

    public void put(String key, String value) {
        store.put(key, value);
    }

    public void delete(String key) {
        store.remove(key);
    }

    public boolean contains(String key) {
        return store.containsKey(key);
    }
}
