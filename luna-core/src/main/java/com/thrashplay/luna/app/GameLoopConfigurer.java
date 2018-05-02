package com.thrashplay.luna.app;

/**
 * @author Sean Kleinjung
 */
public interface GameLoopConfigurer {
    void setRenderOnSameThread();

    void setTargetUpdatesPerSecond(int targetUpdatesPerSecond);
}
