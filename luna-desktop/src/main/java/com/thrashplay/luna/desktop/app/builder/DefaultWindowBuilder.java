package com.thrashplay.luna.desktop.app.builder;

import com.thrashplay.luna.app.ViewConfigurer;
import com.thrashplay.luna.desktop.swing.LunaCanvas;
import com.thrashplay.luna.desktop.swing.LunaWindow;

/**
 * @author Sean Kleinjung
 */
public class DefaultWindowBuilder implements ViewConfigurer {
    private String title;
    private int sceneWidth;
    private int sceneHeight;

    public DefaultWindowBuilder() {
    }

    public DefaultWindowBuilder(String title, int sceneWidth, int sceneHeight) {
        this.title = title;
        this.sceneWidth = sceneWidth;
        this.sceneHeight = sceneHeight;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public void setSceneDimensions(int sceneWidth, int sceneHeight) {
        this.sceneWidth = sceneWidth;
        this.sceneHeight = sceneHeight;
    }

    public int getSceneWidth() {
        return sceneWidth;
    }

    public int getSceneHeight() {
        return sceneHeight;
    }

    public LunaWindow build() {
        return new LunaWindow(title, new LunaCanvas(sceneWidth, sceneHeight));
    }
}
