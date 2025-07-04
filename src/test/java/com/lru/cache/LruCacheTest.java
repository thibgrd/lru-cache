package com.lru.cache;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LruCacheTest {

    private LruCache cache;

    @BeforeEach
    void setup() {
        cache = new LruCache(2);
    }

    @Test
    void testPutAndGet() {
        cache.put(1, 100);
        assertEquals(100, cache.get(1));
    }

    @Test
    void testEvictionWhenCapacityExceeded() {
        cache.put(1, 10);
        cache.put(2, 20);
        cache.put(3, 30);
        assertEquals(-1, cache.get(1));
        assertEquals(20, cache.get(2));
        assertEquals(30, cache.get(3));
    }
}