package com.thrashplay.luna.engine.entity;

/**
 * A facet for game entities that can be updated.
 *
 * @author Sean Kleinjung
 */
public interface UpdateableFacet {
    /**
     * Updates a game object using this facet.
     * @param gameEntity the game entity being updated
     */
    void update(GameEntity gameEntity, float delta);
}
