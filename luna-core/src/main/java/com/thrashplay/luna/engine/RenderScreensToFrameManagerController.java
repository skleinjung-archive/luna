package com.thrashplay.luna.engine;

import com.thrashplay.luna.engine.loop.AbstractMainLoop;
import com.thrashplay.luna.engine.screen.ScreenManager;
import com.thrashplay.luna.graphics.FrameManager;
import com.thrashplay.luna.graphics.LunaGraphics;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.awt.*;

/**
 * @author Sean Kleinjung
 */
@Component
public class RenderScreensToFrameManagerController implements AbstractMainLoop.UpdateController, AbstractMainLoop.RenderController {
    private FrameManager frameManager;
    private ScreenManager screenManager;

    public RenderScreensToFrameManagerController(FrameManager frameManager, ScreenManager screenManager) {
        Assert.notNull(frameManager, "frameManager cannot be null");
        this.frameManager = frameManager;

        Assert.notNull(screenManager, "screenManager cannot be null");
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
