package com.thrashplay.luna.engine;

import com.thrashplay.luna.engine.entity.GameEntity;
import com.thrashplay.luna.graphics.LunaGraphics;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * @author Sean Kleinjung
 */
public class GameEntityManager {
    private List<GameEntity> gameEntities = new ArrayList<>();
    private Map<String, GameEntity> gameEntitiesById = new HashMap<>();

    // this list is used within updateAll, and reset each frame
    private List<GameEntity> gameEntitiesToRemove = new LinkedList<>();

    public synchronized void register(GameEntity gameEntity) {
        if (gameEntity == null) {
            return;
        }

        gameEntities.add(gameEntity);
        if (gameEntity.getId() != null) {
            gameEntitiesById.put(gameEntity.getId(), gameEntity);
        }
    }

    public synchronized void unregister(GameEntity gameEntity) {
        if (gameEntity == null) {
            return;
        }

        gameEntities.remove(gameEntity);
        if (gameEntity.getId() != null) {
            gameEntitiesById.remove(gameEntity.getId());
        }
    }

    public synchronized void unregisterAll() {
        gameEntitiesById.clear();
        gameEntities.clear();
    }

    public GameEntity getGameEntity(String id) {
        return gameEntitiesById.get(id);
    }

    public List<GameEntity> getGameEntities() {
        return gameEntities;
    }

    public synchronized void updateAll(float delta) {
        int len = gameEntities.size();
        for (int i = 0; i < len; i++) {
            GameEntity gameEntity = gameEntities.get(i);
            if (gameEntity.isDead()) {
                gameEntitiesToRemove.add(gameEntity);
            } else {
                gameEntity.update(delta);
            }
        }

        len = gameEntitiesToRemove.size();
        for (int i = 0; i < len; i++) {
            GameEntity gameEntity = gameEntitiesToRemove.get(i);
            gameEntities.remove(gameEntity);
        }

        gameEntitiesToRemove.clear();
    }

    public synchronized void renderAll(final LunaGraphics g) {
        int len = gameEntities.size();
        for (int i = 0; i < len; i++) {
            GameEntity gameEntity = gameEntities.get(i);
            gameEntity.render(g);
        }
    }
}