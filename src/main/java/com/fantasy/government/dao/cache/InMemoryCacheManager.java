package com.fantasy.government.dao.cache;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;

@Qualifier(InMemoryCacheManager.BEAN_QUALIFIER)
@Component
public class InMemoryCacheManager implements CacheManager {
    
    public static final String BEAN_QUALIFIER = "inMemoryCacheManager"; 
    
    private Map<String, Object> cacheMap = new HashMap<>();
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    @Override
    @SuppressWarnings("unchecked")
    public <T> T get(String key, Class<T> clazz) throws IOException {
        try {
            readWriteLock.readLock().lock();
            return (T) cacheMap.get(key);
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T get(String key, TypeReference<T> typeReference) throws IOException {
        try {
            readWriteLock.readLock().lock();
            return (T) cacheMap.get(key);
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    @Override
    public <T> void set(String key, T value) throws IOException {
        try {
            readWriteLock.writeLock().lock();
            cacheMap.put(key, value);
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

}
