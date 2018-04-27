package com.thrashplay.luna.input;

/**
 * @author Sean Kleinjung
 */
public interface PointerListener {
    void onPointerPressed(PointerEvent event);
    void onPointerReleased(PointerEvent event);
    void onPointerDragged(PointerEvent event);
}
