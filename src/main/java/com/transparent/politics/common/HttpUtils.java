package com.transparent.politics.common;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.IOUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpUtils {

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    
    public static <T> T getJsonObjectFromUrl(String url, Class<T> classObject) throws JsonParseException, JsonMappingException, MalformedURLException, IOException {
        String jsonString = IOUtils.toString(new URL(url), "UTF-8");
        return OBJECT_MAPPER.readValue(jsonString, classObject);
    }
    
}
