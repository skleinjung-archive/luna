package com.thrashplay.luna.engine.screen;

import com.thrashplay.luna.engine.Updateable;
import com.thrashplay.luna.graphics.Renderable;

/**
 * @author Sean Kleinjung
 */
public interface ScreenManager extends Updateable, Renderable {
    Screen getScreen();
    void setScreen(String screenName);
}
