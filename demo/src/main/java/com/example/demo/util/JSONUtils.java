package com.example.demo.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONUtils {
    public static String toJSON(Object object) {
        ObjectMapper mapper = new ObjectMapper();
        if (object instanceof String) {
            return (String) object;
        }
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            return "{}";
        }
    }
}
