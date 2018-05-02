package com.thrashplay.luna.desktop.app;

import com.thrashplay.luna.app.LunaAppConfiguration;
import com.thrashplay.luna.app.builder.DefaultGameLoopBuilder;
import com.thrashplay.luna.app.builder.DefaultScreenManagerBuilder;
import com.thrashplay.luna.desktop.app.builder.DefaultWindowBuilder;
import com.thrashplay.luna.desktop.geom.path.AwtPath2dWrapper;
import com.thrashplay.luna.desktop.input.MousePointerManager;
import com.thrashplay.luna.desktop.swing.LunaCanvas;
import com.thrashplay.luna.desktop.swing.LunaWindow;
import com.thrashplay.luna.engine.loop.GameLoop;
import com.thrashplay.luna.engine.loop.GameLoopRenderCallback;
import com.thrashplay.luna.engine.loop.GameLoopUpdateCallback;
import com.thrashplay.luna.engine.screen.ScreenManager;
import com.thrashplay.luna.geom.path.PathWrapperFactory;
import com.thrashplay.luna.graphics.LunaGraphics;
import com.thrashplay.luna.input.PointerManager;
import com.thrashplay.luna.service.DefaultLunaServiceRegistry;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.SwingUtilities;

/**
 * @author Sean Kleinjung
 */
public abstract class AbstractLunaApplication {

    private LunaAppConfiguration appConfiguration;

    private DefaultLunaServiceRegistry registry = new DefaultLunaServiceRegistry();

    private ScreenManager screenManager;
    private LunaWindow window;
    private GameLoop gameLoop;

    // modules
    private PointerManager pointerManager;

    protected AbstractLunaApplication(LunaAppConfiguration appConfiguration) {
        this.appConfiguration = appConfiguration;
    }

    public final void run() {
        DefaultWindowBuilder windowBuilder = new DefaultWindowBuilder("Luna Application", 800, 600);
        appConfiguration.configureView(windowBuilder);
        window = windowBuilder.build();

        // build modules that require the window and/or canvas
        PathWrapperFactory.get().setPathWrapperClass(AwtPath2dWrapper.class);
        registry.registerService(PointerManager.class, new MousePointerManager(window.getLunaCanvas(), windowBuilder.getSceneWidth(), windowBuilder.getSceneHeight()));

        DefaultGameLoopBuilder gameLoopBuilder = new DefaultGameLoopBuilder(60);
        appConfiguration.configureGameLoop(gameLoopBuilder);
        gameLoopBuilder.setUpdateCallback(new GameLoopUpdateCallback() {
            @Override
            public void update(float delta) {
                updateGameState(delta);
            }
        });
        gameLoopBuilder.setRenderCallback(new GameLoopRenderCallback() {
            @Override
            public void render() {
                renderFrame();
            }
        });
        gameLoop = gameLoopBuilder.build();

        DefaultScreenManagerBuilder screenManagerBuilder = new DefaultScreenManagerBuilder();
        appConfiguration.configureScreens(screenManagerBuilder, registry);
        screenManager = screenManagerBuilder.build();

        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                gameLoop.resume();
            }
        });

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                window.setVisible(true);
            }
        });
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
}
