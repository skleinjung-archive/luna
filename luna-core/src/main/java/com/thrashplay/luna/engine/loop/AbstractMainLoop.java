package com.thrashplay.luna.engine.loop;

/**
 * @author Sean Kleinjung
 */
public abstract class AbstractMainLoop implements MainLoop {

    private UpdateController updateController;
    private RenderController renderController;

    public AbstractMainLoop(UpdateController updateController, RenderController renderController) {
        this.updateController = updateController;
        this.renderController = renderController;
    }

    void update(float delta) {
        if (updateController != null) {
            updateController.update(delta);
        }
    }

    void render() {
        if (renderController != null) {
            renderController.render();
        }
    }

    public interface UpdateController {
        void update(float delta);
    }

    public interface RenderController {
        void render();
    }
}
