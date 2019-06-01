package com.mulecode.jwtserver.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class MuleObjectMapper {

    private static final Logger LOGGER = LogManager.getLogger(MuleObjectMapper.class);

    private static ObjectMapper objectMapper;

    public static ObjectMapper getObjectMapper() {

        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
            objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            objectMapper.disable(DeserializationFeature.ACCEPT_FLOAT_AS_INT);
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            objectMapper.registerModule(new Jdk8Module());
            objectMapper.registerModule(new JavaTimeModule());
        }
        return objectMapper;
    }

    public static String writeValueAsString(Object object) {

        try {

            getObjectMapper();
            return objectMapper.writeValueAsString(object);

        } catch (JsonProcessingException e) {

            LOGGER.error(e.getMessage(), e);
            throw new IllegalArgumentException("Object could not be parsed to String: " + e.getMessage());
        }
    }

    public static JsonNode writeStringAsJsonNode(String value) {

        try {

            getObjectMapper();
            return objectMapper.readTree(value);

        } catch (IOException e) {

            LOGGER.error(e.getMessage(), e);
            throw new IllegalArgumentException("String could not be parsed to JsonNode: " + e.getMessage());
        }
    }

    public static <T> T writeValueAsObject(String value, Class<T> classType) {

        try {

            getObjectMapper();
            return objectMapper.readValue(value, classType);

        } catch (IOException e) {

            LOGGER.error(e.getMessage(), e);
            throw new IllegalArgumentException("String could not be parsed to Object: " + e.getMessage());
        }

    }

    public static Map<String, Object> writeValueAsObject(String value) {

        try {

            getObjectMapper();
            return objectMapper.readValue(
                    value,
                    new TypeReference<Map<String, Object>>() {

                    }
            );

        } catch (IOException e) {

            LOGGER.error(e.getMessage(), e);
            throw new IllegalArgumentException("String could not be parsed to Object: " + e.getMessage());
        }

    }

    public static Map<String, Object> convertValueToMap(Object value) {

        getObjectMapper();
        return objectMapper.convertValue(
                value,
                new TypeReference<Map<String, Object>>() {

                }
        );

    }

    public static List<HashMap<String, Object>> writeValueAsObjectList(String value) {

        try {

            getObjectMapper();
            return objectMapper.readValue(
                    value,
                    new TypeReference<List<HashMap<String, Object>>>() {

                    }
            );

        } catch (IOException e) {

            LOGGER.error(e.getMessage(), e);
            throw new IllegalArgumentException("String could not be parsed to Object Array: " + e.getMessage());
        }

    }
}
