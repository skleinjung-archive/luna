package com.thrashplay.luna.android.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.thrashplay.luna.android.app.builder.AndroidViewBuilder;
import com.thrashplay.luna.android.geom.path.AndroidPathWrapper;
import com.thrashplay.luna.android.input.AndroidTouchPointerManager;
import com.thrashplay.luna.android.view.LunaSurfaceView;
import com.thrashplay.luna.app.LunaAppConfiguration;
import com.thrashplay.luna.app.builder.DefaultGameLoopBuilder;
import com.thrashplay.luna.app.builder.DefaultScreenManagerBuilder;
import com.thrashplay.luna.engine.loop.GameLoop;
import com.thrashplay.luna.engine.loop.GameLoopRenderCallback;
import com.thrashplay.luna.engine.loop.GameLoopUpdateCallback;
import com.thrashplay.luna.engine.screen.ScreenManager;
import com.thrashplay.luna.geom.path.PathWrapperFactory;
import com.thrashplay.luna.graphics.LunaGraphics;
import com.thrashplay.luna.input.PointerManager;
import com.thrashplay.luna.service.DefaultLunaServiceRegistry;

/**
 * Copyright (c) 2018 Sean Kleinjung
 * All rights reserved.
 */
public abstract class LunaGame extends Activity {
    private ScreenManager screenManager;
    private LunaSurfaceView surfaceView;
    private GameLoop gameLoop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setFullscreenNoTitle();

        // setup graphics implementation classes for Android
        PathWrapperFactory.get().setPathWrapperClass(AndroidPathWrapper.class);

        DefaultLunaServiceRegistry registry = new DefaultLunaServiceRegistry();
        LunaAppConfiguration config = getAppConfiguration();

        AndroidViewBuilder viewBuilder = new AndroidViewBuilder(this, 640, 480);
        config.configureView(viewBuilder);
        surfaceView = viewBuilder.build();

        // build services that require the surface view
        AndroidTouchPointerManager pointerManager = new AndroidTouchPointerManager(viewBuilder.getSceneWidth(), viewBuilder.getSceneHeight());
        surfaceView.setOnTouchListener(pointerManager);
        registry.registerService(PointerManager.class, pointerManager);

        DefaultGameLoopBuilder gameLoopBuilder = new DefaultGameLoopBuilder(60);
        config.configureGameLoop(gameLoopBuilder);
        gameLoopBuilder.setUpdateCallback(new GameLoopUpdateCallback() {
            @Override
            public void update(float delta) {
                updateGameState(delta);
            }
        });
        gameLoopBuilder.setRenderCallback(new GameLoopRenderCallback() {
            @Override
            public void render() {
                renderFrame();
            }
        });
        gameLoop = gameLoopBuilder.build();

        DefaultScreenManagerBuilder screenManagerBuilder = new DefaultScreenManagerBuilder();
        config.configureScreens(screenManagerBuilder, registry);
        screenManager = screenManagerBuilder.build();

        setContentView(surfaceView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (gameLoop != null) {
            gameLoop.resume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (gameLoop != null) {
            gameLoop.pause();
        }
    }

    /**
     * Sets the activity's window to be fullscreen without a title.
     */
    private void setFullscreenNoTitle() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private void updateGameState(float delta) {
        screenManager.update(delta);
    }

    private void renderFrame() {
        LunaGraphics graphics = surfaceView.beginFrame();
        try {
            if (graphics != null) {
                screenManager.render(graphics);
            }
        } finally {
            surfaceView.endFrame();
        }
    }

    protected ScreenManager getScreenManager() {
        return screenManager;
    }

    protected LunaSurfaceView getSurfaceView() {
        return surfaceView;
    }

    protected GameLoop getGameLoop() {
        return gameLoop;
    }

    protected abstract LunaAppConfiguration getAppConfiguration();

}
