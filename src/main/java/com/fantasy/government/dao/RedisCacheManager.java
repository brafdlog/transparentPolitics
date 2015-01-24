package com.fantasy.government.dao;

import java.io.IOException;

import javax.annotation.PreDestroy;

import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class RedisCacheManager implements CacheManager {

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    
    private JedisPool pool = new JedisPool(new JedisPoolConfig(), "localhost");
    
    @Override
    public <T> T get(String key, Class<T> clazz) throws IOException {
        String valueStr = getString(key);
        if (valueStr == null) {
            return null;
        }
        return OBJECT_MAPPER.readValue(valueStr, clazz);
    }

    @Override
    public <T> T get(String key, TypeReference<T> typeReference) throws IOException {
        String valueStr = getString(key);
        if (valueStr == null) {
            return null;
        }
        return OBJECT_MAPPER.readValue(valueStr, typeReference);
    }

    @Override
    public <T> void set(String key, T value) throws IOException {
        String valueJson = OBJECT_MAPPER.writeValueAsString(value);
        setString(key, valueJson);
    }
    
    @Override
    public String getString(String key) {
        try (Jedis jedis = pool.getResource()) {
            return jedis.get(key);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void setString(String key, String value) {
        try (Jedis jedis = pool.getResource()) {
            jedis.set(key, value);
        } catch (Exception e) {
            // Do nothing. This can happen if redis is not running
        }
    }

    @PreDestroy
    public void cleanup() {
        pool.destroy();
    }

}
