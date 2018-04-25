package com.thrashplay.luna.engine.entity;

import com.thrashplay.luna.engine.Updateable;
import com.thrashplay.luna.graphics.Renderable;

/**
 * @author Sean Kleinjung
 */
public class GameEntityAdapter extends GameEntity {
    public GameEntityAdapter(String id, Object target) {
        super(id);

        if (target instanceof Updateable) {
            addFacet((UpdateableFacet) (gameEntity, delta) -> ((Updateable) target).update(delta));
        }
        if (target instanceof Renderable) {
            addFacet((RenderableFacet) (gameEntity, graphics) -> ((Renderable) target).render(graphics));
        }
    }
}
