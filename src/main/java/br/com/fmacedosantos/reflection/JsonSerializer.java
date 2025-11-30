package br.com.fmacedosantos.reflection;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class JsonSerializer {

    private final ObjectMapper mapper;

    public JsonSerializer() {
        this.mapper = new ObjectMapper();
        this.mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    public String serialize(Object input) {
        String json = null;

        Map<String, Object> attributes = new HashMap<>();
        Class<?> sourceClass = input.getClass();

        for (Field field : sourceClass.getDeclaredFields()) {
            field.setAccessible(true);

            try {
                attributes.put(field.getName(), field.get(input));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        try {
            json = mapper.writeValueAsString(attributes);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return json;
    }
}
