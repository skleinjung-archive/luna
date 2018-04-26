package com.thrashplay.luna.engine.loop;

import com.thrashplay.luna.engine.FpsCounter;

/**
 * @author Sean Kleinjung
 */
public abstract class AbstractMainLoop implements MainLoop {

    private UpdateController updateController;
    private RenderController renderController;

    private FpsCounter fpsCounter = new FpsCounter();

    public AbstractMainLoop(UpdateController updateController, RenderController renderController) {
        this.updateController = updateController;
        this.renderController = renderController;
    }

    void update(float delta) {
        if (updateController != null) {
            updateController.update(delta);
        }

        fpsCounter.onUpdate();
    }

    void render() {
        if (renderController != null) {
            renderController.render();
        }

        fpsCounter.onRender();
    }

    public interface UpdateController {
        void update(float delta);
    }

    public interface RenderController {
        void render();
    }
}
