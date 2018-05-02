package com.thrashplay.luna.engine;

import com.thrashplay.luna.engine.loop.GameLoopRenderCallback;
import com.thrashplay.luna.engine.loop.GameLoopUpdateCallback;
import com.thrashplay.luna.engine.screen.ScreenManager;
import com.thrashplay.luna.graphics.FrameManager;
import com.thrashplay.luna.graphics.LunaGraphics;
import com.thrashplay.luna.util.Assert;

/**
 * @author Sean Kleinjung
 */
public class RenderScreensToFrameManagerController implements GameLoopUpdateCallback, GameLoopRenderCallback {
    private FrameManager frameManager;
    private ScreenManager screenManager;

    public RenderScreensToFrameManagerController(FrameManager frameManager, ScreenManager screenManager) {
        Assert.notNull(frameManager, "frameManager");
        this.frameManager = frameManager;

        Assert.notNull(screenManager, "screenManager");
        this.screenManager = screenManager;
    }

    @Override
    public void update(float delta) {
        screenManager.update(delta);
    }

    @Override
    public void render() {
        LunaGraphics graphics = frameManager.beginFrame();
        try {
            if (graphics != null) {
                screenManager.render(graphics);
            }
        } finally {
            frameManager.endFrame();
        }
    }
}
