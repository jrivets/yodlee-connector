package org.jrivets.connector.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

/**
 * @author kbabushkin
 * @since 11/20/14
 */

public class JsonSerializer {

    private final Gson gson;

    private static class SerializerHolder {
        private static JsonSerializer serializer = new JsonSerializer();
    }

    private JsonSerializer() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        this.gson = gsonBuilder.create();
    }

    public static <T> String toJson(T t) {
        return SerializerHolder.serializer.gson.toJson(t);
    }

    public static String toJson(Object obj, Type objType) {
        return SerializerHolder.serializer.gson.toJson(obj, objType);
    }

    public static <T> T fromJson(String jsonObject, Class<T> clazz) {
        return SerializerHolder.serializer.gson.fromJson(jsonObject, clazz);
    }

    public static <T> T fromJson(String jsonObject, Type objType) {
        return SerializerHolder.serializer.gson.fromJson(jsonObject, objType);
    }
}
