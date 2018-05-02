package com.thrashplay.luna.engine.entity;

import com.thrashplay.luna.engine.Updateable;
import com.thrashplay.luna.graphics.LunaGraphics;
import com.thrashplay.luna.graphics.Renderable;

/**
 * @author Sean Kleinjung
 */
public class GameEntityAdapter extends GameEntity {
    public GameEntityAdapter(String id, final Object target) {
        super(id);

        if (target instanceof Updateable) {
            addFacet(new UpdateableFacet() {
                @Override
                public void update(GameEntity gameEntity, float delta) {
                    ((Updateable) target).update(delta);
                }
            });
        }
        if (target instanceof Renderable) {
            addFacet(new RenderableFacet() {
                @Override
                public void render(GameEntity gameEntity, LunaGraphics graphics) {
                    ((Renderable) target).render(graphics);
                }
            });
        }
    }
}
