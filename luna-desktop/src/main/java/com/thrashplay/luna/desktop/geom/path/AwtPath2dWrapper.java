package com.thrashplay.luna.desktop.geom.path;

import com.thrashplay.luna.geom.path.PathWrapper;

import java.awt.geom.Path2D;

/**
 * Copyright (c) 2018 Sean Kleinjung
 * All rights reserved.
 */
public class AwtPath2dWrapper implements PathWrapper {

    private Path2D.Float nativePath;

    public AwtPath2dWrapper() {
        nativePath = new Path2D.Float();
    }

    @Override
    public Object getNativePath() {
        return nativePath;
    }

    @Override
    public void moveTo(float x, float y) {
        nativePath.moveTo(x, y);
    }

    @Override
    public void lineTo(float x, float y) {
        nativePath.lineTo(x, y);
    }
}
