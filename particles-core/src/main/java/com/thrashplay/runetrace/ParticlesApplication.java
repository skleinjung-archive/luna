package com.thrashplay.runetrace;

import com.thrashplay.luna.desktop.app.AbstractLunaApplication;
import com.thrashplay.luna.desktop.app.ScreensConfigurer;
import com.thrashplay.runetrace.screen.ParticlesScreen;

/**
 * @author Sean Kleinjung
 */
public class ParticlesApplication extends AbstractLunaApplication {
    public static void main(String[] args) {
        new ParticlesApplication().run();
    }

    @Override
    protected void configureScreens(ScreensConfigurer screensConfigurer) {
        screensConfigurer.addScreen(new ParticlesScreen(getPointerManager()), true);
    }
}