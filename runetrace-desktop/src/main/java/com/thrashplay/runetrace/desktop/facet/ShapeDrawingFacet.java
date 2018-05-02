package com.thrashplay.runetrace.desktop.facet;

import com.thrashplay.luna.engine.entity.GameEntity;
import com.thrashplay.luna.engine.entity.RenderableFacet;
import com.thrashplay.luna.engine.entity.UpdateableFacet;
import com.thrashplay.luna.graphics.Colors;
import com.thrashplay.luna.graphics.LunaGraphics;
import com.thrashplay.luna.input.PointerManager;
import com.thrashplay.luna.math.Random;
import com.thrashplay.luna.particle.GravityParticle;
import com.thrashplay.luna.particle.ParticlePool;
import com.thrashplay.luna.particle.ParticleSystem;
import com.thrashplay.runetrace.desktop.particle.OngoingLineParticleSystemBehavior;

import java.awt.geom.Path2D;

/**
 * @author Sean Kleinjung
 */
public class ShapeDrawingFacet implements UpdateableFacet, RenderableFacet {

    boolean wasDragging = false;

    private PointerManager pointerManager;
    private Path2D.Float currentPath = new Path2D.Float();

    private float glowAlpha = 110;
    private boolean glowIncreasing = true;

    public ShapeDrawingFacet(PointerManager pointerManager) {
        this.pointerManager = pointerManager;
    }

    @Override
    public void update(GameEntity gameEntity, float delta) {
//        Iterator<LineDrawingPoint> iterator = points.iterator();
//        while (iterator.hasNext()) {
//            LineDrawingPoint point = iterator.next();
//            point.energy--;
//            if (point.energy < 1) {
//                iterator.remove();
//            }
//        }

        if (pointerManager.isDragging()) {
            if (wasDragging) {
                currentPath.lineTo(pointerManager.getX(), pointerManager.getY());
            } else {
                currentPath.moveTo(pointerManager.getX(), pointerManager.getY());
            }

            gameEntity.addFacet(new ParticleSystem<>(new ParticlePool<>(GravityParticle.class), new OngoingLineParticleSystemBehavior(pointerManager.getX(), pointerManager.getY())));
        }

        if (glowIncreasing) {
            glowAlpha += 1.75;
        } else {
            glowAlpha -= 1.75;
        }
        if (glowAlpha > 210) {
            glowIncreasing = false;
        } else if (glowAlpha < 110) {
            glowIncreasing = true;
        }

        wasDragging = pointerManager.isDragging();
    }

    @Override
    public void render(GameEntity gameEntity, LunaGraphics graphics) {
        graphics.setStrokeWidth(3);
        try {
            graphics.setStrokeWidth(8);
            graphics.setColor(Colors.pack((int) 110, 0xd9, 0x00, 0xff));
            graphics.drawShape(currentPath);

            graphics.setStrokeWidth(6);
            graphics.setColor(Colors.pack(255, 0, 0, 0));
            graphics.drawShape(currentPath);

            graphics.setStrokeWidth(6);
            graphics.setColor(Colors.pack((int) glowAlpha, 0xd9, 0x00, 0xff));
            graphics.drawShape(currentPath);

            graphics.setStrokeWidth(3);
            graphics.setColor(Colors.pack(0xff, 0xc3, 0x97, 0x38));
            graphics.drawShape(currentPath);
        } finally {
            graphics.setStrokeWidth(1);
        }
    }
}
