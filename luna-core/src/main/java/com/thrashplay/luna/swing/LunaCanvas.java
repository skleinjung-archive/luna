package com.thrashplay.luna.swing;

import com.thrashplay.luna.config.DefaultLunaConfig;
import com.thrashplay.luna.geom.Rectangle;
import com.thrashplay.luna.graphics.FrameManager;
import com.thrashplay.luna.graphics.LunaGraphics;
import com.thrashplay.luna.graphics.RenderCoordinateMapping;
import com.thrashplay.luna.graphics.impl.Java2DGraphics;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

/**
 * @author Sean Kleinjung
 */
@Component
public class LunaCanvas extends Canvas implements FrameManager {
    // the buffer strategy used to handle page flipping
    private BufferStrategy bufferStrategy;

    // the graphics object used to render to the current buffer
    private Graphics2D graphics;

    // the frame buffer our scene is rendered to, will be scaled and displayed on the canvas
    private BufferedImage frameBuffer;

    private int sceneWidth;
    private int sceneHeight;

    public LunaCanvas(DefaultLunaConfig config) {
        this.sceneWidth = config.getSceneWidth();
        this.sceneHeight = config.getSceneHeight();
    }

    /**
     * Initialize the canvas. This method must be called after it has been made visible.
     */
    public void initialize() {
        createBufferStrategy(2);
        bufferStrategy = getBufferStrategy();
    }

    @Override
    public LunaGraphics beginFrame() {
        if (graphics == null) {
            frameBuffer = new BufferedImage(sceneWidth, sceneHeight, BufferedImage.TYPE_4BYTE_ABGR);
            graphics = (Graphics2D) frameBuffer.getGraphics();

            // set the clip, so the canvas dimensions can be accessed by Drawable instances
            graphics.setClip(0, 0, sceneWidth, sceneHeight);
        }
        return new Java2DGraphics(graphics);
    }

    @Override
    public void endFrame() {
        if (graphics != null) {
            graphics.dispose();

            Rectangle sceneBounds = new RenderCoordinateMapping(sceneWidth, sceneHeight, getWidth(), getHeight()).getSceneBoundsInScreenCoordinates();
            float scale = (float) sceneBounds.getWidth() / sceneWidth;
            AffineTransform tx = AffineTransform.getTranslateInstance(sceneBounds.getLeft(), sceneBounds.getTop());
            tx.scale(scale, scale);

            Graphics2D backBufferGraphics = (Graphics2D) bufferStrategy.getDrawGraphics();
            backBufferGraphics.setColor(Color.GRAY);
            backBufferGraphics.fillRect(0, 0, getWidth(), getHeight());
            backBufferGraphics.drawImage(frameBuffer, tx, null);
            backBufferGraphics.dispose();

            bufferStrategy.show();
            graphics = null;
        }
    }
}
