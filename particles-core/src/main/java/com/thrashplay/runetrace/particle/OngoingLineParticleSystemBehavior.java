package com.thrashplay.runetrace.particle;

import com.thrashplay.luna.engine.entity.GameEntity;
import com.thrashplay.luna.facet.Position;
import com.thrashplay.luna.graphics.Colors;
import com.thrashplay.luna.graphics.LunaGraphics;
import com.thrashplay.luna.input.PointerManager;
import com.thrashplay.luna.math.Random;
import com.thrashplay.luna.math.Vector2D;
import com.thrashplay.luna.particle.GravityParticle;
import com.thrashplay.luna.particle.Particle;
import com.thrashplay.luna.particle.ParticleSystem;
import com.thrashplay.luna.particle.ParticleSystemBehavior;
import com.thrashplay.luna.util.Timing;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author Sean Kleinjung
 */
public class OngoingLineParticleSystemBehavior implements ParticleSystemBehavior<GravityParticle> {

    private int x;
    private int y;

    public OngoingLineParticleSystemBehavior(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int getNumberOfParticlesToEmit(GameEntity entity, ParticleSystem particleSystem, float delta) {
//        if (Random.getInteger(0, 2) == 0) {
//            return 1;
//        } else {
//            return 0;
//        }
        return 2;
    }

    @Override
    public void initializeParticle(GameEntity entity, ParticleSystem<GravityParticle> particleSystem, GravityParticle particle) {
        particle.set(
                x + Random.getInteger(-3, 4),
                y + Random.getInteger(-3, 4),
                Random.getInteger(32, 256),
                new Vector2D((Math.random() - 0.5D) / 1.5, Math.random() - 0.75D));
        particle.setGravityStrength(0.0075);
    }

    @Override
    public void renderParticles(GameEntity entity, ParticleSystem<GravityParticle> particleSystem, LunaGraphics graphics, List<GravityParticle> particles) {
        List<Particle> sortedParticles = new ArrayList<>(particles);
        sortedParticles.sort(Comparator.comparingDouble(Particle::getEnergy));

        int lastEnergy = -1;
        for (Particle particle : sortedParticles) {
            if ((int) particle.getEnergy() != lastEnergy) {
                lastEnergy = (int) particle.getEnergy();
//                graphics.setColor(Colors.pack(0xff, (int) (0xc3 * (particle.getEnergy() / 255D)), (int) (0x97 * (particle.getEnergy() / 255D)), (int) (0x38 * (particle.getEnergy() / 255D))));
                graphics.setColor(Colors.pack((int) (0xff * (particle.getEnergy() / 255D)), 0xc3, 0x97, 0x38));
            }

            graphics.fillRect((int) particle.getX(), (int) particle.getY(), 1, 1);
        }
    }

    @Override
    public void updateParticle(GameEntity entity, ParticleSystem<GravityParticle> particleSystem, float delta, GravityParticle particle) {
        double magnitude = (particle.getEnergy() / 255D) * 4D;

        particle.setX(particle.getX() + particle.getDirection().getX() * magnitude);
        particle.setY(particle.getY() + particle.getDirection().getY() * magnitude);
        particle.setEnergy(particle.getEnergy() / 1.07);
        if (particle.getEnergy() < 32) {
            particle.setEnergy(0);
        }

        particle.getDirection().setY(particle.getDirection().getY() + particle.getGravityStrength());
    }
}
