package com.thrashplay.luna.input;

/**
 * @author Sean Kleinjung
 */
public interface PointerManager {
    // polling methods
    boolean isDown();
    boolean isDragging();
    int getX();
    int getY();

    // event-driven methods
    void addPointerListener(PointerListener listener);
    void removePointerListener(PointerListener listener);
}
