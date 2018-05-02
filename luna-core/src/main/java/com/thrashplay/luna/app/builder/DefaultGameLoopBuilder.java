package com.thrashplay.luna.app.builder;

import com.thrashplay.luna.app.GameLoopConfigurer;
import com.thrashplay.luna.engine.loop.FixedFpsSingleThreadGameLoop;
import com.thrashplay.luna.engine.loop.GameLoop;
import com.thrashplay.luna.engine.loop.GameLoopRenderCallback;
import com.thrashplay.luna.engine.loop.GameLoopUpdateCallback;

/**
 * @author Sean Kleinjung
 */
public class DefaultGameLoopBuilder implements GameLoopConfigurer {
    private boolean multithreaded = false;
    private int targetUpdatesPerSecond;
    private GameLoopUpdateCallback updateCallback;
    private GameLoopRenderCallback renderCallback;

    public DefaultGameLoopBuilder(int targetUpdatesPerSecond) {
        this.targetUpdatesPerSecond = targetUpdatesPerSecond;
    }

    @Override
    public void setRenderOnSameThread() {
        multithreaded = false;
    }

    public boolean isMultithreaded() {
        return multithreaded;
    }

    @Override
    public void setTargetUpdatesPerSecond(int targetUpdatesPerSecond) {
        this.targetUpdatesPerSecond = targetUpdatesPerSecond;
    }

    public int getTargetUpdatesPerSecond() {
        return targetUpdatesPerSecond;
    }

    public void setUpdateCallback(GameLoopUpdateCallback updateCallback) {
        this.updateCallback = updateCallback;
    }

    public void setRenderCallback(GameLoopRenderCallback renderCallback) {
        this.renderCallback = renderCallback;
    }

    public GameLoop build() {
        if (multithreaded) {
            throw new IllegalStateException("Multithread game loops are not currently supported");
        }

        return new FixedFpsSingleThreadGameLoop(updateCallback, renderCallback, targetUpdatesPerSecond);
    }
}
