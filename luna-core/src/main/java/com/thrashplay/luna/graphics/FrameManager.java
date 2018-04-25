package com.thrashplay.luna.graphics;

/**
 * @author Sean Kleinjung
 */
public interface FrameManager {
    /**
     * Called to begin rendering a new frame.
     * @return the graphics object to use for rendering the frame
     */
    LunaGraphics beginFrame();

    /**
     * Called to signal that frame rendering is complete, and that the frame can be displayed.
     */
    void endFrame();
}
