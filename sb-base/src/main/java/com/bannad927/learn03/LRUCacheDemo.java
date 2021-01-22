package com.bannad927.learn03;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LRU算法
 *
 * @author: chengbinbin
 * @date: 2021.1.22
 **/
@Slf4j
public class LRUCacheDemo<K, V> extends LinkedHashMap {

    private int capacity;

    public LRUCacheDemo(int capacity) {
        super(capacity, 0.75F, true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        return super.size() > capacity;
    }

    public static void main(String[] args) {
        LRUCacheDemo lruCacheDemo = new LRUCacheDemo(3);
        lruCacheDemo.put(1, "a");
        lruCacheDemo.put(2, "b");
        lruCacheDemo.put(3, "c");
        log.info("keySet:{}", lruCacheDemo.keySet());
        lruCacheDemo.put(4, "d");
        log.info("keySet:{}", lruCacheDemo.keySet());
        lruCacheDemo.put(3, "c");
        log.info("keySet:{}", lruCacheDemo.keySet());
        lruCacheDemo.put(3, "c");
        log.info("keySet:{}", lruCacheDemo.keySet());
        lruCacheDemo.put(5, "e");
        log.info("keySet:{}", lruCacheDemo.keySet());
    }

}
