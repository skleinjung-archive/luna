package com.thrashplay.luna.android.app.builder;

import android.content.Context;

import com.thrashplay.luna.android.view.LunaSurfaceView;
import com.thrashplay.luna.app.ViewConfigurer;

/**
 * Copyright (c) 2018 Sean Kleinjung
 * All rights reserved.
 */
public class AndroidViewBuilder implements ViewConfigurer {
    private Context context;
    private int sceneWidth;
    private int sceneHeight;

    public AndroidViewBuilder() {
    }

    public AndroidViewBuilder(Context context, int sceneWidth, int sceneHeight) {
        this.context = context;
        this.sceneWidth = sceneWidth;
        this.sceneHeight = sceneHeight;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public void setTitle(String title) {
        throw new UnsupportedOperationException("Cannot set view title on Android platform.");
    }

    @Override
    public void setSceneDimensions(int sceneWidth, int sceneHeight) {
        this.sceneWidth = sceneWidth;
        this.sceneHeight = sceneHeight;
    }

    public int getSceneWidth() {
        return sceneWidth;
    }

    public int getSceneHeight() {
        return sceneHeight;
    }

    public LunaSurfaceView build() {
        return new LunaSurfaceView(context, sceneWidth, sceneHeight);
    }
}
