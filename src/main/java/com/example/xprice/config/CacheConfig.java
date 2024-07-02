package com.example.xprice.config;

import com.google.common.cache.CacheBuilder;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public CacheManager cacheManager() {
        ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager("productPrices");
        cacheManager.setCacheNames(List.of("productPrices"));
        cacheManager.setStoreByValue(true);
        cacheManager.setAllowNullValues(false);
        return new ConcurrentMapCacheManager("productPrices") {
            @Override
            protected Cache createConcurrentMapCache(final String name) {
                return new ConcurrentMapCache(name,
                        CacheBuilder.newBuilder()
                                .expireAfterWrite(3, TimeUnit.HOURS)
                                .build().asMap(), false);
            }
        };
    }
}
