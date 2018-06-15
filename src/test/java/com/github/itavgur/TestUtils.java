package com.github.itavgur;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectReader;
import com.github.itavgur.model.BaseEntity;
import com.github.itavgur.model.User;
import org.springframework.test.web.servlet.ResultMatcher;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static com.github.itavgur.utils.JacksonObjectMapper.getMapper;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

public class TestUtils {

    public static <T extends Iterable> void assertMatcher(T actual, T expected, String... skippedFields) {
        assertThat(actual).usingRecursiveFieldByFieldElementComparator().usingElementComparatorIgnoringFields(skippedFields).isEqualTo(expected);
    }

    public static <T extends Iterable> void assertMatcher(T actual, T expected) {
        assertMatcher(actual, expected, "");
    }

//    public static <T extends Iterable> void assertMatcher(T actual, T expected, String ignored) {
//        assertThat(actual).usingRecursiveFieldByFieldElementComparator().isEqualTo(expected);
//    }

    public static <T extends BaseEntity> void assertMatcher(T actual, T expected) {
        assertThat(actual).isEqualToComparingFieldByFieldRecursively(expected);
    }

    public static <T extends BaseEntity> void assertMatcher(T actual, T expected, String... skippedFields) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, skippedFields);
    }

    public static ResultMatcher contentJson(User... expected) {
        return content().json(writeIgnoreProps(Arrays.asList(expected), "registered", "password"));
    }

    public static ResultMatcher contentJson(User expected) {
        return content().json(writeIgnoreProps(expected, "registered", "password"));
    }

    public static <T> List<T> readValues(String json, Class<T> clazz) {
        ObjectReader reader = getMapper().readerFor(clazz);
        try {
            return reader.<T>readValues(json).readAll();
        } catch (IOException e) {
            throw new IllegalArgumentException("Invalid read array from JSON:\n'" + json + "'", e);
        }
    }

    public static <T> T readValue(String json, Class<T> clazz) {
        try {
            return getMapper().readValue(json, clazz);
        } catch (IOException e) {
            throw new IllegalArgumentException("Invalid read from JSON:\n'" + json + "'", e);
        }
    }

    public static <T> String writeValue(T obj) {
        try {
            return getMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Invalid write to JSON:\n'" + obj + "'", e);
        }
    }

    public static <T> String writeIgnoreProps(Collection<T> collection, String... ignoreProps) {
        List<Map<String, Object>> list = collection.stream()
                .map(e -> getAsMapWithIgnore(e, ignoreProps))
                .collect(Collectors.toList());
        return writeValue(list);
    }

    public static <T> String writeIgnoreProps(T obj, String... ignoreProps) {
        Map<String, Object> map = getAsMapWithIgnore(obj, ignoreProps);
        return writeValue(map);
    }

    private static <T> Map<String, Object> getAsMapWithIgnore(T obj, String[] ignoreProps) {
        Map<String, Object> map = getMapper().convertValue(obj, new TypeReference<Map<String, Object>>() {
        });
        for (String prop : ignoreProps) {
            map.remove(prop);
        }
        return map;
    }

    public static <T> String writeAdditionProps(T obj, String addName, Object addValue) {
        return writeAdditionProps(obj, Collections.singletonMap(addName, addValue));
    }

    public static <T> String writeAdditionProps(T obj, Map<String, Object> addProps) {
        Map<String, Object> map = getMapper().convertValue(obj, new TypeReference<Map<String, Object>>() {
        });
        map.putAll(addProps);
        return writeValue(map);
    }


}
