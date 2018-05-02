package com.thrashplay.luna.geom;

import com.thrashplay.luna.geom.path.PathWrapper;
import com.thrashplay.luna.geom.path.PathWrapperFactory;

/**
 * Copyright (c) 2018 Sean Kleinjung
 * All rights reserved.
 */
public class Path {

    private PathWrapper wrapper;

    public Path() {
        wrapper = PathWrapperFactory.get().createWrapper();
    }

    public Object getNativePath() {
        return wrapper.getNativePath();
    }

    public void moveTo(float x, float y) {
        wrapper.moveTo(x, y);
    }

    public void lineTo(float x, float y) {
        wrapper.lineTo(x, y);
    }
}
