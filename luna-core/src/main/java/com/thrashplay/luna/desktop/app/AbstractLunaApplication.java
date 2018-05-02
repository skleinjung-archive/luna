package com.thrashplay.luna.desktop.app;

import com.thrashplay.luna.desktop.app.builder.DefaultGameLoopBuilder;
import com.thrashplay.luna.desktop.app.builder.DefaultScreenManagerBuilder;
import com.thrashplay.luna.desktop.app.builder.DefaultWindowBuilder;
import com.thrashplay.luna.desktop.input.MousePointerManager;
import com.thrashplay.luna.desktop.swing.LunaCanvas;
import com.thrashplay.luna.desktop.swing.LunaWindow;
import com.thrashplay.luna.engine.loop.GameLoop;
import com.thrashplay.luna.engine.screen.ScreenManager;
import com.thrashplay.luna.graphics.LunaGraphics;
import com.thrashplay.luna.input.PointerManager;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author Sean Kleinjung
 */
public abstract class AbstractLunaApplication {

    private ScreenManager screenManager;
    private LunaWindow window;
    private GameLoop gameLoop;

    // modules
    private PointerManager pointerManager;

    public final void run() {
        DefaultWindowBuilder windowBuilder = new DefaultWindowBuilder("Luna Application", 800, 600);
        configureWindow(windowBuilder);
        window = windowBuilder.build();

        // build modules that require the window and/or canvas
        pointerManager = new MousePointerManager(window.getLunaCanvas(), windowBuilder.getSceneWidth(), windowBuilder.getSceneHeight());

        DefaultGameLoopBuilder gameLoopBuilder = new DefaultGameLoopBuilder(60);
        configureGameLoop(gameLoopBuilder);
        gameLoopBuilder.setUpdateCallback(this::updateGameState);
        gameLoopBuilder.setRenderCallback(this::renderFrame);
        gameLoop = gameLoopBuilder.build();

        DefaultScreenManagerBuilder screenManagerBuilder = new DefaultScreenManagerBuilder();
        configureScreens(screenManagerBuilder);
        screenManager = screenManagerBuilder.build();

        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                gameLoop.resume();
            }
        });

        SwingUtilities.invokeLater(() -> window.setVisible(true));
    }

    private void updateGameState(float delta) {
        screenManager.update(delta);
    }

    private void renderFrame() {
        LunaCanvas canvas = window.getLunaCanvas();
        LunaGraphics graphics = canvas.beginFrame();
        try {
            if (graphics != null) {
                screenManager.render(graphics);
            }
        } finally {
            canvas.endFrame();
        }
    }

    protected ScreenManager getScreenManager() {
        return screenManager;
    }

    protected LunaWindow getWindow() {
        return window;
    }

    protected GameLoop getGameLoop() {
        return gameLoop;
    }

    protected PointerManager getPointerManager() {
        return pointerManager;
    }

    protected void configureWindow(WindowConfigurer windowConfigurer) {
        // use defaults, can be overridden
    }

    protected void configureGameLoop(GameLoopConfigurer gameLoopConfigurer) {
        // use defaults, can be overridden
    }

    protected abstract void configureScreens(ScreensConfigurer screensConfigurer);

}
