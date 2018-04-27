package com.thrashplay.luna.config;

/**
 * @author Sean Kleinjung
 */
public class DefaultLunaConfig implements LunaGameConfig, LunaLoopConfig, LunaSceneConfig, LunaWindowConfig {

    private String defaultScreen;
    private String windowTitle;
    private int sceneWidth;
    private int sceneHeight;
    private int targetUpdatesPerSecond;

    public DefaultLunaConfig(String defaultScreen, String windowTitle, int sceneWidth, int sceneHeight, int targetUpdatesPerSecond) {
        this.defaultScreen = defaultScreen;
        this.windowTitle = windowTitle;
        this.sceneWidth = sceneWidth;
        this.sceneHeight = sceneHeight;
        this.targetUpdatesPerSecond = targetUpdatesPerSecond;
    }

    @Override
    public String getDefaultScreen() {
        return defaultScreen;
    }

    @Override
    public String getWindowTitle() {
        return windowTitle;
    }

    @Override
    public int getSceneWidth() {
        return sceneWidth;
    }

    @Override
    public int getSceneHeight() {
        return sceneHeight;
    }

    @Override
    public int getTargetUpdatesPerSecond() {
        return targetUpdatesPerSecond;
    }
}
