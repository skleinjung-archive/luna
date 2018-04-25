package com.thrashplay.particles.screen;

import com.thrashplay.luna.engine.screen.Screen;
import org.springframework.stereotype.Component;

import java.awt.*;

/**
 * @author Sean Kleinjung
 */
@Component
public class BlueScreen implements Screen {
    @Override
    public String getName() {
        return "blue";
    }

    @Override
    public void initialize() {

    }

    @Override
    public void shutdown() {

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

    }

    @Override
    public void render(Graphics2D graphics) {
        graphics.setPaint(Color.BLUE);
        graphics.fillRect(0, 0, 640, 480);
    }
}
