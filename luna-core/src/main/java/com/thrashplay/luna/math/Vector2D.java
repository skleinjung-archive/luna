package com.thrashplay.luna.math;

/**
 * TODO: Add class documentation
 *
 * @author Sean Kleinjung
 */
public class Vector2D {
    private int x;
    private int y;

    public Vector2D() {
    }

    public Vector2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
