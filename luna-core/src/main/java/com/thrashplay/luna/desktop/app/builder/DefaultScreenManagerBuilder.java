package com.thrashplay.luna.desktop.app.builder;

import com.thrashplay.luna.desktop.app.ScreensConfigurer;
import com.thrashplay.luna.engine.screen.DefaultScreenManager;
import com.thrashplay.luna.engine.screen.Screen;
import com.thrashplay.luna.engine.screen.ScreenManager;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Sean Kleinjung
 */
public class DefaultScreenManagerBuilder implements ScreensConfigurer {
    private List<Screen> screens = new LinkedList<>();
    private String defaultScreen;

    @Override
    public void addScreen(Screen screen) {
        addScreen(screen, false);
    }

    @Override
    public void addScreen(Screen screen, boolean isDefault) {
        screens.add(screen);
        if (isDefault) {
            defaultScreen = screen.getName();
        }
    }

    public ScreenManager build() {
        return new DefaultScreenManager(screens, defaultScreen);
    }
}
