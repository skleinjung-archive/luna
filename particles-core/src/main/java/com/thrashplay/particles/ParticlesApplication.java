package com.thrashplay.particles;

import com.thrashplay.luna.spring.LunaApplication;
import com.thrashplay.luna.config.DefaultLunaConfig;
import com.thrashplay.luna.AbstractLunaApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author Sean Kleinjung
 */
@LunaApplication
public class ParticlesApplication extends AbstractLunaApplication {
    public static void main(String[] args) {
        new ParticlesApplication().run(args);
    }

    @Bean
    public DefaultLunaConfig getGameConfig() {
        return new DefaultLunaConfig("particles", "Particles", 800, 600, 60);
    }
}