package com.thrashplay.luna.desktop.app;

import com.thrashplay.luna.engine.screen.Screen;

/**
 * @author Sean Kleinjung
 */
public interface ScreensConfigurer {
    void addScreen(Screen screen);
    void addScreen(Screen screen, boolean isDefault);
}
