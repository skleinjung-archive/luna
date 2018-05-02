package com.thrashplay.luna.service;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright (c) 2018 Sean Kleinjung
 * All rights reserved.
 */
public class DefaultLunaServiceRegistry implements LunaServiceRegistry {
    private Map<Class, Object> services = new HashMap<>();

    public <T> void registerService(Class<T> type, T service) {
        services.put(type, service);
    }

    @Override
    public <T> T getService(Class<T> type) {
        //noinspection unchecked
        return (T) services.get(type);
    }
}
