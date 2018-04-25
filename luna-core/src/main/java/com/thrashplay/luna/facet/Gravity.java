package com.thrashplay.luna.facet;

import com.thrashplay.luna.LunaException;
import com.thrashplay.luna.engine.entity.GameEntity;
import com.thrashplay.luna.engine.entity.UpdateableFacet;

/**
 * @author Sean Kleinjung
 */
public class Gravity implements UpdateableFacet {

    private static final int DEFAULT_TERMINAL_VELOCITY = 15;

    private float strength;
    private int terminalVelocity;

    public Gravity(float strength) {
        this(strength, DEFAULT_TERMINAL_VELOCITY);
    }

    public Gravity(float strength, int terminalVelocity) {
        this.strength = strength;
        this.terminalVelocity = terminalVelocity;
    }

    public float getStrength() {
        return strength;
    }

    public void setStrength(float strength) {
        this.strength = strength;
    }

    @Override
    public void update(GameEntity entity, float delta) {
        Movement movement = entity.getFacet(Movement.class);
        if (movement == null) {
            throw new LunaException(String.format("Cannot apply gravity to entity '%s' the entity has no Movement facet", entity.getId()));
        }
        movement.setVelocityY(Math.min(movement.getVelocityY() + strength, terminalVelocity));
    }
}
