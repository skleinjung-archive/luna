package com.thrashplay.particles.particle;

import com.thrashplay.luna.math.Floats;
import com.thrashplay.luna.math.MathUtils;
import com.thrashplay.luna.math.Vector2D;

/**
 * @author Sean Kleinjung
 */
public class Particle {
    private double x;
    private double y;
    private Vector2D direction;
    private double energy;

    public Particle() {
        this(0, 0);
    }

    public Particle(double x, double y) {
        this(x, y, 255);
    }

    public Particle(double x, double y, int energy) {
        this(x, y, energy, new Vector2D(1, 0));
    }

    public Particle(double x, double y, int energy, Vector2D direction) {
        this.x = x;
        this.y = y;
        this.energy = energy;
        this.direction = direction;
    }

    public void set(double x, double y, int energy, Vector2D direction) {
        this.x = x;
        this.y = y;
        this.energy = energy;
        this.direction = direction;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getEnergy() {
        return energy;
    }

    public void setEnergy(double energy) {
        this.energy = energy;
    }

    public Vector2D getDirection() {
        return direction;
    }

    public void setDirection(Vector2D direction) {
        this.direction = direction;
    }

    public boolean isDead() {
        return Floats.areApproximatelyEqual((float) energy, 0);
    }
}
