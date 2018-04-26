package com.thrashplay.particles.screen;

import com.thrashplay.luna.engine.entity.GameEntity;
import com.thrashplay.luna.engine.entity.GameEntityAdapter;
import com.thrashplay.luna.engine.screen.DefaultScreen;
import com.thrashplay.luna.facet.Movement;
import com.thrashplay.luna.facet.Position;
import com.thrashplay.luna.renderable.ClearScreen;
import com.thrashplay.luna.renderable.FpsDisplay;
import com.thrashplay.particles.particle.GravityParticle;
import com.thrashplay.particles.particle.Particle;
import com.thrashplay.particles.particle.ParticlePool;
import com.thrashplay.particles.particle.ParticleSystem;
import com.thrashplay.particles.runetrace.RuneTraceParticleSystemBehavior;
import org.springframework.stereotype.Component;

/**
 * @author Sean Kleinjung
 */
@Component
public class ParticlesScreen extends DefaultScreen {
    @Override
    public String getName() {
        return "particles";
    }

    @Override
    protected void doInitialize() {
        gem.register(new GameEntityAdapter("clearScreen", new ClearScreen()));
        gem.register(new GameEntityAdapter("fps", new FpsDisplay()));

        GameEntity particles = new GameEntity("particles");
        particles.addFacet(new Position(400, 300));
        particles.addFacet(new Movement());
        particles.addFacet(new ParticleSystem<>(new ParticlePool<>(GravityParticle.class), new RuneTraceParticleSystemBehavior()));
        gem.register(particles);

//        GameEntity rectangle = new GameEntity("rectangle");
//        rectangle.addFacet(new Position(200, 200));
//        rectangle.addFacet(new Movement());
//        rectangle.addFacet((Renderer) (gameEntity, graphics) -> {
//            Position position = gameEntity.getFacet(Position.class);
//            if (position == null) {
//                throw new LunaException(String.format("Cannot render entity '%s': there is no Position facet", gameEntity.getId()));
//            }
//
//            float x = position.getX();
//            float y = position.getY();
//            graphics.fillRect((int) x - 50, (int) y - 50, 100, 100, 0xffC39738);
//        });
//        gem.register(rectangle);
    }
}
