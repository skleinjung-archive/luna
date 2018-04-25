package com.thrashplay.luna;

import com.thrashplay.luna.config.LunaLoopConfig;
import com.thrashplay.luna.swing.LunaWindow;
import com.thrashplay.luna.engine.loop.AbstractMainLoop;
import com.thrashplay.luna.engine.loop.FixedFpsSingleThreadMainLoop;
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
    public AbstractMainLoop getMainLoop(LunaLoopConfig loopConfig, AbstractMainLoop.UpdateController updateController, AbstractMainLoop.RenderController renderController) {
        return new FixedFpsSingleThreadMainLoop(updateController, renderController, loopConfig.getTargetUpdatesPerSecond());
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
