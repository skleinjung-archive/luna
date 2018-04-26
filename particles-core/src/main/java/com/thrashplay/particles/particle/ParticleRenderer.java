package com.thrashplay.particles.particle;

import com.thrashplay.luna.engine.entity.GameEntity;
import com.thrashplay.luna.graphics.LunaGraphics;

import java.util.List;

/**
 * @author Sean Kleinjung
 */
public interface ParticleRenderer<T extends Particle> {
    void renderParticles(GameEntity entity, ParticleSystem<T> particleSystem, LunaGraphics graphics, List<T> particles);
}
