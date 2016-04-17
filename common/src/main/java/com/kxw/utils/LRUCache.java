package com.kxw.utils;

/**
 * Created by Kingson.wu on 2015/11/5.
 * {<a href='http://www.importnew.com/16264.html'>@link</a>}
 */

import java.util.LinkedHashMap;
import java.util.Map;


public class LRUCache<K, V> extends LinkedHashMap<K, V> {

    private int cacheSize;

    public LRUCache(int cacheSize) {

        super(16, 0.75f, true);
        this.cacheSize = cacheSize;
    }

    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {

        return size() >= cacheSize;
    }
}