package com.thrashplay.luna.android.input;

import android.view.MotionEvent;
import android.view.View;

import com.thrashplay.luna.geom.Point;
import com.thrashplay.luna.graphics.RenderCoordinateMapping;
import com.thrashplay.luna.input.AbstractPointerManager;

/**
 * @author Sean Kleinjung
 */
public class AndroidTouchPointerManager extends AbstractPointerManager implements View.OnTouchListener {

    private Point destinationPoint = new Point(); // used to hold results of coordinate conversion
    private int sceneWidth;
    private int sceneHeight;

    private boolean down;
    private boolean dragging;
    private int x;
    private int y;

    public AndroidTouchPointerManager(int sceneWidth, int sceneHeight) {
        this.sceneWidth = sceneWidth;
        this.sceneHeight = sceneHeight;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        synchronized(this) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    down = true;
                    dragging = false;
                    break;
                case MotionEvent.ACTION_MOVE:
                    down = true;
                    dragging = true;
                    break;
                case MotionEvent.ACTION_CANCEL:
                case MotionEvent.ACTION_UP:
                    down = false;
                    dragging = false;
                    break;
            }

            // convert the pointer coordinates from device space to scene space
            RenderCoordinateMapping.convertScreenCoordinateToSceneCoordinate(destinationPoint, (int) event.getX(), (int) event.getY(), v.getWidth(), v.getHeight(), sceneWidth, sceneHeight);
            x = destinationPoint.getX();
            y = destinationPoint.getY();

            return true;
        }
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
}
