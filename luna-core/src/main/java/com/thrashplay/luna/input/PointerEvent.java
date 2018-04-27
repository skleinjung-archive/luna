package com.thrashplay.luna.input;

/**
 * @author Sean Kleinjung
 */
public class PointerEvent {
    public enum Type {
        Pressed,
        Released,
        Dragged
    }

    private Type type;
    private int x;
    private int y;

    public PointerEvent(Type type, int x, int y) {
        this.type = type;
        this.x = x;
        this.y = y;
    }

    public Type getType() {
        return type;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
