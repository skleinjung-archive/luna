package com.thrashplay.luna.facet;

import com.thrashplay.luna.LunaException;
import com.thrashplay.luna.engine.entity.GameEntity;
import com.thrashplay.luna.engine.entity.UpdateableFacet;
import com.thrashplay.luna.math.Angles;

/**
 * @author Sean Kleinjung
 */
public class Movement implements UpdateableFacet {
    private float velocityX;
    private float velocityY;
    private float maximumVelocityX;
    private float maximumVelocityY;
    private float accelerationX;
    private float accelerationY;

    public Movement() {
        this(0, 0);
    }

    public Movement(float velocityX, float velocityY) {
        this.velocityX = velocityX;
        this.velocityY = velocityY;
    }

    public float getVelocityX() {
        return velocityX;
    }

    public void setVelocityX(float velocityX) {
        if (maximumVelocityX == 0) {
            this.velocityX = velocityX;
        } else {
            if (velocityX < 0) {
                this.velocityX = Math.max(velocityX, -maximumVelocityX);
            } else {
                this.velocityX = Math.min(velocityX, maximumVelocityX);
            }
        }
    }

    public float getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(float velocityY) {
        if (maximumVelocityY == 0) {
            this.velocityY = velocityY;
        } else {
            if (velocityY < 0) {
                this.velocityY = Math.max(velocityY, -maximumVelocityY);
            } else {
                this.velocityY = Math.min(velocityY, maximumVelocityY);
            }
        }
    }

    public float getMaximumVelocityX() {
        return maximumVelocityX;
    }

    public void setMaximumVelocityX(float maximumVelocityX) {
        this.maximumVelocityX = maximumVelocityX;
    }

    public float getMaximumVelocityY() {
        return maximumVelocityY;
    }

    public void setMaximumVelocityY(float maximumVelocityY) {
        this.maximumVelocityY = maximumVelocityY;
    }

    public float getAccelerationX() {
        return accelerationX;
    }

    public void setAccelerationX(float accelerationX) {
        this.accelerationX = accelerationX;
    }

    public float getAccelerationY() {
        return accelerationY;
    }

    public void setAccelerationY(float accelerationY) {
        this.accelerationY = accelerationY;
    }

    /**
     * Retrieves the current speed of this Movement object as a scalar.
     * @return the movement's speed
     */
    public float getCurrentSpeed() {
        return (float) Math.sqrt(getVelocityX() * getVelocityX() + getVelocityY() * getVelocityY());
    }

    /**
     * Sets the velocity vector from a given direction and speed.
     * <p>
     *     This method will honor the maximumVelocityX and maximumVelocityY values, and so the final velocity vector
     *     may not reflect the specified angle or speed.
     * </p>
     * @param angle the direction to move in, in degrees
     * @param speed  the speed to move at
     */
    public void setAngleAndSpeed(float angle, float speed) {
        double angleInRadians = Angles.toRadians(angle);
        setVelocityX((float) (speed * Math.cos(angleInRadians)));
        setVelocityY((float) (-speed * Math.sin(angleInRadians)));
    }

    @Override
    public void update(GameEntity entity, float delta) {
        setVelocityX(getVelocityX() + (getAccelerationX() * delta));
        setVelocityY(getVelocityY() + (getAccelerationY() * delta));

        Position position = entity.getFacet(Position.class);
        if (position == null) {
            throw new LunaException(String.format("Cannot update position for entity '%s': there is no Position facet", entity.getId()));
        }
        position.setX(((velocityX * delta) + position.getX()));
        position.setY(((velocityY * delta) + position.getY()));
    }
}
