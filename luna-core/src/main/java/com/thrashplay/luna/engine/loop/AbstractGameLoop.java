package com.thrashplay.luna.engine.loop;

import com.thrashplay.luna.util.FpsCounter;

/**
 * @author Sean Kleinjung
 */
public abstract class AbstractGameLoop implements GameLoop {

    private GameLoopUpdateCallback gameLoopUpdateCallback;
    private GameLoopRenderCallback gameLoopRenderCallback;

    private FpsCounter fpsCounter = new FpsCounter();

    public AbstractGameLoop(GameLoopUpdateCallback gameLoopUpdateCallback, GameLoopRenderCallback gameLoopRenderCallback) {
        this.gameLoopUpdateCallback = gameLoopUpdateCallback;
        this.gameLoopRenderCallback = gameLoopRenderCallback;
    }

    void update(float delta) {
        if (gameLoopUpdateCallback != null) {
            gameLoopUpdateCallback.update(delta);
        }

        fpsCounter.onUpdate();
    }

    void render() {
        if (gameLoopRenderCallback != null) {
            gameLoopRenderCallback.render();
        }

        fpsCounter.onRender();
    }

}
