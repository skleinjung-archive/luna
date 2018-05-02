package com.thrashplay.luna.geom.path;

/**
 * Copyright (c) 2018 Sean Kleinjung
 * All rights reserved.
 */
public interface PathWrapper {
    Object getNativePath();
    void moveTo(float x, float y);
    void lineTo(float x, float y);
}
