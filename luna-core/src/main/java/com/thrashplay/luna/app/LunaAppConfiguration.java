package com.thrashplay.luna.app;

import com.thrashplay.luna.service.LunaServiceRegistry;

/**
 * Copyright (c) 2018 Sean Kleinjung
 * All rights reserved.
 */
public abstract class LunaAppConfiguration {
    public void configureView(ViewConfigurer windowConfigurer) {
        // use defaults, can be overridden
    }

    public void configureGameLoop(GameLoopConfigurer gameLoopConfigurer) {
        // use defaults, can be overridden
    }

    public abstract void configureScreens(ScreensConfigurer screensConfigurer, LunaServiceRegistry registry);
}
