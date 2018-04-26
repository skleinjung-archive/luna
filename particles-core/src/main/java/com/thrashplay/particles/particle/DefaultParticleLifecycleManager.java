package com.thrashplay.particles.particle;

import com.thrashplay.luna.LunaException;

/**
 * @author Sean Kleinjung
 */
public class DefaultParticleLifecycleManager<T extends Particle> implements ParticleLifecycleManager<T> {
    private Class<T> particleClass;

    public DefaultParticleLifecycleManager(Class<T> particleClass) {
        this.particleClass = particleClass;
    }

    @Override
    public T acquire() {
        try {
            return particleClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new LunaException(String.format("Failed to create particle of type '%s': %s", particleClass.getName(), e.toString()));
        }
    }

    @Override
    public void release(T particle) {
        // do nothing, let the GC handle it
    }
}
