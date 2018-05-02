package com.thrashplay.luna.service;

/**
 * Copyright (c) 2018 Sean Kleinjung
 * All rights reserved.
 */
public interface LunaServiceRegistry {
    /**
     * Returns a service of the specified type, or null if no such service is registered.
     */
    public <T> T getService(Class<T> type);
}
