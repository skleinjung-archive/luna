package com.thrashplay.luna.facet;

/**
 * TODO: Add class documentation
 *
 * @author Sean Kleinjung
 */
public class Position {
    private float left;
    private float top;
    private int width;
    private int height;

    public Position() {
        this(0, 0);
    }

    public Position(float x, float y) {
        this(x, y, 0, 0);
    }

    public Position(float left, float top, int width, int height) {
        setRect(left, top, width, height);
    }

    public void setX(float x) {
        left = x;
    }

    public float getX() {
        return left;
    }

    public float getLeft() {
        return left;
    }

    public float getRight() {
        return left + width - 1;
    }

    public void setY(float y) {
        top = y;
    }

    public float getY() {
        return top;
    }

    public float getTop() {
        return top;
    }

    public float getBottom() {
        return top + height - 1;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getWidth() {
        return width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public float getCenterY() {
        return getTop() + getHeight() / 2;
    }

    public float getCenterX() {
        return getLeft() + getWidth() / 2;
    }

    public void setRect(float left, float top, int width, int height) {
        this.left = left;
        this.top = top;
        this.width = width;
        this.height = height;
    }
}
