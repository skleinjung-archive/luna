package com.thrashplay.particles.particle;

import com.thrashplay.luna.engine.entity.GameEntity;

/**
 * @author Sean Kleinjung
 */
public interface ParticleUpdater<T extends Particle> {
    void updateParticle(GameEntity entity, ParticleSystem<T> particleSystem, float delta, T particle);
}
