package com.thrashplay.luna.engine.screen;

import com.thrashplay.luna.engine.Updateable;
import com.thrashplay.luna.graphics.Renderable;

/**
 * @author Sean Kleinjung
 */
public interface Screen extends Updateable, Renderable {
    String getName();

    void initialize();
    void shutdown();

    boolean isFinished();
    String getNextScreen();
}
