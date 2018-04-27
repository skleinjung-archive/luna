package com.thrashplay.luna.particle;

import com.thrashplay.luna.engine.entity.GameEntity;

/**
 * @author Sean Kleinjung
 */
public interface ParticleEmissionStrategy<T extends Particle> {
    int getNumberOfParticlesToEmit(GameEntity entity, ParticleSystem<T> particleSystem, float delta);
}
