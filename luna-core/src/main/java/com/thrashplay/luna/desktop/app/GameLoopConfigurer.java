package com.thrashplay.luna.desktop.app;

/**
 * @author Sean Kleinjung
 */
public interface GameLoopConfigurer {
    void setRenderOnSameThread();

    void setTargetUpdatesPerSecond(int targetUpdatesPerSecond);
}
