package com.thrashplay.luna.input;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Sean Kleinjung
 */
public abstract class AbstractPointerManager implements PointerManager {
    private List<PointerListener> pointerListeners = new LinkedList<>();

    protected void firePointerEvent(PointerEvent.Type type, int x, int y) {
        PointerEvent event = new PointerEvent(type, x, y);
        for (PointerListener listener : pointerListeners) {
            switch (event.getType()) {
                case Pressed:
                    listener.onPointerPressed(event);
                    break;
                case Released:
                    listener.onPointerReleased(event);
                    break;
                case Dragged:
                    listener.onPointerDragged(event);
                    break;
            }
        }
    }

//    @Override
    public void addPointerListener(PointerListener listener) {
        if (listener != null) {
            pointerListeners.add(listener);
        }
    }

//    @Override
    public void removePointerListener(PointerListener listener) {
        if (listener != null) {
            pointerListeners.remove(listener);
        }
    }
}
