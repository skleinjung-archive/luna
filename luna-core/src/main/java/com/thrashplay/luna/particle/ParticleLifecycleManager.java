package com.thrashplay.luna.particle;

/**
 * @author Sean Kleinjung
 */
public interface ParticleLifecycleManager<T extends Particle> {
    T acquire();
    void release(T particle);
}
