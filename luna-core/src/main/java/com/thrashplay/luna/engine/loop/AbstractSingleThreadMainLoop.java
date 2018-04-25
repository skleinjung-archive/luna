package com.thrashplay.luna.engine.loop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Sean Kleinjung
 */
public abstract class AbstractSingleThreadMainLoop extends AbstractMainLoop {

    private static final Logger log = LoggerFactory.getLogger(AbstractSingleThreadMainLoop.class);

    protected volatile boolean running = false;
    private Thread thread = null;

    protected AbstractSingleThreadMainLoop(UpdateController updateController, RenderController renderController) {
        super(updateController, renderController);
    }

    protected abstract Runnable getRunnable();

    @Override
    public void pause() {
        log.debug("Pausing game loop...");

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
        log.debug("Resuming game loop...");

        running = true;
        Runnable runnable = getRunnable();
        if (runnable != null) {
            thread = new Thread(runnable, "Luna Game Loop");
            thread.start();
        }
    }
}
