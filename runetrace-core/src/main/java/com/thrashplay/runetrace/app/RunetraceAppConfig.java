package com.thrashplay.runetrace.app;

import com.thrashplay.luna.app.LunaAppConfiguration;
import com.thrashplay.luna.app.ScreensConfigurer;
import com.thrashplay.luna.input.PointerManager;
import com.thrashplay.luna.service.LunaServiceRegistry;
import com.thrashplay.runetrace.screen.ParticlesScreen;

/**
 * Copyright (c) 2018 Sean Kleinjung
 * All rights reserved.
 */
public class RunetraceAppConfig extends LunaAppConfiguration {
    @Override
    public void configureScreens(ScreensConfigurer screensConfigurer, LunaServiceRegistry registry) {
        screensConfigurer.addScreen(new ParticlesScreen(registry.getService(PointerManager.class)), true);
    }
}
