package com.thrashplay.luna.desktop.swing;

import com.thrashplay.luna.config.LunaGameConfig;
import com.thrashplay.luna.config.LunaSceneConfig;
import com.thrashplay.luna.config.LunaWindowConfig;
import com.thrashplay.luna.engine.loop.GameLoop;
import com.thrashplay.luna.util.Assert;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author Sean Kleinjung
 */
public class LunaWindow extends JFrame {
    @SuppressWarnings("WeakerAccess")
    protected LunaCanvas lunaCanvas;

    public LunaWindow(String title, LunaCanvas lunaCanvas) {
        super(title);

        Assert.notNull(lunaCanvas, "lunaCanvas");
        this.lunaCanvas = lunaCanvas;

        getContentPane().add(lunaCanvas);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();

        lunaCanvas.requestFocus();
        lunaCanvas.initialize();
    }

    public LunaCanvas getLunaCanvas() {
        return lunaCanvas;
    }
}
