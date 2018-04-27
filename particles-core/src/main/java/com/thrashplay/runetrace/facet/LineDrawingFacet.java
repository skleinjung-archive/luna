package com.thrashplay.runetrace.facet;

import com.thrashplay.luna.engine.entity.GameEntity;
import com.thrashplay.luna.engine.entity.RenderableFacet;
import com.thrashplay.luna.engine.entity.UpdateableFacet;
import com.thrashplay.luna.graphics.Colors;
import com.thrashplay.luna.graphics.LunaGraphics;
import com.thrashplay.luna.input.PointerManager;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Sean Kleinjung
 */
public class LineDrawingFacet implements UpdateableFacet, RenderableFacet {

    private PointerManager pointerManager;
    private List<LineDrawingPoint> points = new LinkedList<>();

    public LineDrawingFacet(PointerManager pointerManager) {
        this.pointerManager = pointerManager;
    }

    @Override
    public void update(GameEntity gameEntity, float delta) {
        Iterator<LineDrawingPoint> iterator = points.iterator();
        while (iterator.hasNext()) {
            LineDrawingPoint point = iterator.next();
            point.energy--;
            if (point.energy < 1) {
                iterator.remove();
            }
        }

        if (pointerManager.isDragging()) {
            points.add(new LineDrawingPoint(pointerManager.getX(), pointerManager.getY(), 255));
        } else if (points.size() > 0) {
            points.get(points.size() - 1).penUp = true;
        }
    }

    @Override
    public void render(GameEntity gameEntity, LunaGraphics graphics) {
        if (points.size() > 1) {
            graphics.setStrokeWidth(3);
            try {
                for (int i = 1; i < points.size(); i++) {
                    LineDrawingPoint p1 = points.get(i - 1);
                    LineDrawingPoint p2 = points.get(i);

                    graphics.setColor(Colors.pack(0xff, (int) (0xc3 * (p1.energy / 255D)), (int) (0x97 * (p1.energy / 255D)), (int) (0x38 * (p1.energy / 255D))));
                    graphics.drawLine(p1.x, p1.y, p2.x, p2.y);

                    if (p2.penUp) {
                        i++;
                    }
                }
            } finally {
                graphics.setStrokeWidth(1);
            }
        }
    }

    private static class LineDrawingPoint {
        private int x;
        private int y;
        private int energy;
        private boolean penUp;

        public LineDrawingPoint(int x, int y, int energy) {
            this.x = x;
            this.y = y;
            this.energy = energy;
        }
    }
}
