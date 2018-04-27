package com.thrashplay.luna.particle;

import com.thrashplay.luna.engine.entity.GameEntity;

/**
 * @author Sean Kleinjung
 */
public interface ParticleInitializer<T extends Particle> {
    void initializeParticle(GameEntity entity, ParticleSystem<T> particleSystem, T particle);
}
