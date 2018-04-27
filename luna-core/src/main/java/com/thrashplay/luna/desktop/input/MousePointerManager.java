package com.thrashplay.luna.desktop.input;

import com.thrashplay.luna.config.LunaSceneConfig;
import com.thrashplay.luna.desktop.swing.LunaCanvas;
import com.thrashplay.luna.geom.Point;
import com.thrashplay.luna.graphics.RenderCoordinateMapping;
import com.thrashplay.luna.input.AbstractPointerManager;
import com.thrashplay.luna.input.PointerEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author Sean Kleinjung
 */
@Component
public class MousePointerManager extends AbstractPointerManager {

    private LunaCanvas canvas;
    private int sceneWidth;
    private int sceneHeight;

    // mouse state variables
    private boolean down;
    private boolean dragging;
    private int x;
    private int y;

    @Autowired
    public MousePointerManager(LunaCanvas canvas, LunaSceneConfig config) {
        this(canvas, config.getSceneWidth(), config.getSceneHeight());
    }

    public MousePointerManager(LunaCanvas canvas, int sceneWidth, int sceneHeight) {
        this.canvas = canvas;
        this.sceneWidth = sceneWidth;
        this.sceneHeight = sceneHeight;

        MousePointerManagerMouseListener listener = new MousePointerManagerMouseListener();
        canvas.addMouseListener(listener);
        canvas.addMouseMotionListener(listener);
    }

    @Override
    public boolean isDown() {
        return down;
    }

    @Override
    public boolean isDragging() {
        return dragging;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    private class MousePointerManagerMouseListener extends MouseAdapter {
        Point destinationPoint = new Point();

        @Override
        public void mousePressed(MouseEvent e) {
            // todo: check specific buttons
            updateAndFire(true, false, e.getX(), e.getY());
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            // todo: check specific buttons
            updateAndFire(false, false, e.getX(), e.getY());
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            // todo: check specific buttons
            updateAndFire(true, true, e.getX(), e.getY());
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            updateAndFire(false, false, e.getX(), e.getY());
        }

        private void updateAndFire(boolean newDown, boolean newDragging, int canvasX, int canvasY) {
            down = newDown;
            dragging = newDragging;

            RenderCoordinateMapping.convertScreenCoordinateToSceneCoordinate(destinationPoint, canvasX, canvasY, canvas.getWidth(), canvas.getHeight(), sceneWidth, sceneHeight);
            x = destinationPoint.getX();
            y = destinationPoint.getY();

            firePointerEvent(PointerEvent.Type.Pressed, x, y);
        }
    }
}
