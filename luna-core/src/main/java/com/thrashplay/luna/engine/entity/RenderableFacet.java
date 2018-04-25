package com.thrashplay.luna.engine.entity;

import com.thrashplay.luna.graphics.LunaGraphics;

/**
 * A facet for game entities that can be rendered.
 *
 * @author Sean Kleinjung
 */
public interface RenderableFacet {
    /**
     * Renders a game entity using this facet.
     * @param gameEntity the game entity being rendered
     */
    void render(GameEntity gameEntity, LunaGraphics graphics);
}
