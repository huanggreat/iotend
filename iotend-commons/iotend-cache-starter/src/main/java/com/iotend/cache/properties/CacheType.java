package com.iotend.cache.properties;

/**
 * @author huang
 */
public enum CacheType {
    J2CACHE,
    CAFFEINE,
    REDIS,
    NONE,
    ;

    public boolean eq(CacheType cacheType) {
        return cacheType == null ? false : this.name().equals(cacheType.name());
    }
}