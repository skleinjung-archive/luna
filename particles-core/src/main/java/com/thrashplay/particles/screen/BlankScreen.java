package com.thrashplay.particles.screen;

import com.thrashplay.luna.engine.entity.GameEntityAdapter;
import com.thrashplay.luna.engine.screen.DefaultScreen;
import com.thrashplay.luna.renderable.ClearScreen;
import com.thrashplay.luna.renderable.FpsDisplay;
import org.springframework.stereotype.Component;

/**
 * @author Sean Kleinjung
 */
@Component
public class BlankScreen extends DefaultScreen {
    @Override
    public String getName() {
        return "blank";
    }

    @Override
    protected void doInitialize() {
        gem.register(new GameEntityAdapter("clearScreen", new ClearScreen()));
        gem.register(new GameEntityAdapter("fps", new FpsDisplay()));
    }
}