package com.thrashplay.luna.graphics;

import java.awt.Color;

/**
 * @author Sean Kleinjung
 */
@SuppressWarnings("unused")
public interface LunaGraphics {
    /**
     * Enum specifying horizontal alignment options for text rendering.
     */
    enum HorizontalAlignment {
        Center,
        Left,
        Right
    }

    /**
     * Enum specifying vertical alignment options for text rendering.
     */
    enum VerticalAlignment {
        Center,
        Top,
        Bottom
    }

    // Transformation methods

    /**
     * Save the current state of the transformation matrix, which can be restored later via a call to {@link #restoreTransform()}
     * <p>
     *     This method maintains a stack of transformation states. Each time it is called, the current state is saved
     *     on the stack. These saved states are popped and restored via calls to {@link #restoreTransform()}.
     * </p>
     */
    void saveTransform();

    /**
     * Restore the state of the transformation that was saved with a previous call to {@link #saveTransform()}
     * <p>
     *     If this method is called without calling <code>saveTransform</code> first, then an IllegalStateException will
     *     be thrown.
     * </p>
     */
    void restoreTransform();

    void translate(int x, int y);

    void scale(int sx, int sy);

    // state manipulation methods

    void setColor(int color);

    // rendering methods

    void clearScreen(int color);

    void drawLine(int x, int y, int x2, int y2);

    void drawRect(int x, int y, int width, int height);

    void fillRect(int x, int y, int width, int height);

    void drawCircle(int x, int y, int radius);

    void fillCircle(int x, int y, int radius);

    void drawARGB(int a, int r, int g, int b);

    void drawString(String text, int x, int y);

    void drawString(String text, int x, int y, int size);

    void drawString(String text, int x, int y, int size, HorizontalAlignment horizontalAlignment);

    void drawString(String text, int x, int y, int size, HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment);

    // blitting methods

//    public void drawImage(LunaImage image, int x, int y);

    // accessors

    int getWidth();

    int getHeight();
}
