package com.thrashplay.luna.engine.loop;

/**
 * @author Sean Kleinjung
 */
public abstract class AbstractSingleThreadGameLoop extends AbstractGameLoop {

    protected volatile boolean running = false;
    private Thread thread = null;

    protected AbstractSingleThreadGameLoop(GameLoopUpdateCallback gameLoopUpdateCallback, GameLoopRenderCallback gameLoopRenderCallback) {
        super(gameLoopUpdateCallback, gameLoopRenderCallback);
    }

    protected abstract Runnable getRunnable();

    @Override
    public void pause() {
//        log.debug("Pausing game loop...");

        running = false;
        while (thread.isAlive()) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                // ignore and retry
            }
        }
    }

    @Override
    public void resume() {
//        log.debug("Resuming game loop...");

        running = true;
        Runnable runnable = getRunnable();
        if (runnable != null) {
            thread = new Thread(runnable, "Luna Game Loop");
            thread.start();
        }
    }
}
