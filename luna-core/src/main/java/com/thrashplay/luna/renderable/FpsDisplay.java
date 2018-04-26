package com.thrashplay.luna.renderable;

import com.thrashplay.luna.util.FpsCounter;
import com.thrashplay.luna.engine.Updateable;
import com.thrashplay.luna.graphics.LunaGraphics;
import com.thrashplay.luna.graphics.Renderable;

/**
 * @author Sean Kleinjung
 */
public class FpsDisplay implements Renderable, Updateable {
    private int fontSize;
    private FpsCounter fpsCounter;

    public FpsDisplay() {
        this(12);
    }

    public FpsDisplay(int fontSize) {
        this(fontSize, 1000);
    }

    public FpsDisplay(int fontSize, int updateInterval) {
        this.fontSize = fontSize;
        this.fpsCounter = new FpsCounter(updateInterval);
    }

    public int getFontSize() {
        return fontSize;
    }

    @Override
    public void update(float delta) {
        fpsCounter.onUpdate();
    }

    @Override
    public void render(LunaGraphics g) {
        fpsCounter.onRender();

        g.setColor(0xffffffff);
        g.drawString("FPS: " + fpsCounter.getFramesPerSecond(), g.getWidth() - 20, 20, fontSize, LunaGraphics.HorizontalAlignment.Right, LunaGraphics.VerticalAlignment.Top);
        g.drawString("UPS: " + fpsCounter.getUpdatesPerSecond(), g.getWidth() - 20, 40, fontSize, LunaGraphics.HorizontalAlignment.Right, LunaGraphics.VerticalAlignment.Top);
    }
}
