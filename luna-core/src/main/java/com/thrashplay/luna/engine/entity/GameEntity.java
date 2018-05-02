package com.thrashplay.luna.engine.entity;

import com.thrashplay.luna.LunaException;
import com.thrashplay.luna.engine.Updateable;
import com.thrashplay.luna.facet.Movement;
import com.thrashplay.luna.facet.Position;
import com.thrashplay.luna.facet.Renderer;
import com.thrashplay.luna.graphics.LunaGraphics;
import com.thrashplay.luna.graphics.Renderable;
import com.thrashplay.luna.util.Assert;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author Sean Kleinjung
 */
@SuppressWarnings({"ForLoopReplaceableByForEach", "unused" })
public class GameEntity implements Updateable, Renderable {
    private String id;

    // flag indicating whether the object is close enough to the player to be active or not
    private boolean active = true;
    // used to indicate the entity should be removed
    private boolean dead;

    // conceptually, we want a map since we are looking up values by facet class
    // the map iterator performance is bad though, so we use a list and cache commonly used values - resorting to a list search for the rest
    private List<Object> facets = new ArrayList<>();

    // cached references to the most commonly used facets
    private Renderer renderer;
    private Position position;
    private Movement movement;

    public GameEntity(String id) {
        Assert.notNull(id, "id");
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public void addFacet(Object facet) {
        if (facet == null) {
            throw new LunaException("Facet cannot be null.");
        }

        // cache the reference for common types
        if (facet instanceof Renderer) {
            renderer = (Renderer) facet;
        } else if (facet instanceof Position) {
            position = (Position) facet;
        } else if (facet instanceof Movement) {
            movement = (Movement) facet;
        }

        facets.add(facet);
    }

    @SuppressWarnings({"unchecked"})
    public void removeFacet(final Class type) {
        // remove cache references
        if (Renderer.class.equals(type)) {
            renderer = null;
        } else if (Position.class.equals(type)) {
            position = null;
        } else if (Movement.class.equals(type)) {
            movement = null;
        }

        Iterator iterator = facets.iterator();
        while (iterator.hasNext()) {
            Object facet = iterator.next();
            if (type.isAssignableFrom(facet.getClass())) {
                iterator.remove();
            }
        }
    }

    @SuppressWarnings({"unchecked"})
    public <T> T getFacet(Class<T> type) {
        if (Renderer.class.equals(type)) {
            return (T) renderer;
        } else if (Position.class.equals(type)) {
            return (T) position;
        } else if (Movement.class.equals(type)) {
            return (T) movement;
        }

        for (int i = 0; i < facets.size(); i++) {
            Object facet = facets.get(i);
            if (type.isAssignableFrom(facet.getClass())) {
                return (T) facet;
            }
        }

        return null;
    }

    /**
     * Retrieves all facets of a specified type, placing them in the given destination list. The list's contents
     * will be cleared prior to anything be added. If no components match the specified type, then the destination will
     * be empty.
     */
    @SuppressWarnings("unchecked")
    public <T> List<T> getFacets(Class<T> type, List<T> destination) {
        destination.clear();
        for (int i = 0; i < facets.size(); i++) {
            Object facet = facets.get(i);
            if (type.isAssignableFrom(facet.getClass())) {
                destination.add((T) facet);
            }
        }

        return destination;
    }

    public List getFacets() {
        return facets;
    }

    @Override
    public void update(float delta) {
        if (!active) {
            return;
        }

        for (int i = 0; i < facets.size(); i++) {
            Object facet = facets.get(i);
            if (facet instanceof UpdateableFacet) {
                ((UpdateableFacet) facet).update(this, delta);
            }
        }
    }

    @Override
    public void render(LunaGraphics graphics) {
        if (!active) {
            return;
        }

        for (int i = 0; i < facets.size(); i++) {
            Object facet = facets.get(i);
            if (facet instanceof RenderableFacet) {
                ((RenderableFacet) facet).render(this, graphics);
            }
        }
    }
}
