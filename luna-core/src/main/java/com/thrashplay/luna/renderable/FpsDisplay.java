package com.thrashplay.luna.renderable;

import com.thrashplay.luna.engine.Updateable;
import com.thrashplay.luna.engine.loop.Timing;
import com.thrashplay.luna.facet.Renderer;
import com.thrashplay.luna.graphics.LunaGraphics;
import com.thrashplay.luna.graphics.Renderable;

import java.util.concurrent.TimeUnit;

/**
 * @author Sean Kleinjung
 */
public class FpsDisplay implements Renderable, Updateable {
    // how often to update the FPS count, in milliseconds
    private int updateInterval;

    // the font size to render at
    private int fontSize = 12;

    // the number of frames elapsed since the FPS was last updated
    private int framesRendered = 0;

    // the number of updates elapsed since the UPS was last updated
    private int updates = 0;

    // timer used to keep track of update intervals
    private Timing timing = new Timing();

    // the current FPS
    private int fps = 0;

    // the current UPS
    private int ups = 0;

    public FpsDisplay() {
        this(12);
    }

    public FpsDisplay(int fontSize) {
        this(fontSize, 1000);
    }

    public FpsDisplay(int fontSize, int updateInterval) {
        this.updateInterval = updateInterval;
        this.fontSize = fontSize;
    }

    public int getUpdateInterval() {
        return updateInterval;
    }

    public int getFontSize() {
        return fontSize;
    }

    @Override
    public void update(float delta) {
        updates++;

        long elapsed = timing.elapsedAs(TimeUnit.MILLISECONDS);
        if (elapsed >= updateInterval) {
            fps = (int) Math.round(((double) framesRendered / elapsed) * 1000);
            ups = (int) Math.round(((double) updates / elapsed) * 1000);
            framesRendered = 0;
            updates = 0;
            timing.reset();
        }
    }

    @Override
    public void render(LunaGraphics g) {
        framesRendered++;
        g.drawString("FPS: " + fps, g.getWidth() - 20, 20, 0xffffffff, fontSize, LunaGraphics.HorizontalAlignment.Right, LunaGraphics.VerticalAlignment.Top);
        g.drawString("UPS: " + ups, g.getWidth() - 20, 40, 0xffffffff, fontSize, LunaGraphics.HorizontalAlignment.Right, LunaGraphics.VerticalAlignment.Top);
    }
}
