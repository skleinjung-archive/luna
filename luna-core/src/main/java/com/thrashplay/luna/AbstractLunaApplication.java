package com.thrashplay.luna;

import com.thrashplay.luna.config.LunaLoopConfig;
import com.thrashplay.luna.engine.loop.GameLoopRenderCallback;
import com.thrashplay.luna.engine.loop.GameLoopUpdateCallback;
import com.thrashplay.luna.swing.LunaWindow;
import com.thrashplay.luna.engine.loop.AbstractGameLoop;
import com.thrashplay.luna.engine.loop.FixedFpsSingleThreadGameLoop;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;

import java.awt.*;

/**
 * @author Sean Kleinjung
 */
public abstract class AbstractLunaApplication {
    protected void run(String[] args) {
        new SpringApplicationBuilder(getClass())
                .headless(false)
                .run(args);
    }

    @Bean
    public AbstractGameLoop getMainLoop(LunaLoopConfig loopConfig, GameLoopUpdateCallback gameLoopUpdateCallback, GameLoopRenderCallback gameLoopRenderCallback) {
        return new FixedFpsSingleThreadGameLoop(gameLoopUpdateCallback, gameLoopRenderCallback, loopConfig.getTargetUpdatesPerSecond());
    }

    @Bean
    public CommandLineRunner commandLineRunner(LunaWindow lunaWindow) {
        return args -> {
            EventQueue.invokeLater(() -> {
                lunaWindow.setVisible(true);
            });
        };
    }
}
