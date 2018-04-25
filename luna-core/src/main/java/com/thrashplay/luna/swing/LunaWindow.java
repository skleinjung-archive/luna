package com.thrashplay.luna.swing;

import com.thrashplay.luna.config.LunaGameConfig;
import com.thrashplay.luna.config.LunaWindowConfig;
import com.thrashplay.luna.engine.loop.MainLoop;
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

    private MainLoop mainLoop;

    public LunaWindow(LunaGameConfig gameConfig, LunaWindowConfig windowConfig, MainLoop mainLoop, LunaCanvas lunaCanvas) {
        super(windowConfig.getWindowTitle());

        Assert.notNull(mainLoop, "mainLoop cannot be null");
        this.mainLoop = mainLoop;

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
        mainLoop.pause();
    }

    public void onResume() {
        mainLoop.resume();
    }
}
