package com.thrashplay.particles.particle;

/**
 * @author Sean Kleinjung
 */
public interface ParticleSystemBehavior<T extends Particle> extends ParticleEmissionStrategy<T>, ParticleInitializer<T>, ParticleUpdater<T>, ParticleRenderer<T> {
}
