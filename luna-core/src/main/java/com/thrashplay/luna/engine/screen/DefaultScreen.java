package com.thrashplay.luna.engine.screen;

import com.thrashplay.luna.engine.GameEntityManager;
import com.thrashplay.luna.graphics.LunaGraphics;

import java.awt.*;

/**
 * @author Sean Kleinjung
 */
public abstract class DefaultScreen implements Screen {
    protected GameEntityManager gem = new GameEntityManager();

    @Override
    public final void initialize() {
        doInitialize();
    }

    protected void doInitialize() {
        // do nothing by default
    }

    @Override
    public void shutdown() {
        doShutdown();
    }

    protected void doShutdown() {
        // do nothing by default
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public String getNextScreen() {
        return null;
    }

    @Override
    public void update(float delta) {
        gem.updateAll(delta);
    }

    @Override
    public void render(LunaGraphics graphics) {
        gem.renderAll(graphics);
    }
}
