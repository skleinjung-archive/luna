package com.thrashplay.luna.engine.screen;

import com.thrashplay.luna.LunaException;
import com.thrashplay.luna.config.LunaGameConfig;
import com.thrashplay.luna.graphics.LunaGraphics;
import com.thrashplay.luna.util.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Sean Kleinjung
 */
public class DefaultScreenManager implements ScreenManager {

    private Screen currentScreen;
    private Map<String, Screen> screens = new HashMap<>();

    public DefaultScreenManager(List<Screen> screens, String defaultScreen) {
        Assert.notNull(defaultScreen, "defaultScreen");
        if (screens == null || screens.size() < 1) {
            throw new IllegalArgumentException("At least one screen must be specified");
        }

        for (Screen screen : screens) {
            registerScreen(screen);
        }
        setScreen(defaultScreen);
    }

    private void registerScreen(Screen screen) {
        if (screens.containsKey(screen.getName())) {
            throw new LunaException(String.format("Cannot register screen '%s': a screen already exists with that name", screen.getName()));
        }
        screens.put(screen.getName(), screen);
    }

    @Override
    public Screen getScreen() {
        return currentScreen;
    }

    @Override
    public void setScreen(String screenName) {
        if (!screens.containsKey(screenName)) {
            throw new LunaException(String.format("Cannot activate screen '%s': no such screen name is registered.", screenName));
        }

        // shutdown the old screen
        if (currentScreen != null) {
            currentScreen.shutdown();
        }

        // start the next screen
        currentScreen = screens.get(screenName);
        if (currentScreen != null) {
            currentScreen.initialize();
        }
    }

    @Override
    public void update(float delta) {
        if (currentScreen != null) {
            currentScreen.update(delta);

            if (currentScreen.isFinished()) {
                String nextScreen = currentScreen.getNextScreen();
                setScreen(nextScreen);
            }
        }
    }

    @Override
    public void render(LunaGraphics graphics) {
        if (currentScreen != null) {
            currentScreen.render(graphics);
        }
    }
}
