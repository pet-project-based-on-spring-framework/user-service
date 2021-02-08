package com.trl.userservice.core.service.caching;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import static org.assertj.core.api.Assertions.assertThat;

public class CachingTestHelper {

    private final CacheManager cacheManager;

    public CachingTestHelper(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    public void putInCache(String cacheName, Object key, Object value) {
        cacheManager.getCache(cacheName).put(key, value);
    }

    public void assertRecordInCache(String cacheName, String key, Object expectedValue) {
        Cache.ValueWrapper cachedValue = cacheManager.getCache(cacheName).get(key);

        assertThat(cachedValue).isNotNull();
        assertThat(cachedValue.get()).isEqualTo(expectedValue);
    }

    public void assertNoRecordInCache(String cacheName, String key) {
        Cache.ValueWrapper cachedValue = cacheManager.getCache(cacheName).get(key);

        assertThat(cachedValue).isNull();
    }

    public void clearCaches(String... cacheNames) {
        for (String cacheName : cacheNames) {
            cacheManager.getCache(cacheName).clear();
        }
    }

}
