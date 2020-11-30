package com.iotend.common.cache;

import com.iotend.cache.model.CacheKeyBuilder;

import java.time.Duration;

/**
 * 短信验证码 KEY
 *
 * @author huang
 */
public class VerificationCodeCacheKeyBuilder implements CacheKeyBuilder {
    @Override
    public String getPrefix() {
        return CacheKeyDefinition.REGISTER_USER;
    }

    @Override
    public Duration getExpire() {
        return Duration.ofSeconds(5 * 60);
    }
}