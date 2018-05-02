package com.thrashplay.luna.android.graphics;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.thrashplay.luna.LunaException;
import com.thrashplay.luna.geom.Path;
import com.thrashplay.luna.graphics.LunaGraphics;

/**
 * Copyright (c) 2018 Sean Kleinjung
 * All rights reserved.
 */
public class AndroidGraphics implements LunaGraphics {
    private Canvas canvas;
    private Paint paint;
    private int color;
    private int strokeWidth;

    public AndroidGraphics(Canvas canvas) {
        this.canvas = canvas;
        this.paint = new Paint();
    }

    @Override
    public void saveTransform() {
        canvas.save();
    }

    @Override
    public void restoreTransform() {
        canvas.restore();
    }

    @Override
    public void translate(int x, int y) {
        canvas.translate(x, y);
    }

    @Override
    public void scale(int sx, int sy) {
        canvas.scale(sx, sy);
    }

    @Override
    public void setColor(int color) {
        paint.setColor(color);
    }

    @Override
    public void setStrokeWidth(int width) {
        paint.setStrokeWidth(width);
    }

    @Override
    public void clearScreen(int color) {
        canvas.drawRGB((color & 0xff0000) >> 16, (color & 0xff00) >> 8, (color & 0xff));
    }

    @Override
    public void drawLine(int x, int y, int x2, int y2) {
        canvas.drawLine(x, y, x2, y2, paint);
    }

    @Override
    public void drawRect(int x, int y, int width, int height) {
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(x, y, x + width - 1, y + height - 1, paint);
    }

    @Override
    public void fillRect(int x, int y, int width, int height) {
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(x, y, x + width - 1, y + height - 1, paint);
    }

    @Override
    public void drawCircle(int x, int y, int radius) {
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(x, y, radius, paint);
    }

    @Override
    public void fillCircle(int x, int y, int radius) {
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(x, y, radius, paint);
    }

    @Override
    public void drawShape(Path path) {
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawPath((android.graphics.Path) path.getNativePath(), paint);
    }

    @Override
    public void drawARGB(int a, int r, int g, int b) {
        paint.setStyle(Paint.Style.FILL);
        canvas.drawARGB(a, r, g, b);
    }

    @Override
    public void drawString(String text, int x, int y) {
        drawString(text, x, y, 12);
    }

    @Override
    public void drawString(String text, int x, int y, int size) {
        drawString(text, x, y, size, HorizontalAlignment.Left);
    }

    @Override
    public void drawString(String text, int x, int y, int size, HorizontalAlignment horizontalAlignment) {
        drawString(text, x, y, size, horizontalAlignment, VerticalAlignment.Bottom);
    }

    @Override
    public void drawString(String text, int x, int y, int size, HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment) {
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setTextSize(size);
        paint.setTextAlign(getPaintAlign(horizontalAlignment));

        int alignedY = y;
        Rect textBounds;
        switch (verticalAlignment) {
            case Center:
                textBounds = new Rect();
                paint.getTextBounds(text, 0, text.length(), textBounds);
                alignedY = (int) (y - textBounds.exactCenterY());
                break;

            case Top:
                Paint.FontMetrics fm = paint.getFontMetrics();
                alignedY -= fm.ascent + fm.descent;
                break;
        }

        canvas.drawText(text, x, alignedY, paint);
    }

    @Override
    public int getWidth() {
        return canvas.getWidth();
    }

    @Override
    public int getHeight() {
        return canvas.getHeight();
    }

    /**
     * Converts a generic Luna alignment value into an Android Paint.Align instance.
     */
    private Paint.Align getPaintAlign(HorizontalAlignment alignment) {
        switch (alignment) {
            case Center:
                return Paint.Align.CENTER;
            case Left:
                return Paint.Align.LEFT;
            case Right:
                return Paint.Align.RIGHT;
            default:
                throw new LunaException("Unknown alignment value: " + alignment);
        }
    }
}
