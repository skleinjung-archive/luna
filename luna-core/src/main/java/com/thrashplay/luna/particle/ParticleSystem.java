package com.thrashplay.luna.particle;

import com.thrashplay.luna.LunaException;
import com.thrashplay.luna.engine.entity.GameEntity;
import com.thrashplay.luna.engine.entity.RenderableFacet;
import com.thrashplay.luna.engine.entity.UpdateableFacet;
import com.thrashplay.luna.facet.Position;
import com.thrashplay.luna.graphics.LunaGraphics;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Sean Kleinjung
 */
public class ParticleSystem<T extends Particle> implements UpdateableFacet, RenderableFacet {
    private List<T> particles = new LinkedList<>();

    private ParticleLifecycleManager<T> lifecycleManager;
    private ParticleEmissionStrategy<T> emissionStrategy;
    private ParticleInitializer<T> particleInitializer;
    private ParticleUpdater<T> particleUpdater;
    private ParticleRenderer<T> particleRenderer;

    public ParticleSystem(ParticleLifecycleManager<T> lifecycleManager, ParticleSystemBehavior<T> behavior) {
        this(lifecycleManager, behavior, behavior, behavior, behavior);
    }

    public ParticleSystem(ParticleLifecycleManager<T> lifecycleManager, ParticleEmissionStrategy<T> emissionStrategy, ParticleInitializer<T> particleInitializer, ParticleUpdater<T> particleUpdater, ParticleRenderer<T> particleRenderer) {
        this.lifecycleManager = lifecycleManager;
        this.emissionStrategy = emissionStrategy;
        this.particleInitializer = particleInitializer;
        this.particleUpdater = particleUpdater;
        this.particleRenderer = particleRenderer;
    }

    @Override
    public void update(GameEntity gameEntity, float delta) {
        Iterator<T> iterator = particles.iterator();
        while (iterator.hasNext()) {
            T particle = iterator.next();
            particleUpdater.updateParticle(gameEntity, this, delta, particle);

            if (particle.isDead()) {
                lifecycleManager.release(particle);
                iterator.remove();
            }
        }

        int particlesToEmit = emissionStrategy.getNumberOfParticlesToEmit(gameEntity, this, delta);
        for (int i = 0; i < particlesToEmit; i++) {
            T particle = lifecycleManager.acquire();
            particleInitializer.initializeParticle(gameEntity, this, particle);
            particles.add(particle);
        }
    }

    @Override
    public void render(GameEntity gameEntity, LunaGraphics graphics) {
        Position position = gameEntity.getFacet(Position.class);
        if (position == null) {
            throw new LunaException(String.format("Cannot render entity '%s': there is no Position facet", gameEntity.getId()));
        }

//        graphics.saveTransform();
        try {
//            graphics.translate((int) position.getX(), (int) position.getY());

            particleRenderer.renderParticles(gameEntity, this, graphics, particles);
        } finally {
//            graphics.restoreTransform();
        }
    }
}
