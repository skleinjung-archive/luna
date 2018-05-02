package com.thrashplay.runetrace.particle;

import com.thrashplay.luna.engine.entity.GameEntity;
import com.thrashplay.luna.facet.Position;
import com.thrashplay.luna.input.PointerManager;
import com.thrashplay.luna.particle.GravityParticle;
import com.thrashplay.luna.particle.Particle;
import com.thrashplay.luna.particle.ParticleSystem;
import com.thrashplay.luna.particle.ParticleSystemBehavior;
import com.thrashplay.luna.util.Timing;
import com.thrashplay.luna.graphics.Colors;
import com.thrashplay.luna.graphics.LunaGraphics;
import com.thrashplay.luna.math.Random;
import com.thrashplay.luna.math.Vector2D;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author Sean Kleinjung
 */
public class RuneTraceParticleSystemBehavior implements ParticleSystemBehavior<GravityParticle> {

    private Timing timing = new Timing();
    private PointerManager pointerManager;

    public RuneTraceParticleSystemBehavior(PointerManager pointerManager) {
        this.pointerManager = pointerManager;
    }

    @Override
    public int getNumberOfParticlesToEmit(GameEntity entity, ParticleSystem particleSystem, float delta) {
        if (!timing.isStarted()) {
            timing.reset();
        }

//        if (timing.elapsedAs(TimeUnit.MILLISECONDS) > 100) {
//            timing.reset();
//            return 512;
//        } else {
//            return 0;
//        }

        return pointerManager.isDown() ? 64 : 0;
    }

    @Override
    public void initializeParticle(GameEntity entity, ParticleSystem<GravityParticle> particleSystem, GravityParticle particle) {
        Position position = entity.getFacet(Position.class);
        int x = (int) position.getX();
        int y = (int) position.getY();

        particle.set(
                x + Random.getInteger(-3, 4),
                y + Random.getInteger(-3, 4),
                Random.getInteger(32, 256),
                new Vector2D((Math.random() - 0.5D) / 1.5, Math.random() - 0.75D));
        particle.setGravityStrength(0.0075);
    }

    @Override
    public void renderParticles(GameEntity entity, ParticleSystem<GravityParticle> particleSystem, LunaGraphics graphics, List<GravityParticle> particles) {
        List<? extends Particle> sortedParticles = new ArrayList<>(particles);
        Collections.sort(sortedParticles, new Comparator<Particle>() {
            @Override
            public int compare(Particle o1, Particle o2) {
                return Double.compare(o1.getEnergy(), o2.getEnergy());
            }
        });

        int lastEnergy = -1;
        for (Particle particle : sortedParticles) {
            if ((int) particle.getEnergy() != lastEnergy) {
                lastEnergy = (int) particle.getEnergy();
                graphics.setColor(Colors.pack(0xff, (int) (0xc3 * (particle.getEnergy() / 255D)), (int) (0x97 * (particle.getEnergy() / 255D)), (int) (0x38 * (particle.getEnergy() / 255D))));
            }

            graphics.fillRect((int) particle.getX(), (int) particle.getY(), 1, 1);
        }
    }

    @Override
    public void updateParticle(GameEntity entity, ParticleSystem<GravityParticle> particleSystem, float delta, GravityParticle particle) {
        double magnitude = (particle.getEnergy() / 255D) * 3D;

        particle.setX(particle.getX() + particle.getDirection().getX() * magnitude);
        particle.setY(particle.getY() + particle.getDirection().getY() * magnitude);
        particle.setEnergy(particle.getEnergy() / 1.01);
        if (particle.getEnergy() < 32) {
            particle.setEnergy(0);
        }

        particle.getDirection().setY(particle.getDirection().getY() + particle.getGravityStrength());
    }
}

