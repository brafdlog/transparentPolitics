package com.fantasy.government.dao.cache;

import java.io.IOException;

import com.fasterxml.jackson.core.type.TypeReference;

public interface CacheManager {

    <T> T get(String key, Class<T> clazz) throws IOException;
    
    <T> T get(String key, TypeReference<T> typeReference) throws IOException;
    
    <T> void set(String key, T value) throws IOException;
    
    String getString(String key);
    
    void setString(String key, String value);
    
}
