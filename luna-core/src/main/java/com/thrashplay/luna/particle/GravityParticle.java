package com.thrashplay.luna.particle;

/**
 * @author Sean Kleinjung
 */
public class GravityParticle extends Particle {
    private double gravityStrength;

    public double getGravityStrength() {
        return gravityStrength;
    }

    public void setGravityStrength(double gravityStrength) {
        this.gravityStrength = gravityStrength;
    }
}
