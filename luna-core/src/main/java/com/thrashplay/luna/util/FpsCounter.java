package com.thrashplay.luna.util;

import java.util.concurrent.TimeUnit;

/**
 * @author Sean Kleinjung
 */
public class FpsCounter {
    // how often to update the FPS count, in milliseconds
    private int updateInterval;
    // the number of frames elapsed since the FPS was last updated
    private int frames = 0;
    // the number of updates elapsed since the UPS was last updated
    private int updates = 0;
    // timer used to keep track of update intervals
    private Timing timing = new Timing();
    // the current FPS
    private int framesPerSecond = 0;
    // the current UPS
    private int updatesPerSecond = 0;

    public FpsCounter() {
        this(1000);
    }

    public FpsCounter(int updateInterval) {
        this.updateInterval = updateInterval;
    }

    public int getFramesPerSecond() {
        return framesPerSecond;
    }

    public int getUpdatesPerSecond() {
        return updatesPerSecond;
    }

    public void onUpdate() {
        updates++;

        long elapsed = timing.elapsedAs(TimeUnit.MILLISECONDS);
        if (elapsed >= updateInterval) {
            framesPerSecond = (int) Math.round(((double) frames / elapsed) * 1000);
            updatesPerSecond = (int) Math.round(((double) updates / elapsed) * 1000);
            frames = 0;
            updates = 0;
            timing.reset();
        }
    }

    public void onRender() {
        frames++;
    }
}
