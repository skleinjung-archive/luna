package com.thrashplay.luna.engine.loop;

import java.util.concurrent.TimeUnit;

/**
 * @author Sean Kleinjung
 */
public class FixedFpsSingleThreadMainLoop extends AbstractSingleThreadMainLoop {

    private int targetUpdatesPerSecond = 0;

    public FixedFpsSingleThreadMainLoop(UpdateController updateController, RenderController renderController, int targetUpdatesPerSecond) {
        super(updateController, renderController);

        this.targetUpdatesPerSecond = targetUpdatesPerSecond;
        if (targetUpdatesPerSecond < 1) {
            throw new IllegalArgumentException("targetUpdatesPerSecond must be greater than or equal to 1");
        }
    }

    @Override
    protected Runnable getRunnable() {
        return () -> {
            Timing timing = new Timing();
            timing.reset();
            long elapsed = 0;
            long frameDuration = 1000 / targetUpdatesPerSecond;

            while (running) {
                elapsed += timing.elapsedAs(TimeUnit.NANOSECONDS);
                timing.reset();

                while (TimeUnit.MILLISECONDS.convert(elapsed, TimeUnit.NANOSECONDS) >= frameDuration) {
                    update(1.0f);
                    elapsed -= TimeUnit.NANOSECONDS.convert(frameDuration, TimeUnit.MILLISECONDS);
                }

                render();

                // we wait for 1ms because the observed updates per second is 1/2 the requested when we don't
                synchronized (Thread.currentThread()) {
                    try {
                        Thread.currentThread().wait(1);
                    } catch (InterruptedException e) { /* do nothing */ }
                }
                Thread.yield();
            }
        };
    }
}
