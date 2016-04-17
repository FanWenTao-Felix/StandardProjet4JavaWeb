package com.kxw.filter;

import org.apache.commons.lang3.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kingson.wu on 2015/9/24.
 */
public class ThreadContext {


    private static final ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<>();

    public static void bind() {
        threadLocal.set(new HashMap<String, Object>());
    }
    public static void unbind() {
        threadLocal.get().clear();
        threadLocal.remove();
    }

    public static Object getValue(String key) {
        if (threadLocal == null || threadLocal.get() == null) {
            return null;
        }
        return threadLocal.get().get(key);
    }

    public static void put(String key, Object value) {

        if(threadLocal.get() == null){
            ThreadContext.bind();
        }

        if (StringUtils.isBlank(key)) {
            throw new IllegalArgumentException("key must not be empty");
        }

        if (value == null) {
            remove(key);
            return;
        }
        threadLocal.get().put(key, value);
    }

    public static Object remove(String key) {
        Object value = threadLocal.get().remove(key);
        return value;
    }


}
