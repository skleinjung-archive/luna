package com.thrashplay.luna.graphics.impl;

import com.thrashplay.luna.graphics.LunaGraphics;
import org.springframework.util.Assert;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.Stack;

/**
 * @author Sean Kleinjung
 */
public class Java2DGraphics implements LunaGraphics {
    // the graphics object used for rendering
    private Graphics2D graphics;

    // the saved transformation
    private Stack<AffineTransform> savedTransforms = new Stack<>();

    public Java2DGraphics(Graphics2D graphics) {
        Assert.notNull(graphics, "graphics cannot be null");
        this.graphics = graphics;
    }

    @Override
    public void saveTransform() {
        savedTransforms.push(graphics.getTransform());
    }

    @Override
    public void restoreTransform() {
        graphics.setTransform(savedTransforms.pop());
    }

    @Override
    public void translate(int x, int y) {
        graphics.translate(x, y);
    }

    @Override
    public void scale(int sx, int sy) {
        graphics.scale(sx, sy);
    }

    @Override
    public void setColor(int color) {
        graphics.setColor(new Color(color, true));
    }

    @Override
    public void clearScreen(int color) {
        graphics.setColor(new Color(color, false));
        java.awt.Rectangle clip = graphics.getClipBounds();
        graphics.fillRect(clip.x, clip.y, clip.width, clip.height);
    }

    @Override
    public void drawLine(int x, int y, int x2, int y2) {
        graphics.drawLine(x, y, x2, y2);
    }

    @Override
    public void drawRect(int x, int y, int width, int height) {
        graphics.drawRect(x, y, width, height);
    }

    @Override
    public void fillRect(int x, int y, int width, int height) {
        graphics.fillRect(x, y, width, height);
    }

    @Override
    public void drawCircle(int x, int y, int radius) {
        graphics.drawOval(x - radius, y - radius, radius * 2, radius * 2);
    }

    @Override
    public void fillCircle(int x, int y, int radius) {
        graphics.fillOval(x - radius, y - radius, radius * 2, radius * 2);
    }

    @Override
    public void drawARGB(int a, int r, int g, int b) {
        graphics.setColor(new Color(r, g, b, a));
        java.awt.Rectangle clip = graphics.getClipBounds();
        graphics.fillRect(clip.x, clip.y, clip.width, clip.height);
    }

    @Override
    public void drawString(String text, int x, int y) {
        graphics.drawString(text, x, y);
    }

    @Override
    public void drawString(String text, int x, int y, int size) {
        graphics.setFont(graphics.getFont().deriveFont((float) size));
        graphics.drawString(text, x, y);
    }

    @Override
    public void drawString(String text, int x, int y, int size, HorizontalAlignment horizontalAlignment) {
        drawString(text, x, y, size, horizontalAlignment, VerticalAlignment.Bottom);
    }

    @Override
    public void drawString(String text, int x, int y, int size, HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment) {
        graphics.setFont(graphics.getFont().deriveFont((float) size));

        FontMetrics fm = graphics.getFontMetrics();

        int alignedX = x;
        switch (horizontalAlignment) {
            case Center:
                alignedX -= (fm.stringWidth(text) / 2);
                break;

            case Right:
                alignedX -= fm.stringWidth(text);
                break;
        }

        int alignedY = y;
        switch (verticalAlignment) {
            case Center:
                alignedY += (fm.getAscent() - fm.getDescent()) / 2;
                break;

            case Top:
                alignedY += fm.getAscent() - fm.getDescent() - fm.getLeading();
                break;
        }

        graphics.drawString(text, alignedX, alignedY);
    }

//    @Override
//    public void drawImage(LunaImage image, int x, int y) {
//        graphics.drawImage(getBufferedImage(image), null, x, y);
//    }

//    private BufferedImage getBufferedImage(LunaImage image) {
//        if (image instanceof DesktopImage) {
//            return ((DesktopImage) image).getBufferedImage();
//        } else {
//            throw new LunaException("Cannot draw unknown image type: " + image);
//        }
//    }

    @Override
    public int getWidth() {
        return (int) graphics.getClipBounds().getWidth();
    }

    @Override
    public int getHeight() {
        return (int) graphics.getClipBounds().getHeight();
    }
}
