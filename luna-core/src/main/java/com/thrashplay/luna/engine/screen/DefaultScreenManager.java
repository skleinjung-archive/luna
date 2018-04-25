package com.thrashplay.luna.engine.screen;

import com.thrashplay.luna.LunaException;
import com.thrashplay.luna.config.LunaGameConfig;
import com.thrashplay.luna.engine.Updateable;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Sean Kleinjung
 */
@Component
public class DefaultScreenManager implements Updateable, ScreenManager {

    private Screen currentScreen;
    private Map<String, Screen> screens = new HashMap<>();

    public DefaultScreenManager(LunaGameConfig gameConfig, List<Screen> screens) {
        Assert.notNull(gameConfig,"gameConfig cannot be null");

        if (screens == null || screens.size() < 1) {
            throw new IllegalArgumentException("At least one screen must be specified");
        }

        for (Screen screen : screens) {
            registerScreen(screen);
        }
        setScreen(gameConfig.getDefaultScreen());
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
    public void render(Graphics2D graphics) {
        if (currentScreen != null) {
            currentScreen.render(graphics);
        }
    }
}
