package com.thrashplay.luna.graphics;

import java.awt.*;

/**
 * @author Sean Kleinjung
 */
public interface FrameManager {
    /**
     * Called to begin rendering a new frame.
     * @return the graphics object to use for rendering the frame
     */
    Graphics2D beginFrame();

    /**
     * Called to signal that frame rendering is complete, and that the frame can be displayed.
     */
    void endFrame();
}
