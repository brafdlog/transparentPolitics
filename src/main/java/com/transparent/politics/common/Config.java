package com.transparent.politics.common;

import com.transparent.politics.dao.cache.InMemoryCacheManager;

public class Config {

    public static final String CACHE_PROVIDER_BEAN_QUALIFIER = InMemoryCacheManager.BEAN_QUALIFIER;
    public static final int DAYS_UNTIL_RECALCULATE = 2;
    
}
