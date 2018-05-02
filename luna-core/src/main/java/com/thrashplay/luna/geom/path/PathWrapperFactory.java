package com.thrashplay.luna.geom.path;

import com.thrashplay.luna.LunaException;

/**
 * Copyright (c) 2018 Sean Kleinjung
 * All rights reserved.
 */
public class PathWrapperFactory {
    private static final PathWrapperFactory INSTANCE = new PathWrapperFactory();
    public static PathWrapperFactory get() {
        return INSTANCE;
    }

    private Class<? extends PathWrapper> pathWrapperClass;

    public void setPathWrapperClass(Class<? extends PathWrapper> pathWrapperClass) {
        this.pathWrapperClass = pathWrapperClass;
    }

    public PathWrapper createWrapper() {
        if (pathWrapperClass == null) {
            throw new IllegalStateException("No PathWrapper class has been set for this platform");
        }

        try {
            return pathWrapperClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new LunaException("Failed to instantiate path wrapper: " + e.toString(), e);
        }
    }
}
