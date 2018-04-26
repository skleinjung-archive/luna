package com.thrashplay.luna.swing;

import com.thrashplay.luna.config.LunaGameConfig;
import com.thrashplay.luna.config.LunaWindowConfig;
import com.thrashplay.luna.engine.loop.GameLoop;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author Sean Kleinjung
 */
@Component
public class LunaWindow extends JFrame {
    @SuppressWarnings("WeakerAccess")
    protected LunaCanvas lunaCanvas;

    private GameLoop gameLoop;

    public LunaWindow(LunaGameConfig gameConfig, LunaWindowConfig windowConfig, GameLoop gameLoop, LunaCanvas lunaCanvas) {
        super(windowConfig.getWindowTitle());

        Assert.notNull(gameLoop, "mainLoop cannot be null");
        this.gameLoop = gameLoop;

        Assert.notNull(lunaCanvas, "lunaCanvas cannot be null");
        this.lunaCanvas = lunaCanvas;
        this.lunaCanvas.setPreferredSize(new Dimension(windowConfig.getSceneWidth(), windowConfig.getSceneHeight()));

        getContentPane().add(lunaCanvas);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();

        lunaCanvas.requestFocus();
        lunaCanvas.initialize();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                onResume();
            }
        });
    }

    public void onPause() {
        gameLoop.pause();
    }

    public void onResume() {
        gameLoop.resume();
    }
}
