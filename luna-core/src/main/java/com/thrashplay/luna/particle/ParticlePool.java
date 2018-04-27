package com.thrashplay.luna.particle;

import com.thrashplay.luna.LunaException;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Sean Kleinjung
 */
public class ParticlePool<T extends Particle> implements ParticleLifecycleManager<T> {
    private Class<T> particleClass;
    private List<T> pool = new LinkedList<>();

    public ParticlePool(Class<T> particleClass) {
        this.particleClass = particleClass;
    }

    public synchronized T acquire() {
        if (pool.size() > 0) {
            return pool.remove(0);
        } else {
            try {
                return particleClass.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                throw new LunaException(String.format("Failed to instantiate particle class '%s': %s", particleClass.getName(), e.toString()), e);
            }
        }
    }

    public void release(T particle) {
        pool.add(particle);
    }
}
