package com.thrashplay.luna.android.geom.path;

import android.graphics.Path;

import com.thrashplay.luna.geom.path.PathWrapper;

/**
 * Copyright (c) 2018 Sean Kleinjung
 * All rights reserved.
 */
public class AndroidPathWrapper implements PathWrapper {

    private Path nativePath;

    public AndroidPathWrapper() {
        nativePath = new Path();
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
