package com.thrashplay.luna.android.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.thrashplay.luna.android.graphics.AndroidGraphics;
import com.thrashplay.luna.geom.Rectangle;
import com.thrashplay.luna.graphics.FrameManager;
import com.thrashplay.luna.graphics.LunaGraphics;
import com.thrashplay.luna.graphics.RenderCoordinateMapping;

/**
 * Copyright (c) 2018 Sean Kleinjung
 * All rights reserved.
 */
public class LunaSurfaceView extends SurfaceView implements FrameManager {

    private static final String TAG = "Luna.LunaSurfaceView";

    private int sceneWidth;
    private int sceneHeight;

    private SurfaceHolder holder;
    private Bitmap frameBuffer;

    private Canvas canvas;

    public LunaSurfaceView(Context context, int sceneWidth, int sceneHeight) {
        super(context);
        setKeepScreenOn(true);
        holder = getHolder();

        this.sceneWidth = sceneWidth;
        this.sceneHeight = sceneHeight;

        frameBuffer =  Bitmap.createBitmap(sceneWidth, sceneHeight, Bitmap.Config.ARGB_8888);
    }

    /**
     * Returns null if the drawing subsystem is not ready for rendering.
     */
    @Override
    public LunaGraphics beginFrame() {
        if (canvas == null) {
            canvas = new Canvas(frameBuffer);
        }
        return new AndroidGraphics(canvas);
    }

    @Override
    public void endFrame() {
        if (canvas != null) {
            if (holder.getSurface().isValid()) {
                Rect dstRect = new Rect();
                Rectangle sceneBounds = new RenderCoordinateMapping(sceneWidth, sceneHeight, getWidth(), getHeight()).getSceneBoundsInScreenCoordinates();
                dstRect.set(sceneBounds.getLeft(), sceneBounds.getTop(), sceneBounds.getRight(), sceneBounds.getBottom());

                Canvas canvas = holder.lockCanvas();
                canvas.drawBitmap(frameBuffer, null, dstRect, null);
                holder.unlockCanvasAndPost(canvas);
            }

            canvas = null;
        }
    }
}