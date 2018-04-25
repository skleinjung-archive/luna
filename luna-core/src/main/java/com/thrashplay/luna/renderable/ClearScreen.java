package com.thrashplay.luna.renderable;

import com.thrashplay.luna.graphics.LunaGraphics;
import com.thrashplay.luna.graphics.Renderable;

import java.awt.*;

/**
 * @author Sean Kleinjung
 */
public class ClearScreen implements Renderable {
    private int color;

    public ClearScreen() {
        this(0);
    }

    public ClearScreen(int color) {
        this.color = color;
    }

    @Override
    public void render(LunaGraphics graphics) {
        graphics.clearScreen(color);
    }
}
